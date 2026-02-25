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

public class ChefaoPoderoso extends Chefe {
    private static final long serialVersionUID = 1L;

    private long tempoUltimoAtaquePadrao;
    private static final long COOLDOWN_ATAQUE_PADRAO = 2200; // Milissegundos
    private long tempoUltimoAtaqueGlobal;
    private static final long COOLDOWN_ATAQUE_GLOBAL_NORMAL = 11000;
    private static final long COOLDOWN_ATAQUE_GLOBAL_ENFURECIDO = 7500;
    private long tempoUltimaBarragemProjeteis;
    private static final long COOLDOWN_BARRAGEM_PROJETEIS_NORMAL = 8000;
    private static final long COOLDOWN_BARRAGEM_PROJETEIS_ENFURECIDO = 5500;

    private Random random = new Random();
    private enum EstadoAI { MOVENDO_ESTRATEGICAMENTE, PREPARANDO_ATAQUE_GLOBAL, ATACANDO_GLOBAL, DISPARANDO_BARRAGEM, ATAQUE_CORPO_A_CORPO, OCIOSO }
    private EstadoAI estadoAtual = EstadoAI.OCIOSO;
    private long tempoEntradaEstado = 0;
    private Posicao alvoMovimento = null;
    private int tipoAtaqueGlobalAtual = 0;

    public ChefaoPoderoso(Posicao posicaoInicial, Heroi alvo) {
        super("Chefão Supremo",
                posicaoInicial,
                (int)(Constantes.TAMANHO_TILE * 2.8),
                (int)(Constantes.TAMANHO_TILE * 2.8),
                Constantes.CHEFE_PODEROSO_FASE5,
                750,  // vidaMaxima
                38.0, // velocidade
                25,   // ataque
                15,   // defesa
                alvo, // alvoHeroi
                33,   // vidaParaEnfurecerPercentual
                750,  // pontos
                new Color(139, 0, 0) // corPoderChefe (DarkRed)
        );
        this.tempoUltimoAtaquePadrao = System.currentTimeMillis();
        this.tempoUltimoAtaqueGlobal = System.currentTimeMillis() - COOLDOWN_ATAQUE_GLOBAL_NORMAL / 3;
        this.tempoUltimaBarragemProjeteis = System.currentTimeMillis() - COOLDOWN_BARRAGEM_PROJETEIS_NORMAL / 2;
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
        long cooldownAtaqueGlobalAtual = enfurecido ? COOLDOWN_ATAQUE_GLOBAL_ENFURECIDO : COOLDOWN_ATAQUE_GLOBAL_NORMAL;
        long cooldownBarragemAtual = enfurecido ? COOLDOWN_BARRAGEM_PROJETEIS_ENFURECIDO : COOLDOWN_BARRAGEM_PROJETEIS_NORMAL;
        double distanciaAlvo = this.posicao.distanciaAte(alvo.getPosicao());

        switch (estadoAtual) {
            case OCIOSO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 800 : 1300)) {
                    int chance = random.nextInt(100);
                    if (tempoAtual - tempoUltimoAtaqueGlobal > cooldownAtaqueGlobalAtual && chance < (enfurecido ? 55 : 40)) {
                        mudarEstado(EstadoAI.PREPARANDO_ATAQUE_GLOBAL);
                    } else if (tempoAtual - tempoUltimaBarragemProjeteis > cooldownBarragemAtual && chance < (enfurecido ? 80 : 65)) {
                        mudarEstado(EstadoAI.DISPARANDO_BARRAGEM);
                    } else if (distanciaAlvo < this.largura * 1.1 && tempoAtual - tempoUltimoAtaquePadrao > COOLDOWN_ATAQUE_PADRAO) {
                        mudarEstado(EstadoAI.ATAQUE_CORPO_A_CORPO);
                    } else {
                        mudarEstado(EstadoAI.MOVENDO_ESTRATEGICAMENTE);
                    }
                }
                break;

            case MOVENDO_ESTRATEGICAMENTE:
                if (alvoMovimento == null || this.posicao.distanciaAte(alvoMovimento) < Constantes.TAMANHO_TILE * 1.0 || tempoAtual - tempoEntradaEstado > 3000) {
                    alvoMovimento = encontrarPosicaoEstrategica(mapa);
                    if (alvoMovimento == null) {
                        mudarEstado(EstadoAI.OCIOSO);
                        break;
                    }
                    tempoEntradaEstado = tempoAtual; // Reinicia tempo no estado ao escolher novo alvo de movimento
                }
                moverParaPosicao(mapa, alvoMovimento, deltaTime);
                //se chegou perto do alvo de movimento ou tempo esgotou, volta a ser ocioso para reavaliar
                if (this.posicao.distanciaAte(alvoMovimento) < Constantes.TAMANHO_TILE * 0.8 || tempoAtual - tempoEntradaEstado > 3500) {
                    mudarEstado(EstadoAI.OCIOSO);
                }
                break;

            case PREPARANDO_ATAQUE_GLOBAL:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                //animação de preparação
                if (tempoAtual - tempoEntradaEstado > (enfurecido ? 1200 : 2000)) { // Tempo de preparação
                    mudarEstado(EstadoAI.ATACANDO_GLOBAL);
                }
                break;

