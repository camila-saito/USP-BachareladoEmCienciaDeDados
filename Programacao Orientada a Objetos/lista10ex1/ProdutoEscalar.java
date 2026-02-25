/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex1;

/**
 *
 * @author camil
 */
public class ProdutoEscalar implements OperacaoVetorial{
    public void calcula(Vetor v) {
        float res = v.getVetor1()[0]*v.getVetor2()[0] + v.getVetor1()[1]*v.getVetor2()[1] + v.getVetor1()[2]*v.getVetor2()[2];
        System.out.println("Produto escalar: " + res);
    }
}
