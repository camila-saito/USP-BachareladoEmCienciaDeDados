/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex1;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Polinomio {
    private int grauMax;
    private ArrayList<Termo> termos;

    public Polinomio(int grauMax) {
        this.grauMax = grauMax;
        termos = new ArrayList<Termo>();
    }
    
    public void add(double coef, int ord) { //adiciona um novo termo ao polinomio
        Termo temp = new Termo(coef, ord); //criacao de um termo
        termos.add(temp);
    }

    @Override
    public String toString() {
        Termo temp = null;
        String sPol = "";
        for(int i=0; i<termos.size(); i++) {
            temp = termos.get(i);
            sPol += temp.toString() + "\n"; //vai concatenando as strings
        }
        return sPol;
    }
    
    public double calcula(double x) {
        double resultado = 0d;
        Termo temp = null;
        for(int i=0; i<termos.size(); i++) {
            temp = termos.get(i);
            resultado += temp.calcula(x);
        }
        return resultado;
    }
}
