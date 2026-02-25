//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.auxiliar;

import java.io.Serializable;

public class Posicao implements Serializable {
    private int linha;
    private int coluna;
    private double x;
    private double y;

    public Posicao(int var1, int var2) {
        this.setPosicao(var1, var2);
    }

    public Posicao(double var1, double var3) {
        this.x = var1;
        this.y = var3;
    }

    public boolean setPosicao(int var1, int var2) {
        if (var1 >= 0 && var2 >= 0) {
            this.linha = var1;
            this.coluna = var2;
            this.x = (double)(var2 * 32);
            this.y = (double)(var1 * 32);
            return true;
        } else {
            return false;
        }
    }

    public boolean setPosicao(double var1, double var3) {
        this.x = var1;
        this.y = var3;
        this.linha = (int)(var3 / (double)32.0F);
        this.coluna = (int)(var1 / (double)32.0F);
        return true;
    }

    public int getLinha() {
        return this.linha;
    }

    public void setLinha(int var1) {
        this.linha = var1;
        this.y = (double)(var1 * 32);
    }

    public int getColuna() {
        return this.coluna;
    }

    public void setColuna(int var1) {
        this.coluna = var1;
        this.x = (double)(var1 * 32);
    }

    public double getX() {
        return this.x;
    }

    public void setX(double var1) {
        this.x = var1;
        this.coluna = (int)(var1 / (double)32.0F);
    }

    public double getY() {
        return this.y;
    }

    public void setY(double var1) {
        this.y = var1;
        this.linha = (int)(var1 / (double)32.0F);
    }

    public boolean moveUp() {
        return this.setPosicao(this.linha - 1, this.coluna);
    }

    public boolean moveDown() {
        return this.setPosicao(this.linha + 1, this.coluna);
    }

    public boolean moveRight() {
        return this.setPosicao(this.linha, this.coluna + 1);
    }

    public boolean moveLeft() {
        return this.setPosicao(this.linha, this.coluna - 1);
    }

    public boolean ehIgual(Posicao var1) {
        return this.linha == var1.getLinha() && this.coluna == var1.getColuna();
    }

    public double distanciaAte(Posicao var1) {
        double var2 = this.x - var1.getX();
        double var4 = this.y - var1.getY();
        return Math.sqrt(var2 * var2 + var4 * var4);
    }
}
