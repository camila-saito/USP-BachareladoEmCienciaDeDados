/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex1;

/**
 *
 * @author camil
 */
public class ProdutoVetorial implements OperacaoVetorial{
    public void calcula(Vetor v) { 
        /*v1 = (u1, u2, u3), v2 = (v1, v2, v3)
        u*v = (u2*v3 - u3*v2, u3*v1 - u1*v3, u1*v2 - u2*v1) */
        
        float[] res = new float[] {
            v.getVetor1()[1]*v.getVetor2()[2] - v.getVetor1()[2]*v.getVetor2()[1],
            v.getVetor1()[2]*v.getVetor2()[0] - v.getVetor1()[0]*v.getVetor2()[2],
            v.getVetor1()[0]*v.getVetor2()[2] - v.getVetor1()[1]*v.getVetor2()[1]
        };
        System.out.println("Produto vetorial: (" + res[0] + ", " + res[1] + ", " + res[2] + ")");
    }
}
