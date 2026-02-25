/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public class Acrescimo extends DividaDecorator {

    public Acrescimo(Divida aDividaASerDecorada) {
        super(aDividaASerDecorada);
        super.percentual = 10.0;
    }

    public double getValorDivida() {
        return dividaASerDecorada.getValorDivida() + super.percentual;
    }

    public double getPercentual() {
        return super.percentual;
    }

    @Override 
    public String toString(){
        return dividaASerDecorada.toString() + " com " + super.percentual + " de taxa";
    }
}
