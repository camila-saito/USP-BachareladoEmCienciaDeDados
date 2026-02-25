/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex2;

/**
 *
 * @author camil
 */
public class Livro extends Produto{
    private String autor;

    public Livro(String autor, float preco, String descricao) {
        super(preco, descricao);
        this.autor = autor;
    }
    
    @Override
    public String getDescricao() {
        return "Descricao: " + descricao + ", autor: " + autor;
    }
}
