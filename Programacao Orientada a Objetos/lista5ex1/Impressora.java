/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex1;

/**
 *
 * @author camil
 */
public class Impressora extends Dispositivo{
    private int qtdCores;

    public Impressora() {
        super();
        qtdCores = 4;
    }

    public void reabastecerTinta(){
        System.out.println("Tinta reabastecida");
    }
}
