/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public class Gerente extends Aprovador {

    public Gerente(Aprovador prox) {
        super(prox);
    }

    public void aprovar(SolicitacaoCompra s) {
        if(s.getValor() <= 10000)
            System.out.println("Aprovado pelo gerente");
        else System.out.println("Nao aprovado pelo gerente");
    }
}
