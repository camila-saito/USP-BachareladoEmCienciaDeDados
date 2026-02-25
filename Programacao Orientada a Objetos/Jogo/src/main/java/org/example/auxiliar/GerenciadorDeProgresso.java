package org.example.auxiliar;

import org.example.graficos.Mapa;
import org.example.graficos.Tile;
import org.example.motor.MotorJogo;


import java.io.*;

public class GerenciadorDeProgresso {
    private static final String NOME_ARQUIVO_SALVAMENTO = "fase_salva.dat";

    public static void salvarMapa(Mapa mapa, int fase) {
        File arquivo = new File(NOME_ARQUIVO_SALVAMENTO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            mapa.setNumeroFase(fase);
            oos.writeObject(mapa);

            System.out.println("Fase salva com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static Mapa carregarMapa() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO_SALVAMENTO))) {
            System.out.println("Carregando fase...");
            Mapa mapaCarregado = (Mapa) ois.readObject();

            // Recarregar imagens
            for (int i = 0; i < mapaCarregado.getAlturaEmTiles(); i++) {
                for (int j = 0; j < mapaCarregado.getLarguraEmTiles(); j++) {
                    Tile tile = mapaCarregado.getTile(i, j);
                    if (tile != null && tile.getImagemIcon() == null) {
                        tile.setImagem(tile.getNomeArquivoImagemOriginal());
                    }
                }
            }
            System.out.println("Fase carregada com sucesso.");
            System.out.println("Número da fase salva: " + mapaCarregado.getNumeroFase());


            return mapaCarregado;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o mapa: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
