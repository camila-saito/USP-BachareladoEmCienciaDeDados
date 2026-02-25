/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista1ex1;

/**
 *
 * @author camil
 */
public class Produto {
    String nome;
    float preco;
    private int qtd; //private - faz a variavel nao ser acessivel
    final int MAXESTOQUE = 2200; //constante - valor fixo
    final int MINESTOQUE = 100;
    public Produto() { //contrutor 1 - construtores nao tem tipo de retorno
        System.out.println("Produto criado.");
    }
    public Produto(String umNome, float umPreco, int umQtd) { //construtor 2
        nome = umNome;
        preco = umPreco;
        qtd = umQtd;
    }
    public int getQtd() { //metodos de acesso: get e set
        return qtd;
    }
    public void print() { //metodo
        System.out.println("Nome: " + nome + "preco: " + preco + "qtd: " + qtd);
    }
    public void adicionar(int umaQtd) {
        if(qtd + umaQtd > MAXESTOQUE) {
            System.out.println("ERRO: estoque excedido.");
        }
        else {
            qtd += umaQtd;
        }
    }
    public void remover(int umaQtd) {
        if(qtd - umaQtd < MINESTOQUE) {
            System.out.println("ERRO: estoque insuficiente.");
        }
        else {
            qtd -= umaQtd;
        }
    }
    public String toString() { //printar de maneira legivel
        return "Nome: " + nome + "preco: " + preco + "qtd: " + qtd;
    }
}
