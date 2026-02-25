//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.auxiliar;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;

public class GerenciadorDeAudio {
    private static Map<String, Clip> musicasCarregadas = new HashMap();
    private static Map<String, Clip> efeitosSonorosCarregados = new HashMap();
    private static Clip musicaAtual = null;

    private static Clip carregarAudio(String var0, boolean var1) {
        try {
            URL var2 = GerenciadorDeAudio.class.getResource(var0);
            if (var2 == null) {
                System.err.println("Não foi possível encontrar o arquivo de áudio: " + var0);
                return null;
            } else {
                AudioInputStream var3 = AudioSystem.getAudioInputStream(var2);
                Clip var4 = AudioSystem.getClip();
                var4.open(var3);
                if (var1) {
                    musicasCarregadas.put(var0, var4);
                } else {
                    efeitosSonorosCarregados.put(var0, var4);
                }

                System.out.println("Áudio carregado: " + var0);
                return var4;
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var5) {
            System.err.println("Erro ao carregar o áudio: " + var0);
            ((Exception)var5).printStackTrace();
            return null;
        }
    }

    public static void tocarMusica(String var0, boolean var1) {
        pararMusica();
        Clip var2 = (Clip)musicasCarregadas.get(var0);
        if (var2 == null) {
            var2 = carregarAudio(var0, true);
        }

        if (var2 != null) {
            musicaAtual = var2;
            var2.setFramePosition(0);
            if (var1) {
                var2.loop(-1);
            } else {
                var2.start();
            }
        }

    }

    public static void pararMusica() {
        if (musicaAtual != null && musicaAtual.isRunning()) {
            musicaAtual.stop();
        }

        musicaAtual = null;
    }

    public static void tocarEfeitoSonoro(String var0) {
        Clip var1 = (Clip)efeitosSonorosCarregados.get(var0);
        if (var1 == null) {
            var1 = carregarAudio(var0, false);
        }

        if (var1 != null) {
            try {
                URL var2 = GerenciadorDeAudio.class.getResource(var0);
                AudioInputStream var3 = AudioSystem.getAudioInputStream(var2);
                Clip var4 = AudioSystem.getClip();
                var4.open(var3);
                var4.start();
                var4.addLineListener((var0x) -> {
                    if (var0x.getType() == Type.STOP) {
                        var0x.getLine().close();
                    }

                });
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var5) {
                System.err.println("Erro ao tocar efeito sonoro (nova instância): " + var0);
                ((Exception)var5).printStackTrace();
            }
        }

    }

    public static void preCarregarAudios() {
        System.out.println("Pré-carregamento de áudios iniciado (implementação pendente).");
    }

    public static void limparCacheAudio() {
        pararMusica();

        for(Clip var1 : musicasCarregadas.values()) {
            var1.close();
        }

        musicasCarregadas.clear();

        for(Clip var3 : efeitosSonorosCarregados.values()) {
            var3.close();
        }

        efeitosSonorosCarregados.clear();
        System.out.println("Cache de áudio limpo.");
    }
}
