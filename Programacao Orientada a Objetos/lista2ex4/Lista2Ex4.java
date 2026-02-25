/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista2ex4;

/**
 *
 * @author camil
 */
public class Lista2Ex4 {

    public static void main(String[] args) {
        Superpoder p1 = new Superpoder(1, "Raio laser", 6.42f);
        Superpoder p2 = new Superpoder(2, "Gelo", 4.52f);
        
        Superheroi h = new Superheroi(8, "Incirvel", "Roberto", 50.0f);
        //h.addPoder(p1);
        Vilao v = new Vilao(15, "Geiser", "Carlos", 40.0f);
        //v.addPoder(p2);
        
        Personagem ph = h;
        Personagem pv = v;
        
        while(ph.getVida() > 0 || pv.getVida() > 0) {
            ph.atacar(p1.getIntensidadeAtk(), p1.getNome(), pv);
            pv.atacar(p2.getIntensidadeAtk(), p2.getNome(), ph);
        }
        
        if(ph.getVida() > 0) {
            System.out.println(ph.nome);
        }
        else {
            System.out.println(pv.nome);
        }
        System.out.println(" ganhou a batalha!\n");
    }
}
