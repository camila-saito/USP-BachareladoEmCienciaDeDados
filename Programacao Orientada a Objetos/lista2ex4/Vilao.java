/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex4;

/**
 *
 * @author camil
 */
public class Vilao extends Personagem {
    private int anosDePrisao;

    public int getAnosDePrisao() {
        return anosDePrisao;
    }

    public void setAnosDePrisao(int anosDePrisao) {
        this.anosDePrisao = anosDePrisao;
    }

    public Vilao(int anosDePrisao, String nome, String nomeVidaReal, float vida) {
        super(nome, nomeVidaReal, vida);
        this.anosDePrisao = anosDePrisao;
    }
    
    
}
