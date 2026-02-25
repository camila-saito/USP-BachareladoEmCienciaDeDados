/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex1;

import java.util.Arrays;

/**
 *
 * @author camil
 */
public class Estoque { //composicao -> produtos sao perdidos se perder o estoque
    private Produto produtos[];
    private int qtdAtual;
    final int MAXESTOQUE = 100;

    public Estoque() {
        produtos = new Produto[MAXESTOQUE];
        qtdAtual = 0;
    }
    
    public boolean adicionarProduto(Produto produto) {
        for(int i=0; i<qtdAtual; i++) {
            if(produto == produtos[i])
                return false;
        }
        produtos[qtdAtual] = produto;
        qtdAtual++;
        return true;
    }
    
    public boolean removerProdutoPorId(int id) {
        for(int i=0; i<qtdAtual; i++) {
            if(produtos[i].getId() == id) {
                qtdAtual -= 1; //diminui o 'topo' do estoque, mas ultimo elemento continua la; ex. tem 3 prod, diminui pra 2, mas o produtos[2] ainda existe
                for(int j=i; j<qtdAtual; j++) { //vai ate a qtdAtual depois de diminuida
                    produtos[j] = produtos[j+1]; //passa todos os produtos pra frente
                } //ultimo elemento duplicado; ex. produtos[2] existe, mas quando adicionar novo produto sera sobrescrito
                return true;
            }
        }
        return false;
    }
    
    public Produto buscarProdutoPorNome(String nome) {
        for(int i=0; i<qtdAtual; i++) {
            if(produtos[i].getNome().equals(nome)) { //produtos[i].getNome() == nome
                return produtos[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Estoque{" + "produtos=" + Arrays.toString(produtos) + ", qtdAtual=" + qtdAtual + '}';
    }
}
