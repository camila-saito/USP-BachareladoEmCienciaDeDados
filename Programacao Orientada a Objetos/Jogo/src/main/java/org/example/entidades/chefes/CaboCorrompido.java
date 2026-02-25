package org.example.entidades.chefes;

import org.example.entidades.Chefe;
import org.example.entidades.Heroi;
import org.example.entidades.Projetil;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;
import org.example.motor.MotorJogoListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Rectangle;

public class CaboCorrompido extends Chefe {
    private static final long serialVersionUID = 1L;

    private long tempoUltimoAtaquePadrao;
    private static final long COOLDOWN_ATAQUE_PADRAO = 2500; // Milissegundos
    private long tempoUltimoAtaqueEspecialChicote;
    private static final long COOLDOWN_ATAQUE_ESPECIAL_CHICOTE = 7000; // Milissegundos
    private Random random = new Random();

    private enum EstadoAI { PATRULHANDO_ARENA, INVESTINDO_ALVO, PREPARANDO_CHICOTE, ATACANDO_CHICOTE, RECUANDO, ESPERANDO_PADRAO }
    private EstadoAI estadoAtual = EstadoAI.PATRULHANDO_ARENA;
    private long tempoEntradaEstado = 0;
    private Posicao alvoPatrulha = null;
    private int chicotadasNaSequencia = 0;
    private final int MAX_CHICOTADAS_SEQUENCIA = 3;

    public CaboCorrompido(Posicao posicaoInicial, Heroi alvo) {
        super("Cabo Corrompido",
                posicaoInicial,
                (int)(Constantes.TAMANHO_TILE * 2.2), // largura
                (int)(Constantes.TAMANHO_TILE * 0.7), // altura
                Constantes.CHEFE_CABO_CORROMPIDO_FASE2, // caminhoImagem
                280,  // vidaMaxima
                40.0, // velocidade
                15,   // ataque
                5,    // defesa
                alvo, // alvoHeroi
                50,   // vidaParaEnfurecerPercentual
                280,  // pontos
                new Color(0, 200, 200) // corPoderChefe (Cyan mais escuro)
        );
        this.tempoUltimoAtaquePadrao = System.currentTimeMillis();
        this.tempoUltimoAtaqueEspecialChicote = System.currentTimeMillis() - COOLDOWN_ATAQUE_ESPECIAL_CHICOTE / 2;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }

    @Override
    public void inteligenciaArtificialChefe(Mapa mapa, long deltaTime) {
        if (alvo == null || !alvo.estaVivo() || !this.estaVivo()) {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
            return;
        }

        long tempoAtual = System.currentTimeMillis();
        double distanciaAlvo = this.posicao.distanciaAte(alvo.getPosicao());

        switch (estadoAtual) {
            case PATRULHANDO_ARENA:
                patrulhar(mapa, deltaTime);
                if (distanciaAlvo < Constantes.TAMANHO_TILE * 7) {
                    mudarEstado(EstadoAI.INVESTINDO_ALVO);
                }
                break;

            case INVESTINDO_ALVO:
                perseguirAlvo(mapa, deltaTime, Constantes.TAMANHO_TILE * 2.5);
                if (tempoAtual - tempoUltimoAtaqueEspecialChicote > COOLDOWN_ATAQUE_ESPECIAL_CHICOTE && distanciaAlvo < Constantes.TAMANHO_TILE * 6) {
                    mudarEstado(EstadoAI.PREPARANDO_CHICOTE);
                    chicotadasNaSequencia = 0;
                } else if (podeAtacarAlvo(this.largura * 0.7) && tempoAtual - tempoUltimoAtaquePadrao > COOLDOWN_ATAQUE_PADRAO) {
                    this.atacandoAgora = true; // Sinaliza intenção de ataque padrão
                    mudarEstado(EstadoAI.ESPERANDO_PADRAO);
                }
                if (distanciaAlvo > Constantes.TAMANHO_TILE * 10) { // Se o alvo fugir muito
                    mudarEstado(EstadoAI.PATRULHANDO_ARENA);
                }
                break;

            case PREPARANDO_CHICOTE:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                // snimação de preparação aqui, se houver
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 500 : 800)) { // Tempo de preparação
                    mudarEstado(EstadoAI.ATACANDO_CHICOTE);
                }
                break;

