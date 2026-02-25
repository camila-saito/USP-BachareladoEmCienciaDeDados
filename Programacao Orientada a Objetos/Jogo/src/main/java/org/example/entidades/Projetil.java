//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entidades;

import org.example.auxiliar.Posicao;
import org.example.entidades.Entidade;
import org.example.graficos.Mapa;
import java.awt.Color;
import java.awt.Graphics;

public class Projetil extends Entidade {
    private static final long serialVersionUID = 1L;
    private double velocidadeX;
    private double velocidadeY;
    public static final int LARGURA_PADRAO = 8;
    public static final int ALTURA_PADRAO = 8;
    private double velocidadeProjetil = (double)300.0F;
    private boolean doHeroi;
    private int dano;
    private boolean destruido = false;
    private long tempoCriacao;
    private long duracaoMaximaMs = -1L;
    private Heroi alvoPerseguicao = null;
    private double fatorPerseguicao = (double)0.0F;

    public Projetil(String var1, Posicao var2, double var3, boolean var5, int var6) {
        super(var1, var2, 8, 8, (String)null, 1);
        this.velocidadeProjetil = (double)300.0F;
        this.velocidadeX = Math.cos(var3) * this.velocidadeProjetil;
        this.velocidadeY = Math.sin(var3) * this.velocidadeProjetil;
        this.doHeroi = var5;
        this.dano = var6;
        this.visivel = true;
        this.tempoCriacao = System.currentTimeMillis();
    }

    public Projetil(String var1, Posicao var2, double var3, boolean var5, int var6, String var7) {
        super(var1, var2, 8, 8, var7, 1);
        this.velocidadeProjetil = (double)300.0F;
        this.velocidadeX = Math.cos(var3) * this.velocidadeProjetil;
        this.velocidadeY = Math.sin(var3) * this.velocidadeProjetil;
        this.doHeroi = var5;
        this.dano = var6;
        this.visivel = true;
        this.tempoCriacao = System.currentTimeMillis();
    }

    public void atualizar(long var1, Mapa var3) {
        if (this.visivel && !this.destruido) {
            long var4 = System.currentTimeMillis();
            if (this.duracaoMaximaMs > 0L && var4 - this.tempoCriacao > this.duracaoMaximaMs) {
                this.setDestruido();
            } else {
                if (this.alvoPerseguicao != null && this.alvoPerseguicao.estaVivo() && this.fatorPerseguicao > (double)0.0F) {
                    double var6 = this.alvoPerseguicao.getPosicao().getX() + (double)this.alvoPerseguicao.getLargura() / (double)2.0F;
                    double var8 = this.alvoPerseguicao.getPosicao().getY() + (double)this.alvoPerseguicao.getAltura() / (double)2.0F;
                    double var10 = this.posicao.getX() + (double)this.largura / (double)2.0F;
                    double var12 = this.posicao.getY() + (double)this.altura / (double)2.0F;
                    double var14 = var6 - var10;
                    double var16 = var8 - var12;
                    double var18 = Math.atan2(var16, var14);
                    double var20 = Math.atan2(this.velocidadeY, this.velocidadeX);

                    double var22;
                    for(var22 = var18 - var20; var22 > Math.PI; var22 -= (Math.PI * 2D)) {
                    }

                    while(var22 < -Math.PI) {
                        var22 += (Math.PI * 2D);
                    }

                    double var24 = var20 + var22 * this.fatorPerseguicao;
                    this.velocidadeX = Math.cos(var24) * this.velocidadeProjetil;
                    this.velocidadeY = Math.sin(var24) * this.velocidadeProjetil;
                }

                double var26 = (double)var1 / (double)1.0E9F;
                this.posicao.setX(this.posicao.getX() + this.velocidadeX * var26);
                this.posicao.setY(this.posicao.getY() + this.velocidadeY * var26);
            }
        }
    }

    public void desenhar(Graphics var1, int var2, int var3) {
        if (this.visivel && !this.destruido) {
            if (this.imagemIconAtual != null) {
                super.desenhar(var1, var2, var3);
            } else {
                var1.setColor(this.doHeroi ? Color.CYAN : Color.RED);
                var1.fillRect((int)(this.posicao.getX() - (double)var2 - (double)4.0F), (int)(this.posicao.getY() - (double)var3 - (double)4.0F), 8, 8);
            }
        }

    }

    public boolean isDoHeroi() {
        return this.doHeroi;
    }

    public int getDano() {
        return this.dano;
    }

    public void setVelocidadeProjetil(double var1) {
        double var3 = Math.atan2(this.velocidadeY, this.velocidadeX);
        this.velocidadeProjetil = var1;
        this.velocidadeX = Math.cos(var3) * this.velocidadeProjetil;
        this.velocidadeY = Math.sin(var3) * this.velocidadeProjetil;
    }

    public void setDuracaoMaxima(int var1) {
        this.duracaoMaximaMs = (long)var1;
    }

    public void setPersegueAlvo(Heroi var1, double var2) {
        this.alvoPerseguicao = var1;
        this.fatorPerseguicao = var2;
    }

    public void setDestruido() {
        this.destruido = true;
        this.visivel = false;
    }

    public boolean isDestruido() {
        return this.destruido;
    }
}
