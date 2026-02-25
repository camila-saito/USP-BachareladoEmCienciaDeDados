/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex4;
import java.util.Date;

/**
 *
 * @author camil
 */
public class ProdutoPerecivelEspecial extends ProdutoPerecivel{

    public ProdutoPerecivelEspecial(Date validade, int codigo, float precoUnitario, String descricao, int qtdEstoque) {
        super(validade, codigo, precoUnitario, descricao, qtdEstoque);
    }
    
    @Override
    public void imprimir() {
        System.out.println("Produto " + codigo + ", " + descricao + ", quantidade " + qtdEstoque + ", valido ate " + validade);
    }
}
