/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex4;

/**
 *
 * @author camil
 */
public class NumeroComplexo { // z = a + bi
    private double real;
    private double imaginario;

    public NumeroComplexo(double real, double imaginario) {
        this.real = real;
        this.imaginario = imaginario;
    }

    public double getReal() {
        return real;
    }

    public double getImaginario() {
        return imaginario;
    }

    public double modulo() { //raiz de a²+b² -> a = real e b = imaginaria
        double a = Math.pow(real, 2);
        double b = Math.pow(imaginario, 2);
        double temp = a + b;
        return Math.sqrt(temp);
    }

    @Override
    public String toString() {
        if(imaginario >= 0d)
            return "z = " + real + " + " + imaginario + "i";
        else // parte imaginaria eh negativa
            return "z = " + real + " - " + (-1)*imaginario + "i"; 
    }

    public NumeroComplexo adicionar(NumeroComplexo z2) { // z1=a+bi e z2=c+di -> z1+z2 = (a-c) + (b-d)i
        NumeroComplexo resultado = new NumeroComplexo(real-z2.getReal(), imaginario-z2.getImaginario());
        return resultado;
    }

    public NumeroComplexo multiplicar(NumeroComplexo z2) { // z1=a+bi e z2=c+di -> z1*z2 = (ac-bd) + (ad-bc)i
        double r = real*z2.getReal() - imaginario*z2.getImaginario();
        double i = real*z2.getImaginario() - imaginario*getReal();
        NumeroComplexo resultado = new NumeroComplexo(r, i);
        return resultado;
    }
}
