/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista3;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Predio {
    private ArrayList<Apartamento> aptos;
    private int totalAndares;

    public Predio(int totalAndares, int qtdAptos) { //LEMBRAR: verificacao da qtd de aptos na main
        this.totalAndares = totalAndares;
        aptos = new ArrayList<Apartamento>();
    }
    
    public void addApto(Apartamento apto) {
        aptos.add(apto);
    }
    
    public boolean setMoradorDeAp(int numDoAp, Pessoa pMorador) {
        for(int i=0; i<aptos.get(numDoAp-1).moradores.size(); i++) {
            if(aptos.get(numDoAp-1).moradores.get(i).getNome().equals(pMorador.getNome())) 
                return false;
        }
        aptos.get(numDoAp-1).adicionarPessoa(pMorador);
        return true;
    }
}
