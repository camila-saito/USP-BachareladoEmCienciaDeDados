/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex3;

/**
 *
 * @author camil
 */
public abstract class Dispositivo {
    protected String nome;
    protected boolean ligado;

    public Dispositivo(String nome) {
        this.nome = nome;
        ligado = false;
    }
    
    public void ligar() {
        if(ligado) {
            System.out.println("Dispositivo ja esta ligado");
        }
        else {
            ligado = true;
            System.out.println("Dispositivo ligado");
        }
    }
    
    public void desligar() {
        if(ligado) {
            ligado = false;
            System.out.println("Dispositivo desligado");
        }
        else 
            System.out.println("Dispositivo ja esta desligado");
    }
    
    public boolean estaLigado() {
        return ligado;
    }
    
    public void status() {
        System.out.print(nome + ": ");
        if(ligado) 
            System.out.println("ligado");
        else 
            System.out.println("desligado");
    }
    
    public abstract void descricao();
}
