//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entidades;

import org.example.auxiliar.Posicao;
import java.io.Serializable;

public abstract class Personagem extends Entidade implements Serializable {
    private static final long serialVersionUID = 1L;
    protected boolean bTransponivel;
    protected boolean bMortal;
    protected int ataque;
    protected int defesa;
    protected long ultimoDanoSofridoTimestamp = 0L;
    protected static final long COOLDOWN_DANO_MS = 5000L;
    protected boolean invencivelTemporariamente = false;

    public Personagem(String var1, Posicao var2, int var3, int var4, String var5, int var6, double var7, int var9, int var10) {
        super(var1, var2, var3, var4, var5, var6);
        this.velocidade = var7;
        this.ataque = var9;
        this.defesa = var10;
        this.bTransponivel = false;
        this.bMortal = true;
    }

    public void receberDano(int var1) {
        if (System.currentTimeMillis() - this.ultimoDanoSofridoTimestamp >= 5000L) {
            int var2 = var1 - this.defesa;
            if (var2 < 1) {
                var2 = 1;
            }

            this.vidaAtual -= var2;
            this.ultimoDanoSofridoTimestamp = System.currentTimeMillis();
            this.invencivelTemporariamente = true;
            System.out.println(this.getNomeEntidade() + " recebeu " + var2 + " de dano. Vida restante: " + this.vidaAtual);
            if (this.vidaAtual <= 0) {
                this.vidaAtual = 0;
                System.out.println(this.getNomeEntidade() + " foi derrotado.");
            }

        }
    }

    public boolean isInvencivelTemporariamente() {
        if (this.invencivelTemporariamente && System.currentTimeMillis() - this.ultimoDanoSofridoTimestamp >= 5000L) {
            this.invencivelTemporariamente = false;
        }

        return this.invencivelTemporariamente;
    }

    public boolean moveUp(double var1) {
        return true;
    }

    public boolean moveDown(double var1) {
        return true;
    }

    public boolean moveRight(double var1) {
        return true;
    }

    public boolean moveLeft(double var1) {
        return true;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public void setAtaque(int var1) {
        this.ataque = var1;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public void setDefesa(int var1) {
        this.defesa = var1;
    }

    public boolean isbTransponivel() {
        return this.bTransponivel;
    }

    public void setbTransponivel(boolean var1) {
        this.bTransponivel = var1;
    }

    public boolean isbMortal() {
        return this.bMortal;
    }

    public void setbMortal(boolean var1) {
        this.bMortal = var1;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(double var1) {
        this.velocidade = var1;
    }

    public boolean setPosicaoAbsoluta(int var1, int var2) {
        double var3 = (double)(var2 * 32);
        double var5 = (double)(var1 * 32);
        super.setPosicao(var3, var5);
        return true;
    }
}
