/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex5;

/**
 *
 * @author camil
 */
public class TimeFutebol implements Comparavel{
    private String nome;
    private int nVitorias;
    private int nEmpates;
    private int pontos;

    public TimeFutebol(String nome, int vitorias, int empates) {
        this.nome = nome;
        nVitorias = vitorias;
        nEmpates = empates;
        pontos = nVitorias*3 + nEmpates;
    }

    public int getVitorias() {
        return nVitorias;
    }

    public int getEmpates() {
        return nEmpates;
    }

    public int getPontos() {
        return pontos;
    }

    public String toString() {
        return "Time " + nome + ": " + pontos + " pontos";
    }

    public int comparaCom(Object outroObjeto) {
        if(outroObjeto instanceof TimeFutebol) {
            TimeFutebol t = (TimeFutebol) outroObjeto;
            if(pontos > t.pontos) 
                return 1;
            else if(pontos == t.pontos)
                return 0;
            else
                return -1;
        }
        return 0;
    }
}
