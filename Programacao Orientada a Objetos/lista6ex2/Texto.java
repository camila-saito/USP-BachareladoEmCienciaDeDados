/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex2;

/**
 *
 * @author camil
 */
public class Texto extends Documento{
    private int nPalavras;

    public Texto(int nPalavras, String titulo, String autor, int tamanho) {
        super(titulo, autor, tamanho);
        this.nPalavras = nPalavras;
    }

    public int contarPalavras() {
        return nPalavras;
    }

    @Override
    public boolean abrir() {
        System.out.println("Abrindo o texto " + super.titulo);
        return true;
    }
    
    @Override
    public void formatar() {
        System.out.println("Formatando documento-texto...");
    }

    @Override
    public String toString() {
        return "Texto: '" + super.titulo + "', numero de palavras=" + nPalavras;
    }   
}
