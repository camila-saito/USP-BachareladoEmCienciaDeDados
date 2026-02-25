/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista10ex2;
import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author camil
 */
public class VogalCounterInputStream extends FilterInputStream{
    private int countVogais;
    private final HashSet<Character> sh =  new HashSet<>();
    
    public VogalCounterInputStream(InputStream umObjetoASerDecorado) {
        super(umObjetoASerDecorado);
        countVogais = 0;
        
        sh.add('a');
        sh.add('e');
        sh.add('i');
        sh.add('o');
        sh.add('u');
        sh.add('A');
        sh.add('E');
        sh.add('I');
        sh.add('O');
        sh.add('U');
    }
    
    /*("aeiouAEIOU").indexOf(charLido) == -1; -> se nao achar o indice na string, retorna -1 (nao tem a letra na string)
        OU
        HashSet<Char> sh = new HashSet<>(); sh.add('a'); if(sh.contains(charLido)) countVogais++;*/
    
    @Override
    public int read() throws IOException{
        char charLido = (char)in.read();
        
        //SE charLido IN (a,e,i,o,u,A,E,I,O,U) countVogais++;
        if(sh.contains(charLido))
            countVogais++;
        
        return (int)charLido;
    }
    
    public int getCountVogais() {
        return countVogais;
    }
}
