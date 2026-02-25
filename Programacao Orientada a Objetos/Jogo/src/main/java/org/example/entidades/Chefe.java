package org.example.entidades;

import org.example.auxiliar.Posicao;
import org.example.graficos.Mapa;
import org.example.motor.MotorJogoListener;
import org.example.entidades.Projetil; // Certifique-se que Projetil está importado
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public abstract class Chefe extends Personagem {
    private static final long serialVersionUID = 1L;

    protected Heroi alvo;
    protected boolean enfurecido;
    protected int vidaParaEnfurecer; // Percentual
    protected MotorJogoListener listenerMotor;
    protected double velocidadeBase;
    protected int ataqueBase;
    protected int defesaBase;
    protected int ataqueCorpoACorpoBase;
    protected Color corPoder;
    protected double velocidadeX, velocidadeY; //para movimentação mais complexa
    
    protected boolean atacandoAgora = false; //flag para isAtacando()
    protected long tempoUltimoAtaqueRealizado = 0;
    protected long cooldownAtaqueMsGeral = 2500; // Cooldown geral para ataques do chefe
    protected int pontosAoDerrotar = 100; //Pontos padrão ao derrotar um chefe

    public Chefe(String nome, Posicao posicaoInicial, int largura, int altura, String caminhoImagem,
                 int vidaMaxima, double velocidade, int ataque, int defesa, Heroi alvo, 
                 int vidaParaEnfurecerPercentual, int pontos, Color corPoderChefe) { 
        super(nome, posicaoInicial, largura, altura, caminhoImagem, vidaMaxima, velocidade, ataque, defesa);
        this.alvo = alvo;
        this.enfurecido = false;
        this.vidaParaEnfurecer = vidaParaEnfurecerPercentual;
        this.bMortal = true;
        this.velocidadeBase = velocidade;
        this.ataqueBase = ataque;
        this.defesaBase = defesa;
        // this.ataqueCorpoACorpoBase = ataqueCorpoACorpo; //se for usar, adicionar ao construtor
        this.pontosAoDerrotar = pontos;
        this.corPoder = corPoderChefe;
    }

    public void setMotorJogoListener(MotorJogoListener listener) {
        this.listenerMotor = listener;
    }

    public abstract void inteligenciaArtificialChefe(Mapa mapa, long deltaTime);

    //esse método será chamado pelo MotorJogo. As classes filhas devem implementá-lo
    // para definir como o chefe ataca, retornando uma lista de projéteis.
    public abstract List<Projetil> atacarAlvoMultiplo(Posicao posicaoAlvo);

    @Override
    public void atualizar(long deltaTime, Mapa mapa) {
        if (!estaVivo()) {
            this.velocidadeX = 0;
            this.velocidadeY = 0;
            return;
        }
        this.atacandoAgora = false; //reseta a flag no início de cada atualização

        if (alvo == null || !alvo.estaVivo()) {
             //logica de patrulha ou idle se não houver alvo
        } else {
            if (!enfurecido && (getVidaAtual() <= (getVidaMaxima() * vidaParaEnfurecer / 100.0))) {
                entrarModoEnfurecido();
            }
            inteligenciaArtificialChefe(mapa, deltaTime);
            //a IA deve setar this.atacandoAgora = true se decidir atacar
        }
    }

    protected void entrarModoEnfurecido() {
        this.enfurecido = true;
        this.setVelocidade(this.velocidadeBase * 1.5); //ex: aumenta velocidade
        this.setAtaque(this.ataqueBase + 10); //ex: aumenta ataque
        System.out.println(this.getNomeEntidade() + " entrou em MODO ENFURECIDO!");
    }

    // Método isAtacando() exigido pelo MotorJogo
    public boolean isAtacando() {
        return this.atacandoAgora;
    }

    // Método podeAtacar() exigido pelo MotorJogo
    public boolean podeAtacar() {
        //a logica de cooldown e alcance pode ser mais específica na IA ou no atacarAlvoMultiplo
        return System.currentTimeMillis() - tempoUltimoAtaqueRealizado > cooldownAtaqueMsGeral && alvo != null && alvo.estaVivo();
    }
    
    //chamado por atacarAlvoMultiplo para registrar o tempo do ataque
    protected void registrarTempoAtaque(){
        this.tempoUltimoAtaqueRealizado = System.currentTimeMillis();
    }

    protected boolean podeAtacarAlvo(double alcance) {
        if (alvo == null || !alvo.estaVivo()) return false;
        double distancia = this.posicao.distanciaAte(alvo.getPosicao());
        return distancia <= alcance;
    }

    //metodo getPontosAoDerrotar() exigido pelo MotorJogo
    public int getPontosAoDerrotar() {
        return this.pontosAoDerrotar;
    }

    public void setPontosAoDerrotar(int pontos) {
        this.pontosAoDerrotar = pontos;
    }

    public double getVelocidadeBase() {
        return velocidadeBase;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public Color getCorPoder() {
        return corPoder;
    }
}

