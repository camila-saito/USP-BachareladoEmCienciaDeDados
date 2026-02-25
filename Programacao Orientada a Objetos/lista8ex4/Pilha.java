/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex4;

/**
 *
 * @author camil
 */
public class Pilha <T>{
    private Object[] p;
    private int tam;
    private int topo;

    public Pilha(int tam) {
        this.tam = tam;
        p = new Object[tam];
        topo = -1;
    }

    public int qtdElem() {
        return topo+1;
    }

    public boolean empilhar(T elem) { //empilhar
        if(topo < tam){
            p[++topo] = elem;
            return true;
        }
        else {
            return false;
        }
    }

    public T desempilhar() {
        if(topo == -1)
            return null;
        else {
            Object temp = p[topo];
            p[topo] = null;
            topo--;
            return (T)temp;
        }
    }

    public boolean estaVazia() {
        if(topo == -1)
            return true;
        else return false;
    }
}
