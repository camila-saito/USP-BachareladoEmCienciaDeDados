/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex4;

/**
 *
 * @author camil
 */

public class Superpoder {
    private int categoria;
    private String nome;
    private float intensidadeAtk;

    public int getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public float getIntensidadeAtk() {
        return intensidadeAtk;
    }
    
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIntensidadeAtk(float intensidadeAtk) {
        this.intensidadeAtk = intensidadeAtk;
    }
        
    public Superpoder(int categoria, String nome, float intensidadeAtk) {
        this.categoria = categoria;
        this.nome = nome;
        this.intensidadeAtk = intensidadeAtk;
    }
}
