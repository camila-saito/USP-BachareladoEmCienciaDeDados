/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex2;

/**
 *
 * @author camil
 */
public class PainelPrincipal implements ObservadorTemperatura {
    
    public void atualizar(double temp) {
        System.out.println("Temperatura agora: " + temp + "°C.");
    }

}
