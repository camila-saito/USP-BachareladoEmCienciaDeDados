/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex4;

/**
 *
 * @author camil
 */
public class Produto {
    protected int codigo;
    protected float precoUnitario;
    protected String descricao;
    protected int qtdEstoque;

    public Produto(int codigo, float precoUnitario, String descricao, int qtdEstoque) {
        this.codigo = codigo;
        this.precoUnitario = precoUnitario;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int retirarEstoque(int qtd) { //qtd = qtd q quer se retirar
        if(qtdEstoque >= qtd) {
            qtdEstoque -= qtd;
            return qtd; 
        }
        else {
            qtd = qtdEstoque;
            qtdEstoque = 0;
            return qtd; //retorna qtd q foi possivel retirar
        }
    }
    
    public boolean adicionarEstoque(int qtd) { //qtd = qtd q se quer add
        qtdEstoque += qtd;
        return true;
    }
    
    public void imprimir() {
        System.out.println("Produto " + codigo + ", " + descricao + ", custo de R$" + precoUnitario + ", quantidade " + qtdEstoque);
    }
}
