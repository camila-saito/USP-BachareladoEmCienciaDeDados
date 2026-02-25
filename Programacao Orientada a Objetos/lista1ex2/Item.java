/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista1ex2;

/**
 *
 * @author camil
 */
public class Item {
    private String nome;
    private double precoUnitario;
    private int qtd;

    public Item(String nome, double precoUnitario, int qtd) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.qtd = qtd;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQtd() {
        return qtd;
    }
    
    
}
