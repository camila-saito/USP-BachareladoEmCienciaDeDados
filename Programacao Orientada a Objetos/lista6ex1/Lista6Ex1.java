/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista6ex1;

/**
 *
 * @author camil
 */
public class Lista6Ex1 {

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        estoque.adicionarProduto(new Produto(1, "Mouse Gamer", 120.0, 15));
        estoque.adicionarProduto(new Produto(2, "Teclado mecanico", 250.0, 10));
        estoque.adicionarProduto(new Produto(3, "Monitor LED", 900.0, 5));
        Produto produtoBuscado = estoque.buscarProdutoPorNome("Mouse Gamer");
        produtoBuscado.vender(3);
        produtoBuscado.repor(2);
        System.out.println(estoque);
    }
}
