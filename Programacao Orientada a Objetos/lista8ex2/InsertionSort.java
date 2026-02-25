/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista8ex2;

/**
 *
 * @author camil
 * @param <T>
 */
public class InsertionSort <T extends Comparable> {
    public void sort(T a[]) {
        for(int j=1; j<a.length; j++) {
            T temp = a[j];
            int i = j-1;
            while(i >= 0 && (a[i].compareTo(temp) > 0)) { //a[i]>temp
                a[i+1] = a[i];
                i--;
            }
            a[i+1] = temp;
        }
    }
}
