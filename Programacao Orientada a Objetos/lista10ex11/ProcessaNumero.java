/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex11;

/**
 *
 * @author camil
 */
public abstract class ProcessaNumero {
    protected ProcessaNumero prox;

    public ProcessaNumero(ProcessaNumero umProx) {
        prox = umProx;
    }

    public void processa(double d) {
        quadrado(d);
        raiz(d);
        if(this.prox != null)
            this.prox.processa(d);
    }

    public abstract void quadrado(double d);
    public abstract void raiz(double d);
}
