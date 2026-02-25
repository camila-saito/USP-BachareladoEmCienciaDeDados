/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex2;

/**
 *
 * @author camil
 */
public class Apresentacao extends Documento{
    private int nSlides;

    public Apresentacao(int nSlides, String titulo, String autor, int tamanho) {
        super(titulo, autor, tamanho);
        this.nSlides = nSlides;
    }

    public int contarSlides() {
        return nSlides;
    }
    
    @Override
    public boolean abrir() {
        System.out.println("Abrindo a apresentacao " + super.titulo);
        return true;
    }
    
    @Override
    public void formatar() {
        System.out.println("Formatando apresentacao...");
    }
    
    @Override
    public String toString() {
        return "Texto: '" + super.titulo + "', numero de slides=" + nSlides;
    } 
}
