/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex1;

/**
 *
 * @author camil
 */

//float - usa alguns bites para falar onde esta a virgula; int -todos os bits sao para o numero

public class Termo {
    private double coeficiente;
    private int ordem;

    public Termo(double coeficiente, int ordem) { //construtor -> fazer atribuições necessarias
        this.coeficiente = coeficiente;
        this.ordem = ordem;
    }

    @Override
    public String toString() {
        return "Termo{" + "coeficiente=" + coeficiente + ", ordem=" + ordem + '}';
    }

    public double getCoeficiente() {
        return coeficiente;
    }

    public int getOrdem() {
        return ordem;
    }
    
    public double calcula(double x) {
        double temp = Math.pow(x, ordem); //pow - 'x' elevado a 'ordem'
        double finalCalculus = coeficiente*temp;
        return finalCalculus;
        //return coeficiente*Math.pow(x, ordem);
    }
}
