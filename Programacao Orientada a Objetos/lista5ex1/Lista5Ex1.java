/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista5ex1;

/**
 *
 * @author camil
 */
public class Lista5Ex1 {

    public static void main(String[] args) {
        Impressora i = new Impressora();
        Teclado t = new Teclado("mecanico");
        
        i.ligar();
        i.checarStatus();
        i.reabastecerTinta();
        i.desligar();
        
        t.ligar();
        t.calibrar();
        t.getTipo();
        t.desligar();
    }
}
