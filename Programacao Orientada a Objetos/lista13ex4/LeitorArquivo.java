/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista13ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author camil
 */
public class LeitorArquivo implements Runnable{
    private String nomeArquivo;
    private int total;
    
    public LeitorArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        total = 0;
    }

    public int getTotal() {
        return total;
    }

    public void run() {
        //Código para contar as linhas de um arquivo: 
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) { 
            while (br.readLine() != null) 
                total++; 
        } catch (IOException e) { 
            System.out.println("Erro ao ler " + nomeArquivo); 
        } 
    }
}
