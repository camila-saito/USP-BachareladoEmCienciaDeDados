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

public class GuardiaoFirewall extends Chefe {
    private static final long serialVersionUID = 1L;

    private long tempoUltimaAcao;
    private static final long COOLDOWN_ACAO_BASE = 2500; // Milissegundos
    private static final long DURACAO_ESCUDO_NORMAL = 6000;
    private static final long DURACAO_ESCUDO_ENFURECIDO = 8000;
    private long tempoAtivacaoEscudo;
    private boolean escudoAtivo;
    private Random random = new Random();

    private enum EstadoAI { DEFENSIVO_ESCUDO, PREPARANDO_PULSO, ATACANDO_PULSO, MOVIMENTANDO_TATICO, OCIOSO }
    private EstadoAI estadoAtual = EstadoAI.OCIOSO;
    private long tempoEntradaEstado = 0;
    private Posicao alvoMovimento = null;
    private int pulsosDisparados = 0;
    private final int MAX_PULSOS_SEQUENCIA = 3;

    public GuardiaoFirewall(Posicao posicaoInicial, Heroi alvo) {
        super("Guardião Firewall",
                posicaoInicial,
                (int)(Constantes.TAMANHO_TILE * 2.1), // largura
                (int)(Constantes.TAMANHO_TILE * 2.6), // altura
                Constantes.CHEFE_GUARDIAN_FIREWALL_FASE4, // caminhoImagem
                380,  // vidaMaxima
                28.0, // velocidade
                16,   // ataque
                10,   // defesa
                alvo, // alvoHeroi
                35,   // vidaParaEnfurecerPercentual
                380,  // pontos
                new Color(30, 144, 255) // corPoderChefe (DodgerBlue)
        );
        this.tempoUltimaAcao = System.currentTimeMillis();
        this.escudoAtivo = false;
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
        long duracaoEscudoAtual = enfurecido ? DURACAO_ESCUDO_ENFURECIDO : DURACAO_ESCUDO_NORMAL;

        if (escudoAtivo && (tempoAtual - tempoAtivacaoEscudo > duracaoEscudoAtual)) {
            desativarEscudo();
        }

        switch (estadoAtual) {
            case OCIOSO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 1200: 2200)) { // Tempo em ocioso
                    int acao = random.nextInt(100);
                    if (!escudoAtivo && acao < (enfurecido ? 65 : 45)) {
                        ativarEscudo();
                        mudarEstado(EstadoAI.DEFENSIVO_ESCUDO);
                    } else if (acao < (enfurecido ? 90 : 75)) {
                        mudarEstado(EstadoAI.PREPARANDO_PULSO);
                        pulsosDisparados = 0;
                    } else {
                        mudarEstado(EstadoAI.MOVIMENTANDO_TATICO);
                    }
                }
                break;

            case DEFENSIVO_ESCUDO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                // Pode ter um pequeno movimento de "patrulha defensiva" aqui
                if (!escudoAtivo || tempoAtual - tempoEntradaEstado > duracaoEscudoAtual + 500) { // Um pouco mais de tempo no estado após escudo acabar
                    mudarEstado(EstadoAI.OCIOSO);
                }
                break;

            case MOVIMENTANDO_TATICO:
                if (alvoMovimento == null || this.posicao.distanciaAte(alvoMovimento) < Constantes.TAMANHO_TILE * 1.5) {
                    alvoMovimento = encontrarPosicaoTatica(mapa);
                    if (alvoMovimento == null) { // Se não encontrar, fica ocioso
                        mudarEstado(EstadoAI.OCIOSO);
                        break;
                    }
                }
                moverParaPosicao(mapa, alvoMovimento, deltaTime);
                if (tempoAtual - tempoEntradaEstado > 3500) { // Tempo máximo movimentando
                    mudarEstado(EstadoAI.OCIOSO);
                }
                break;

