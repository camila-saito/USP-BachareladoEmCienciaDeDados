package org.example.entidades.chefes;

import org.example.entidades.Chefe;
import org.example.entidades.Heroi;
import org.example.entidades.Projetil;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;
import org.example.motor.MotorJogoListener; // Importa se for usar o listener

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Rectangle;

public class AlgoritmoAnomalo extends Chefe {
    private static final long serialVersionUID = 1L;

    private long tempoUltimoAtaquePadrao;
    private static final long COOLDOWN_ATAQUE_PADRAO = 3000; // Milissegundos
    private long tempoUltimoTeleporte;
    private static final long COOLDOWN_TELEPORTE_NORMAL = 7000;
    private static final long COOLDOWN_TELEPORTE_ENFURECIDO = 4000;
    private long tempoUltimoAtaqueRajada;
    private static final long COOLDOWN_ATAQUE_RAJADA = 10000;

    private Random random = new Random();
    private enum EstadoAI { OBSERVANDO, TELEPORTANDO, ATACANDO_RAJADA, PERSEGUINDO_POS_TELEPORTE, ATACANDO_PADRAO }
    private EstadoAI estadoAtual = EstadoAI.OBSERVANDO;
    private long tempoEntradaEstado = 0;
    private Posicao proximaPosTeleporte = null;
    private int contagemAtaquesRajada = 0;
    private final int MAX_ATAQUES_RAJADA_SEQUENCIA = 3;

    public AlgoritmoAnomalo(Posicao posicaoInicial, Heroi alvo) {
        super("Algoritmo Anômalo",
                posicaoInicial,
                (int)(Constantes.TAMANHO_TILE * 1.9),
                (int)(Constantes.TAMANHO_TILE * 1.9),
                Constantes.CHEFE_ALGORITMO_ANOMALO_FASE3,
                320,  // vidaMaxima
                45.0, // velocidade
                18,   // ataque
                7,    // defesa
                alvo, // alvoHeroi
                40,   // vidaParaEnfurecerPercentual
                320,  // pontos
                new Color(255, 140, 0) // corPoderChefe (Laranja Escuro)pode alterar se quiser
        );
        this.tempoUltimoAtaquePadrao = System.currentTimeMillis();
        this.tempoUltimoTeleporte = System.currentTimeMillis();
        this.tempoUltimoAtaqueRajada = System.currentTimeMillis() - COOLDOWN_ATAQUE_RAJADA / 2;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }
    //cooldown = tempo de espera que o npc espera para usar umahabilidade
    @Override
    public void inteligenciaArtificialChefe(Mapa mapa, long deltaTime) {
        if (alvo == null || !alvo.estaVivo() || !this.estaVivo()) {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
            return;
        }

        long tempoAtual = System.currentTimeMillis();
        long cooldownTeleporteAtual = enfurecido ? COOLDOWN_TELEPORTE_ENFURECIDO : COOLDOWN_TELEPORTE_NORMAL;
        double distanciaAlvo = this.posicao.distanciaAte(alvo.getPosicao());

        switch (estadoAtual) {
            case OBSERVANDO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                //tenta teleportar primeiro se o cooldown permitir
                if (tempoAtual - tempoUltimoTeleporte > cooldownTeleporteAtual) {
                    mudarEstado(EstadoAI.TELEPORTANDO);
                } else if (tempoAtual - tempoUltimoAtaqueRajada > COOLDOWN_ATAQUE_RAJADA && distanciaAlvo < Constantes.TAMANHO_TILE * 10) {
                    mudarEstado(EstadoAI.ATACANDO_RAJADA);
                    contagemAtaquesRajada = 0;
                } else if (tempoAtual - tempoEntradaEstado > 2500) {//se ficar muito tempo observando, persegue
                    mudarEstado(EstadoAI.PERSEGUINDO_POS_TELEPORTE);
                }
                break;

            case TELEPORTANDO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (proximaPosTeleporte == null) {
                    proximaPosTeleporte = encontrarPosicaoTeleporteSegura(mapa);
                }
                //animação de teleporte (se houver)
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 600 : 1000)) { //tempo para "carregar" o teleporte em ms
                    if (proximaPosTeleporte != null) {
                        this.posicao.setX(proximaPosTeleporte.getX());
                        this.posicao.setY(proximaPosTeleporte.getY());
                        System.out.println(this.getNomeEntidade() + " teleportou para " + String.format("%.1f, %.1f", proximaPosTeleporte.getX(), proximaPosTeleporte.getY()));
                    } else {
                        System.err.println(this.getNomeEntidade() + " falhou ao encontrar local para teleporte, tentando novamente.");
                    }
                    tempoUltimoTeleporte = tempoAtual;
                    proximaPosTeleporte = null;
                    mudarEstado(EstadoAI.PERSEGUINDO_POS_TELEPORTE); //após teleportar, persegue ou ataca
                }
                break;

