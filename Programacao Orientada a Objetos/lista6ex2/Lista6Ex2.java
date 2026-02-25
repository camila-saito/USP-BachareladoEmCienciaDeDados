/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista6ex2;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Lista6Ex2 {

    public static void main(String[] args) {
        ArrayList<Documento> biblioteca = new ArrayList<>();
        biblioteca.add(new Texto(1500, "Relatorio Anual", "Carlos Silva", 250));
        biblioteca.add(new Planilha(500, "Financas", "Maria Souza", 120));
        biblioteca.add(new Apresentacao(15, "Pitch Vendas", "Joao Pereira", 340));
        
        for(Documento doc : biblioteca) { //for-each -> (tipo elemento : colecao) - cada item da colecao sera armazenado em elemento a cada iteracao
            doc.abrir();
            doc.formatar();
            System.out.println(doc);
        }
    }
}
