/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista8ex5;

/**
 *
 * @author camil
 */
public class Lista8Ex5 {

    public static void main(String[] args) {
        Integer lista[] = new Integer[5];
        lista[0] = 6; //new Integer(6);
        lista[1] = 10;
        lista[2] = 3;
        lista[3] = 3;
        lista[4] = 12;

        double media = Calculadora.media(lista);
        System.out.println(media);
    }
}
