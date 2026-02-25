/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista1ex1;

/**
 *
 * @author camil
 */
public class Lista1Ex1 {
    public static void main(String[] args) {
        System.out.println("Bom Dia");
        Produto p1 = new Produto();
        System.out.println(p1);
        System.out.println(p1.preco);
        System.out.println(p1.nome);
        System.out.println(p1.getQtd());
        p1.print();
        
        System.out.println("----------------------------------");
        Produto p2 = new Produto("TV", 3000.0f, 2000);
        System.out.println(p2);
        System.out.println(p2.preco);
        System.out.println(p2.nome);
        System.out.println(p2.getQtd());
        p2.print();
        
        System.out.println("----------------------------------");
        p2.adicionar(100);
        p2.remover(50);
        
        System.out.println("----------------------------------");
        p2.adicionar(1000);
        p2.remover(2000);
        p2.print();
    }
}
