//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entidades;

import org.example.auxiliar.GerenciadorDeAssets;
import org.example.auxiliar.Posicao;
import org.example.graficos.Mapa;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;

public abstract class Entidade implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Posicao posicao;
    protected final Posicao posicaoInicialOriginal;
    protected transient BufferedImage imagemBase;
    protected transient ImageIcon imagemIconAtual;
    protected String caminhoImagemBase;
    protected String nomeEntidade;
    protected boolean visivel;
    protected int largura;
    protected int altura;
    protected double velocidade;
    protected int vidaAtual;
    protected int vidaMaxima;

    public Entidade(String var1, Posicao var2, int var3, int var4, String var5, int var6) {
        this.nomeEntidade = var1;
        this.posicao = new Posicao(var2.getX(), var2.getY());
        this.posicaoInicialOriginal = new Posicao(var2.getX(), var2.getY());
        this.largura = var3;
        this.altura = var4;
        this.caminhoImagemBase = var5;
        this.visivel = true;
        this.vidaMaxima = var6;
        this.vidaAtual = var6;
        this.carregarImagemBase(this.caminhoImagemBase);
    }

    protected void carregarImagemBase(String var1) {
        if (var1 != null && !var1.isEmpty()) {
            this.imagemBase = GerenciadorDeAssets.carregarImagem(var1);
            if (this.imagemBase != null) {
                Image var2 = this.imagemBase.getScaledInstance(this.largura, this.altura, 4);
                this.imagemIconAtual = new ImageIcon(var2);
            } else {
                System.err.println("Falha ao carregar imagem base para entidade: " + var1 + " para " + this.nomeEntidade);
                this.imagemIconAtual = null;
                this.imagemBase = null;
            }
        } else {
            this.imagemBase = null;
            this.imagemIconAtual = null;
        }

    }

    public abstract void atualizar(long var1, Mapa var3);

    public void desenhar(Graphics var1, int var2, int var3) {
        if (this.visivel && this.imagemIconAtual != null) {
            var1.drawImage(this.imagemIconAtual.getImage(), (int)this.posicao.getX() - var2, (int)this.posicao.getY() - var3, (ImageObserver)null);
        } else if (this.visivel && this.imagemBase != null) {
            var1.drawImage(this.imagemBase, (int)this.posicao.getX() - var2, (int)this.posicao.getY() - var3, this.largura, this.altura, (ImageObserver)null);
        } else if (this.visivel) {
            var1.setColor(Color.GRAY);
            var1.fillRect((int)this.posicao.getX() - var2, (int)this.posicao.getY() - var3, this.largura, this.altura);
            var1.setColor(Color.BLACK);
            var1.drawString(this.nomeEntidade != null ? this.nomeEntidade.substring(0, Math.min(this.nomeEntidade.length(), 3)) : "???", (int)this.posicao.getX() - var2 + 5, (int)this.posicao.getY() - var3 + 15);
        }

    }

    public String getNomeEntidade() {
        return this.nomeEntidade;
    }

    public void setNomeEntidade(String var1) {
        this.nomeEntidade = var1;
    }

    public Posicao getPosicao() {
        return this.posicao;
    }

    public Posicao getPosicaoInicialOriginal() {
        return this.posicaoInicialOriginal;
    }

    public void setPosicao(Posicao var1) {
        this.posicao = var1;
    }

    public void setPosicao(double var1, double var3) {
        if (this.posicao == null) {
            this.posicao = new Posicao(var1, var3);
        } else {
            this.posicao.setPosicao(var1, var3);
        }

    }

    public boolean isVisivel() {
        return this.visivel;
    }

    public void setVisivel(boolean var1) {
        this.visivel = var1;
    }

    public ImageIcon getImagemIconAtual() {
        return this.imagemIconAtual;
    }

    public BufferedImage getImagemBase() {
        return this.imagemBase;
    }

    public int getLargura() {
        return this.largura;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getVidaAtual() {
        return this.vidaAtual;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public String getCaminhoImagemBase() {
        return this.caminhoImagemBase;
    }

    public void receberDano(int var1) {
        this.vidaAtual -= var1;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }

        System.out.println(this.nomeEntidade + " recebeu " + var1 + " de dano. Vida restante: " + this.vidaAtual);
    }

    public void curar(int var1) {
        this.vidaAtual += var1;
        if (this.vidaAtual > this.vidaMaxima) {
            this.vidaAtual = this.vidaMaxima;
        }

    }

    public void curarTotalmente() {
        this.vidaAtual = this.vidaMaxima;
    }

    public boolean estaVivo() {
        return this.vidaAtual > 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.posicao.getX(), (int)this.posicao.getY(), this.largura, this.altura);
    }

    private void writeObject(ObjectOutputStream var1) throws IOException {
        var1.defaultWriteObject();
    }

    private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        var1.defaultReadObject();
        if (this.caminhoImagemBase != null && !this.caminhoImagemBase.isEmpty()) {
            this.carregarImagemBase(this.caminhoImagemBase);
        } else {
            this.imagemBase = null;
            this.imagemIconAtual = null;
        }

    }
}
