/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex3;

/**
 *
 * @author camil
 */
public class ABBGenerica <T extends Number> {
    private No<T> raiz;

    public ABBGenerica() {
        raiz = null;
    }

    public void inserir(T valor) {
        raiz = auxInserir(this.raiz, valor);
    }

    private No<T> auxInserir(No<T> r, T valor) {
        if(r == null) {
            return new No<T>(valor);
        }

        if(r.valor == valor) {
            return r;
        }

        if (valor.doubleValue() < r.valor.doubleValue())
            r.esq = auxInserir(r.esq, valor);
        else
            r.dir = auxInserir(r.dir, valor);

        return r;
    }

    public boolean busca(T valor) {
        if(auxBusca(this.raiz, valor) != null)
            return true;
        else return false; 
    }

    private No<T> auxBusca(No<T> r, T valor) {
        if(r == null || r.valor.equals(valor)) 
            return r;
        
        if(valor.doubleValue() < r.valor.doubleValue())
            return auxBusca(r.esq, valor);
        else return auxBusca(r.dir, valor);
    }

    public void imprimir() {
        auxImprimir(this.raiz);
        System.out.println();
    }

    private void auxImprimir(No<T> r) {
        if(r != null) {
            auxImprimir(r.esq);
            System.out.print(r.valor + " ");
            auxImprimir(r.dir);
        }
    }
}
