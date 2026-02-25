//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.auxiliar;

import org.example.entidades.Entidade;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GerenciadorDePersonagemCustom {
    private static final String NOME_ENTRADA_ZIP = "personagem.dat";

    public static boolean salvarPersonagemEmZip(Entidade var0, String var1) {
        if (var0 != null && var1 != null && !var1.isEmpty()) {
            try {
                boolean var6;
                try (
                        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                        ObjectOutputStream var3 = new ObjectOutputStream(var2);
                ) {
                    var3.writeObject(var0);
                    byte[] var4 = var2.toByteArray();

                    try (
                            FileOutputStream var5 = new FileOutputStream(var1);
                            ZipOutputStream var19 = new ZipOutputStream(var5);
                    ) {
                        ZipEntry var7 = new ZipEntry("personagem.dat");
                        var19.putNextEntry(var7);
                        var19.write(var4);
                        var19.closeEntry();
                        PrintStream var20 = System.out;
                        String var21 = var0.getNomeEntidade() != null ? var0.getNomeEntidade() : "Desconhecido";
                        var20.println("Personagem " + var21 + " salvo em: " + var1);
                        boolean var8 = true;
                        return var8;
                    } catch (IOException var15) {
                        System.err.println("Erro ao escrever o arquivo Zip: " + var1);
                        var15.printStackTrace();
                        var6 = false;
                    }
                }

                return var6;
            } catch (IOException var18) {
                PrintStream var10000 = System.err;
                String var10001 = var0.getNomeEntidade() != null ? var0.getNomeEntidade() : "Desconhecido";
                var10000.println("Erro ao serializar a entidade: " + var10001);
                var18.printStackTrace();
                return false;
            }
        } else {
            System.err.println("Erro: Entidade ou caminho do arquivo Zip é nulo/vazio.");
            return false;
        }
    }

    public static Entidade carregarPersonagemDeZip(String var0) {
        if (var0 != null && var0.toLowerCase().endsWith(".zip")) {
            try {
                Object var21;
                try (
                        FileInputStream var1 = new FileInputStream(var0);
                        ZipInputStream var2 = new ZipInputStream(var1);
                ) {
                    ZipEntry var3 = var2.getNextEntry();
                    if (var3 != null && var3.getName().equals("personagem.dat")) {
                        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
                        byte[] var5 = new byte[1024];

                        int var6;
                        while((var6 = var2.read(var5)) > 0) {
                            var4.write(var5, 0, var6);
                        }

                        var2.closeEntry();

                        try (
                                ByteArrayInputStream var7 = new ByteArrayInputStream(var4.toByteArray());
                                ObjectInputStream var22 = new ObjectInputStream(var7);
                        ) {
                            Entidade var9 = (Entidade)var22.readObject();
                            PrintStream var10000 = System.out;
                            String var10001 = var9.getNomeEntidade() != null ? var9.getNomeEntidade() : "Desconhecido";
                            var10000.println("Personagem " + var10001 + " carregado de: " + var0);
                            Entidade var10 = var9;
                            return var10;
                        } catch (ClassNotFoundException | ClassCastException | IOException var17) {
                            System.err.println("Erro ao deserializar a entidade do arquivo Zip: " + var0);
                            ((Exception)var17).printStackTrace();
                            Object var8 = null;
                            return (Entidade)var8;
                        }
                    }

                    System.err.println("Erro: Arquivo Zip não contém a entrada esperada 'personagem.dat': " + var0);
                    var21 = null;
                }

                return (Entidade)var21;
            } catch (IOException var20) {
                System.err.println("Erro ao ler o arquivo Zip: " + var0);
                var20.printStackTrace();
                return null;
            }
        } else {
            System.err.println("Erro: Caminho do arquivo Zip é inválido ou não é .zip: " + var0);
            return null;
        }
    }
}
