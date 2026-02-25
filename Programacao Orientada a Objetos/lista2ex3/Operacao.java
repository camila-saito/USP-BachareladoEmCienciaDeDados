/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex3;

/**
 *
 * @author camil
 */
public class Operacao {
    private float operando1, operando2;
    private int op;
    Usuario user;
    Data data;

    public Operacao(float operando1, float operando2, Usuario user, Data data) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.user = user;
        this.data = data;
    }
    
    public float calcula(int operacao) {
        if(operacao == 1 || operacao == 2 || operacao == 3 || operacao == 4) {
            op = operacao;
            switch(op) {
                case 1: return operando1 + operando2;
                case 2: return operando1 - operando2;
                case 3: return operando1 * operando2;
                case 4: return operando1 / operando2;
                default: System.out.println("Operacao invalida\n"); return 0;
            }
        }
        else {
            System.out.println("Operacao invalida\n"); 
            return 0;
        }
    }
    
    public void exibirResultado() {
        System.out.println("Usuario: " + user.getNome() + '(' + user.getCpf() + "), de " + user.getIdade() + "anos\n");
        System.out.println("Data: " + data.toString() + "\n");
        switch(op) {
            case 1: System.out.println("Adicao: " + operando1 + '+' + operando2 + '=' + calcula(1)); break;
            case 2: System.out.println("Subtracao: " + operando1 + '-' + operando2 + '=' + calcula(2)); break;
            case 3: System.out.println("Multiplicacao: " + operando1 + '*' + operando2 + '=' + calcula(3)); break;
            case 4: System.out.println("Divisao: " + operando1 + '/' + operando2 + '=' + calcula(4)); break;
            default: System.out.println("Operacao invalida"); break;
        }
        System.out.println("\n");
    }
}
