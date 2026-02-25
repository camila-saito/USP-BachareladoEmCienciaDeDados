/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista4ex3;

/**
 *
 * @author camil
 */
public class Gerente extends Funcionario{

    public Gerente(String nome, float salarioBase) {
        super(nome, salarioBase);
    }
    
    @Override
    public float calcularSalario() {
        return salarioBase + 2000;
    }
}
