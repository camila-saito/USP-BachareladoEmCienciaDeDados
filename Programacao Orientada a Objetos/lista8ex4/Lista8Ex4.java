/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista8ex4;

/**
 *
 * @author camil
 */
public class Lista8Ex4 {

    public static void main(String[] args) {
        Pilha<String> p = new Pilha<>(10);
        p.empilhar("suica");
        p.empilhar("limonada ");
        p.empilhar("picole de ");
        p.empilhar("Eu adoro ");

        while(!p.estaVazia()) {
            System.out.println(p.desempilhar());
        }
    }
}
