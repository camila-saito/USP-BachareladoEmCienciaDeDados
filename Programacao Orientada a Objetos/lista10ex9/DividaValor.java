/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public class DividaValor extends Divida {

    public DividaValor(double valor) {
        super.valor = valor;
    }

    public double getValorDivida() {
        return super.valor;
    }

    @Override 
    public String toString(){
        String v = String.format("%.2f", super.valor);
        return "Voce tem uma divida de R$" + v;
    }
}
