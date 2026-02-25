/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex1;

/**
 *
 * @author camil
 */
public class Produto implements Comparable {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public int compareTo(Object o) { // this.compareTo(o) (this > o)
        Produto p = (Produto) o;
        if(this.preco > p.preco) 
            return 1;
        else if(this.preco < p.preco)
            return -1;
        else 
            return 0;
    }

    @Override
    public String toString() {
        return nome + " (R$" + preco + ")";
    }
}
