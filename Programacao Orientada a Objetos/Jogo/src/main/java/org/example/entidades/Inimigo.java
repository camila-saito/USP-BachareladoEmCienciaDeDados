package org.example.entidades;

import org.example.auxiliar.Posicao;
import org.example.graficos.Mapa;
import org.example.auxiliar.GerenciadorDeAudio;
import org.example.auxiliar.Constantes;
import java.util.Random;

public abstract class Inimigo extends Personagem {
    private static final long serialVersionUID = 1L;

    protected Heroi alvo;
    protected double raioDeVisao;
    protected double raioDeAtaque;
    protected long tempoUltimoMovimento;
    protected static final long INTERVALO_MOVIMENTO = 200;
    protected static final long DURACAO_PATRULHA_DIRECAO = 3000;
    protected long tempoInicioPatrulhaDirecao;
    protected int direcaoPatrulhaX = 0;
    protected int direcaoPatrulhaY = 0;
    protected Random randomPatrulha = new Random();

    private boolean atacandoAgora = false;
    private long tempoUltimoAtaqueRealizado = 0;
    protected long cooldownAtaqueMs = 2000;
    protected int pontosAoDerrotar = 10;

    public Inimigo(String nome, Posicao posicaoInicial, int largura, int altura, String caminhoImagem,
                   int vidaMaxima, double velocidade, int ataque, int defesa, Heroi alvo,
                   double raioDeVisao, double raioDeAtaque, int pontos) {
        super(nome, posicaoInicial, largura, altura, caminhoImagem, vidaMaxima, velocidade, ataque, defesa);
        this.alvo = alvo;
        this.raioDeVisao = raioDeVisao;
        this.raioDeAtaque = raioDeAtaque;
        this.tempoUltimoMovimento = System.currentTimeMillis();
        this.tempoInicioPatrulhaDirecao = System.currentTimeMillis();
        this.pontosAoDerrotar = pontos;
        escolherNovaDirecaoPatrulha();
    }

    public abstract void inteligenciaArtificial(Mapa mapa);

    @Override
    public void atualizar(long deltaTime, Mapa mapa) {
        if (!estaVivo()) {
            return;
        }
        this.atacandoAgora = false;

        long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoMovimento > INTERVALO_MOVIMENTO) {
            inteligenciaArtificial(mapa);
            tempoUltimoMovimento = tempoAtual;
        }

        if (alvo != null && alvo.estaVivo() && podeAtacar()) {
            if (this.posicao.distanciaAte(alvo.getPosicao()) <= raioDeAtaque) {

                this.atacandoAgora = true; // Indica intenção de ataque
            }
        }
    }

    protected boolean podeVerAlvo() {
        if (alvo == null || !alvo.estaVivo()) return false;
        double distancia = this.posicao.distanciaAte(alvo.getPosicao());
        return distancia <= raioDeVisao;
    }


    public boolean isAtacando() {
        return this.atacandoAgora; // Retorna a flag de intenção de ataque do ciclo atual
    }


    public boolean podeAtacar() {
        return System.currentTimeMillis() - tempoUltimoAtaqueRealizado > cooldownAtaqueMs && alvo != null && alvo.estaVivo() && this.posicao.distanciaAte(alvo.getPosicao()) <= raioDeAtaque;
    }


    public Projetil atacarAlvo(Posicao posicaoAlvo) {
        if (podeAtacar()) {
            this.atacandoAgora = true; //confirma que está atacando neste momento
            this.tempoUltimoAtaqueRealizado = System.currentTimeMillis();
            System.out.println(this.getNomeEntidade() + " ataca em direção a " + posicaoAlvo.getX() + "," + posicaoAlvo.getY());
            GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/inimigo_ataque.wav");

            double angulo = Math.atan2(posicaoAlvo.getY() - (this.posicao.getY() + this.altura / 2.0),
                    posicaoAlvo.getX() - (this.posicao.getX() + this.largura / 2.0));

            Posicao posProjetil = new Posicao(
                    this.posicao.getX() + this.largura / 2.0 - Projetil.LARGURA_PADRAO / 2.0,
                    this.posicao.getY() + this.altura / 2.0 - Projetil.ALTURA_PADRAO / 2.0
            );
            //asssumindo um sprite padrão para projéteis de inimigos
            return new Projetil("projetil_inimigo", posProjetil, angulo, false, this.ataque, "/imagens/projetil_inimigo.png");
        }
        return null; //n pode atacar ou não está atacando
    }

    protected void moverEmDirecaoAlvo(Mapa mapa) {
        if (alvo == null || !alvo.estaVivo() || !podeVerAlvo()) return;

        double deltaX = alvo.getPosicao().getX() - this.posicao.getX();
        double deltaY = alvo.getPosicao().getY() - this.posicao.getY();

        double novoX = this.posicao.getX();
        double novoY = this.posicao.getY();
        double passo = this.velocidade / 5.0;

        if (Math.abs(deltaX) > Constantes.TOLERANCIA_MOVIMENTO || Math.abs(deltaY) > Constantes.TOLERANCIA_MOVIMENTO) {
            double angulo = Math.atan2(deltaY, deltaX);
            novoX += passo * Math.cos(angulo);
            novoY += passo * Math.sin(angulo);
        }

        Posicao posAntiga = new Posicao(this.posicao.getX(), this.posicao.getY());
        this.posicao.setX(novoX);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setX(posAntiga.getX());
        }

        this.posicao.setY(novoY);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setY(posAntiga.getY());
        }
    }

    protected void escolherNovaDirecaoPatrulha() {
        int escolha = randomPatrulha.nextInt(5);
        direcaoPatrulhaX = 0;
        direcaoPatrulhaY = 0;
        switch (escolha) {
            case 0: direcaoPatrulhaY = -1; break;
            case 1: direcaoPatrulhaY = 1; break;
            case 2: direcaoPatrulhaX = -1; break;
            case 3: direcaoPatrulhaX = 1; break;
            // case 4: não faz nada, fica parado
        }
        tempoInicioPatrulhaDirecao = System.currentTimeMillis();
    }

    protected void patrulhar(Mapa mapa) {
        long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoInicioPatrulhaDirecao > DURACAO_PATRULHA_DIRECAO) {
            escolherNovaDirecaoPatrulha();
        }

        double novoX = this.posicao.getX();
        double novoY = this.posicao.getY();
        double passo = this.velocidade / 7.0;

        novoX += direcaoPatrulhaX * passo;
        novoY += direcaoPatrulhaY * passo;

        if (mapa != null) {
            novoX = Math.max(0, Math.min(novoX, mapa.getLarguraEmPixels() - this.largura));
            novoY = Math.max(0, Math.min(novoY, mapa.getAlturaEmPixels() - this.altura));
        }

        Posicao posAntiga = new Posicao(this.posicao.getX(), this.posicao.getY());
        this.posicao.setX(novoX);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setX(posAntiga.getX());
            escolherNovaDirecaoPatrulha();
        }

        this.posicao.setY(novoY);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setY(posAntiga.getY());
            escolherNovaDirecaoPatrulha();
        }
    }

    //meodo getPontosAoDerrotar() exigido pelo MotorJogo
    public int getPontosAoDerrotar() {
        return this.pontosAoDerrotar;
    }

    public void setPontosAoDerrotar(int pontos) {
        this.pontosAoDerrotar = pontos;
    }
}

