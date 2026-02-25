package org.example.motor;

import org.example.auxiliar.Constantes;
import org.example.auxiliar.Posicao;
import org.example.entidades.*;
import org.example.graficos.Mapa;
import org.example.graficos.TelaJogo;
import org.example.itens.*;
import org.example.core.Fase; // Importa a nova classe Fase

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.awt.Rectangle;
import org.example.auxiliar.GerenciadorDeAudio;

public class MotorJogo implements Runnable, MotorJogoListener {

    private TelaJogo telaJogo;
    private Heroi heroi;
    private Fase faseCorrente;

    private List<Projetil> projeteis;
    private List<Entidade> entidadesCustomizadas;

    private boolean jogoRodando = false;
    private boolean jogoPausado = false;
    private Thread threadJogo;
    private Random random = new Random();

    private int faseAtualNumero = 1;
    private final int TOTAL_FASES = 5;
    private boolean transicionandoFase = false;
    private boolean gameOver = false;
    private boolean jogoConcluido = false;

    private Rectangle areaArenaChefe;

    private final int FPS_ALVO = 60;
    private final long TEMPO_POR_FRAME_ALVO = 1000000000 / FPS_ALVO;

    public MotorJogo(TelaJogo tela) {
        this.telaJogo = tela;
        this.projeteis = new ArrayList<>();
        this.entidadesCustomizadas = new ArrayList<>();
    }

    public synchronized void iniciar() {
        if (jogoRodando) return;
        jogoRodando = true;
        gameOver = false;
        jogoConcluido = false;
        faseAtualNumero = 1;
        threadJogo = new Thread(this);
        carregarFase(faseAtualNumero);
        threadJogo.start();
        System.out.println("[MotorJogo] Jogo iniciado.");
    }