            case ATACANDO_GLOBAL:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                this.atacandoAgora = true; // Sinaliza intenção de ataque global
                //a criação dos projéteis ocorre em atacarAlvoMultiplo
                //o ataque global é instantâneo ou tem uma duração muito curta no estado
                tempoUltimoAtaqueGlobal = tempoAtual;
                tipoAtaqueGlobalAtual = (tipoAtaqueGlobalAtual + 1) % 3; // Alterna entre os 3 tipos de ataque global
                mudarEstado(EstadoAI.OCIOSO); // Volta a ocioso após o ataque
                break;

            case DISPARANDO_BARRAGEM:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                //animação de preparação da barragem
                if (tempoAtual - tempoEntradaEstado > 600) { //teempo de preparação
                    this.atacandoAgora = true; // Sinaliza intenção de ataque (barragem)
                    //a criação dos projéteis ocorre em atacarAlvoMultiplo
                    tempoUltimaBarragemProjeteis = tempoAtual;
                    mudarEstado(EstadoAI.OCIOSO); //vollta a ocioso após a barragem
                }
                break;

            case ATAQUE_CORPO_A_CORPO:
                this.velocidadeX = 0;
                this.velocidadeY = 0;
                this.atacandoAgora = true; //sinaliza intenção de ataque corpo a corpo
                //acriação do projétil (ou efeito) ocorre em atacarAlvoMultiplo
                if (tempoAtual - tempoEntradaEstado > 600) { // Duração do estado de ataque
                    tempoUltimoAtaquePadrao = tempoAtual;
                    mudarEstado(EstadoAI.OCIOSO);
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
            this.velocidadeX = 0;
        }

        this.posicao.setY(novoY);
        if (mapa != null && (mapa.colideCom(this) || !mapa.getBounds().contains(this.getBounds()))) {
            this.posicao.setY(posAntiga.getY());
            this.velocidadeY = 0;
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
        if (Math.sqrt(deltaX*deltaX + deltaY*deltaY) < Constantes.TAMANHO_TILE * 0.3) {
            this.velocidadeX = 0; this.velocidadeY = 0; return;
        }
        double angulo = Math.atan2(deltaY, deltaX);
        double vel = this.enfurecido ? this.getVelocidadeBase() * 1.25 : this.getVelocidadeBase();

        this.velocidadeX = vel * Math.cos(angulo);
        this.velocidadeY = vel * Math.sin(angulo);
    }

