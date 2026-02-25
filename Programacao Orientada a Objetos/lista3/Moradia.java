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
public class Moradia {
    protected int qtdComodos;
    private String endereco;
    protected ArrayList<Pessoa> moradores;

    public Moradia(int qtdComodos, String endereco) {
        this.qtdComodos = qtdComodos;
        this.endereco = endereco;
        moradores = new ArrayList<Pessoa>();
    }
    
    public void exibirInfo() {
        System.out.println("Numero de comodos: " + qtdComodos);
        System.out.println("Endereco: " + endereco);
        System.out.print("Moradores: ");
        for(int i=0; i<moradores.size(); i++) {
            System.out.print(moradores.get(i).getNome());
            if(i+1 != moradores.size()) {
                System.out.print(", ");
            }
        }
        System.out.print("\n");
    }
    
    public void adicionarPessoa(Pessoa p) {
        moradores.add(p);
    }
    
    public void removerPessoa(Pessoa p) {
        moradores.remove(p);
    }
    
    public float calculoCustosTotais(){
        return qtdComodos*100f;
    }
}
