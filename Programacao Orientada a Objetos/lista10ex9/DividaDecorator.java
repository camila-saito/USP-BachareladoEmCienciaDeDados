/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public abstract class DividaDecorator extends Divida{
    protected Divida dividaASerDecorada;
    protected double percentual;

    public DividaDecorator(Divida aDividaASerDecorada) {
        dividaASerDecorada = aDividaASerDecorada;
    }

    public abstract double getPercentual();
}
