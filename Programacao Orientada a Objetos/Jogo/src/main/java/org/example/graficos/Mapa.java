package org.example.graficos;

import org.example.auxiliar.Constantes;
import org.example.auxiliar.Posicao;
import org.example.entidades.Entidade; 
import org.example.entidades.Projetil;
import org.example.auxiliar.GerenciadorDeAudio;
import java.awt.Graphics;
import java.io.*;
import java.awt.Rectangle;

public class Mapa implements Serializable {
    private Tile[][] grelhaTiles;
    private int larguraEmTiles;
    private int alturaEmTiles;
    private int faseAtual;
    private String nomeArquivoMapa; //aicionado para referência


    private int offsetX;
    private int offsetY;
    private Rectangle areaArenaChefe; //adicionado para armazenar a área da arena do chefe

    public Mapa(String nomeArquivoMapaFase, int faseAtual) {
        this.offsetX = 0;
        this.offsetY = 0;
        this.faseAtual = faseAtual;
        this.nomeArquivoMapa = nomeArquivoMapaFase; //arzena o nome do arquivo
        carregarMapaDeArquivo(nomeArquivoMapaFase);
    }


    public String getNomeArquivoMapa() {
        return nomeArquivoMapa;
    }

    public int getNumeroFase() {
        return faseAtual;
    }

    public void setNumeroFase(int numeroFase) {
        this.faseAtual = numeroFase;
    }

    public void setAreaArenaChefe(Rectangle areaArenaChefe) {
        this.areaArenaChefe = areaArenaChefe;
    }

    public Rectangle getAreaArenaChefe() {
        return this.areaArenaChefe;
    }

    private void inicializarMapaPadraoVazio() {
        this.larguraEmTiles = 20; 
        this.alturaEmTiles = 15;
        this.grelhaTiles = new Tile[alturaEmTiles][larguraEmTiles];
        String tileChaoPadrao = Constantes.TILE_CHAO_FASE1;
        String tileParedePadrao = Constantes.TILE_PAREDE_FASE1;

        for (int i = 0; i < alturaEmTiles; i++) {
            for (int j = 0; j < larguraEmTiles; j++) {
                if (i == 0 || i == alturaEmTiles - 1 || j == 0 || j == larguraEmTiles - 1) {
                    grelhaTiles[i][j] = new Tile(tileParedePadrao, Tile.TipoTile.PAREDE_FASE1, false);
                } else {
                    grelhaTiles[i][j] = new Tile(tileChaoPadrao, Tile.TipoTile.CHAO_FASE1, true);
                }
            }
        }
    }

    public void carregarMapaDeArquivo(String nomeArquivo) {
        try {
            InputStream is = getClass().getResourceAsStream(Constantes.CAMINHO_MAPAS + nomeArquivo);
            if (is == null) {
                System.err.println("Não foi possível encontrar o arquivo de mapa: " + Constantes.CAMINHO_MAPAS + nomeArquivo);
                inicializarMapaPadraoVazio();
                return;
            }
            BufferedReader leitor = new BufferedReader(new InputStreamReader(is));

            String linhaDimensao = leitor.readLine();
            String[] dimensoes = linhaDimensao.split(" ");
            this.larguraEmTiles = Integer.parseInt(dimensoes[0]);
            this.alturaEmTiles = Integer.parseInt(dimensoes[1]);
            this.grelhaTiles = new Tile[this.alturaEmTiles][this.larguraEmTiles];

            String linha;
            for (int i = 0; i < this.alturaEmTiles; i++) {
                linha = leitor.readLine();
                if (linha == null) {
                    System.err.println("Arquivo de mapa incompleto: " + nomeArquivo);
                    inicializarMapaPadraoVazio();
                    return;
                }
                String[] valoresTile = linha.split(" ");
                for (int j = 0; j < this.larguraEmTiles; j++) {
                    if (j < valoresTile.length) {
                        int tipoId = Integer.parseInt(valoresTile[j]);
                        grelhaTiles[i][j] = criarTilePeloTipoId(tipoId, this.faseAtual);
                    } else {
                        grelhaTiles[i][j] = criarTilePeloTipoId(Constantes.TILE_ID_CHAO, this.faseAtual);
                    }
                }
            }
            leitor.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar o mapa do arquivo: " + nomeArquivo + " - " + e.getMessage());
            e.printStackTrace();
            inicializarMapaPadraoVazio();
        }
    }

