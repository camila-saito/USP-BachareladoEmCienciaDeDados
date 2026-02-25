package org.example.entidades;

import java.io.PrintStream;
import org.example.auxiliar.Posicao;
import org.example.graficos.Mapa;
import org.example.itens.Item;

public class Heroi extends Personagem {
    private static final long serialVersionUID = 1L;
    private int pontuacao = 0;
    private int pontuacaoFase = 0;
    private boolean movendoParaCima;
    private boolean movendoParaBaixo;
    private boolean movendoParaEsquerda;
    private boolean movendoParaDireita;
    private boolean atacando;
    private long tempoUltimoAtaque;
    private static final long COOLDOWN_ATAQUE = 500L;
    private boolean poderQuebraBlocosAtivo = false;
    private long tempoExpiracaoPoderQuebraBlocos = 0L;

    public Heroi(String var1, Posicao var2, int var3, int var4) {
        super(var1, var2, 32, 32, "/imagens/personagens/ze_junior.png", 100, (double)150.0F, var3, var4);
        this.bTransponivel = false;
        this.atacando = false;
        this.tempoUltimoAtaque = 0L;
    }

    public void setMovendoParaCima(boolean var1) {
        this.movendoParaCima = var1;
    }

    public void setMovendoParaBaixo(boolean var1) {
        this.movendoParaBaixo = var1;
    }

    public void setMovendoParaEsquerda(boolean var1) {
        this.movendoParaEsquerda = var1;
    }

    public void setMovendoParaDireita(boolean var1) {
        this.movendoParaDireita = var1;
    }

    public boolean isMovendoParaCima() {
        return this.movendoParaCima;
    }

    public boolean isMovendoParaBaixo() {
        return this.movendoParaBaixo;
    }

    public boolean isMovendoParaEsquerda() {
        return this.movendoParaEsquerda;
    }

    public boolean isMovendoParaDireita() {
        return this.movendoParaDireita;
    }

    public void setAtacando(boolean var1) {
        long var2 = System.currentTimeMillis();
        if (var1 && var2 - this.tempoUltimoAtaque > 500L) {
            this.atacando = true;
            this.tempoUltimoAtaque = var2;
        } else if (!var1) {
            this.atacando = false;
        }

    }

    public boolean isAtacando() {
        return this.atacando;
    }

    public void resetAtaqueFlag() {
        this.atacando = false;
    }

    public void ativarPoderQuebraBlocos(int var1) {
        this.poderQuebraBlocosAtivo = true;
        this.tempoExpiracaoPoderQuebraBlocos = System.currentTimeMillis() + (long)var1;
        System.out.println("[Heroi] Poder Quebra-Blocos ATIVADO por " + var1 / 1000 + "s.");
    }

    public boolean isPoderQuebraBlocosAtivo() {
        if (this.poderQuebraBlocosAtivo && System.currentTimeMillis() > this.tempoExpiracaoPoderQuebraBlocos) {
            this.poderQuebraBlocosAtivo = false;
            System.out.println("[Heroi] Poder Quebra-Blocos EXPIRADO.");
        }

        return this.poderQuebraBlocosAtivo;
    }

    public void atualizar(long var1, Mapa var3) {
        if (this.estaVivo()) {
            ;
        }
    }

    public void coletarItem(Item var1) {
        PrintStream var10000 = System.out;
        String var10001 = this.getNomeEntidade();
        var10000.println(var10001 + " coletou: " + String.valueOf(var1.getTipo()));
        var1.aplicarEfeito(this);
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void adicionarPontuacao(int var1) {
        this.pontuacao += var1;
        this.pontuacaoFase += var1;
    }

    public void resetarPontuacaoFase() {
        this.pontuacaoFase = 0;
        System.out.println("[Heroi] Pontuação da fase resetada.");
    }

    public int getPontuacaoFase() {
        return this.pontuacaoFase;
    }

    public void atacar() {
        this.setAtacando(true);
    }
}
