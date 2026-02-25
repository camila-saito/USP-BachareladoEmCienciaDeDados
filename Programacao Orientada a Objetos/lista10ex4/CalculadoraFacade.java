/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex4;

/**
 *
 * @author camil
 */
public class CalculadoraFacade {
    private Operacao operacao = new Operacao();
    
    private static double calcular(String operador, double opA, double opB) {
        //cria objeto do tipo adequado via factory Operacao operacao
        return operacao.calcular(opA, opB);
    }
    public double calcularExpressao(String expressao) {
        String[] elementos = expressao.trim().split(" ");
        calcular(elementos[1], (double)elementos[0], (double)elementos[2]);
    }
}
