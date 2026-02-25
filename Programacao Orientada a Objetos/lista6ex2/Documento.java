/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex2;

/**
 *
 * @author camil
 */
public abstract class Documento {
    protected String titulo;
    protected String autor;
    protected int tamanho;

    public Documento(String titulo, String autor, int tamanho) {
        this.titulo = titulo;
        this.autor = autor;
        this.tamanho = tamanho;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public abstract boolean abrir();
    
    public abstract void formatar();
}
