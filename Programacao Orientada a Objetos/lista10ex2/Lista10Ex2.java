/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lista10ex2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author camil
 */
public class Lista10Ex2 {
    
    /*VogalCounterInputStream vc = new VogalCounterInputStream(new ZipInputStream("file.zip"));
    VogalCounterInputStream vc = new VogalCounterInputStream(System.in); //BufferedReader*/

    public static void main(String[] args) {
        try {
            System.out.println("=== Exemplo 1 ===");

            String zipFilePath = "C:\\Users\\camil\\Downloads\\a.txt.zip";
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zis.getNextEntry();
            if (entry != null) {
                System.out.println("Lendo arquivo dentro do ZIP: " + entry.getName());

                VogalCounterInputStream vcis = new VogalCounterInputStream(zis);
                int byteLido;
                while ((byteLido = vcis.read()) != -1) {
                    System.out.print((char) byteLido);
                }
                System.out.println("\nTotal de vogais lidas no ZIP: " + vcis.getCountVogais());
            } else {
                System.out.println("Nenhum arquivo encontrado no ZIP.");
            }
            zis.closeEntry();


            System.out.println("\n=== Exemplo 2 ===");

            VogalCounterInputStream vcis = new VogalCounterInputStream(System.in);
            int byteLido;
            while ((byteLido = vcis.read()) != -1) {
                    System.out.print((char) byteLido);
            }

            System.out.println("\nTotal de vogais lidas do teclado: " + vcis.getCountVogais());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
