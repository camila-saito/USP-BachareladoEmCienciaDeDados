/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex11;
import java.lang.Math;

/**
 *
 * @author camil
 */
public class Classe1 extends ProcessaNumero {
    
    public Classe1(ProcessaNumero prox) {
        super(prox);
    }

    public void quadrado(double d) {
        if(d % 2 == 0)
            System.out.println("1. Quadrado de " + d + ": " + Math.pow(d, 2));
    }

    public void raiz(double d) {
        if(d % 2 == 0) {
            if(d < 0) 
                System.out.println("1. Sem raiz nos reais");
            else 
                System.out.println("1. Raiz quadrada de " + d + ": " + Math.sqrt(d));
        }
    }
}
