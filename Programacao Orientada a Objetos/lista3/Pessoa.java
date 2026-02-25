/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista3;

/**
 *
 * @author camil
 */
public class Pessoa {
    private String nome;
    private int idade;
    private String ocupacao;

    public Pessoa(String nome, int idade, String ocupacao) {
        this.nome = nome;
        this.idade = idade;
        this.ocupacao = ocupacao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }
    
    public void exibirInfo() {
        System.out.println("Nome: " + nome + ", idade: " + idade + ", ocupacao: " + ocupacao + "\n");
    }
    
    public void envelhecer(int anos) { //qtos anos a pessoa envelheceu
        idade += anos;
    }
}
