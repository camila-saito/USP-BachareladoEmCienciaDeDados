//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.graficos;

import org.example.auxiliar.GerenciadorDeAssets;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Tile implements Serializable {
    private transient ImageIcon imagemIcon;
    private transient BufferedImage imagemBruta;
    private boolean transponivel;
    private TipoTile tipoTile;
    private String nomeArquivoImagemOriginal;

    public Tile(String var1, TipoTile var2, boolean var3) {
        this.tipoTile = var2;
        this.transponivel = var3;
        this.nomeArquivoImagemOriginal = var1;
        this.carregarEConfigurarImagem(var1);
    }

    public Tile(TipoTile var1, boolean var2) {
        this.tipoTile = var1;
        this.transponivel = var2;
        this.imagemBruta = null;
        this.imagemIcon = null;
        this.nomeArquivoImagemOriginal = null;
    }

    private void carregarEConfigurarImagem(String var1) {
        this.imagemBruta = GerenciadorDeAssets.carregarImagem(var1);
        if (this.imagemBruta != null) {
            Image var2 = this.imagemBruta.getScaledInstance(32, 32, 4);
            this.imagemIcon = new ImageIcon(var2);
        } else {
            this.imagemIcon = null;
            System.err.println("Falha ao carregar imagem para o tile: " + var1);
        }

    }

    public void desenhar(Graphics var1, int var2, int var3) {
        if (this.imagemIcon != null) {
            var1.drawImage(this.imagemIcon.getImage(), var2, var3, (ImageObserver)null);
        }

    }

    public boolean isTransponivel() {
        return this.transponivel;
    }

    public void setTransponivel(boolean var1) {
        this.transponivel = var1;
    }

    public TipoTile getTipoTile() {
        return this.tipoTile;
    }

    public void setTipoTile(TipoTile var1) {
        this.tipoTile = var1;
    }

    public ImageIcon getImagemIcon() {
        return this.imagemIcon;
    }

    public BufferedImage getImagemBruta() {
        return this.imagemBruta;
    }

    public void setImagem(String var1) {
        this.nomeArquivoImagemOriginal = var1;
        this.carregarEConfigurarImagem(var1);
    }
    public String getNomeArquivoImagemOriginal() {
        return nomeArquivoImagemOriginal;
    }


    public static enum TipoTile {
        CHAO_FASE1,
        PAREDE_FASE1,
        CHAO_FASE2,
        PAREDE_FASE2,
        CHAO_FASE3,
        PAREDE_FASE3,
        CHAO_FASE4,
        PAREDE_FASE4,
        CHAO_FASE5,
        PAREDE_FASE5,
        ITEM_VIDA,
        ITEM_VANTAGEM,
        PORTAL_PROXIMA_FASE,
        VAZIO;
    }
}