    private Tile criarTilePeloTipoId(int tipoId, int fase) {
        String imagemChao = "";
        String imagemParede = "";
        Tile.TipoTile tipoTileChao = Tile.TipoTile.CHAO_FASE1;
        Tile.TipoTile tipoTileParede = Tile.TipoTile.PAREDE_FASE1;

        switch (fase) {
            case 1: imagemChao = Constantes.TILE_CHAO_FASE1; imagemParede = Constantes.TILE_PAREDE_FASE1; tipoTileChao = Tile.TipoTile.CHAO_FASE1; tipoTileParede = Tile.TipoTile.PAREDE_FASE1; break;
            case 2: imagemChao = Constantes.TILE_CHAO_FASE2; imagemParede = Constantes.TILE_PAREDE_FASE2; tipoTileChao = Tile.TipoTile.CHAO_FASE2; tipoTileParede = Tile.TipoTile.PAREDE_FASE2; break;
            case 3: imagemChao = Constantes.TILE_CHAO_FASE3; imagemParede = Constantes.TILE_PAREDE_FASE3; tipoTileChao = Tile.TipoTile.CHAO_FASE3; tipoTileParede = Tile.TipoTile.PAREDE_FASE3; break;
            case 4: imagemChao = Constantes.TILE_CHAO_FASE4; imagemParede = Constantes.TILE_PAREDE_FASE4; tipoTileChao = Tile.TipoTile.CHAO_FASE4; tipoTileParede = Tile.TipoTile.PAREDE_FASE4; break;
            case 5: imagemChao = Constantes.TILE_CHAO_FASE5; imagemParede = Constantes.TILE_PAREDE_FASE5; tipoTileChao = Tile.TipoTile.CHAO_FASE5; tipoTileParede = Tile.TipoTile.PAREDE_FASE5; break;
            default: imagemChao = Constantes.TILE_CHAO_FASE1; imagemParede = Constantes.TILE_PAREDE_FASE1; break;
        }

        switch (tipoId) {
            case Constantes.TILE_ID_CHAO: return new Tile(imagemChao, tipoTileChao, true);
            case Constantes.TILE_ID_PAREDE: return new Tile(imagemParede, tipoTileParede, false);
            default: return new Tile(imagemChao, tipoTileChao, true);
        }
    }

    public void desenhar(Graphics g) {
        for (int i = 0; i < alturaEmTiles; i++) {
            for (int j = 0; j < larguraEmTiles; j++) {
                if (grelhaTiles[i][j] != null) {
                    grelhaTiles[i][j].desenhar(g, j * Constantes.TAMANHO_TILE - offsetX, i * Constantes.TAMANHO_TILE - offsetY);
                }
            }
        }
    }



    public Tile getTile(int linha, int coluna) {
        if (linha >= 0 && linha < alturaEmTiles && coluna >= 0 && coluna < larguraEmTiles) {
            return grelhaTiles[linha][coluna];
        }
        return null;
    }

    public void setTile(int linha, int coluna, Tile tile) {
        if (linha >= 0 && linha < alturaEmTiles && coluna >= 0 && coluna < larguraEmTiles) {
            grelhaTiles[linha][coluna] = tile;
        }
    }

    public int getLarguraEmTiles() { return larguraEmTiles; }
    public int getAlturaEmTiles() { return alturaEmTiles; }
    public int getLarguraEmPixels() { return larguraEmTiles * Constantes.TAMANHO_TILE; }
    public int getAlturaEmPixels() { return alturaEmTiles * Constantes.TAMANHO_TILE; }

