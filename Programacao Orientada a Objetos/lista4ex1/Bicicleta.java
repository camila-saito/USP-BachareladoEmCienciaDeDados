/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex1;

/**
 *
 * @author camil
 */
public class Bicicleta extends Veiculo{
    private boolean ferrugem;

    public Bicicleta(int id) {
        super(id);
        ferrugem = true;
    }
    
    @Override
    public void listarVerificacoes() { 
        super.listarVerificacoes();
        System.out.print("Precisa desenferrujar? ");
        if(ferrugem == true) System.out.println("Sim"); 
        else System.out.println("Nao");
    }
    
    public void desenferrujar() {
        if(ferrugem == true) {
            ferrugem = false;
            System.out.println("Seu veiculo foi desenferrujado");
        }
        else System.out.println("Seu veiculo nao precisa ser desenferrujado");
    }
}
