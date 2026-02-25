/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista10ex1;

/**
 *
 * @author camil
 */
public class Lista10Ex1 {

    public static void main(String[] args) {
        Vetor vetores = new Vetor(new float[] {1, 2, 0}, new float[] {3, 0, 1});
        vetores.operacaoVetorial(new Adicao());
        vetores.operacaoVetorial(new ProdutoEscalar());
        vetores.operacaoVetorial(new ProdutoVetorial());
    }
}
