/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public class Desconto extends DividaDecorator {

    public Desconto(Divida aDividaASerDecorada) {
        super(aDividaASerDecorada);
        super.percentual = 0.3;
    }

    public double getValorDivida() {
        return dividaASerDecorada.getValorDivida() - super.percentual * dividaASerDecorada.getValorDivida();
    }

    public double getPercentual() {
        return super.percentual;
    }

    @Override 
    public String toString(){
        return dividaASerDecorada.toString() + " com " + super.percentual + " de desconto";
    }
}
