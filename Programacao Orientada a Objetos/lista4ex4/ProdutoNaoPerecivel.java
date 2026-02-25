/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex4;

/**
 *
 * @author camil
 */
public class ProdutoNaoPerecivel extends Produto{
    private int garantia; //anos de garantia

    public ProdutoNaoPerecivel(int garantia, int codigo, float precoUnitario, String descricao, int qtdEstoque) {
        super(codigo, precoUnitario, descricao, qtdEstoque);
        this.garantia = garantia;
    }

    public int getGarantia() {
        return garantia;
    }
    
    
}