    private Posicao encontrarPosicaoEstrategica(Mapa mapa) {
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

            //tenta encontrar uma posição que não seja muito perto nem muito longe do alvo, e que seja transponível
            double distanciaDoAlvo = posPotencial.distanciaAte(alvo.getPosicao());
            if (distanciaDoAlvo > Constantes.TAMANHO_TILE * 2.5 && distanciaDoAlvo < Constantes.TAMANHO_TILE * 8 && mapa.ehAreaTransponivel(boundsPotencial)) {
                return posPotencial;
            }
            tentativas++;
        }
        return null; //nao encontrou posição ideal
    }

    @Override
    public List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvoDoHeroi) {
        List<Projetil> projeteisCriados = new ArrayList<>();

        if (estadoAtual == EstadoAI.ATACANDO_GLOBAL && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " executa ATAQUE DE SOBRECARGA GLOBAL!");
            if (alvo == null || !alvo.estaVivo() || listenerMotor == null) return projeteisCriados;
            Rectangle arena = listenerMotor.getArenaChefe();
            if (arena == null) return projeteisCriados; // Precisa da arena para ataques globais

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            int danoGlobal = this.getAtaque() + (enfurecido ? 15 : 5);

            switch (tipoAtaqueGlobalAtual) {
                case 0: //chuuva de Energia Cadente
                    System.out.println("Padrão Global: CHUVA DE ENERGIA CADENTE!");
                    int numMeteoros = enfurecido ? 15 : 10;
                    for (int i = 0; i < numMeteoros; i++) {
                        double xSpawn = arena.getX() + random.nextDouble() * arena.getWidth();
                        Posicao spawnProjetil = new Posicao(xSpawn - Projetil.LARGURA_PADRAO/2.0, arena.getY() - Projetil.ALTURA_PADRAO - 10); // Spawn um pouco acima da arena
                        Projetil meteoro = new Projetil("MeteoroSupremo_" + i, spawnProjetil, Math.toRadians(90), false, danoGlobal, Constantes.CAMINHO_CHEFES + "projetil_supremo_meteoro.png");
                        meteoro.setVelocidadeProjetil(280.0 + random.nextInt(120));
                        meteoro.setDuracaoMaxima(4000);
                        projeteisCriados.add(meteoro);
                    }
                    break;
                case 1: //ondas  de Choque Terrestres
                    System.out.println("Padrão Global: ONDAS DE CHOQUE TERRESTRES!");
                    int numOndas = enfurecido ? 6 : 4;
                    for (int i = 0; i < numOndas; i++) {
                        //ondas  partem do chefe em direções variadas
                        double anguloOnda = (2 * Math.PI / numOndas) * i + (random.nextDouble() - 0.5) * Math.toRadians(20);
                        Posicao spawnOnda = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                        Projetil onda = new Projetil("OndaChoqueSuprema_" + i, spawnOnda, anguloOnda, false, danoGlobal, Constantes.CAMINHO_CHEFES + "projetil_supremo_onda.png");
                        onda.setVelocidadeProjetil(enfurecido ? 200.0 : 160.0);
                        onda.setDuracaoMaxima(3000); // Ondas se dissipam
                        projeteisCriados.add(onda);
                    }
                    break;
                case 2: //feixes de Energia Perseguidores
                    System.out.println("Padrão Global: FEIXES DE ENERGIA PERSEGUIDORES!");
                    int numFeixes = enfurecido ? 4 : 3;
                    for (int i = 0; i < numFeixes; i++) {
                        Posicao spawnFeixe = new Posicao(centroChefe.getX() + (random.nextDouble() -0.5) * this.largura * 0.8,
                                centroChefe.getY() + (random.nextDouble() -0.5) * this.altura * 0.8);
                        Projetil feixe = new Projetil("FeixePerseguidorSupremo_" + i, spawnFeixe, 0, false, danoGlobal - 3, Constantes.CAMINHO_CHEFES + "projetil_supremo_feixe.png");
                        feixe.setVelocidadeProjetil(90.0 + random.nextInt(50));
                        feixe.setPersegueAlvo(alvo, 0.06 + random.nextDouble()*0.04); // Fator de perseguição
                        feixe.setDuracaoMaxima(7000); // Feixes duram mais
                        projeteisCriados.add(feixe);
                    }
                    break;
            }
            registrarTempoAtaque();
            this.atacandoAgora = false;

        } else if (estadoAtual == EstadoAI.DISPARANDO_BARRAGEM && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " dispara BARRAGEM DE PROJÉTEIS!");
            if (alvo == null || !alvo.estaVivo()) return projeteisCriados;

            Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
            double anguloBase = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(),
                    posicaoAlvoDoHeroi.getX() - centroChefe.getX());

            int numProjeteis = enfurecido ? 10 : 7;
            double spreadTotal = Math.toRadians(enfurecido ? 50 : 35);
            double velocidadeProjetil = enfurecido ? 220.0 : 180.0;
            int danoBarragem = this.getAtaque() - 8;

            for (int i = 0; i < numProjeteis; i++) {
                double anguloProjetil = anguloBase - (spreadTotal / 2.0) + (i * spreadTotal / (numProjeteis > 1 ? (numProjeteis -1) : 1) );
                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                Projetil p = new Projetil("ProjetilBarragemSupremo_" + i, posProjetil, anguloProjetil, false, danoBarragem, Constantes.CAMINHO_CHEFES + "projetil_supremo_barragem.png");
                p.setVelocidadeProjetil(velocidadeProjetil);
                p.setDuracaoMaxima(3000);
                projeteisCriados.add(p);
            }
            registrarTempoAtaque();
            this.atacandoAgora = false;

        } else if (estadoAtual == EstadoAI.ATAQUE_CORPO_A_CORPO && this.atacandoAgora) {
            System.out.println(this.getNomeEntidade() + " tenta um ATAQUE CORPO A CORPO devastador!");
            //logica para ataque corpo a corpo (pode não gerar projétil, mas causar dano direto se o herói estiver no alcance)
            // Ex: if (this.getBounds().intersects(alvo.getBounds())) { alvo.receberDano(this.getAtaque() + 10); }
            //por enquanto, vamos simular com um projétil de curtíssimo alcance e duração
            if (alvo != null && alvo.estaVivo()) {
                Posicao centroChefe = new Posicao(this.posicao.getX() + this.largura / 2.0, this.posicao.getY() + this.altura / 2.0);
                double angulo = Math.atan2(posicaoAlvoDoHeroi.getY() - centroChefe.getY(), posicaoAlvoDoHeroi.getX() - centroChefe.getX());
                Posicao posProjetil = new Posicao(centroChefe.getX() - Projetil.LARGURA_PADRAO/2.0, centroChefe.getY() - Projetil.ALTURA_PADRAO/2.0);
                Projetil p = new Projetil("AtaqueCorpoSupremo", posProjetil, angulo, false, this.getAtaque() + 5, Constantes.CAMINHO_CHEFES + "projetil_supremo_cac.png");
                p.setVelocidadeProjetil(350.0);
                p.setDuracaoMaxima(300);
                projeteisCriados.add(p);
            }
            registrarTempoAtaque();
            this.atacandoAgora = false;
        }
        return projeteisCriados;
    }

    @Override
    protected void entrarModoEnfurecido() {
        super.entrarModoEnfurecido();
        this.setVelocidade(this.getVelocidadeBase() * 1.3);
        this.setAtaque(this.getAtaqueBase() + 12);
        this.setDefesa(this.getDefesaBase() + 8);
        System.out.println(this.getNomeEntidade() + " REVELA SEU VERDADEIRO PODER! PREPARE-SE PARA A ANIQUILAÇÃO TOTAL!");
    }
}

