/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista2ex1;

/**
 *
 * @author camil
 */
public class Lista2Ex1 {
    public static void main(String[] args) {
        Polinomio p = new Polinomio(5);
        p.add(3.5d, 3); //Termo t1 = new Termo(3.5d, 3);
        p.add(2.4d, 4); //Termo t2 = new Termo(2.4d, 4);
        //P(x) = 3.5x^3 + 2.4x^4
        
        System.out.println(p.toString());
        
        double res = p.calcula(5.1d);
        System.out.println(res);
    }
}
