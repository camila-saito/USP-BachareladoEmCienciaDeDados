/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex3;

/**
 *
 * @author camil
 */
public class Lampada extends Dispositivo{

    public Lampada(String nome) {
        super(nome);
    }
    
    @Override
    public void descricao() {
        System.out.println("Lampada: emite luz quando ligada");
    }
}
