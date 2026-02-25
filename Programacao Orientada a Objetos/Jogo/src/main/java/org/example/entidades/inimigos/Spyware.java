package org.example.entidades.inimigos;

import org.example.entidades.Inimigo;
import org.example.entidades.Heroi;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;

public class Spyware extends Inimigo {

    public Spyware(Posicao posicaoInicial, Heroi alvo) {
        super("Spyware da Fase 4",
                posicaoInicial,
                Constantes.TAMANHO_TILE, // largura
                Constantes.TAMANHO_TILE, //altura
                Constantes.INIMIGO_SPYWARE_FASE4, //caminho da imagem
                35,   // vida máxima
                80.0, //velocidade (rápido e evasivo?)
                7,    // ataque (talvez um ataque que drena algo?)
                4,    //defesa
                alvo,
                220.0, //raio de visão
                Constantes.TAMANHO_TILE * 1.8, //raio de ataque (pode ser um pouco maior se for um "drain")
                25 //pontos ao derrotar (exemplo)
        );
    }

    @Override
    public void inteligenciaArtificial(Mapa mapa) {
        if (podeVerAlvo()) {
            moverEmDirecaoAlvo(mapa);
            //spyware poderia tentar manter distância ou se esconder após atacar.
        } else {
            patrulhar(mapa);
        }
    }
}

