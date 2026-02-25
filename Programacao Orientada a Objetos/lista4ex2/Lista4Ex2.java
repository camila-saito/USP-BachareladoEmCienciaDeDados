/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista4ex2;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Lista4Ex2 {

    public static void main(String[] args) {
        ArrayList<Produto> carrinho;
        carrinho = new ArrayList<Produto>();
        
        Mouse m1 = new Mouse("Optico", 750f, "Saida USB, 1600dpi");
        Mouse m2 = new Mouse("Optico", 1000f, "Saida USB, 1800dpi");
        Mouse m3 = new Mouse("Optico", 890.99f, "Saida Bluetooth, 1600dpi");
        Livro l1 = new Livro("Machado de Assis", 25f, "Quincas Borba, 5a Edicao, 2015");
        Livro l2 = new Livro("Conceicao Evaristo", 30f, "Olhos d'agua, 4a Edicao, 2016");
        Livro l3 = new Livro("Tomas Antonio Gonzaga", 28f, "Marilia de Dirceu, 7a Edicao, 2020");
        
        carrinho.add(m1);
        carrinho.add(l1);
        carrinho.add(m2);
        carrinho.add(m3);
        carrinho.add(l2);
        carrinho.add(l3);
        
        for(int i=0; i<carrinho.size(); i++) {
            String temp;
            temp = carrinho.get(i).getDescricao();
            System.out.println(temp);
        }
    }
}
