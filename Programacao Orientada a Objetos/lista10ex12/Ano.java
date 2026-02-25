/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex12;
import java.time.LocalDate;

/**
 *
 * @author camil
 */
public class Ano implements Observador {
    
    public void atualizar(StringBuilder s) {
        String aux = s + " Ano: " + LocalDate.now().getYear() + ".";
        s.setLength(0);
        s.append(aux);
    }

}
