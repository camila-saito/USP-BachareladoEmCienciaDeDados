/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex2;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class SensorTemperatura { //obj observado
    private ArrayList<ObservadorTemperatura> observadores = new ArrayList<>();

    public void addObservador(ObservadorTemperatura o) {
        observadores.add(o);
    }

    public void removeObservador(ObservadorTemperatura o) {
        observadores.remove(o);
    }

    public void setTemperatura(double temp) {
        for(int i=0; i<observadores.size(); i++) {
            observadores.get(i).atualizar(temp);
        }
    }
}
