/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex5;

/**
 *
 * @author camil
 */
public class Calculadora {

    public static <T extends Number> double media(T[] lista) {
        double temp = 0;
        for(int i=0; i<lista.length; i++) {
            temp += lista[i].doubleValue();
        }
        return temp/lista.length;
    }

    public static <T extends Number> double soma(T[] lista) {
        double temp = 0;
        for(int i=0; i<lista.length; i++) {
            temp += lista[i].doubleValue();
        }
        return temp;
    }
}
