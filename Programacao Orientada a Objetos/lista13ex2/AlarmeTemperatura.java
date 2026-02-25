/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex2;

/**
 *
 * @author camil
 */
public class AlarmeTemperatura implements ObservadorTemperatura {

    public void atualizar(double temp) {
        if(temp > 40.0) 
            System.out.println("ALERTA: temperatura acima de 40 graus Celsius.");
    }
    
}
