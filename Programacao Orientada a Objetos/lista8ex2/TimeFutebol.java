/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex2;

/**
 *
 * @author camil
 */
public class TimeFutebol implements Comparable{
    private int pontos;

    public TimeFutebol(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    @Override
    public int compareTo(Object o) { //this > o
        TimeFutebol t = (TimeFutebol) o;
        if(this.pontos > t.pontos)
            return 1;
        else if(this.pontos < t.pontos)
            return -1;
        return 0;
    }
    
}
