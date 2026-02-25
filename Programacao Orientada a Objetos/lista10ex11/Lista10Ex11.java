/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista10ex11;

/**
 *
 * @author camil
 */
public class Lista10Ex11 {

    public static void main(String[] args) {
        ProcessaNumero chain = new Classe1(new Classe2(new Classe3(null)));
        chain.processa(28);
        chain.processa(27);
        chain.processa(25);
    }
}
