/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex3;

/**
 *
 * @author camil
 */
public class Funcionario {
    protected String nome;
    protected float salarioBase;

    public Funcionario(String nome, float salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }
    
    public float calcularSalario() {
        return salarioBase;
    }
}
