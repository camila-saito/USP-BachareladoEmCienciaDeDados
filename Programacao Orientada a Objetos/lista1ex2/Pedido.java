/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista1ex2;

/**
 *
 * @author camil
 */
public class Pedido {
    private int qtdAtual; //tamanho do array de itens ate o momento
    private Item itens[];
    final int MAXESTOQUE = 10;
    
    public Pedido() { //cria o array e inicializa ele com 0 itens
        itens = new Item[MAXESTOQUE];
        qtdAtual = 0;
    }

    public int getQtdAtual() {
        return qtdAtual;
    }

    public Item[] getItens() {
        return itens;
    }
    
    public boolean adicionarItem(Item item) {
        if(qtdAtual < MAXESTOQUE) {
            itens[qtdAtual] = item;
            qtdAtual++;
            return true;
        }
        else return false;
    }
    
    public double calcularTotal() {
        double total = 0;
        for(int i=0; i<qtdAtual; i++) {
            total = total + itens[i].getQtd()*(itens[i].getPrecoUnitario());
        }
        return total;
    }
    
    public void exibirPedido() {
        for(int i=0; i<qtdAtual; i++) {
            System.out.println("Item: " + itens[i].getNome() + "\n");
            System.out.println("Preco unitario: " + itens[i].getPrecoUnitario() + "\n");
            System.out.println("Quantidade: " + itens[i].getQtd() + "\n\n");
        }
        System.out.println("Total: R$" + calcularTotal() + "\n");
    }
}
