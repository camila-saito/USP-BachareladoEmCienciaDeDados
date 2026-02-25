/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista4ex4;
import java.util.*; //Date

/**
 *
 * @author camil
 */
public class Lista4Ex4 {

    public static void main(String[] args) {
        Date data1 = new Date(2025, 12, 27);
        Date data2 = new Date(2025, 04, 15);
        ProdutoPerecivel pp1 = new ProdutoPerecivel(data1, 1, 15f, "Enlatado", 30);
        ProdutoPerecivel pp2 = new ProdutoPerecivel(data2, 2, 10f, "Banana", 30);
        ProdutoPerecivelEspecial ppe = new ProdutoPerecivelEspecial(data1, 3, 17f, "Leite", 20);
        ProdutoNaoPerecivel pnp = new ProdutoNaoPerecivel(5, 4, 80f, "Cadeira", 10);
        
        Estoque e = new Estoque();
        e.cadastrarProduto(pp1);
        e.cadastrarProduto(pp2);
        e.cadastrarProduto(ppe);
        e.cadastrarProduto(pnp);
        
        System.out.println(e.consultarProduto(2).getDescricao());
        
        System.out.println(e.estoqueTotal());
        
        e.imprimir();
    }
}
