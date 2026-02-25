/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista13ex1;

/**
 *
 * @author camil
 */
public class Lista13Ex1 {

    public static void main(String[] args) {
        Aprovador cadeia = new Supervisor(new Gerente(new Diretor(null))); // Supervisor -> Gerente -> Diretor
        cadeia.processa(new SolicitacaoCompra(500.0, "Patinete")); //Supervisor aprova
        cadeia.processa(new SolicitacaoCompra(8000, "Celular")); //Gerente aprova
        cadeia.processa(new SolicitacaoCompra(13000, "TV Plasma")); //Diretor aprova
    }
}
