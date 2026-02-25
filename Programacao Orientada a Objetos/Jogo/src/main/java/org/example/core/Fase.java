//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.core;

import org.example.auxiliar.Posicao;
import org.example.entidades.Chefe;
import org.example.entidades.Heroi;
import org.example.entidades.Inimigo;
import org.example.entidades.chefes.AlgoritmoAnomalo;
import org.example.entidades.chefes.ByteCorrompido;
import org.example.entidades.chefes.CaboCorrompido;
import org.example.entidades.chefes.ChefaoPoderoso;
import org.example.entidades.chefes.GuardiaoFirewall;
import org.example.entidades.inimigos.Bot;
import org.example.entidades.inimigos.Bug;
import org.example.entidades.inimigos.Spyware;
import org.example.entidades.inimigos.SubrotinaCorrompida;
import org.example.entidades.inimigos.Virus;
import org.example.graficos.Mapa;
import org.example.itens.Item;
import org.example.itens.ItemVantagem;
import org.example.itens.ItemVida;
import org.example.motor.MotorJogoListener;
import java.awt.Rectangle;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fase {
    private int numeroFase;
    private Heroi heroi;
    private List<Inimigo> inimigos;
    private Chefe chefe;
    private List<Item> itens;
    private Mapa mapa;
    private Random random = new Random();
    private boolean chefeSpawnadoNestaFase = false;
    private MotorJogoListener motorListener;

    public Fase(int var1, Heroi var2, Mapa var3, MotorJogoListener var4) {
        this.numeroFase = var1;
        this.heroi = var2;
        this.mapa = var3;
        this.inimigos = new ArrayList();
        this.itens = new ArrayList();
        this.chefe = null;
        this.motorListener = var4;
        System.out.println("[Fase] Criando Fase " + var1 + " com mapa: " + (this.mapa != null ? this.mapa.getNomeArquivoMapa() : "null"));
        this.spawnEntidadesPadrao();
    }

    private void spawnEntidadesPadrao() {
        if (this.mapa == null) {
            System.err.println("[Fase] Mapa é nulo. Não é possível spawnar entidades.");
        } else {
            System.out.println("[Fase] Spawning entidades padrão para fase " + this.getNumeroFase());
            int var1 = 5 + this.numeroFase * 2;

            for(int var2 = 0; var2 < var1; ++var2) {
                Posicao var3 = this.encontrarPosicaoSpawnValidaAleatoria(this.heroi != null ? this.heroi.getPosicao() : null, (double)160.0F);
                if (var3 != null) {
                    Object var4 = null;
                    switch (this.numeroFase) {
                        case 1:
                            var4 = new Bug(var3, this.heroi);
                            break;
                        case 2:
                            var4 = new Virus(var3, this.heroi);
                            break;
                        case 3:
                            var4 = new Bot(var3, this.heroi);
                            break;
                        case 4:
                            var4 = new Spyware(var3, this.heroi);
                            break;
                        case 5:
                            var4 = new SubrotinaCorrompida(var3, this.heroi);
                            break;
                        default:
                            var4 = new Bug(var3, this.heroi);
                    }

                    if (var4 != null) {
                        this.inimigos.add((Inimigo) var4);
                    }
                } else {
                    System.err.println("[Fase] Não foi possível encontrar posição para inimigo " + var2 + " da fase " + this.numeroFase);
                }
            }

            System.out.println("[Fase] " + this.inimigos.size() + " inimigos adicionados.");
            byte var5 = 2;

            for(int var6 = 0; var6 < var5; ++var6) {
                Posicao var9 = this.encontrarPosicaoSpawnValidaAleatoria(this.heroi != null ? this.heroi.getPosicao() : null, (double)96.0F);
                if (var9 != null) {
                    this.itens.add(new ItemVida(var9));
                }
            }

            if (this.numeroFase > 1) {
                Posicao var7 = this.encontrarPosicaoSpawnValidaAleatoria(this.heroi != null ? this.heroi.getPosicao() : null, (double)96.0F);
                if (var7 != null) {
                    this.itens.add(new ItemVantagem(var7, 15000));
                }
            }

            System.out.println("[Fase] " + this.itens.size() + " itens adicionados.");
        }
    }

    public void tentarSpawnarChefe(Rectangle var1) {
        if (this.chefe == null && !this.chefeSpawnadoNestaFase && var1 != null && this.mapa != null) {
            int var10001 = this.numeroFase;
            System.out.println("[Fase] Tentando spawnar chefe da fase " + var10001 + " na arena: " + String.valueOf(var1));
            Posicao var2 = this.encontrarPosicaoSpawnSeguraNaArena(var1, this.heroi != null ? this.heroi.getPosicao() : null, (double)128.0F);
            if (var2 == null) {
                System.err.println("[Fase] Não foi possível encontrar posição SEGURA para o chefe na arena. Tentando aleatória na arena.");
                var2 = this.encontrarPosicaoAleatoriaNaArena(var1);
            }

            if (var2 != null) {
                Object var3 = null;
                switch (this.numeroFase) {
                    case 1:
                        var3 = new ByteCorrompido(var2, this.heroi);
                        break;
                    case 2:
                        var3 = new CaboCorrompido(var2, this.heroi);
                        break;
                    case 3:
                        var3 = new AlgoritmoAnomalo(var2, this.heroi);
                        break;
                    case 4:
                        var3 = new GuardiaoFirewall(var2, this.heroi);
                        break;
                    case 5:
                        var3 = new ChefaoPoderoso(var2, this.heroi);
                        break;
                    default:
                        System.err.println("[Fase] Número de fase inválido para spawn de chefe: " + this.numeroFase);
                        return;
                }

                if (var3 != null) {
                    this.chefe = (Chefe)var3;
                    if (this.motorListener != null) {
                        this.chefe.setMotorJogoListener(this.motorListener);
                    }

                    this.chefeSpawnadoNestaFase = true;
                    PrintStream var10000 = System.out;
                    String var5 = this.chefe.getNomeEntidade();
                    var10000.println("[Fase] Chefe " + var5 + " spawnado com sucesso em " + var2.getX() + "," + var2.getY() + "!");
                } else {
                    System.err.println("[Fase] Falha ao instanciar o objeto Chefe para fase " + this.numeroFase);
                }
            } else {
                System.err.println("[Fase] Falha crítica final: Não foi possível encontrar NENHUMA posição para o CHEFE na arena para fase " + this.numeroFase);
            }

        }
    }

    private Posicao encontrarPosicaoSpawnValidaAleatoria(Posicao var1, double var2) {
        if (this.mapa == null) {
            return null;
        } else {
            for(int var4 = 0; var4 < 100; ++var4) {
                double var5 = this.random.nextDouble() * (double)this.mapa.getLarguraEmPixels();
                double var7 = this.random.nextDouble() * (double)this.mapa.getAlturaEmPixels();
                Posicao var9 = new Posicao(var5, var7);
                if (this.mapa.ehTransponivelNaPosicaoPixel(var5, var7) && (var1 == null || var9.distanciaAte(var1) >= var2)) {
                    return var9;
                }
            }

            System.err.println("[Fase] Não foi possível encontrar posição válida aleatória após 100 tentativas para fase " + this.numeroFase);
            return null;
        }
    }

    private Posicao encontrarPosicaoSpawnSeguraNaArena(Rectangle var1, Posicao var2, double var3) {
        if (this.mapa != null && var1 != null) {
            for(int var5 = 0; var5 < 100; ++var5) {
                double var6 = var1.getX() + this.random.nextDouble() * var1.getWidth();
                double var8 = var1.getY() + this.random.nextDouble() * var1.getHeight();
                Posicao var10 = new Posicao(var6, var8);
                if (this.mapa.ehTransponivelNaPosicaoPixel(var6, var8) && (var2 == null || var10.distanciaAte(var2) >= var3)) {
                    return var10;
                }
            }

            System.err.println("[Fase] Não foi possível encontrar posição segura na arena para fase " + this.numeroFase);
            return null;
        } else {
            return null;
        }
    }

    private Posicao encontrarPosicaoAleatoriaNaArena(Rectangle var1) {
        if (this.mapa != null && var1 != null) {
            for(int var2 = 0; var2 < 50; ++var2) {
                double var3 = var1.getX() + this.random.nextDouble() * var1.getWidth();
                double var5 = var1.getY() + this.random.nextDouble() * var1.getHeight();
                if (this.mapa.ehTransponivelNaPosicaoPixel(var3, var5)) {
                    return new Posicao(var3, var5);
                }
            }

            System.err.println("[Fase] Não foi possível encontrar posição aleatória na arena para fase " + this.numeroFase);
            return null;
        } else {
            return null;
        }
    }

    public Heroi getHeroi() {
        return this.heroi;
    }

    public List<Inimigo> getInimigos() {
        return this.inimigos;
    }

    public Chefe getChefe() {
        return this.chefe;
    }

    public List<Item> getItens() {
        return this.itens;
    }

    public Mapa getMapa() {
        return this.mapa;
    }

    public int getNumeroFase() {
        return this.numeroFase;
    }

    public boolean isChefeSpawnado() {
        return this.chefeSpawnadoNestaFase;
    }

    public void setChefeDerrotado(boolean var1) {
        if (var1 && this.chefe != null) {
            PrintStream var10000 = System.out;
            String var10001 = this.chefe.getNomeEntidade();
            var10000.println("[Fase] Chefe " + var10001 + " marcado como derrotado na Fase " + this.numeroFase);
        }

    }

    public boolean isChefeDerrotado() {
        return this.chefe != null && !this.chefe.estaVivo();
    }

    public void limparEntidadesDaFase() {
        if (this.inimigos != null) {
            this.inimigos.clear();
        }

        if (this.itens != null) {
            this.itens.clear();
        }

        this.chefe = null;
        this.chefeSpawnadoNestaFase = false;
        System.out.println("[Fase] Entidades da Fase " + this.numeroFase + " (inimigos, itens, chefe) limpas.");
    }
}
