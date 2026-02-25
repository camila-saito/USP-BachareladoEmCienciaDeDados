/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista3;
import java.util.Scanner;

/**
 *
 * @author camil
 */
public class Lista3 {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa("Rosana", 48, "Professora");
        Pessoa p2 = new Pessoa("Paulo", 25, "Pedreiro");
        
        p1.exibirInfo();
        p1.envelhecer(5);
        p2.envelhecer(3);
        p2.exibirInfo();
        
        /*Moradia m1 = new Moradia(6, "Rua das Bandeiras");
        Moradia m2 = new Moradia(7, "Rua das Nacoes");
        
        m1.adicionarPessoa(p1);
        m2.adicionarPessoa(p2);
        
        m1.exibirInfo();
        m2.exibirInfo();*/
        
        Apartamento a1 = new Apartamento(3, true, 4, "Rua Australia");
        Apartamento a2 = new Apartamento(2, true, 4, "Rua Australia");
        /*Casa c1 = new Casa("Madeira", 6, "Rua das Bandeiras");
        Casa c2 = new Casa("Ferro", 7, "Rua das Nacoes");
        
        Moradia m1 = a1;
        Moradia m2 = a2;
        Moradia m3 = c1;
        Moradia m4 = c2;
        
        Moradia[] moradias = new Moradia[4];
        moradias[0] = m1;
        moradias[1] = m2;
        moradias[2] = m3;
        moradias[3] = m4;*/
        
        Scanner scan = new Scanner(System.in);
        int qtdAptos;
        
        while(true) {
            System.out.print("Digite o numero de aptos (entre 2 e 10): ");
            if(scan.hasNextInt()) {
                qtdAptos = scan.nextInt();
                if(qtdAptos >= 2 && qtdAptos <= 10)
                    break; //sai do loop quando o num for valido
                else
                    System.out.println("Quantidade invalida");
            }
            else {
                System.out.println("Entrada invalida");
                scan.next();
            }
        }
        
        Predio p = new Predio(qtdAptos, 2);
        a1.adicionarPessoa(p1);
        a2.adicionarPessoa(p2);
        p.addApto(a1);
        p.addApto(a2);
    }
}
