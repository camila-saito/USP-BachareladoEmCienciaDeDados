/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.entidades.personagemcustom;

import org.example.auxiliar.Posicao;
import org.example.auxiliar.GerenciadorDePersonagemCustom;
import org.example.entidades.inimigos.Bot;
import org.example.motor.MotorJogo;
import org.example.graficos.TelaJogo;

/**
 *
 * @author camil
 */
public class CriacaoPersonagem {

    public static void main(String[] args) {
        
        ExploradorEstelar explorador = new ExploradorEstelar("Explorador", new Posicao(2, 2), "Ajudantes", 5);
        
        if(GerenciadorDePersonagemCustom.salvarPersonagemEmZip(explorador, "personagem.zip"))
            System.out.println("Personagem criado com sucesso.");
        else
            System.out.println("Erro ao criar personagem");
        
    }
}
