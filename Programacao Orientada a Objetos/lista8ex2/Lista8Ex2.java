/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista8ex2;

/**
 *
 * @author camil
 */
public class Lista8Ex2 {

    public static void main(String[] args) {
        TimeFutebol a[] = new TimeFutebol[10];
        a[0] = new TimeFutebol(5);
        a[1] = new TimeFutebol(15);       
        a[2] = new TimeFutebol(30);       
        a[3] = new TimeFutebol(3);       
        a[4] = new TimeFutebol(13);       
        a[5] = new TimeFutebol(24);       
        a[6] = new TimeFutebol(7);       
        a[7] = new TimeFutebol(16);       
        a[8] = new TimeFutebol(15);       
        a[9] = new TimeFutebol(25);       

        for(int i=0; i<a.length; i++) {
            System.out.println(a[i].getPontos());
        }
        
        System.out.println("--------------------------------");

        InsertionSort<TimeFutebol> s = new InsertionSort<>();
        s.sort(a);
        for(int i=0; i<a.length; i++) {
            System.out.println(a[i].getPontos());
        }
    }
}
