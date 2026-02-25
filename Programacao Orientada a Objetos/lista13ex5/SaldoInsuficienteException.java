/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex5;

/**
 *
 * @author camil
 */
public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException() {
        super("ERRO: saldo insuficiente do cliente.");
    }
}
