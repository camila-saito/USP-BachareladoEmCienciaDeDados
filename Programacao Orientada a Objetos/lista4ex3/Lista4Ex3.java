/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista4ex3;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Lista4Ex3 {

    public static void main(String[] args) {
        Funcionario f = new Funcionario("Alberto", 6000f);
        Estagiario e = new Estagiario("Beto", 3000f);
        Gerente g = new Gerente("Carlos", 6500f);
        Vendedor v = new Vendedor(1000f, 50.0f, "Danilo", 5000f);
        
        ArrayList<Funcionario> funcionarios;
        funcionarios = new ArrayList<Funcionario>();
        
        funcionarios.add(f);
        funcionarios.add(e);
        funcionarios.add(g);
        funcionarios.add(v);
        
        for(int i=0; i<funcionarios.size(); i++) {
            float temp;
            temp = funcionarios.get(i).calcularSalario();
            System.out.println(temp);
        }
    }
}
