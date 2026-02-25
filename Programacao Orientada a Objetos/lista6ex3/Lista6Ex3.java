/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista6ex3;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Lista6Ex3 {

    public static void main(String[] args) {
        ArrayList<MetodoPagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new CartaoCredito("1234567898765432", 1000));
        pagamentos.add(new Pix("usuario@pix.com"));
        pagamentos.add(new BoletoBancario(23793, "2025-04-10"));

        double valorCompra = 300d;
        double total = 0d;

        for(MetodoPagamento metodo : pagamentos) {
            metodo.realizarPagamento(valorCompra);
            total += (metodo.getTaxaPagamento()*valorCompra) + valorCompra;
        }

        System.out.printf("Custo total com taxas: R$%.2f%n", total);
    }
}
