/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex3;

/**
 *
 * @author camil
 */
public class Vendedor extends Funcionario{
    private float vendas;
    private float percentualComissao;

    public Vendedor(float vendas, float percentualComissao, String nome, float salarioBase) {
        super(nome, salarioBase);
        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
    }
    
    @Override
    public float calcularSalario() {
        return super.calcularSalario() + (vendas * percentualComissao / 100);
    }
}
