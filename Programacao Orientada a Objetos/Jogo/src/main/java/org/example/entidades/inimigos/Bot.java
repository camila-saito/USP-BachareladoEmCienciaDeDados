package org.example.entidades.inimigos;

import org.example.entidades.Inimigo;
import org.example.entidades.Heroi;
import org.example.auxiliar.Posicao;
import org.example.auxiliar.Constantes;
import org.example.graficos.Mapa;

public class Bot extends Inimigo {

    public Bot(Posicao posicaoInicial, Heroi alvo) {
        super("Bot da Fase 3",
                posicaoInicial,
                Constantes.TAMANHO_TILE, //largura
                Constantes.TAMANHO_TILE, //altura
                Constantes.INIMIGO_BOT_FASE3, //caminho da imagem
                40,   //vida máxima
                50.0, //velocidade (mais lento, talvez mais resistente)
                10,   //ataque
                5,    //defesa
                alvo,
                200.0, //raio de visão
                Constantes.TAMANHO_TILE * 1.5, //raio de ataque
                20 //pontos ao derrotar (exemplo)
        );
    }

    @Override
    public void inteligenciaArtificial(Mapa mapa) {
        if (podeVerAlvo()) {
            moverEmDirecaoAlvo(mapa);
        } else {
            patrulhar(mapa); //adiciona o comportamento de patrulha
        }
        //bots poderiam ter um padrão de movimento mais previsível ou patrulhar uma área.
    }
}

