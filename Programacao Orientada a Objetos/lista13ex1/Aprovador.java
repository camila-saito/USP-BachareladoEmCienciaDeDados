/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public abstract class Aprovador {
    Aprovador prox;
    
    public Aprovador(Aprovador umProx) {
        prox = umProx;
    }
    
    public void processa(SolicitacaoCompra s) {
        aprovar(s);
        if(this.prox != null)
            this.prox.processa(s);
        System.out.println("Passou");
    }

    public abstract void aprovar(SolicitacaoCompra s);
}
