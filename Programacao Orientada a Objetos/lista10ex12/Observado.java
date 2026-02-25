/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex12;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Observado {
    private ArrayList<Observador> obs = new ArrayList<>();

    public void addObservador(Observador o) {
        obs.add(o);
    }

    public void removeObservador(Observador o) {
        obs.remove(o);
    }

    public void atualizaObservador(StringBuilder s) {
        for(int i=0; i<obs.size(); i++) {
            obs.get(i).atualizar(s);
            System.out.println(s);
        }
    }
}
