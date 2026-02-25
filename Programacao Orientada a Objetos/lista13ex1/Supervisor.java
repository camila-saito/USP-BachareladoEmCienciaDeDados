/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public class Supervisor extends Aprovador {
    
    public Supervisor(Aprovador prox) {
        super(prox);
    }

    public void aprovar(SolicitacaoCompra s) {
        if(s.getValor() <= 1000)
            System.out.println("Aprovado pelo supervisor");
        else System.out.println("Nao aprovado pelo supervisor");
    }
}