            case ATACANDO_CHICOTE:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > 350) { //intervalo entre chicotadas
                    this.atacandoAgora = true; //sinaliza intenção de ataque especial (chicote)
                    //logica de criar projéteis será em atacarAlvoMultiplo
                    chicotadasNaSequencia++;
                    tempoEntradaEstado = tempoAtual; // Reinicia tempo para próxima chicotada ou fim
                    if (chicotadasNaSequencia >= (enfurecido ? MAX_CHICOTADAS_SEQUENCIA + 2 : MAX_CHICOTADAS_SEQUENCIA)) {
                        tempoUltimoAtaqueEspecialChicote = tempoAtual;
                        mudarEstado(EstadoAI.RECUANDO); //recua após a sequência de chicotes
                    } else {
                        //permanece em ATACANDO_CHICOTE para a próxima chicotada
                    }
                }
                break;

            case ESPERANDO_PADRAO: //estado de espera após ataque padrão
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > 600) { //cooldown visual pós-ataque
                    mudarEstado(EstadoAI.RECUANDO);
                }
                break;

            case RECUANDO:
                recuarDoAlvo(mapa, deltaTime, Constantes.TAMANHO_TILE * 3);
                if (tempoAtual - tempoEntradaEstado > 1200 || distanciaAlvo > Constantes.TAMANHO_TILE * 5) {
                    mudarEstado(EstadoAI.INVESTINDO_ALVO);
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
            this.velocidadeX = -this.velocidadeX * 0.3;
        }

        this.posicao.setY(novoY);
        if (mapa != null && (mapa.colideCom(this) || !mapa.getBounds().contains(this.getBounds()))) {
            this.posicao.setY(posAntiga.getY());
            this.velocidadeY = -this.velocidadeY * 0.3;
        }
    }

    private void mudarEstado(EstadoAI novoEstado) {
        this.estadoAtual = novoEstado;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }

    private void perseguirAlvo(Mapa mapa, long deltaTime, double distanciaMinima) {
        if (alvo == null || !alvo.estaVivo()) { this.velocidadeX = 0; this.velocidadeY = 0; return; }
        double targetX = alvo.getPosicao().getX() + alvo.getLargura() / 2.0;
        double targetY = alvo.getPosicao().getY() + alvo.getAltura() / 2.0;
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.enfurecido ? this.getVelocidadeBase() * 1.3 : this.getVelocidadeBase();

        if (distancia > distanciaMinima) {
            this.velocidadeX = vel * Math.cos(angulo);
            this.velocidadeY = vel * Math.sin(angulo);
        } else if (distancia < distanciaMinima - Constantes.TAMANHO_TILE * 0.8) { // Muito perto, afasta um pouco
            this.velocidadeX = -vel * Math.cos(angulo) * 0.6;
            this.velocidadeY = -vel * Math.sin(angulo) * 0.6;
        } else {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
        }
    }

    private void recuarDoAlvo(Mapa mapa, long deltaTime, double distanciaRecuoMin) {
        if (alvo == null || !alvo.estaVivo()) { this.velocidadeX = 0; this.velocidadeY = 0; return; }
        double targetX = alvo.getPosicao().getX() + alvo.getLargura() / 2.0;
        double targetY = alvo.getPosicao().getY() + alvo.getAltura() / 2.0;
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = currentX - targetX; // Invertido para recuar
        double deltaY = currentY - targetY;
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.getVelocidadeBase() * 0.9;

        this.velocidadeX = vel * Math.cos(angulo);
        this.velocidadeY = vel * Math.sin(angulo);
    }

    private void patrulhar(Mapa mapa, long deltaTime) {
        if (alvoPatrulha == null || this.posicao.distanciaAte(alvoPatrulha) < Constantes.TAMANHO_TILE * 1.5) {
            Rectangle arena = null;
            if (listenerMotor != null) {
                arena = listenerMotor.getArenaChefe(); // Supondo que MotorJogoListener tem getArenaChefe()
            }
            if (mapa != null && arena != null) {
                alvoPatrulha = new Posicao(
                        arena.getX() + random.nextDouble() * arena.getWidth(),
                        arena.getY() + random.nextDouble() * arena.getHeight()
                );
            } else { // Fallback se arena não disponível
                alvoPatrulha = new Posicao(this.getPosicaoInicialOriginal().getX() + (random.nextDouble() - 0.5) * Constantes.TAMANHO_TILE * 6,
                        this.getPosicaoInicialOriginal().getY() + (random.nextDouble() - 0.5) * Constantes.TAMANHO_TILE * 6);
            }
            //garantir que o alvo da patrulha está dentro dos limites do mapa
            if (mapa != null) {
                alvoPatrulha.setX(Math.max(this.largura/2.0, Math.min(alvoPatrulha.getX(), mapa.getLarguraEmPixels() - this.largura/2.0)));
                alvoPatrulha.setY(Math.max(this.altura/2.0, Math.min(alvoPatrulha.getY(), mapa.getAlturaEmPixels() - this.altura/2.0)));
            }
        }

        double targetX = alvoPatrulha.getX();
        double targetY = alvoPatrulha.getY();
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.getVelocidadeBase() * 0.4;

        this.velocidadeX = vel * Math.cos(angulo);
        this.velocidadeY = vel * Math.sin(angulo);
    }

    @Override
    public List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvoDoHeroi) {
        List<Projetil> projeteisCriados = new ArrayList<>();
        long tempoAtual = System.currentTimeMillis();

        if (estadoAtual == EstadoAI.ATACANDO_CHICOTE && tempoAtual - tempoUltimoAtaqueEspecialChicote > COOLDOWN_ATAQUE_ESPECIAL_CHICOTE / MAX_CHICOTADAS_SEQUENCIA) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE ESPECIAL: Chicote Elétrico!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double anguloParaAlvo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(),
                    posicaoAlvoDoHeroi.getX() - centroChefe.getX());

            int numSegmentosChicote = enfurecido ? 2 : 1; // Chicote mais focado
            double velocidadeProjetilChicote = 300.0;
            int danoChicote = this.getAtaqueBase() + 5;

            for (int i = 0; i < numSegmentosChicote; i++) {
                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                //pequena variação no ângulo para simular o chicote
                double anguloAtual = anguloParaAlvo + (random.nextDouble() - 0.5) * Math.toRadians(10);

                Projetil p = new Projetil("ChicoteEletrico_" + chicotadasNaSequencia + "_" + i, posProjetil, anguloAtual, false, danoChicote, Constantes.CAMINHO_CHEFES + "projetil_cabo_chicote.png");
                p.setVelocidadeProjetil(velocidadeProjetilChicote);
                p.setDuracaoMaxima(enfurecido ? 700 : 500); // Duração curta para o chicote
                projeteisCriados.add(p);
            }
            //n resetar tempoUltimoAtaqueEspecialChicote aqui, só no final da sequência
            registrarTempoAtaque(); // Registra tempo do ataque geral do chefe

        } else if (estadoAtual == EstadoAI.ESPERANDO_PADRAO && tempoAtual - tempoUltimoAtaquePadrao > COOLDOWN_ATAQUE_PADRAO) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE PADRÃO: Descarga Curta!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;
            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double angulo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(), posicaoAlvoDoHeroi.getX() - centroChefe.getX());
            Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
            Projetil p = new Projetil("DescargaCabo", posProjetil, angulo, false, this.getAtaque(), Constantes.CAMINHO_CHEFES + "projetil_cabo_padrao.png");
            p.setVelocidadeProjetil(enfurecido ? 280.0 : 220.0);
            projeteisCriados.add(p);
            this.tempoUltimoAtaquePadrao = tempoAtual;
            registrarTempoAtaque();
        }

        //adicionar projéteis ao listenerMotor se o método existir
        if (listenerMotor != null && !projeteisCriados.isEmpty()) {
        }
        return projeteisCriados;
    }

    @Override
    protected void entrarModoEnfurecido() {
        super.entrarModoEnfurecido();
        this.setVelocidade(this.getVelocidadeBase() * 1.35);
        this.setAtaque(this.getAtaqueBase() + 7);
        System.out.println(this.getNomeEntidade() + " está FURIOSO e chicoteando mais rápido!");
    }
}

