/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista1ex2;

/**
 *
 * @author camil
 */
public class Lista1Ex2 {
    public static void main(String[] args) {
        Pedido  p = new Pedido();
        Item i1 = new Item("Teclado", 500d, 2);
        Item i2 = new Item("Mouse", 370d, 4);
        Item i3 = new Item("Monitor", 1000d, 3);
        
        p.adicionarItem(i1);
        p.adicionarItem(i2);
        p.adicionarItem(i3);
        
        p.exibirPedido();
    }
}
