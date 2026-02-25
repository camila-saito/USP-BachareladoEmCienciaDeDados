/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista13ex5;

/**
 *
 * @author camil
 */
public class Lista13Ex5 {

    public static void main(String[] args) {
        
        Conta c = new Conta("Jose Almeida");
        c.depositar(500.0);

        try {
            c.sacar(800);
        } catch(SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
}
