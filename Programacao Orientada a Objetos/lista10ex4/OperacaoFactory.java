/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex4;

/**
 *
 * @author camil
 */
public class OperacaoFactory {
    public static Operacao criar(String operador) {
        return switch(operador) {
            case "+" -> new Soma();
            case "-" -> new Subtracao();
            case "*" -> new Multiplicacao();
            case "/" -> new Divisao();
            default -> null;
        };
    }
}