    public synchronized void parar() {
        if (!jogoRodando) return;
        jogoRodando = false;
        try {
            threadJogo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("[MotorJogo] Jogo parado.");
    }

    private void carregarFase(int numeroFase) {
        GerenciadorDeAudio.pararMusica();
        System.out.println("[MotorJogo] Carregando Fase: " + numeroFase);
        this.faseAtualNumero = numeroFase;
        this.transicionandoFase = true;
        this.projeteis.clear();
        this.entidadesCustomizadas.clear();

        String nomeArquivoMapa = "mapa_fase" + numeroFase + "_tematico.txt";
        Mapa mapaDaFase = new Mapa(nomeArquivoMapa, numeroFase);
        this.telaJogo.setMapaAtual(mapaDaFase);

        int larguraMapaEmPixels = mapaDaFase.getLarguraEmPixels();
        int alturaMapaEmPixels = mapaDaFase.getAlturaEmPixels();
        int larguraArena = larguraMapaEmPixels / 3;
        int xArena = larguraMapaEmPixels - larguraArena;
        this.areaArenaChefe = new Rectangle(xArena, 0, larguraArena, alturaMapaEmPixels);
        mapaDaFase.setAreaArenaChefe(this.areaArenaChefe);

        System.out.println("[MotorJogo] Arena do Chefe definida em: x=" + xArena + ", y=0, w=" + larguraArena + ", h=" + alturaMapaEmPixels);

        Posicao posInicialHeroi = new Posicao(Constantes.TAMANHO_TILE * 1.5, Constantes.TAMANHO_TILE * 1.5);
        if (!mapaDaFase.ehTransponivelNaPosicaoPixel(posInicialHeroi.getX(), posInicialHeroi.getY())) {
            System.err.println("[MotorJogo] Posição inicial padrão do herói não é transponível! Procurando alternativa...");
            posInicialHeroi = encontrarPosicaoSpawnValidaAleatoriaForaDaArena(mapaDaFase);
            if (posInicialHeroi == null) {
                posInicialHeroi = new Posicao(Constantes.TAMANHO_TILE * 0.5, Constantes.TAMANHO_TILE * 0.5);
                System.err.println("[MotorJogo] Falha crítica ao encontrar posição inicial para o herói. Usando (0.5,0.5) como fallback.");
            }
        }

        if (this.heroi == null) {
            this.heroi = new Heroi("Herói Principal", posInicialHeroi, 100, 10);
        } else {
            this.heroi.setPosicao(posInicialHeroi.getX(), posInicialHeroi.getY());
            this.heroi.curarTotalmente();
            this.heroi.resetarPontuacaoFase();
        }

        this.faseCorrente = new Fase(numeroFase, this.heroi, mapaDaFase, this);
        
        GerenciadorDeAudio.tocarMusica("/audio/musicas/fase" + numeroFase + ".wav", true);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            this.transicionandoFase = false;
            System.out.println("[MotorJogo] Fase " + numeroFase + " iniciada!");
        }).start();
    }

    private Posicao encontrarPosicaoSpawnValidaAleatoriaForaDaArena(Mapa mapa) {
        int tentativas = 0;
        while (tentativas < 100) {
            int col = random.nextInt(mapa.getLarguraEmTiles());
            int lin = random.nextInt(mapa.getAlturaEmTiles());
            double x = col * Constantes.TAMANHO_TILE + Constantes.TAMANHO_TILE / 2.0;
            double y = lin * Constantes.TAMANHO_TILE + Constantes.TAMANHO_TILE / 2.0;

            Rectangle tileRect = new Rectangle((int)x, (int)y, 1, 1);
            if (mapa.ehTransponivelNaPosicaoPixel(x, y) && (areaArenaChefe == null || !areaArenaChefe.intersects(tileRect))) {
                return new Posicao(x, y);
            }
            tentativas++;
        }
        return null;
    }

    public void adicionarEntidadeCustomizada(Entidade entidade) {
        if (entidade != null) {
            this.entidadesCustomizadas.add(entidade);
            if (entidade instanceof Chefe) {
                ((Chefe) entidade).setMotorJogoListener(this);
            }
            System.out.println("[MotorJogo] Entidade customizada '" + entidade.getNomeEntidade() + "' adicionada.");
        }
    }

    @Override
    public void run() {
        long ultimoTempoUpdate = System.nanoTime();
        long acumuladorTempo = 0;

        while (jogoRodando) {
            long agora = System.nanoTime();
            long deltaTimeNano = agora - ultimoTempoUpdate;
            ultimoTempoUpdate = agora;
            acumuladorTempo += deltaTimeNano;

            if (!jogoPausado && !transicionandoFase && !gameOver && !jogoConcluido) {
                while (acumuladorTempo >= TEMPO_POR_FRAME_ALVO) {
                    atualizarLogica(TEMPO_POR_FRAME_ALVO);
                    acumuladorTempo -= TEMPO_POR_FRAME_ALVO;
                }
            }
            renderizarGraficos();

            long tempoDeLoop = System.nanoTime() - agora;
            long tempoDeEspera = (TEMPO_POR_FRAME_ALVO - tempoDeLoop) / 1000000;

            if (tempoDeEspera < 0) tempoDeEspera = 5;
            try {
                Thread.sleep(tempoDeEspera);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                jogoRodando = false;
            }
        }
    }

    private void atualizarLogica(long deltaTimeNano) {
        if (heroi == null || faseCorrente == null || faseCorrente.getMapa() == null) return;
        if (!heroi.estaVivo()) {
            if (!gameOver) {
                gameOver = true;
                GerenciadorDeAudio.pararMusica();
                GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/game_over.wav");
                System.out.println("[MotorJogo] GAME OVER!");
            }
            return;
        }

        Mapa mapaAtual = faseCorrente.getMapa();

        if (faseCorrente.getChefe() == null && !faseCorrente.isChefeSpawnado() && areaArenaChefe != null && heroi.getBounds().intersects(areaArenaChefe)) {
            faseCorrente.tentarSpawnarChefe(areaArenaChefe);
            if (faseCorrente.getChefe() != null) {
                GerenciadorDeAudio.pararMusica();
                GerenciadorDeAudio.tocarMusica("/audio/musicas/chefe" + faseAtualNumero + "_epic.wav", true);

                //AJUSTE PARA EVITAR FICAR PRESO NA BORDA DA ARENA
                //esse código é executado quando o chefe é spawnado, momento em que a arena "fecha".
                // O objetivo é garantir que Zé não fique preso na linha exata da borda.
                if (heroi != null && areaArenaChefe != null && mapaAtual != null) { // Checagens de nulidade adicionais
                    double posicaoEsquerdaHeroi = heroi.getPosicao().getX();
                    //verififca se o herói está muito próximo da borda esquerda da arena ou ligeiramente fora mas já ativou o chefe.
                    if (Math.abs(posicaoEsquerdaHeroi - areaArenaChefe.getX()) < Constantes.TAMANHO_TILE / 2.0 ||
                            (posicaoEsquerdaHeroi < areaArenaChefe.getX() && heroi.getBounds().intersects(areaArenaChefe))) {

                        System.out.println("[MotorJogo] Verificando ajuste de posição do herói na entrada da arena (X atual: " + posicaoEsquerdaHeroi + ", Borda Arena X: " + areaArenaChefe.getX() + ")...");

                        double[] tentativasX = {
                                areaArenaChefe.getX() + 1.0, // 1 pixel para dentro
                                areaArenaChefe.getX() + Constantes.TAMANHO_TILE / 4.0, // Um quarto de tile para dentro
                                areaArenaChefe.getX() + Constantes.TAMANHO_TILE / 2.0 - heroi.getLargura() / 2.0 + 1.0 // Tentativa de centralizar no primeiro tile da arena (ajustado)
                        };

                        boolean ajustado = false;
                        for (double tentativaX : tentativasX) {
                            //gararante que a tentativa de X não coloque o herói fora dos limites do mapa à direita
                            if (tentativaX + heroi.getLargura() > mapaAtual.getLarguraEmPixels()) continue;
                            if (tentativaX < areaArenaChefe.getX()) continue; //garante que a tentativa é para DENTRO ou na borda da arena

                            Rectangle boundsAjustado = new Rectangle((int)tentativaX, (int)heroi.getPosicao().getY(), heroi.getLargura(), heroi.getAltura());

                            if (mapaAtual.getBounds().contains(boundsAjustado) && mapaAtual.ehAreaTransponivel(boundsAjustado)) {
                                heroi.setPosicao(tentativaX, heroi.getPosicao().getY());
                                System.out.println("[MotorJogo] Herói ajustado para X=" + tentativaX + " para evitar ficar preso na borda da arena.");
                                ajustado = true;
                                break;
                            }
                        }

                        if (!ajustado) {
                            System.err.println("[MotorJogo] AVISO: Não foi possível ajustar o herói para uma posição transponível ao entrar na arena do chefe. Pode continuar preso.");
                        }
                    }
                }
            }
        }

        mapaAtual.atualizarOffset(heroi.getPosicao().getX(), heroi.getPosicao().getY(), heroi.getLargura(), heroi.getAltura());

        double proximoX = heroi.getPosicao().getX();
        double proximoY = heroi.getPosicao().getY();
        double deltaMovimento = (heroi.getVelocidade() * deltaTimeNano) / 1000000000.0;

        if (heroi.isMovendoParaCima()) proximoY -= deltaMovimento;
        if (heroi.isMovendoParaBaixo()) proximoY += deltaMovimento;
        if (heroi.isMovendoParaEsquerda()) proximoX -= deltaMovimento;
        if (heroi.isMovendoParaDireita()) proximoX += deltaMovimento;

        verificarECorrigirColisaoComMapaEArena(heroi, proximoX, proximoY, mapaAtual);

        heroi.atualizar(deltaTimeNano, mapaAtual);
        if (heroi.isAtacando()) {
            double anguloAtaque = calcularAnguloAtaqueHeroi();
            Posicao posProjetil = new Posicao(heroi.getPosicao().getX() + heroi.getLargura()/2.0 - Projetil.LARGURA_PADRAO/2.0,
                    heroi.getPosicao().getY() + heroi.getAltura()/2.0 - Projetil.ALTURA_PADRAO/2.0);
            projeteis.add(new Projetil("projetil_heroi", posProjetil, anguloAtaque, true, heroi.getAtaque(), "/imagens/projetil_heroi.png"));
            GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/tiro_heroi.wav");
            heroi.resetAtaqueFlag();
        }

        for (Inimigo inimigo : faseCorrente.getInimigos()) {
            inimigo.atualizar(deltaTimeNano, mapaAtual);
            if (inimigo.isAtacando() && inimigo.podeAtacar()) {
                Projetil p = inimigo.atacarAlvo(heroi.getPosicao());
                if (p != null) projeteis.add(p);
            }
        }

        Chefe chefeDaFase = faseCorrente.getChefe();
        if (chefeDaFase != null && chefeDaFase.estaVivo()) {
            chefeDaFase.atualizar(deltaTimeNano, mapaAtual);
            if (chefeDaFase.isAtacando() && chefeDaFase.podeAtacar()) {
                List<Projetil> projeteisChefe = chefeDaFase.atacarAlvoMultiplo(heroi.getPosicao());
                if (projeteisChefe != null && !projeteisChefe.isEmpty()) {
                    projeteis.addAll(projeteisChefe);
                }
            }
        }

        Iterator<Projetil> iteradorProjeteis = projeteis.iterator();
        while (iteradorProjeteis.hasNext()) {
            Projetil projetil = iteradorProjeteis.next();
            projetil.atualizar(deltaTimeNano, mapaAtual);
            if (projetil.isDestruido() || !mapaAtual.getBounds().intersects(projetil.getBounds())) {
                iteradorProjeteis.remove();
                continue;
            }
            if (mapaAtual.projetilColideComBlocoDestrutivel(projetil) && heroi.isPoderQuebraBlocosAtivo()) {
                iteradorProjeteis.remove();
                continue;
            } else if (mapaAtual.colideCom(projetil)) {
                iteradorProjeteis.remove();
                continue;
            }

            if (projetil.isDoHeroi()) {
                for (Inimigo inimigo : faseCorrente.getInimigos()) {
                    if (inimigo.estaVivo() && projetil.getBounds().intersects(inimigo.getBounds())) {
                        inimigo.receberDano(projetil.getDano());
                        GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/impacto_inimigo.wav");
                        if (!inimigo.estaVivo()) {
                            heroi.adicionarPontuacao(inimigo.getPontosAoDerrotar());
                            System.out.println("[MotorJogo] Inimigo " + inimigo.getNomeEntidade() + " derrotado!");
                        }
                        iteradorProjeteis.remove();
                        break;
                    }
                }
                if (chefeDaFase != null && chefeDaFase.estaVivo() && projetil.getBounds().intersects(chefeDaFase.getBounds())) {
                    chefeDaFase.receberDano(projetil.getDano());
                    GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/impacto_chefe.wav");
                    if (!chefeDaFase.estaVivo()) {
                        heroi.adicionarPontuacao(chefeDaFase.getPontosAoDerrotar());
                        faseCorrente.setChefeDerrotado(true);
                        onChefeDerrotado(chefeDaFase);
                        System.out.println("[MotorJogo] Chefe " + chefeDaFase.getNomeEntidade() + " derrotado!");
                        GerenciadorDeAudio.pararMusica();
                        GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/vitoria_chefe.wav");
                    }
                    iteradorProjeteis.remove();
                }
            } else {
                if (heroi.estaVivo() && projetil.getBounds().intersects(heroi.getBounds())) {
                    heroi.receberDano(projetil.getDano());
                    GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/dano_heroi.wav");
                    iteradorProjeteis.remove();
                }
            }
        }

        Iterator<Item> iteradorItens = faseCorrente.getItens().iterator();
        while (iteradorItens.hasNext()) {
            Item item = iteradorItens.next();
            if (heroi.getBounds().intersects(item.getBounds())) {
                heroi.coletarItem(item);
                GerenciadorDeAudio.tocarEfeitoSonoro("/audio/efeitos/coleta_item.wav");
                iteradorItens.remove();
            }
        }

        faseCorrente.getInimigos().removeIf(inimigo -> !inimigo.estaVivo());

        if (faseCorrente.getChefe() != null && faseCorrente.isChefeDerrotado() && !transicionandoFase) {
            avancarParaProximaFase();
        }

        for (Entidade ec : entidadesCustomizadas) {
            ec.atualizar(deltaTimeNano, mapaAtual);
        }
    }

    private void verificarECorrigirColisaoComMapaEArena(Personagem personagem, double proximoX, double proximoY, Mapa mapa) {
        Rectangle boundsFuturoX = new Rectangle((int) proximoX, (int) personagem.getPosicao().getY(), personagem.getLargura(), personagem.getAltura());
        Rectangle boundsFuturoY = new Rectangle((int) personagem.getPosicao().getX(), (int) proximoY, personagem.getLargura(), personagem.getAltura());
        Rectangle boundsFuturosAmbos = new Rectangle((int) proximoX, (int) proximoY, personagem.getLargura(), personagem.getAltura());

        boolean colisaoX = false;
        boolean colisaoY = false;

        if (!mapa.getBounds().contains(boundsFuturosAmbos)) {
            if (proximoX < 0) proximoX = 0;
            if (proximoY < 0) proximoY = 0;
            if (proximoX + personagem.getLargura() > mapa.getLarguraEmPixels()) proximoX = mapa.getLarguraEmPixels() - personagem.getLargura();
            if (proximoY + personagem.getAltura() > mapa.getAlturaEmPixels()) proximoY = mapa.getAlturaEmPixels() - personagem.getAltura();

            boundsFuturoX = new Rectangle((int) proximoX, (int) personagem.getPosicao().getY(), personagem.getLargura(), personagem.getAltura());
            boundsFuturoY = new Rectangle((int) personagem.getPosicao().getX(), (int) proximoY, personagem.getLargura(), personagem.getAltura());
        }

        if (!mapa.ehAreaTransponivel(boundsFuturoX)) {
            colisaoX = true;
        }
        if (!mapa.ehAreaTransponivel(boundsFuturoY)) {
            colisaoY = true;
        }

        if (personagem instanceof Heroi && faseCorrente.getChefe() != null && faseCorrente.getChefe().estaVivo()) {
            if (areaArenaChefe != null && !areaArenaChefe.contains(boundsFuturoX) && personagem.getBounds().intersects(areaArenaChefe)) {
                if (proximoX < areaArenaChefe.getX()) {
                    proximoX = areaArenaChefe.getX();
                    colisaoX = true;
                }
            }
        }

        if (!colisaoX) {
            personagem.getPosicao().setX(proximoX);
        }
        if (!colisaoY) {
            personagem.getPosicao().setY(proximoY);
        }
    }

    private double calcularAnguloAtaqueHeroi() {
        if (heroi.isMovendoParaCima() && heroi.isMovendoParaDireita()) return Math.toRadians(-45);
        if (heroi.isMovendoParaCima() && heroi.isMovendoParaEsquerda()) return Math.toRadians(-135);
        if (heroi.isMovendoParaBaixo() && heroi.isMovendoParaDireita()) return Math.toRadians(45);
        if (heroi.isMovendoParaBaixo() && heroi.isMovendoParaEsquerda()) return Math.toRadians(135);
        if (heroi.isMovendoParaCima()) return Math.toRadians(-90);
        if (heroi.isMovendoParaBaixo()) return Math.toRadians(90);
        if (heroi.isMovendoParaEsquerda()) return Math.toRadians(180);
        return 0;
    }

    private void avancarParaProximaFase() {
        if (transicionandoFase) return;

        System.out.println("[MotorJogo] Fase " + faseAtualNumero + " concluída!");
        faseAtualNumero++;
        if (faseAtualNumero > TOTAL_FASES) {
            jogoConcluido = true;
            GerenciadorDeAudio.pararMusica();
            GerenciadorDeAudio.tocarMusica("/audio/musicas/vitoria_final.wav", false);
            System.out.println("[MotorJogo] Todas as fases concluídas! JOGO VENCIDO!");
        } else {
            transicionandoFase = true;
            if (faseCorrente != null) {
                faseCorrente.limparEntidadesDaFase();
            }
            carregarFase(faseAtualNumero);
        }
    }

    private void renderizarGraficos() {
        if (telaJogo != null) {
            telaJogo.renderizar();
        }
    }

    // Métodos para ControleTeclado
    public void setHeroiMovendoCima(boolean estado) { if (heroi != null) heroi.setMovendoParaCima(estado); }
    public void setHeroiMovendoBaixo(boolean estado) { if (heroi != null) heroi.setMovendoParaBaixo(estado); }
    public void setHeroiMovendoEsquerda(boolean estado) { if (heroi != null) heroi.setMovendoParaEsquerda(estado); }
    public void setHeroiMovendoDireita(boolean estado) { if (heroi != null) heroi.setMovendoParaDireita(estado); }
    public void heroiTentaAtacar() { if (heroi != null) heroi.atacar(); } // O método atacar() em Heroi deve setar a flag isAtacando
    public void alternarPausa() {
        if (transicionandoFase || gameOver || jogoConcluido) return; // Não pausa em certas condições
        jogoPausado = !jogoPausado;
        if (jogoPausado) {
            // GerenciadorDeAudio.pausarMusica(); // Método não existe, considerar alternativa
            GerenciadorDeAudio.pararMusica(); // Usar pararMusica como alternativa temporária
            System.out.println("[MotorJogo] Jogo Pausado");
        } else {
            // GerenciadorDeAudio.retomarMusica(); // Método não existe, considerar alternativa
            // Para retomar, seria necessário saber qual música estava tocando.
            // Por agora, a música não será retomada automaticamente ao despausar.
            // Uma lógica mais complexa seria necessária para armazenar e retomar a música anterior.
            System.out.println("[MotorJogo] Jogo Resumido");
        }
    }

    public Heroi getHeroi() { return heroi; }
    public Chefe getChefeAtual() { return faseCorrente != null ? faseCorrente.getChefe() : null; }
    public int getFaseAtualNumero() { return faseAtualNumero; }
    public boolean isGameOver() { return gameOver; }
    public boolean isJogoConcluido() { return jogoConcluido; }
    public boolean isTransicionandoFase() { return transicionandoFase; }

    public List<Entidade> getTodasEntidadesVisiveis() {
        List<Entidade> visiveis = new ArrayList<>();
        if (faseCorrente == null) return visiveis;

        if (heroi != null && heroi.isVisivel()) {
            visiveis.add(heroi);
        }
        if (faseCorrente.getInimigos() != null) {
            faseCorrente.getInimigos().stream()
                    .filter(e -> e != null && e.isVisivel())
                    .forEach(visiveis::add);
        }
        if (faseCorrente.getChefe() != null && faseCorrente.getChefe().isVisivel()) {
            visiveis.add(faseCorrente.getChefe());
        }
        if (faseCorrente.getItens() != null) {
            faseCorrente.getItens().stream()
                    .filter(i -> i != null && i.isVisivel())
                    .forEach(visiveis::add);
        }
        if (projeteis != null) {
            projeteis.stream()
                    .filter(p -> p != null && p.isVisivel())
                    .forEach(visiveis::add);
        }
        if (entidadesCustomizadas != null) {
            entidadesCustomizadas.stream()
                    .filter(ec -> ec != null && ec.isVisivel())
                    .forEach(visiveis::add);
        }
        return visiveis;
    }

    @Override
    public Rectangle getArenaChefe() {
        return this.areaArenaChefe;
    }

    @Override
    public void onChefeDerrotado(Chefe chefe) {
        System.out.println("[MotorJogoListener] Evento: Chefe " + chefe.getNomeEntidade() + " derrotado recebido.");
    }

    @Override
    public void onSpawnProjetilRequisitado(Projetil projetil) {
        if (projetil != null) {
            this.projeteis.add(projetil);
        }
    }

    @Override
    public List<Entidade> getEntidadesProximas(Entidade entidadeCentral, double raio) {
        List<Entidade> proximas = new ArrayList<>();
        if (faseCorrente == null || entidadeCentral == null) return proximas;

        if (heroi != null && heroi != entidadeCentral && entidadeCentral.getPosicao().distanciaAte(heroi.getPosicao()) <= raio) {
            proximas.add(heroi);
        }

        if (faseCorrente.getInimigos() != null) {
            for (Inimigo inimigo : faseCorrente.getInimigos()) {
                if (inimigo != null && inimigo != entidadeCentral && inimigo.estaVivo() && entidadeCentral.getPosicao().distanciaAte(inimigo.getPosicao()) <= raio) {
                    proximas.add(inimigo);
                }
            }
        }

        Chefe chefeDaFase = faseCorrente.getChefe();
        if (chefeDaFase != null && chefeDaFase != entidadeCentral && chefeDaFase.estaVivo() && entidadeCentral.getPosicao().distanciaAte(chefeDaFase.getPosicao()) <= raio) {
            proximas.add(chefeDaFase);
        }

        if (entidadesCustomizadas != null) {
            for (Entidade ec : entidadesCustomizadas) {
                if (ec != null && ec != entidadeCentral && ec.estaVivo() && entidadeCentral.getPosicao().distanciaAte(ec.getPosicao()) <= raio ){
                    proximas.add(ec);
                }
            }
        }
        return proximas;
    }

    @Override
    public boolean verificarColisaoComMapa(Entidade entidade) {
        if (faseCorrente == null || faseCorrente.getMapa() == null) return true;
        return faseCorrente.getMapa().colideCom(entidade);
    }

    @Override
    public Heroi getAlvoHeroi() {
        return this.heroi;
    }

    public boolean isJogoPausado() {
        return jogoPausado;
    }

    public void reiniciarComMapa(Mapa novoMapa, int numeroFase) {
        if (novoMapa == null) {
            System.err.println("[MotorJogo] Mapa inválido para reinício.");
            return;
        }

        GerenciadorDeAudio.pararMusica();
        this.projeteis.clear();
        this.entidadesCustomizadas.clear();
        this.transicionandoFase = true;

        this.faseAtualNumero = numeroFase;
        this.telaJogo.setMapaAtual(novoMapa);

        this.areaArenaChefe = calcularAreaArenaChefe(novoMapa);
        novoMapa.setAreaArenaChefe(this.areaArenaChefe);

        Posicao novaPosHeroi = encontrarPosicaoSpawnValidaAleatoriaForaDaArena(novoMapa);
        if (novaPosHeroi == null) novaPosHeroi = new Posicao(0.5, 0.5);

        if (this.heroi == null) {
            this.heroi = new Heroi("Herói", novaPosHeroi, 100, 10);
        } else {
            this.heroi.setPosicao(novaPosHeroi.getX(), novaPosHeroi.getY());
            this.heroi.curarTotalmente();
        }

        this.faseCorrente = new Fase(numeroFase, this.heroi, novoMapa, this);

        GerenciadorDeAudio.tocarMusica("/audio/musicas/fase" + numeroFase + ".wav", true);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            this.transicionandoFase = false;
            System.out.println("[MotorJogo] Fase reiniciada com novo mapa!");
        }).start();
    }

    private Rectangle calcularAreaArenaChefe(Mapa mapa) {
        int largura = mapa.getLarguraEmPixels();
        int altura = mapa.getAlturaEmPixels();
        int larguraArena = largura / 3;
        int xArena = largura - larguraArena;
        return new Rectangle(xArena, 0, larguraArena, altura);
    }

}



