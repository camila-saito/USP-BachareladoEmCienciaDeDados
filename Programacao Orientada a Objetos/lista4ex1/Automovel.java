/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex1;

/**
 *
 * @author camil
 */
public class Automovel extends Veiculo{
    private boolean oleo;

    public Automovel(int id) {
        super(id);
        oleo = true;
    }
    
    @Override
    public void listarVerificacoes() { 
        super.listarVerificacoes();
        System.out.print("Precisa trocar o oleo? ");
        if(oleo == true) System.out.println("Sim"); 
        else System.out.println("Nao");
    }
    
    public void trocarOleo() {
        if(oleo == true) {
            oleo = false;
            System.out.println("O oleo do seu veiculo foi trocado.");
        }
        else System.out.println("Seu veiculo nao precisa de troca de oleo.");
    }
}
