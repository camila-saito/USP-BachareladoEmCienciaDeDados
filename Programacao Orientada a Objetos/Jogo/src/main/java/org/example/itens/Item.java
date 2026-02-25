//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.itens;

import org.example.auxiliar.Posicao;
import org.example.entidades.Entidade;
import org.example.entidades.Heroi;
import org.example.graficos.Mapa;

public abstract class Item extends Entidade {
    private static final long serialVersionUID = 1L;
    private TipoItem tipoItem;
    private boolean coletado;

    public Item(String var1, Posicao var2, String var3, TipoItem var4, int var5, int var6) {
        super(var1, var2, var5, var6, var3, 1);
        this.tipoItem = var4;
        this.coletado = false;
        this.velocidade = (double)0.0F;
    }

    public abstract void aplicarEfeito(Heroi var1);

    public void atualizar(long var1, Mapa var3) {
        if (this.coletado) {
            this.setVisivel(false);
        }

    }

    public TipoItem getTipo() {
        return this.tipoItem;
    }

    public boolean isColetado() {
        return this.coletado;
    }

    public void setColetado(boolean var1) {
        this.coletado = var1;
        if (var1) {
            this.setVisivel(false);
        }

    }

    public static enum TipoItem {
        VIDA,
        VANTAGEM_ATAQUE_CHEFE,
        PONTUACAO;
    }
}
