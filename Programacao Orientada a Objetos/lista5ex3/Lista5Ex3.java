/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista5ex3;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Lista5Ex3 {

    public static void main(String[] args) {
        ArrayList<Dispositivo> lista = new ArrayList<Dispositivo>();
        
        Lampada l = new Lampada("Lampada360");
        lista.add(l);
        CaixaSom cs = new CaixaSom("Caixa 1800");
        lista.add(cs);
        CortinaAutomatica ca = new CortinaAutomatica("Cortina Inteligente");
        lista.add(ca);
        
        for(int i=0; i<lista.size(); i++) {
            lista.get(i).ligar();
        }
        
        for(int i=0; i<lista.size(); i++) {
            if(lista.get(i) instanceof ControlavelPorVoz) {
                if(lista.get(i) instanceof CaixaSom caixaSom)
                    caixaSom.executarComandoVoz("tocar musica");
                else //CortinaAutomatica
                    ((CortinaAutomatica) lista.get(i)).executarComandoVoz("abrir");
            }
        }
        
        for(int i=0; i<lista.size(); i++) {
            lista.get(i).status();
        }
    }
}
