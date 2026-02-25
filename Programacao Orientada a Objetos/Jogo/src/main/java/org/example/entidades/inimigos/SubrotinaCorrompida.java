package org.example.entidades.inimigos;

import org.example.entidades.Inimigo;
import org.example.entidades.Heroi;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;

public class SubrotinaCorrompida extends Inimigo {

    public SubrotinaCorrompida(Posicao posicaoInicial, Heroi alvo) {
        super("Subrotina Corrompida da Fase 5",
                posicaoInicial,
                Constantes.TAMANHO_TILE, //largura
                Constantes.TAMANHO_TILE, //altura
                Constantes.INIMIGO_SUBROTINA_FASE5, //caminho da imagem
                50,   //vida máxima
                65.0, //velocidade
                12,   //ataque
                6,    //deefesa
                alvo,
                250.0, //raio de visão
                Constantes.TAMANHO_TILE * 1.7, //raaio de ataque
                30 //pontos ao derrotar (exemplo)
        );
    }

    @Override
    public void inteligenciaArtificial(Mapa mapa) {
        if (podeVerAlvo()) {
            moverEmDirecaoAlvo(mapa);
            //subrotinas corrompidas poderiam ter um ataque que causa lentidão ou outro debuff.
        } else {
            patrulhar(mapa);
        }
    }
}