    public void setOffset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        int maxOffsetX = getLarguraEmPixels() - Constantes.LARGURA_TELA;
        int maxOffsetY = getAlturaEmPixels() - Constantes.ALTURA_TELA;
        if (this.offsetX < 0) this.offsetX = 0;
        if (this.offsetY < 0) this.offsetY = 0;
        if (this.offsetX > maxOffsetX && maxOffsetX > 0) this.offsetX = maxOffsetX;
        else if (maxOffsetX <= 0) this.offsetX = 0;
        if (this.offsetY > maxOffsetY && maxOffsetY > 0) this.offsetY = maxOffsetY;
        else if (maxOffsetY <= 0) this.offsetY = 0;
    }

    public int getOffsetX() { return offsetX; }
    public int getOffsetY() { return offsetY; }

    public void atualizarOffset(double xHeroi, double yHeroi, int larguraHeroi, int alturaHeroi) {
        int centroXHeroi = (int) (xHeroi + larguraHeroi / 2.0);
        int centroYHeroi = (int) (yHeroi + alturaHeroi / 2.0);
        int novoOffsetX = centroXHeroi - Constantes.LARGURA_TELA / 2;
        int novoOffsetY = centroYHeroi - Constantes.ALTURA_TELA / 2;
        setOffset(novoOffsetX, novoOffsetY);
    }
    
    public boolean ehTransponivelNaPosicaoPixel(double x, double y) {
        int coluna = (int) (x / Constantes.TAMANHO_TILE);
        int linha = (int) (y / Constantes.TAMANHO_TILE);
        Tile tile = getTile(linha, coluna);
        return tile != null && tile.isTransponivel();
    }

    public boolean ehAreaTransponivel(Rectangle area) {
        int tileEsquerda = (int) (area.getX() / Constantes.TAMANHO_TILE);
        int tileDireita = (int) ((area.getX() + area.getWidth() -1) / Constantes.TAMANHO_TILE);
        int tileTopo = (int) (area.getY() / Constantes.TAMANHO_TILE);
        int tileBaixo = (int) ((area.getY() + area.getHeight() -1) / Constantes.TAMANHO_TILE);

        for (int lin = tileTopo; lin <= tileBaixo; lin++) {
            for (int col = tileEsquerda; col <= tileDireita; col++) {
                Tile tile = getTile(lin, col);
                if (tile == null || !tile.isTransponivel()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean colideCom(Entidade entidade) {
        if (entidade == null) return false;
        return !ehAreaTransponivel(entidade.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(0, 0, getLarguraEmPixels(), getAlturaEmPixels());
    }

    public boolean projetilColideComBlocoDestrutivel(Projetil projetil) {
        if (projetil == null || projetil.isDestruido()) {
            return false;
        }

        Rectangle boundsProjetil = projetil.getBounds();
        
        Posicao[] pontosVerificacao = {
            new Posicao(boundsProjetil.getMinX(), boundsProjetil.getMinY()),
            new Posicao(boundsProjetil.getMaxX(), boundsProjetil.getMinY()),
            new Posicao(boundsProjetil.getMinX(), boundsProjetil.getMaxY()),
            new Posicao(boundsProjetil.getMaxX(), boundsProjetil.getMaxY()),
            new Posicao(boundsProjetil.getCenterX(), boundsProjetil.getCenterY())
        };

        for (Posicao ponto : pontosVerificacao) {
            int col = (int) (ponto.getX() / Constantes.TAMANHO_TILE);
            int lin = (int) (ponto.getY() / Constantes.TAMANHO_TILE);

            Tile tile = getTile(lin, col);

            if (tile != null && !tile.isTransponivel()) {
                setTile(lin, col, criarTilePeloTipoId(Constantes.TILE_ID_CHAO, this.faseAtual));
                GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/bloco_quebra.wav");
                projetil.setDestruido(); 
                return true;
            }
        }
        return false;
    }
}