            case PERSEGUINDO_POS_TELEPORTE:
                perseguirAlvo(mapa, deltaTime, Constantes.TAMANHO_TILE * 2.5);
                if (podeAtacarAlvo(Constantes.TAMANHO_TILE * 3.0) && tempoAtual - tempoUltimoAtaquePadrao > COOLDOWN_ATAQUE_PADRAO) {
                    mudarEstado(EstadoAI.ATACANDO_PADRAO);
                }
                // Se ficar muito tempo perseguindo sem atacar, volta a observar para talvez teleportar
                if (tempoAtual - tempoEntradaEstado > 4000) {
                    mudarEstado(EstadoAI.OBSERVANDO);
                }
                break;

            case ATACANDO_PADRAO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                this.atacandoAgora = true; //sinaliza intenção de ataque padrão
                //a criação do projétil ocorre em atacarAlvoMultiplo
                if (tempoAtual - tempoEntradaEstado > 500) { //dração do estado de ataque padrão
                    tempoUltimoAtaquePadrao = tempoAtual;
                    mudarEstado(EstadoAI.OBSERVANDO); //volta a observar apos o ataque
                }
                break;

            case ATACANDO_RAJADA:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                // Animação de preparação da rajada
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 300 : 500)) { // Tempo de preparação por rajada
                    this.atacandoAgora = true; // Sinaliza intenção de ataque especial (rajada)
                    // A criação dos projéteis ocorre em atacarAlvoMultiplo
                    contagemAtaquesRajada++;
                    tempoEntradaEstado = tempoAtual; // Reinicia tempo para próxima parte da rajada
                    if (contagemAtaquesRajada >= (enfurecido ? MAX_ATAQUES_RAJADA_SEQUENCIA + 1 : MAX_ATAQUES_RAJADA_SEQUENCIA)) {
                        tempoUltimoAtaqueRajada = tempoAtual;
                        mudarEstado(EstadoAI.OBSERVANDO); // Volta a observar após sequência de rajadas
                    }
                }
                break;
        }
        aplicarMovimento(mapa, deltaTime);
    }

    private void aplicarMovimento(Mapa mapa, long deltaTime) {
        double deltaSeg = deltaTime / 1_000_000_000.0;
        Posicao posAntiga = new Posicao(this.posicao.getX(), this.posicao.getY());
        double novoX = this.posicao.getX() + this.velocidadeX * deltaSeg;
        double novoY = this.posicao.getY() + this.velocidadeY * deltaSeg;

        this.posicao.setX(novoX);
        if (mapa != null && (mapa.colideCom(this) || !mapa.getBounds().contains(this.getBounds()))) {
            this.posicao.setX(posAntiga.getX());
            this.velocidadeX = -this.velocidadeX * 0.2;
        }

        this.posicao.setY(novoY);
        if (mapa != null && (mapa.colideCom(this) || !mapa.getBounds().contains(this.getBounds()))) {
            this.posicao.setY(posAntiga.getY());
            this.velocidadeY = -this.velocidadeY * 0.2;
        }
    }

    private void mudarEstado(EstadoAI novoEstado) {
        this.estadoAtual = novoEstado;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }

    private void perseguirAlvo(Mapa mapa, long deltaTime, double distanciaIdeal) {
        if (alvo == null || !alvo.estaVivo()) { this.velocidadeX = 0; this.velocidadeY = 0; return; }
        double targetX = alvo.getPosicao().getX() + alvo.getLargura() / 2.0;
        double targetY = alvo.getPosicao().getY() + alvo.getAltura() / 2.0;
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.enfurecido ? this.getVelocidadeBase() * 1.2 : this.getVelocidadeBase();

        if (distancia > distanciaIdeal) {
            this.velocidadeX = vel * Math.cos(angulo);
            this.velocidadeY = vel * Math.sin(angulo);
        } else {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
        }
    }

    private Posicao encontrarPosicaoTeleporteSegura(Mapa mapa) {
        Rectangle arena = null;
        if (listenerMotor != null) {
            arena = listenerMotor.getArenaChefe();
        }
        if (mapa == null || arena == null) { // Fallback se mapa ou arena não disponíveis
            return new Posicao(this.posicao.getX() + (random.nextDouble() - 0.5) * Constantes.TAMANHO_TILE * 8,
                    this.posicao.getY() + (random.nextDouble() - 0.5) * Constantes.TAMANHO_TILE * 8);
        }

        int tentativas = 0;
        while (tentativas < 20) {
            double randX = arena.getX() + random.nextDouble() * arena.getWidth();
            double randY = arena.getY() + random.nextDouble() * arena.getHeight();

            Posicao posPotencial = new Posicao(
                    Math.max(arena.getX() + this.largura / 2.0, Math.min(randX, arena.getX() + arena.getWidth() - this.largura / 2.0)),
                    Math.max(arena.getY() + this.altura / 2.0, Math.min(randY, arena.getY() + arena.getHeight() - this.altura / 2.0))
            );

            Rectangle boundsPotencial = new Rectangle((int)(posPotencial.getX() - this.largura/2.0), (int)(posPotencial.getY() - this.altura/2.0), this.largura, this.altura);

            // Verifica se a posição está longe do alvo e se é transponível (idealmente)
            if (posPotencial.distanciaAte(alvo.getPosicao()) > Constantes.TAMANHO_TILE * 4 && mapa.ehAreaTransponivel(boundsPotencial) ) {
                return posPotencial;
            }
            tentativas++;
        }
        // Se não encontrar após tentativas, retorna uma posição aleatória mais simples dentro da arena
        return new Posicao(arena.getX() + random.nextDouble() * (arena.getWidth() - this.largura) + this.largura/2.0,
                arena.getY() + random.nextDouble() * (arena.getHeight() - this.altura) + this.altura/2.0);
    }

    @Override
    public List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvoDoHeroi) {
        List<Projetil> projeteisCriados = new ArrayList<>();
        long tempoAtual = System.currentTimeMillis();

        if (estadoAtual == EstadoAI.ATACANDO_RAJADA && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE ESPECIAL: Rajada de Lógica Fragmentada! (" + (contagemAtaquesRajada) + "/" + (enfurecido ? MAX_ATAQUES_RAJADA_SEQUENCIA + 1 : MAX_ATAQUES_RAJADA_SEQUENCIA) + ")");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double anguloParaAlvo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(),
                    posicaoAlvoDoHeroi.getX() - centroChefe.getX());

            int numProjeteisPorRajada = enfurecido ? 4 : 3;
            double spreadAngulo = Math.toRadians(15);

            for (int i = 0; i < numProjeteisPorRajada; i++) {
                double anguloProjetil = anguloParaAlvo + (i - (numProjeteisPorRajada - 1.0) / 2.0) * spreadAngulo;
                anguloProjetil += (random.nextDouble() - 0.5) * Math.toRadians(5); // Pequena aleatoriedade

                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                Projetil p = new Projetil("RajadaLogica_" + contagemAtaquesRajada + "_" + i, posProjetil, anguloProjetil, false, this.getAtaque() + 2, Constantes.CAMINHO_CHEFES + "projetil_algoritmo_rajada.png");
                p.setVelocidadeProjetil(enfurecido ? 200.0 : 170.0);
                p.setDuracaoMaxima(2500);
                projeteisCriados.add(p);
            }
            registrarTempoAtaque(); // Registra tempo do ataque geral do chefe
            this.atacandoAgora = false; // Reseta flag após gerar projéteis para esta parte da rajada

        } else if (estadoAtual == EstadoAI.ATACANDO_PADRAO && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE PADRÃO: Pulso Anômalo!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;
            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double angulo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(), posicaoAlvoDoHeroi.getX() - centroChefe.getX());
            Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
            Projetil p = new Projetil("PulsoAnomalo", posProjetil, angulo, false, this.getAtaque(), Constantes.CAMINHO_CHEFES + "projetil_algoritmo_padrao.png");
            p.setVelocidadeProjetil(enfurecido ? 240.0 : 200.0);
            projeteisCriados.add(p);
            registrarTempoAtaque();
            this.atacandoAgora = false; // Reseta flag após gerar projétil
        }
        return projeteisCriados;
    }

    @Override
    protected void entrarModoEnfurecido() {
        super.entrarModoEnfurecido();
        this.setVelocidade(this.getVelocidadeBase() * 1.15);
        this.setAtaque(this.getAtaqueBase() + 5);
        System.out.println(this.getNomeEntidade() + " está instável e teleportando mais rápido!");
    }
}

