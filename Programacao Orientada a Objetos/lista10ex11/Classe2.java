/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex11;

/**
 *
 * @author camil
 */
public class Classe2 extends ProcessaNumero {
    
    public Classe2(ProcessaNumero prox) {
        super(prox);
    }

    public void quadrado(double d) {
        if(d % 3 == 0)
            System.out.println("2. Quadrado de " + d + ": " + Math.pow(d, 2));
    }

    public void raiz(double d) {
        if(d % 3 == 0) {
            if(d < 0) 
                System.out.println("2. Sem raiz nos reais");
            else 
                System.out.println("2. Raiz quadrada de " + d + ": " + Math.sqrt(d));
        }
    }
}

