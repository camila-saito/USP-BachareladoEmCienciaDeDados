package org.example.entidades.inimigos;

import org.example.entidades.Inimigo;
import org.example.entidades.Heroi;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;

public class Virus extends Inimigo {

    public Virus(Posicao posicaoInicial, Heroi alvo) {
        super("Vírus da Fase 2",
                posicaoInicial,
                Constantes.TAMANHO_TILE, //largura
                Constantes.TAMANHO_TILE, //altura
                Constantes.INIMIGO_VIRUS_FASE2, //caminho da imagem
                30,   //vida máxima
                70.0, //velocidade
                8,    //ataque
                3,    //defesa
                alvo,
                180.0, //raio de visão
                Constantes.TAMANHO_TILE * 1.6, //raio de ataque
                15 //pontos ao derrotar (exemplo)
        );
    }

    @Override
    public void inteligenciaArtificial(Mapa mapa) {
        if (podeVerAlvo()) {
            moverEmDirecaoAlvo(mapa);
        } else {
            patrulhar(mapa); //adiciona o comportamento de patrulha
        }
        //virus poderia ter um comportamento de se multiplicar ou deixar um rastro temporário de "corrupção"
    }
}

