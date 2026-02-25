/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex1;

/**
 *
 * @author camil
 */
public class Vetor {
    private float vetor1[];
    private float vetor2[];

    public Vetor(float[] vetor1, float[] vetor2) {
        this.vetor1 = vetor1;
        this.vetor2 = vetor2;
    }

    public float[] getVetor1() {
        return vetor1;
    }

    public float[] getVetor2() {
        return vetor2;
    }
    
    public void operacaoVetorial(OperacaoVetorial op) {
        op.calcula(this);
    }
}
