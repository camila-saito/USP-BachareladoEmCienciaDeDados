/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista10ex9;

/**
 *
 * @author camil
 */
public class Lista10Ex9 {

    public static void main(String[] args) {
        DividaValor d = new DividaValor(50.0);
        System.out.println(d.getValorDivida());
        System.out.println(d.toString());

        Juros j = new Juros(d);
        System.out.println(j.toString());

        Desconto dc = new Desconto(j);
        System.out.println(dc.toString());

        Acrescimo a = new Acrescimo(dc);
        System.out.println(a.toString());
    }
}
