/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista13ex2;

/**
 *
 * @author camil
 */
public class Lista13Ex2 {

    public static void main(String[] args) {
        SensorTemperatura s = new SensorTemperatura();

        s.addObservador(new PainelPrincipal());
        s.addObservador(new AlarmeTemperatura());

        s.setTemperatura(30.0);
        s.setTemperatura(45.0);
    }
}
