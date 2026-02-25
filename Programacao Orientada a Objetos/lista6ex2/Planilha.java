/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex2;

/**
 *
 * @author camil
 */
public class Planilha extends Documento{
    private int qtdCelulas;

    public Planilha(int qtdCelulas, String titulo, String autor, int tamanho) {
        super(titulo, autor, tamanho);
        this.qtdCelulas = qtdCelulas;
    }

    public int contarCelulas() {
        return qtdCelulas;
    }
    
    @Override
    public boolean abrir() {
        System.out.println("Abrindo a planilha " + super.titulo);
        return true;
    }
    
    @Override
    public void formatar() {
        System.out.println("Formatando planilha...");
    }
    
    @Override
    public String toString() {
        return "Texto: '" + super.titulo + "', numero de celulas=" + qtdCelulas;
    } 
}
