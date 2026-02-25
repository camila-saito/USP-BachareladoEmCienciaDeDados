/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista8ex3;

/**
 *
 * @author camil
 */
public class Lista8Ex3 {

    public static void main(String[] args) {
        ABBGenerica<Integer> abb = new ABBGenerica<>();
        abb.inserir(6);
        abb.inserir(9);
        abb.inserir(3);
        abb.inserir(16);
        abb.inserir(2);
        abb.inserir(13);

        if(abb.busca(3))
            System.out.println("O 3 esta na arvore");
        else System.out.println("O 3 nao esta na arvore");

        if(abb.busca(8))
            System.out.println("O 8 esta na arvore");
        else System.out.println("O 8 nao esta na arvore");

        abb.imprimir();
    }
}
