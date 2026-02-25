/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex12;

/**
 *
 * @author camil
 */
public class Tamanho implements Observador {

    public void atualizar(StringBuilder s) {
        String aux = s + " Tamanho: " + s.length() + ".";
        s.setLength(0);
        s.append(aux);
    }
    
}
