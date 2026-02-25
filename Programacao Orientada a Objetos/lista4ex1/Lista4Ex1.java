/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista4ex1;

/**
 *
 * @author camil
 */
public class Lista4Ex1 {

    public static void main(String[] args) {
        Oficina o = new Oficina();
        Automovel a1 = new Automovel(11);
        Automovel a2 = new Automovel(22);
        Bicicleta b1 = new Bicicleta(33);
        Bicicleta b2 = new Bicicleta(44);
        
        Veiculo v1 = a1;
        o.addVeiculo(v1);
        Veiculo v2 = b1;
        o.addVeiculo(v2);
        Veiculo v3 = a2;
        o.addVeiculo(v3);
        Veiculo v4 = b2;
        o.addVeiculo(v4);
        
        Veiculo v;
        for(int i=0; i<4; i++) {
            v = o.proximo(i);
            o.manutencao(v);
        }
    }
}
