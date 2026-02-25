/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex1;

/**
 *
 * @author camil
 */
public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int qtd;

    public Produto(int id, String nome, double preco, int qtd) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public boolean vender(int qtdVendida) {
        if(qtdVendida <= qtd) {
            qtd -= qtdVendida;
            return true;
        }
        return false;
    }
    
    public void repor(int qtdReposta) {
        qtd += qtdReposta;
    }

    @Override
    public String toString() {
        return "Produto (" + id + "): nome=" + nome + ", preco=R$" + preco + ", qtd=" + qtd + '}';
    }
    
    
}
