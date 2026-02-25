/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista3;

/**
 *
 * @author camil
 */
public class Apartamento extends Moradia{
    private int nApto;
    private boolean garagem;

    public Apartamento(int nApto, boolean garagem, int qtdComodos, String endereco) {
        super(qtdComodos, endereco);
        this.nApto = nApto;
        this.garagem = garagem;
    }
    
    public boolean temGaragem(boolean garagem){
        return garagem;
    }
    
    public float calculoCustosTotais() {
        float temp = qtdComodos*70f + nApto*15.5f;
        if(garagem) {
            temp *= 50f;
        }
        return temp;
    }
}
