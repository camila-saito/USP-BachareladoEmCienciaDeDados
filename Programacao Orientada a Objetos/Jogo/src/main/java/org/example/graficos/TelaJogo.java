//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.graficos;

import org.example.auxiliar.GerenciadorDePersonagemCustom;
import org.example.auxiliar.GerenciadorDeProgresso;
import org.example.auxiliar.Posicao;
import org.example.entidades.Entidade;
import org.example.motor.MotorJogo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorMap;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import javax.swing.JPanel;

public class TelaJogo extends JPanel implements DropTargetListener, KeyListener {
    private Mapa mapaAtual;
    private MotorJogo motorJogo;
    private Font fonteGrande = new Font("Arial", 1, 48);
    private Font fonteMedia = new Font("Arial", 1, 24);
    private Font fontePequena = new Font("Arial", 0, 18);

    public TelaJogo(MotorJogo var1) {
        this.motorJogo = var1;
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        new DropTarget(this, 3, this, true, (FlavorMap)null);
        System.out.println("[TelaJogo] TelaJogo inicializada com DropTarget.");
    }

    public void setMapaAtual(Mapa var1) {
        this.mapaAtual = var1;
        System.out.println("[TelaJogo] Mapa atual definido.");
    }

    public void setMotorJogo(MotorJogo var1) {
        this.motorJogo = var1;
        System.out.println("[TelaJogo] MotorJogo definido via setter.");
    }

    protected void paintComponent(Graphics var1) {
        super.paintComponent(var1);
        if (this.motorJogo == null) {
            System.out.println("[TelaJogo] MotorJogo é null em paintComponent. Retornando.");
        } else {
            if (this.motorJogo.isGameOver()) {
                this.desenharTelaGameOver(var1);
            } else if (this.motorJogo.isJogoConcluido()) {
                this.desenharTelaVitoria(var1, new String[]{"Vinicius Moraes de Carvalho", "Camila Aya Saito", "Victoria Fávero Nunes"});
            } else if (this.motorJogo.isTransicionandoFase()) {
                this.desenharTelaTransicaoFase(var1, this.motorJogo.getFaseAtualNumero());
            } else {
                if (this.mapaAtual != null) {
                    this.mapaAtual.desenhar(var1);
                }

                List<Entidade> var2 = this.motorJogo.getTodasEntidadesVisiveis();
                if (var2 != null) {
                    for(Entidade var4 : var2) {
                        if (var4 != null && var4.isVisivel()) {
                            var4.desenhar(var1, this.mapaAtual != null ? this.mapaAtual.getOffsetX() : 0, this.mapaAtual != null ? this.mapaAtual.getOffsetY() : 0);
                        }
                    }
                }

                this.desenharHUD(var1);
            }

        }
    }

    private void desenharHUD(Graphics var1) {
        if (this.motorJogo != null && this.motorJogo.getHeroi() != null && this.motorJogo.getHeroi().estaVivo()) {
            var1.setColor(Color.WHITE);
            var1.setFont(this.fontePequena);
            var1.drawString("Vida: " + this.motorJogo.getHeroi().getVidaAtual() + "/" + this.motorJogo.getHeroi().getVidaMaxima(), 10, 20);
            var1.drawString("Pontuação: " + this.motorJogo.getHeroi().getPontuacao(), 10, 40);
            var1.drawString("Fase: " + this.motorJogo.getFaseAtualNumero(), 700, 20);
            var1.setColor(Color.GRAY);
            var1.fillRect(10, 50, 200, 20);
            var1.setColor(Color.GREEN);
            double var2 = (double)this.motorJogo.getHeroi().getVidaAtual() / (double)this.motorJogo.getHeroi().getVidaMaxima();
            var1.fillRect(10, 50, (int)((double)200.0F * var2), 20);
            var1.setColor(Color.WHITE);
            var1.drawRect(10, 50, 200, 20);
            if (this.motorJogo.getChefeAtual() != null && this.motorJogo.getChefeAtual().estaVivo()) {
                var1.setColor(Color.GRAY);
                var1.fillRect(250, 550, 300, 25);
                var1.setColor(Color.RED);
                double var4 = (double)this.motorJogo.getChefeAtual().getVidaAtual() / (double)this.motorJogo.getChefeAtual().getVidaMaxima();
                var1.fillRect(250, 550, (int)((double)300.0F * var4), 25);
                var1.setColor(Color.WHITE);
                var1.drawRect(250, 550, 300, 25);
                var1.setFont(this.fonteMedia);
                String var6 = this.motorJogo.getChefeAtual().getNomeEntidade();
                FontMetrics var7 = var1.getFontMetrics(this.fonteMedia);
                int var8 = var7.stringWidth(var6);
                var1.drawString(var6, 400 - var8 / 2, 540);
            }
        }

    }

    public void desenharTelaGameOver(Graphics var1) {
        var1.setColor(Color.BLUE);
        var1.fillRect(0, 0, 800, 600);
        var1.setFont(this.fonteGrande);
        var1.setColor(Color.RED);
        String var2 = "GAME OVER";
        FontMetrics var3 = var1.getFontMetrics(this.fonteGrande);
        int var4 = (800 - var3.stringWidth(var2)) / 2;
        int var5 = (600 - var3.getHeight()) / 2 + var3.getAscent();
        var1.drawString(var2, var4, var5);
        var1.setFont(this.fonteMedia);
        var1.setColor(Color.WHITE);
        String var6 = "Pressione ESC para sair";
        var3 = var1.getFontMetrics(this.fonteMedia);
        var4 = (800 - var3.stringWidth(var6)) / 2;
        var1.drawString(var6, var4, var5 + 60);
    }

