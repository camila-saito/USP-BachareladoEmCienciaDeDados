/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex3;

/**
 *
 * @author camil
 */
public class CaixaSom extends Dispositivo implements ControlavelPorVoz{

    public CaixaSom(String nome) {
        super(nome);
    }
    
    @Override
    public void descricao() {
        System.out.println("Caixa de som: emite som do dispositivo conectado em um volume maior");
    }
    
    @Override
    public void executarComandoVoz(String comando) {
        if("tocar musica".equals(comando)) {
            if(ligado == true)
                System.out.println("Musica tocando...");
            else 
                System.out.println("Caixa desligada");
        }
        else
            System.out.println("Comando invalido");
    }
}
