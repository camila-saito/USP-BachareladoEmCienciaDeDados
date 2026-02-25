/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista2ex3;

/**
 *
 * @author camil
 */
public class Lista2Ex3 {

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Amanda", 25, 384);
        Usuario u2 = new Usuario("Ricardo", 28, 852);
        Data d = new Data(17, 3, 2025);
        Operacao op1 = new Operacao(2.5f, 1.8f, u1, d);
        Operacao op2 = new Operacao(5.1f, 4.3f, u2, d);
        
        float res1 = op1.calcula(1);        
        op1.exibirResultado();
        float res2 = op2.calcula(3);
        op2.exibirResultado();
        
        res1 = op1.calcula(2);        
        op1.exibirResultado();        
        res2 = op2.calcula(4);
        op2.exibirResultado();
    }
}
