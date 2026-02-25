/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex1;

/**
 *
 * @author camil
 */
public class Veiculo {
    protected int id;
    protected boolean ajustes;
    protected boolean limpeza;

    public Veiculo(int id) { //qnd 'veiculo' eh criado, ambas as instancias precisam de reparo
        this.id = id;
        ajustes = true;
        limpeza = true;
    }
    
    public void listarVerificacoes() {
        System.out.println("Veiculo: " + id);
        
        System.out.print("Precisa de ajustes? ");
        if(ajustes == true) System.out.println("Sim"); 
        else System.out.println("Nao");
        
        System.out.print("Precisa de limpeza? ");
        if(limpeza == true) System.out.println("Sim"); 
        else System.out.println("Nao");
    }
    
    public void ajustar() {
        if(ajustes == true) {
            ajustes = false;
            System.out.println("Seu veiculo foi ajustado");
        }
        else System.out.println("Seu veiculo nao precisa de ajustes");
    }
    
    public void limpar() {
        if(limpeza == true) {
            limpeza = false;
            System.out.println("Seu veiculo foi limpado");
        }
        else System.out.println("Seu veiculo nao precisa de limpeza");
    }
}
