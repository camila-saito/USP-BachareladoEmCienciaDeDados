/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public class SolicitacaoCompra {
    private double valor;
    private String descricao;

    public SolicitacaoCompra(double val, String desc) {
        valor = val;
        descricao = desc;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
