/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex4;

/**
 *
 * @author camil
 */
public class MultiplicacaoComplexa implements OperacaoComplexa {


    public NumeroComplexo executar(NumeroComplexo a, NumeroComplexo b){
        NumeroComplexo resultado = a.multiplicar(b);
        return resultado;
    }
}
