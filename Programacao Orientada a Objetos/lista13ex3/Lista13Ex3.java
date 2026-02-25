/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista13ex3;

/**
 *
 * @author camil
 */
public class Lista13Ex3 {

    public static void main(String[] args) {
        PilhaGenerica<Integer> p = new PilhaGenerica<>();
        p.push(6);
        p.push(5);
        p.push(8);

        while(!p.estaVazia()) {
            System.out.println(p.pop());
        }
    }
}
