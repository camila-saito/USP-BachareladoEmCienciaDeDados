/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public class Diretor extends Aprovador {

    public Diretor(Aprovador prox) {
        super(prox);
    }

    public void aprovar(SolicitacaoCompra s) {
        System.out.println("Aprovado pelo Diretor");
    }
}
