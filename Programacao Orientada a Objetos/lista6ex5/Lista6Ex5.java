/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista6ex5;

/**
 *
 * @author camil
 */
public class Lista6Ex5 {

    public static void bubbleSort(Object[] array) { //ordenar em ordem crescente
        int n = array.length;
        boolean trocou;
        do {
            trocou = false;
            for(int i=0; i<n-1; i++) {
                Comparavel atual = (Comparavel) array[i];
                Comparavel proximo = (Comparavel) array[i+1];
                if(atual.comparaCom(proximo) > 0) { // this eh maior que outroObjeto || atual eh maior que proximo
                    Object temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    trocou = true;
                }
            }
            n--;
        }while(trocou);
    }

    public static void main(String[] args) {
        Object times[] = new TimeFutebol[5];
        times[0] = new TimeFutebol("a", 5, 3);
        times[1] = new TimeFutebol("b", 3, 6);
        times[2] = new TimeFutebol("c", 7, 2);
        times[3] = new TimeFutebol("d", 2, 3);
        times[4] = new TimeFutebol("e", 4, 5);

        for(int i=0; i<times.length; i++) {
            System.out.println(times[i]);
        }

        bubbleSort(times);

        for(int i=0; i<times.length; i++) {
            System.out.println(times[i]);
        }
    }
}
