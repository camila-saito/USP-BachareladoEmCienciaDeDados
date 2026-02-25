/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex3;

/**
 *
 * @author camil
 */
public class No <T>{
    protected T info;
    protected No<T> prox;

    protected No(T info) {
        this.info = info;
        prox = null;
    }
}
