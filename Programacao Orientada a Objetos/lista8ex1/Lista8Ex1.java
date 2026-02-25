/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista8ex1;

/**
 *
 * @author camil
 */
public class Lista8Ex1 {

    public static void main(String[] args) {
        /*ClasseGenericaA c = new ClasseGenericaA<Double>(3.5, 2.9);

        System.out.println(c.getMax());
        System.out.println(c.getMin());
        System.out.println(c.toString());*/

        ClasseGenericaB g = new ClasseGenericaB<Comparable>(new Produto("Leite", 8.90), new Produto("Suco", 10.50));

        System.out.println(g.getMax());
        System.out.println(g.getMin());
        System.out.println(g.toString());
    }
}
