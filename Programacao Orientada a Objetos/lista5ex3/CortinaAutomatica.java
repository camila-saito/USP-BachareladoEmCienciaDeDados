/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista5ex3;

/**
 *
 * @author camil
 */
public class CortinaAutomatica extends Dispositivo implements ControlavelPorVoz{
    public CortinaAutomatica(String nome) {
        super(nome);
    }
    
    @Override
    public void descricao() {
        System.out.println("Cortina: bloqueia a entrada de iluminacao externa de uma janela");
    }
    
    @Override
    public void executarComandoVoz(String comando) {
        if(null == comando) System.out.println("Comando invalido");
        else switch (comando) {
            case "abrir" -> {
                if(ligado == true)
                    System.out.println("Cortina abrindo...");
                else
                    System.out.println("Cortina desligada");
            }
            case "fechar" -> {
                if(ligado == true)
                    System.out.println("Cortina fechando...");
                else
                    System.out.println("Cortina desligada");
            }
            default -> System.out.println("Comando invalido");
        }
    }
    /*public void executarComandoVoz(String comando) {
        if("abrir".equals(comando)) {
            if(ligado == true)
                System.out.println("Cortina abrindo...");
            else 
                System.out.println("Cortina desligada");
        }
        else if("fechar".equals(comando)) {
            if(ligado == true)
                System.out.println("Cortina fechando...");
            else 
                System.out.println("Cortina desligada");
        }
        else
            System.out.println("Comando invalido");
    }*/
}
