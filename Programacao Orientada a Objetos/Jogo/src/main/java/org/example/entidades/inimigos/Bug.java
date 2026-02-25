package org.example.entidades.inimigos;

import org.example.entidades.Inimigo;
import org.example.entidades.Heroi;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;

public class Bug extends Inimigo {

    public Bug(Posicao posicaoInicial, Heroi alvo) {
        super("Bug da Fase 1",
                posicaoInicial,
                Constantes.TAMANHO_TILE, //largura
                Constantes.TAMANHO_TILE, //altura
                Constantes.INIMIGO_BUG_FASE1, //caminho da imagem
                20,   //vida máxima
                60.0, //velocidade (pixels por segundo)
                5,    //ataque
                2,    //deefesa
                alvo,
                150.0, //raio de visão (pixels)
                Constantes.TAMANHO_TILE * 1.5, //raio de ataque (curto alcance)
                10 //pontos ao derrotar
        );
    }

    @Override
    public void inteligenciaArtificial(Mapa mapa) {
        if (podeVerAlvo()) {
            moverEmDirecaoAlvo(mapa);
        } else {
            patrulhar(mapa); //adiciona o comportamento de patrulha
        }
    }

}

