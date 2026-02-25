package org.example.entidades.chefes;

import org.example.entidades.Chefe;
import org.example.entidades.Heroi;
import org.example.entidades.Projetil;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;
import java.awt.Color;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class ByteCorrompido extends Chefe {
    private static final long serialVersionUID = 1L;

    private long tempoUltimoAtaquePadrao;
    //cooldowns ajustados para maior agressividade
    private static final long COOLDOWN_ATAQUE_PADRAO_BASE = 1600; //milissegundos (antes 2000)
    private static final long COOLDOWN_ATAQUE_PADRAO_ENFURECIDO = 1100;
    private long tempoUltimoAtaqueEspecial;
    private static final long COOLDOWN_ATAQUE_ESPECIAL_BASE = 7000; //milissegundos (antes 8000)
    private static final long COOLDOWN_ATAQUE_ESPECIAL_ENFURECIDO = 5500;

    private Random random = new Random();
    private enum EstadoAI { PERSEGUINDO, ATACANDO_ESPECIAL, RECUANDO_PARA_ESPECIAL, ESPERANDO, INVESTIDA }
    private EstadoAI estadoAtual = EstadoAI.PERSEGUINDO;
    private long tempoEntradaEstado = 0;
    private Posicao alvoMovimentoEspecial = null;
    private Posicao alvoInvestida = null;
    private long tempoUltimaInvestida = 0;
    private static final long COOLDOWN_INVESTIDA = 10000; // Cooldown para o novo ataque de investida

    public ByteCorrompido(Posicao posicaoInicial, Heroi alvo) {
        super("Byte Corrompido",
                posicaoInicial,
                (int)(Constantes.TAMANHO_TILE * 1.8),
                (int)(Constantes.TAMANHO_TILE * 1.8),
                Constantes.CHEFE_BYTE_CORROMPIDO_FASE1,
                250,  // vidaMaxima aumentada (antes 200)
                40.0, // velocidade base aumentada (antes 35.0)
                15,   // ataque aumentado (antes 12)
                4,    // defesa aumentada (antes 3)
                alvo,
                50,
                250,   // pontos aumentados (antes 200)
                Color.MAGENTA
        );
        this.tempoUltimoAtaquePadrao = System.currentTimeMillis();
        this.tempoUltimoAtaqueEspecial = System.currentTimeMillis() - getCooldownAtaqueEspecial() / 2;
        this.tempoEntradaEstado = System.currentTimeMillis();
        this.tempoUltimaInvestida = System.currentTimeMillis() - COOLDOWN_INVESTIDA / 2; // Permite investida mais cedo
    }

    private long getCooldownAtaquePadrao() {
        return enfurecido ? COOLDOWN_ATAQUE_PADRAO_ENFURECIDO : COOLDOWN_ATAQUE_PADRAO_BASE;
    }

    private long getCooldownAtaqueEspecial() {
        return enfurecido ? COOLDOWN_ATAQUE_ESPECIAL_ENFURECIDO : COOLDOWN_ATAQUE_ESPECIAL_BASE;
    }

    @Override
    public void inteligenciaArtificialChefe(Mapa mapa, long deltaTime) {
        if (alvo == null || !alvo.estaVivo() || !this.estaVivo()) {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
            return;
        }

        long tempoAtual = System.currentTimeMillis();

        //logica de transição de estados
        switch (estadoAtual) {
            case PERSEGUINDO:
                perseguirAlvo(mapa, deltaTime, Constantes.TAMANHO_TILE * 2.5); // Manter uma distância média
                //tentar Investida
                if (tempoAtual - tempoUltimaInvestida > COOLDOWN_INVESTIDA && this.posicao.distanciaAte(alvo.getPosicao()) < Constantes.TAMANHO_TILE * 6) {
                    mudarEstado(EstadoAI.INVESTIDA);
                    break;
                }
                //tentar Ataque Especial
                if (tempoAtual - tempoUltimoAtaqueEspecial > getCooldownAtaqueEspecial() && this.posicao.distanciaAte(alvo.getPosicao()) > Constantes.TAMANHO_TILE * 3.5) {
                    mudarEstado(EstadoAI.RECUANDO_PARA_ESPECIAL);
                }
                //tentar Ataque Padrão
                else if (podeAtacarAlvo(Constantes.TAMANHO_TILE * 3.0) && tempoAtual - tempoUltimoAtaquePadrao > getCooldownAtaquePadrao()) {
                    this.atacandoAgora = true;
                    mudarEstado(EstadoAI.ESPERANDO);
                }
                break;

            case RECUANDO_PARA_ESPECIAL:
                if (alvoMovimentoEspecial == null) {
                    double anguloOposto = Math.atan2(this.posicao.getY() - alvo.getPosicao().getY(), this.posicao.getX() - alvo.getPosicao().getX());
                    double distRecuo = Constantes.TAMANHO_TILE * 4; // Distância de recuo ajustada (antes 5)
                    alvoMovimentoEspecial = new Posicao(
                            this.posicao.getX() + Math.cos(anguloOposto) * distRecuo,
                            this.posicao.getY() + Math.sin(anguloOposto) * distRecuo
                    );
                    if (mapa != null) {
                        alvoMovimentoEspecial.setX(Math.max(0, Math.min(alvoMovimentoEspecial.getX(), mapa.getLarguraEmPixels() - this.largura)));
                        alvoMovimentoEspecial.setY(Math.max(0, Math.min(alvoMovimentoEspecial.getY(), mapa.getAlturaEmPixels() - this.altura)));
                    }
                }
                moverParaPosicao(alvoMovimentoEspecial, mapa, deltaTime, this.getVelocidadeBase() * 1.3); // Movimento de recuo mais rápido
                if (this.posicao.distanciaAte(alvoMovimentoEspecial) < Constantes.TAMANHO_TILE * 0.5 || tempoAtual - tempoEntradaEstado > 2500) { // Tempo de recuo reduzido (antes 3000)
                    mudarEstado(EstadoAI.ATACANDO_ESPECIAL);
                    alvoMovimentoEspecial = null;
                }
                break;

            case ATACANDO_ESPECIAL:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > 600) { // Delay para "carregar" o ataque especial (antes 700)
                    this.atacandoAgora = true;
                    mudarEstado(EstadoAI.PERSEGUINDO);
                }
                break;

            case ESPERANDO: // Após ataque padrão
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 250 : 500)) { // Tempo de espera reduzido (antes 400/800)
                    mudarEstado(EstadoAI.PERSEGUINDO);
                }
                break;

            case INVESTIDA:
                if (alvoInvestida == null) {
                    alvoInvestida = new Posicao(alvo.getPosicao().getX(), alvo.getPosicao().getY());
                    System.out.println(this.getNomeEntidade() + " inicia INVESTIDA!");
                }
                moverParaPosicao(alvoInvestida, mapa, deltaTime, this.getVelocidadeBase() * (enfurecido ? 2.2 : 1.8)); // Velocidade de investida alta
                //se colidir com o herói durante a investida (MotorJogo lidaria com dano por contato, se implementado)
                //ou se atingir o ponto ou tempo limite
                if (this.getBounds().intersects(alvo.getBounds()) || this.posicao.distanciaAte(alvoInvestida) < Constantes.TAMANHO_TILE * 0.5 || tempoAtual - tempoEntradaEstado > 1500) {
                    System.out.println(this.getNomeEntidade() + " termina INVESTIDA!");
                    tempoUltimaInvestida = tempoAtual;
                    alvoInvestida = null;
                    mudarEstado(EstadoAI.PERSEGUINDO);
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

        if (mapa != null) {
            novoX = Math.max(0, Math.min(novoX, mapa.getLarguraEmPixels() - this.largura));
            novoY = Math.max(0, Math.min(novoY, mapa.getAlturaEmPixels() - this.altura));
            if (mapa.getAreaArenaChefe() != null) {
                java.awt.Rectangle arena = mapa.getAreaArenaChefe();
                novoX = Math.max(arena.getX(), Math.min(novoX, arena.getX() + arena.getWidth() - this.largura));
                novoY = Math.max(arena.getY(), Math.min(novoY, arena.getY() + arena.getHeight() - this.altura));
            }
        }

        this.posicao.setX(novoX);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setX(posAntiga.getX());
            this.velocidadeX = -this.velocidadeX * 0.3;
        }

        this.posicao.setY(novoY);
        if (mapa != null && mapa.colideCom(this)) {
            this.posicao.setY(posAntiga.getY());
            this.velocidadeY = -this.velocidadeY * 0.3;
        }
    }

    @Override
    public List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvoDoHeroi) {
        List<Projetil> projeteisCriados = new ArrayList<>();
        long tempoAtual = System.currentTimeMillis();

        if (estadoAtual == EstadoAI.ATACANDO_ESPECIAL && tempoAtual - tempoUltimoAtaqueEspecial > getCooldownAtaqueEspecial()) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE ESPECIAL: Fragmentos Corrompidos!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double anguloBase = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(), posicaoAlvoDoHeroi.getX() - centroChefe.getX());

            int numProjeteis = enfurecido ? 8 : 5; //maiis projéteis (antes 6/4)
            double anguloVariacao = Math.toRadians(18); //menor variação para um cone mais focado (antes 20)

            for (int i = 0; i < numProjeteis; i++) {
                double anguloProjetil = anguloBase + (i - (numProjeteis - 1.0) / 2.0) * anguloVariacao;
                anguloProjetil += (random.nextDouble() - 0.5) * Math.toRadians(8); //menor aleatoriedade (antes 10)

                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                Projetil p = new Projetil("ProjetilCorrompido-" + i, posProjetil, anguloProjetil, false, this.getAtaque() + (enfurecido ? 8 : 5), Constantes.CAMINHO_CHEFES + "projetil_byte_especial.png");
                p.setVelocidadeProjetil(enfurecido ? 200.0 : 170.0); //velocidade aumentada (antes 180/150)
                projeteisCriados.add(p);
            }
            this.tempoUltimoAtaqueEspecial = tempoAtual;
            registrarTempoAtaque();
        } else if (estadoAtual == EstadoAI.ESPERANDO && tempoAtual - tempoUltimoAtaquePadrao > getCooldownAtaquePadrao()) {
            System.out.println(this.getNomeEntidade() + " usa ATAQUE PADRÃO!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double angulo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(), posicaoAlvoDoHeroi.getX() - centroChefe.getX());
            Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
            Projetil p = new Projetil("ProjetilBytePadrao", posProjetil, angulo, false, this.getAtaque(), Constantes.CAMINHO_CHEFES + "projetil_byte_padrao.png");
            p.setVelocidadeProjetil(enfurecido ? 260.0 : 220.0); //velocidade aumentada (antes 220/180)
            projeteisCriados.add(p);
            this.tempoUltimoAtaquePadrao = tempoAtual;
            registrarTempoAtaque();
        }
        this.atacandoAgora = false; //reseta a flag após tentativa de ataque
        return projeteisCriados;
    }

    private void mudarEstado(EstadoAI novoEstado) {
        System.out.println(this.getNomeEntidade() + " mudando para estado: " + novoEstado);
        this.estadoAtual = novoEstado;
        this.tempoEntradaEstado = System.currentTimeMillis();
    }

    private void perseguirAlvo(Mapa mapa, long deltaTime, double distanciaIdeal) {
        if (alvo == null || !alvo.estaVivo()) {
            this.velocidadeX = 0; this.velocidadeY = 0; return;
        }
        double targetX = alvo.getPosicao().getX() + alvo.getLargura() / 2.0;
        double targetY = alvo.getPosicao().getY() + alvo.getAltura() / 2.0;
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distanciaAtual = Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        //se estiver muito perto, afasta um pouco, se estiver muito longe, aproxima.
        //O objetivo é manter a 'distanciaIdeal'
        double vel = this.enfurecido ? this.getVelocidadeBase() * 1.5 : this.getVelocidadeBase() * 1.1;
        double angulo = Math.atan2(deltaY, deltaX);

        if (distanciaAtual < distanciaIdeal * 0.8) { // Muito perto, recuar um pouco
            this.velocidadeX = -vel * Math.cos(angulo) * 0.7; // Recua mais devagar
            this.velocidadeY = -vel * Math.sin(angulo) * 0.7;
        } else if (distanciaAtual > distanciaIdeal * 1.2) { // Longe demais, aproximar
            this.velocidadeX = vel * Math.cos(angulo);
            this.velocidadeY = vel * Math.sin(angulo);
        } else { // Distância boa, pode reduzir velocidade ou fazer movimentos laterais
            this.velocidadeX = vel * Math.cos(angulo) * 0.5; // Movimento mais lento de manutenção de posição
            this.velocidadeY = vel * Math.sin(angulo) * 0.5;
            // Adicionar chance de movimento lateral para esquiva?
            if (random.nextDouble() < 0.1) { // 10% de chance de um pequeno strafe
                double anguloStrafe = angulo + (random.nextBoolean() ? Math.PI/2 : -Math.PI/2);
                this.velocidadeX += vel * Math.cos(anguloStrafe) * 0.4;
                this.velocidadeY += vel * Math.sin(anguloStrafe) * 0.4;
            }
        }
    }

    private void moverParaPosicao(Posicao destino, Mapa mapa, long deltaTime, double velocidadeMovimento) {
        if (destino == null) {
            this.velocidadeX = 0; this.velocidadeY = 0; return;
        }
        double targetX = destino.getX();
        double targetY = destino.getY();
        double currentX = this.posicao.getX() + this.largura / 2.0;
        double currentY = this.posicao.getY() + this.altura / 2.0;

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        if (Math.sqrt(deltaX*deltaX + deltaY*deltaY) < Constantes.TAMANHO_TILE * 0.3) {
            this.velocidadeX = 0; this.velocidadeY = 0; return;
        }
        double angulo = Math.atan2(deltaY, deltaX);
        this.velocidadeX = velocidadeMovimento * Math.cos(angulo);
        this.velocidadeY = velocidadeMovimento * Math.sin(angulo);
    }

    @Override
    protected void entrarModoEnfurecido() {
        super.entrarModoEnfurecido();
        System.out.println(this.getNomeEntidade() + " está FURIOSO! Velocidade e agressividade aumentadas!");

    }
}

