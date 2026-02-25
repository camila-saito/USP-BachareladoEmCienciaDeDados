//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.itens;

import org.example.auxiliar.Posicao;
import org.example.entidades.Heroi;

import java.io.PrintStream;

public class ItemVida extends Item {
    private static final long serialVersionUID = 1L;
    private int quantidadeCura;

    public ItemVida(Posicao var1) {
        super("Item de Vida", var1, "/imagens/item_vida.png", TipoItem.VIDA, 32, 32);
        this.quantidadeCura = 25;
    }

    public ItemVida(Posicao var1, int var2) {
        super("Item de Vida Especifico", var1, "/imagens/item_vida.png", TipoItem.VIDA, 32, 32);
        this.quantidadeCura = var2;
    }

    public void aplicarEfeito(Heroi var1) {
        if (var1 != null && var1.estaVivo()) {
            var1.curar(this.quantidadeCura);
            PrintStream var10000 = System.out;
            String var10001 = var1.getNomeEntidade();
            var10000.println(var10001 + " curou " + this.quantidadeCura + " de vida. Vida atual: " + var1.getVidaAtual());
            this.setColetado(true);
        }

    }

    public int getQuantidadeCura() {
        return this.quantidadeCura;
    }
}
