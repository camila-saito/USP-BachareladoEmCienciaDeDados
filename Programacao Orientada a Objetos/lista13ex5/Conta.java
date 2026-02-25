/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex5;

/**
 *
 * @author camil
 */
public class Conta {
    private String titular;
    private double saldo;

    public Conta(String titular) {
        this.titular = titular;
        saldo = 0;
    }
        
    public void sacar(double valor) throws SaldoInsuficienteException{
        if(valor > saldo) {
            throw new SaldoInsuficienteException();
        }
        else {
            saldo = saldo - valor;
        }
    }
    
    public void depositar(double valor) {
        saldo += valor;
    }
}
