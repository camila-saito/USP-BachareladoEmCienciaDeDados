/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public class Juros extends DividaDecorator{
    
    public Juros(Divida aDividaASerDecorada) {
        super(aDividaASerDecorada);
        super.percentual = 0.15;
    }

    public double getValorDivida() {
        return dividaASerDecorada.getValorDivida() + super.percentual * dividaASerDecorada.getValorDivida();
    }

    public double getPercentual() {
        return super.percentual;
    }

    @Override 
    public String toString(){
        return dividaASerDecorada.toString() + " mais " + super.percentual + " de juros";
    }
}
