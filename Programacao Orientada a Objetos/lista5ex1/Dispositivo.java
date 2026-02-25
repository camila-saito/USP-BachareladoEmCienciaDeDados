/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex1;

/**
 *
 * @author camil
 */
public class Dispositivo {
    private boolean ligar;
    private int status;
    private boolean calibrar;

    public Dispositivo() {
        ligar = false;
        status = 0;
        calibrar = true;
    }
    
    public void ligar() {
        ligar = true;
        status = 1;
        System.out.println("O dispositivo foi ligado");
    }    
    
    public void desligar() {
        ligar = false;
        status = 0;
        System.out.println("O dispositivo foi desligado");
    } 
    
    public void checarStatus() {
        System.out.println("Status do dispositivo: " + status);
    }
    
    public void calibrar() {
        calibrar = true;
        System.out.println("O dispositivo foi calibrado");
    }
}
