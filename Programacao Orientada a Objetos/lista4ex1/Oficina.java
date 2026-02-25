/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex1;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Oficina {
    private ArrayList<Veiculo> veiculos;

    public Oficina() {
        veiculos = new ArrayList<Veiculo>();
    }
    
    public Veiculo proximo(int indice) {
        if(veiculos.get(indice) != null) {
            return veiculos.get(indice);
        }
        System.out.println("Nao ha mais veiculos!");
        return null;
    }
    
    public void manutencao(Veiculo v) {
        v.ajustar();
        v.limpar();
        
        if(v instanceof Automovel) {
            ((Automovel) v).trocarOleo();
        }
        
        v.listarVerificacoes();
    }
    
    public void addVeiculo(Veiculo v) {
        veiculos.add(v);
    }
}
