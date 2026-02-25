//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.itens;

import org.example.auxiliar.GerenciadorDeAudio;
import org.example.auxiliar.Posicao;
import org.example.entidades.Heroi;

import java.io.PrintStream;

public class ItemVantagem extends Item {
    private static final long serialVersionUID = 1L;
    private int duracaoEfeitoMs;

    public ItemVantagem(Posicao var1, int var2) {
        super("Item Quebra-Blocos", var1, "/imagens/item_vantagem.png", TipoItem.PONTUACAO, 32, 32);
        this.duracaoEfeitoMs = var2;
    }

    public void aplicarEfeito(Heroi var1) {
        if (var1 != null && var1.estaVivo()) {
            PrintStream var10000 = System.out;
            String var10001 = var1.getNomeEntidade();
            var10000.println(var10001 + " coletou Item Quebra-Blocos! Pode quebrar blocos por " + this.duracaoEfeitoMs / 1000 + "s.");
            var1.ativarPoderQuebraBlocos(this.duracaoEfeitoMs);
            this.setColetado(true);
            GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/item_vantagem_coleta.wav");
        }

    }

    public int getDuracaoEfeitoMs() {
        return this.duracaoEfeitoMs;
    }
}
