/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista10ex12;

/**
 *
 * @author camil
 */
public class Lista10Ex12 {

    public static void main(String[] args) {
        Observado obj = new Observado();

        obj.addObservador(new Maiuscula());
        obj.addObservador(new Ano());
        obj.addObservador(new Tamanho());

        obj.atualizaObservador(new StringBuilder("Cansei de estudar para a prova."));

        obj.atualizaObservador(new StringBuilder("Adoro sorvete de leite"));
    }
}
