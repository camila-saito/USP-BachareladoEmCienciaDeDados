/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista13ex4;

/**
 *
 * @author camil
 */
public class Lista13Ex4 {

    public static void main(String[] args) {
        //colocar txt na pasta do projeto (C:\Users\camil\Documents\POO\Lista13Ex4)
        try {
            LeitorArquivo l1 = new LeitorArquivo("a.txt");
            Thread t1 = new Thread(l1);
            t1.start();
            t1.join();

            LeitorArquivo l2 = new LeitorArquivo("b.txt");
            Thread t2 = new Thread(l2);
            t2.start();
            t2.join();

            System.out.println(l1.getTotal());
            System.out.println(l2.getTotal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
