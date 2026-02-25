/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex3;

/**
 *
 * @author camil
 */
public class PilhaGenerica <T>{
    private No<T> topo;

    public PilhaGenerica() {
        topo = null;
    }

    public void push(T elem) { //empilhar
        if(topo == null) {
            topo = new No<T>(elem);
        }
        else {
            No<T> temp = new No<T>(elem);
            temp.prox = topo;
            topo = temp;
        }
    }

    public T pop() { //desempilhar
        if(topo == null)
            return null;
        else {
            No<T> temp = topo;
            topo = topo.prox;
            return temp.info;
        }
    }

    public boolean estaVazia() {
        if(topo == null)
            return true;
        else return false;
    }
}
