/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex3;

/**
 *
 * @author camil
 */
public class No <T extends Number> {
    protected T valor;
    protected No<T> esq;
    protected No<T> dir;

    public No(T valor) {
        this.valor = valor;
        esq = null;
        dir = null;
    }
}
