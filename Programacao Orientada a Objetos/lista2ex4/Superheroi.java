/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex4;

/**
 *
 * @author camil
 */
public class Superheroi extends Personagem {
    private int nivelFama;

    public int getNivelFama() {
        return nivelFama;
    }

    public void setNivelFama(int nivelFama) {
        this.nivelFama = nivelFama;
    }

    public Superheroi(int nivelFama, String nome, String nomeVidaReal, float vida) {
        super(nome, nomeVidaReal, vida);
        this.nivelFama = nivelFama;
    }
    
    
}
