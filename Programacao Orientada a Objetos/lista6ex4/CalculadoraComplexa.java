/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex4;

/**
 *
 * @author camil
 */
public class CalculadoraComplexa {
    private OperacaoComplexa operacao;

    public CalculadoraComplexa() {
        operacao = null;
    }

    public void setOperacao(OperacaoComplexa op) {
        operacao = op;
    }

    public NumeroComplexo calcular(NumeroComplexo a, NumeroComplexo b){
        NumeroComplexo resultado = operacao.executar(a, b);
        return resultado;
    }
}
