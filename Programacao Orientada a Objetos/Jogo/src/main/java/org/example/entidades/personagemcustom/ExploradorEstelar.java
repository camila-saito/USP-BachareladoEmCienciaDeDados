//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.entidades.personagemcustom;

import org.example.auxiliar.Posicao;
import org.example.entidades.Entidade;
import org.example.graficos.Mapa;

public class ExploradorEstelar extends Entidade {
    private static final long serialVersionUID = 1L;
    private String faccao;
    private int nivelHabilidade;

    public ExploradorEstelar(String var1, Posicao var2, String var3, int var4) {
        super(var1, var2, 32, 32, "/imagens/personagens/personagem_explorador_estelar.png", 120);
        this.faccao = var3;
        this.nivelHabilidade = var4;
    }

    public String getFaccao() {
        return this.faccao;
    }

    public void setFaccao(String var1) {
        this.faccao = var1;
    }

    public int getNivelHabilidade() {
        return this.nivelHabilidade;
    }

    public void setNivelHabilidade(int var1) {
        this.nivelHabilidade = var1;
    }

    public void atualizar(long var1, Mapa var3) {
    }
}