            case PREPARANDO_PULSO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                // Animação de preparação do pulso
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 700 : 1200)) { // Tempo de preparação
                    mudarEstado(EstadoAI.ATACANDO_PULSO);
                }
                break;

            case ATACANDO_PULSO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > 600) { // Intervalo entre pulsos
                    this.atacandoAgora = true; // Sinaliza intenção de ataque (pulso)
                    // A criação dos projéteis ocorre em atacarAlvoMultiplo
                    pulsosDisparados++;
                    tempoEntradaEstado = tempoAtual; // Reinicia tempo para próximo pulso ou fim
                    if (pulsosDisparados >= (enfurecido ? MAX_PULSOS_SEQUENCIA + 2 : MAX_PULSOS_SEQUENCIA)) {
                        mudarEstado(EstadoAI.OCIOSO); // Volta a ocioso após sequência de pulsos
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
            this.velocidadeX = 0; // Para ao colidir
        }

        this.posicao.setY(novoY);
        if (mapa != null && (mapa.colideCom(this) || !mapa.getBounds().contains(this.getBounds()))) {
            this.posicao.setY(posAntiga.getY());
            this.velocidadeY = 0; // Para ao colidir
        }
    }

    private void mudarEstado(EstadoAI novoEstado) {
        this.estadoAtual = novoEstado;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }

    private void moverParaPosicao(Mapa mapa, Posicao destino, long deltaTime) {
        if (destino == null) {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
            return;
        }
        double targetX = destino.getX();
        double targetY = destino.getY();
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        if (Math.sqrt(deltaX*deltaX + deltaY*deltaY) < Constantes.TAMANHO_TILE * 0.5) {
            this.velocidadeX = 0; this.velocidadeY = 0; return;
        }
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.getVelocidadeBase() * (enfurecido ? 1.2 : 1.0);

        this.velocidadeX = vel * Math.cos(angulo);
        this.velocidadeY = vel * Math.sin(angulo);
    }

    private Posicao encontrarPosicaoTatica(Mapa mapa) {
        Rectangle arena = null;
        if (listenerMotor != null) {
            arena = listenerMotor.getArenaChefe();
        }
        if (mapa == null || arena == null) { return null; }

        int tentativas = 0;
        while (tentativas < 15) {
            double randX = arena.getX() + random.nextDouble() * arena.getWidth();
            double randY = arena.getY() + random.nextDouble() * arena.getHeight();
            Posicao posPotencial = new Posicao(
                    Math.max(arena.getX() + this.largura / 2.0, Math.min(randX, arena.getX() + arena.getWidth() - this.largura / 2.0)),
                    Math.max(arena.getY() + this.altura / 2.0, Math.min(randY, arena.getY() + arena.getHeight() - this.altura / 2.0))
            );
            Rectangle boundsPotencial = new Rectangle((int)(posPotencial.getX() - this.largura/2.0), (int)(posPotencial.getY() - this.altura/2.0), this.largura, this.altura);

            if (posPotencial.distanciaAte(alvo.getPosicao()) > Constantes.TAMANHO_TILE * 3 && mapa.ehAreaTransponivel(boundsPotencial)) {
                return posPotencial;
            }
            tentativas++;
        }
        return null; // Não encontrou posição ideal
    }

    private void ativarEscudo() {
        if (!escudoAtivo) {
            System.out.println(this.getNomeEntidade() + " ativa ESCUDO DE DADOS!");
            this.escudoAtivo = true;
            this.setDefesa(this.getDefesaBase() + (enfurecido ? 25 : 15));
            this.tempoAtivacaoEscudo = System.currentTimeMillis();
            // Mudar sprite ou adicionar efeito visual para o escudo
        }
    }

    private void desativarEscudo() {
        if (this.escudoAtivo) {
            System.out.println(this.getNomeEntidade() + " desativa ESCUDO DE DADOS!");
            this.escudoAtivo = false;
            this.setDefesa(this.getDefesaBase());
            // Remover efeito visual do escudo
        }
    }

    @Override
    public List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvoDoHeroi) {
        List<Projetil> projeteisCriados = new ArrayList<>();

        if (estadoAtual == EstadoAI.ATACANDO_PULSO && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " libera PULSO DE FIREWALL! (" + pulsosDisparados + "/" + (enfurecido ? MAX_PULSOS_SEQUENCIA + 2 : MAX_PULSOS_SEQUENCIA) + ")");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            int numProjeteisPulso = enfurecido ? 10 : 7;
            double velocidadeProjetilPulso = enfurecido ? 160.0 : 130.0;
            int danoPulso = this.getAtaque() - 2; // Pulso é mais para controle de área

            for (int i = 0; i < numProjeteisPulso; i++) {
                double angulo = (2 * Math.PI / numProjeteisPulso) * i;
                // adiciona uma pequena variação para não ser perfeitamente simétrico
                angulo += (random.nextDouble() - 0.5) * Math.toRadians(8);

                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                Projetil p = new Projetil("PulsoFirewall_" + pulsosDisparados + "_" + i, posProjetil, angulo, false, danoPulso, Constantes.CAMINHO_CHEFES + "projetil_firewall_pulso.png");
                p.setVelocidadeProjetil(velocidadeProjetilPulso);
                p.setDuracaoMaxima(enfurecido ? 2200 : 1800); //ptojéteis de pulso duram um pouco mais
                projeteisCriados.add(p);
            }
            registrarTempoAtaque();
            this.atacandoAgora = false; //reseta flag após gerar projéteis para este pulso
        }
        return projeteisCriados;
    }

    @Override
    public void receberDano(int quantidade) {
        int danoReal = quantidade;
        if (escudoAtivo) {
            danoReal = Math.max(1, quantidade - (this.getDefesa() - this.getDefesaBase())); // Dano mínimo de 1 se escudo absorver tudo
            System.out.println("Escudo do " + this.getNomeEntidade() + " absorveu parte do dano! Dano recebido: " + danoReal);
        }
        super.receberDano(danoReal);

        if (this.estaVivo() && escudoAtivo && this.getVidaAtual() < this.getVidaMaxima() * 0.15 && random.nextInt(100) < 40) {
            System.out.println("O Escudo do " + this.getNomeEntidade() + " estilhaça sob pressão intensa!");
            desativarEscudo();
        }

        if (!this.estaVivo()) {
            desativarEscudo(); //gaarante que o escudo é desativado se o chefe morrer
        }
    }

    @Override
    protected void entrarModoEnfurecido() {
        super.entrarModoEnfurecido();
        this.setDefesa(this.getDefesaBase() + 8); //aumenta mais a defesa base no modo enfurecido
        System.out.println(this.getNomeEntidade() + " entra em MODO DE DEFESA TOTAL! Escudos mais resistentes e pulsos mais densos!");
        if (!escudoAtivo) { //se não estiver com escudo, ativa um ao enfurecer
            ativarEscudo();
            mudarEstado(EstadoAI.DEFENSIVO_ESCUDO);
        }
    }
}

