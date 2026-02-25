/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista2ex4;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Personagem {
    protected String nome;
    protected String nomeVidaReal;
    private ArrayList<Superpoder> poderes;
    private float vida;

    public String getNome() {
        return nome;
    }

    public String getNomeVidaReal() {
        return nomeVidaReal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeVidaReal(String nomeVidaReal) {
        this.nomeVidaReal = nomeVidaReal;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public Personagem(String nome, String nomeVidaReal, float vida) {
        this.nome = nome;
        this.nomeVidaReal = nomeVidaReal;
        this.vida = vida;
    }
    
    public void addPoder(Superpoder poder) {
        poderes.add(poder);
    }
    
    public void atacar(float intATK, String nomePoder, Personagem inimigo) {
        if(Math.random() <= 0.5) {
            inimigo.vida -= intATK;
            System.out.println(inimigo.nome + " recebeu " + nomePoder + "(" + intATK + " de dano).\n");
        }
        else System.out.println("Ataque anulado!\n");
    }
}
