/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex1;

/**
 *
 * @author camil
 */
public class ClasseGenericaA <T extends Number> {
    private T a;
    private T b;

    public ClasseGenericaA(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getMax() {
        if(a.doubleValue() > b.doubleValue()) 
            return a;
        else 
            return b;
    }

    public T getMin() {
        if(a.doubleValue() < b.doubleValue()) 
            return a;
        else 
            return b;
    }

    @Override
    public String toString() {
        return "Valores da classe: " + this.getMax() + ", " + this.getMin();
    }
}
