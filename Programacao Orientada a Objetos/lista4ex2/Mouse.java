/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex2;

/**
 *
 * @author camil
 */
public class Mouse extends Produto{
    private String tipo;

    public Mouse(String tipo, float preco, String descricao) {
        super(preco, descricao);
        this.tipo = tipo;
    }

    @Override
    public String getDescricao() {
        return "Descricao: " + descricao + ", tipo: " + tipo;
    }
    
    
}
