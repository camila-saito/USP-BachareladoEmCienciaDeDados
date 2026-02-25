/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex2;

/**
 *
 * @author camil
 */
public class Produto {
    protected String nomeloja;
    protected float preco;
    protected String descricao;

    public Produto(float preco, String descricao) {
        nomeloja = "Americanas";
        this.preco = preco;
        this.descricao = descricao;
    }

    public String getNomeloja() {
        return nomeloja;
    }

    public float getPreco() {
        return preco;
    }

    public void setNomeloja(String nomeloja) {
        this.nomeloja = nomeloja;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
