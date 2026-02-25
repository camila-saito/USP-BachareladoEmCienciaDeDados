//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.auxiliar;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class GerenciadorDeAssets {
    private static Map<String, BufferedImage> imagensCarregadas = new HashMap();

    public static BufferedImage carregarImagem(String var0) {
        if (imagensCarregadas.containsKey(var0)) {
            return (BufferedImage)imagensCarregadas.get(var0);
        } else {
            try (InputStream var1 = GerenciadorDeAssets.class.getResourceAsStream(var0)) {
                if (var1 == null) {
                    System.err.println("Não foi possível encontrar o arquivo de imagem: " + var0);
                    return null;
                } else {
                    BufferedImage var2 = ImageIO.read(var1);
                    imagensCarregadas.put(var0, var2);
                    System.out.println("Imagem carregada: " + var0);
                    return var2;
                }
            } catch (IOException var6) {
                System.err.println("Erro ao carregar a imagem: " + var0);
                var6.printStackTrace();
                return null;
            }
        }
    }

    public static void limparCache() {
        imagensCarregadas.clear();
    }
}
