/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista6ex3;

/**
 *
 * @author camil
 */
public class CartaoCredito implements MetodoPagamento {
    private String numeroCartao;
    private double limiteDisponivel;

    public CartaoCredito(String numeroCartao, double limiteDisponivel) {
        this.numeroCartao = numeroCartao;
        this.limiteDisponivel = limiteDisponivel;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void realizarPagamento(double valor) {
        if(valor <= limiteDisponivel) {
            limiteDisponivel -= valor;
            System.out.println("Pagamento de R$" + valor + " feito. Limite disponivel: R$" + limiteDisponivel);
        }
        else {
            System.out.println("Limite insuficiente");
        }
    }

    public double getTaxaPagamento(){
        return 0.10; //10% de taxa
    }
}
