/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex1;

/**
 *
 * @author camil
 */
public class ClasseGenericaB <T extends Comparable> {
    private T a;
    private T b;

    public ClasseGenericaB(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getMax() {
        if(a.compareTo(b) > 0) // a>b
            return a;
        else 
            return b;
    }

    public T getMin() {
        if(a.compareTo(b) < 0) // a<b 
            return a;
        else 
            return b;
    }

    @Override
    public String toString() {
        return "Valores da classe: " + this.getMax() + ", " + this.getMin();
    }
}
