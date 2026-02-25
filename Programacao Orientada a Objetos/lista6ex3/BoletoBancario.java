/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex3;

/**
 *
 * @author camil
 */
public class BoletoBancario implements MetodoPagamento{
    private int codigoBarra;
    private String dataVencimento;

    public BoletoBancario(int codigoBarra, String dataVencimento) {
        this.codigoBarra = codigoBarra;
        this.dataVencimento = dataVencimento;
    }

    public int getCodigoBarra() {
        return codigoBarra;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void realizarPagamento(double valor){
        System.out.println("Pagamento de R$" + valor + " registrado");
    }

    public double getTaxaPagamento() {
        return 0.15;
    }
}