    public void desenharTelaVitoria(Graphics var1, String[] var2) {
        var1.setColor(new Color(0, 50, 0));
        var1.fillRect(0, 0, 800, 600);
        var1.setFont(this.fonteGrande);
        var1.setColor(Color.YELLOW);
        String var3 = "PARABÉNS, VOCÊ VENCEU!";
        FontMetrics var4 = var1.getFontMetrics(this.fonteGrande);
        int var5 = (800 - var4.stringWidth(var3)) / 2;
        int var6 = 150 + var4.getAscent();
        var1.drawString(var3, var5, var6);
        var1.setFont(this.fonteMedia);
        var1.setColor(Color.WHITE);
        String var7 = "Obrigado por jogar!";
        FontMetrics var8 = var1.getFontMetrics(this.fonteMedia);
        int var9 = (800 - var8.stringWidth(var7)) / 2;
        var1.drawString(var7, var9, var6 + 80);
        var1.setFont(this.fontePequena);
        var1.setColor(Color.CYAN);
        String var10 = "Desenvolvido por:";
        FontMetrics var11 = var1.getFontMetrics(this.fontePequena);
        int var12 = (800 - var11.stringWidth(var10)) / 2;
        int var13 = var6 + 160;
        var1.drawString(var10, var12, var13);
        var13 += 30;

        for(String var17 : var2) {
            int var18 = (800 - var11.stringWidth(var17)) / 2;
            var1.drawString(var17, var18, var13);
            var13 += 25;
        }
    }

    public void desenharTelaTransicaoFase(Graphics var1, int var2) {
        var1.setColor(Color.BLACK);
        var1.fillRect(0, 0, 800, 600);
        var1.setFont(this.fonteGrande);
        var1.setColor(Color.CYAN);
        String var3 = "FASE " + var2;
        FontMetrics var4 = var1.getFontMetrics(this.fonteGrande);
        int var5 = (800 - var4.stringWidth(var3)) / 2;
        int var6 = (600 - var4.getHeight()) / 2 + var4.getAscent() - 30;
        var1.drawString(var3, var5, var6);
        var1.setFont(this.fonteMedia);
        var1.setColor(Color.WHITE);
        String var7 = "Carregando...";
        FontMetrics var8 = var1.getFontMetrics(this.fonteMedia);
        int var9 = (800 - var8.stringWidth(var7)) / 2;
        var1.drawString(var7, var9, var6 + 40);
        String var10 = "";
        switch (var2) {
            case 1:
                var10 = "O Sistema Inicial";
                break;
            case 2:
                var10 = "O Circuito Rápido";
                break;
            case 3:
                var10 = "A Cidade dos Algoritmos";
                break;
            case 4:
                var10 = "O Labirinto do Firewall";
                break;
            case 5:
                var10 = "O Núcleo Digital";
        }

        if (!var10.isEmpty()) {
            var1.setFont(this.fonteMedia);
            var4 = var1.getFontMetrics(this.fonteMedia);
            var5 = (800 - var4.stringWidth(var10)) / 2;
            var1.drawString(var10, var5, var6 + 60);
        }

    }

    public void renderizar() {
        this.repaint();
    }

    public void dragEnter(DropTargetDragEvent var1) {
    }

    public void dragOver(DropTargetDragEvent var1) {
    }

    public void dropActionChanged(DropTargetDragEvent var1) {
    }

    public void dragExit(DropTargetEvent var1) {
    }

    public void drop(DropTargetDropEvent var1) {
        try {
            Transferable var2 = var1.getTransferable();
            if (var2.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                var1.acceptDrop(1);
                List var3 = (List)var2.getTransferData(DataFlavor.javaFileListFlavor);
                if (var3 != null && !var3.isEmpty()) {
                    File var4 = (File)var3.get(0);
                    if (var4.getName().toLowerCase().endsWith(".zip")) {
                        System.out.println("[TelaJogo] Ficheiro ZIP largado: " + var4.getAbsolutePath());
                        Entidade var5 = GerenciadorDePersonagemCustom.carregarPersonagemDeZip(var4.getAbsolutePath());
                        if (var5 != null && this.motorJogo != null) {
                            Point var6 = var1.getLocation();
                            double var7 = var6.getX() + (double)(this.mapaAtual != null ? this.mapaAtual.getOffsetX() : 0);
                            double var9 = var6.getY() + (double)(this.mapaAtual != null ? this.mapaAtual.getOffsetY() : 0);
                            var7 -= (double)var5.getLargura() / (double)2.0F;
                            var9 -= (double)var5.getAltura() / (double)2.0F;
                            var5.setPosicao(new Posicao(var7, var9));
                            this.motorJogo.adicionarEntidadeCustomizada(var5);
                            System.out.println("[TelaJogo] Personagem " + var5.getNomeEntidade() + " adicionado ao jogo em (" + var7 + ", " + var9 + ").");
                        } else {
                            System.err.println("[TelaJogo] Falha ao carregar personagem do ZIP ou motorJogo é null.");
                        }
                    } else {
                        System.out.println("[TelaJogo] Ficheiro largado não é .zip: " + var4.getName());
                    }
                }

                var1.dropComplete(true);
            } else {
                var1.rejectDrop();
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            var1.dropComplete(false);
        }

    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                GerenciadorDeProgresso.salvarMapa(mapaAtual, motorJogo.getFaseAtualNumero());
                break;
            case KeyEvent.VK_L:
                Mapa mapaCarregado = GerenciadorDeProgresso.carregarMapa();
                if (mapaCarregado != null) {
                        this.motorJogo.reiniciarComMapa(mapaCarregado, mapaCarregado.getNumeroFase());
                    System.out.println("Jogo carregado com sucesso.");
                }else {
                    System.out.println("Falha ao carregar o jogo.");
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    
    public MotorJogo getMotorJogo() {
        return motorJogo;
    }
    
    
}
