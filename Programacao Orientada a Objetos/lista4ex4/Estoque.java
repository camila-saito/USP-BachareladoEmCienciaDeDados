/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex4;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Estoque {
    private ArrayList<Produto> estoque;

    public Estoque() {
        estoque = new ArrayList<Produto>();
    }
    
    public boolean cadastrarProduto(Produto p) {
        for(int i=0; i<estoque.size(); i++) {
            if(estoque.get(i) == p) {
                return false;
            }
        }
        estoque.add(p);
        return true;
    }
    
    public Produto consultarProduto(int cod) {
        for(int i=0; i<estoque.size(); i++) {
            if(estoque.get(i).getCodigo() == cod)
                return estoque.get(i);
        }
        System.out.println("Produto nao encontrado.");
        return null;
    }
    
    public boolean retirarProduto(Produto p) {
        for(int i=0; i<estoque.size(); i++) {
            if(estoque.get(i) == p) {
                estoque.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public float custoTotal() {
        float total = 0;
        for(int i=0; i<estoque.size(); i++) {
            total += (estoque.get(i).getPrecoUnitario()*estoque.get(i).qtdEstoque);
        }
        return total;
    }
    
    public void imprimir() {
        for(int i=0; i<estoque.size(); i++) {
            estoque.get(i).imprimir();
        }
    }
    
    public int estoqueTotal() {
        int total = 0;
        for(int i=0; i<estoque.size(); i++) {
            total+= estoque.get(i).getQtdEstoque();
        }
        return total;
    }
}
