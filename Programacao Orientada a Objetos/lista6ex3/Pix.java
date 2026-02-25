/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex3;

/**
 *
 * @author camil
 */
public class Pix implements MetodoPagamento {
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void realizarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " no pix realizado");
    }

    public double getTaxaPagamento(){
        return 0.0;
    }
}
