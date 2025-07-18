#include <stdio.h>

// Definição do tamanho do tabuleiro
#define TAMANHO_TABULEIRO 10

// Estrutura da célula do jogo
typedef struct {
    int temBomba;
    int estaAberta;
    int qtdVizinhos;
} celula;

// Função para inicializar o jogo com bombas fixas
void iniciar_jogo(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO]) {
    int l;
    int c;
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            jogo[l][c].temBomba = 0;
            jogo[l][c].estaAberta = 0;
            jogo[l][c].qtdVizinhos = 0;
        }
    }

    // Posições fixas das bombas
    jogo[1][1].temBomba = 1;
    jogo[2][3].temBomba = 1;
    jogo[4][7].temBomba = 1;
    jogo[7][2].temBomba = 1;
    jogo[8][8].temBomba = 1;
}

// Função para contar o número de bombas vizinhas
int quantidade_bombas_vizinhas(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int l, int c) {
    int quantidade = 0;
    if (l > 0 && jogo[l - 1][c].temBomba) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && jogo[l + 1][c].temBomba) quantidade++;
    if (c > 0 && jogo[l][c - 1].temBomba) quantidade++;
    if (c < TAMANHO_TABULEIRO - 1 && jogo[l][c + 1].temBomba) quantidade++;
    if (l > 0 && c > 0 && jogo[l - 1][c - 1].temBomba) quantidade++;
    if (l > 0 && c < TAMANHO_TABULEIRO - 1 && jogo[l - 1][c + 1].temBomba) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && c > 0 && jogo[l + 1][c - 1].temBomba) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && c < TAMANHO_TABULEIRO - 1 && jogo[l + 1][c + 1].temBomba) quantidade++;
    return quantidade;
}

// Função para contar as bombas vizinhas em todo o tabuleiro
void contar_bombas(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int vizinhos[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO]) {
    int l;
    int c;
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            vizinhos[l][c] = quantidade_bombas_vizinhas(jogo, l, c);
        }
    }
}

// Função para imprimir o tabuleiro
void imprimir(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int aberto[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int vizinhos[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO]) {
    int l;
    int c;
    printf("\n\n\t    ");
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        printf(" %2d ", l);
    }
    printf("\n\t   -----------------------------------------\n");

    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        printf("\t%2d  |", l);
        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            if (aberto[l][c]) {
                if (jogo[l][c].temBomba) {
                    printf(" * ");
                } else {
                    printf(" %d ", vizinhos[l][c]);
                }
            } else {
                printf("   ");
            }
            printf("|");
        }
        printf("\n\t   -----------------------------------------\n");
    }
}

// Função para abrir células do tabuleiro
void abrir_celula(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int aberto[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int vizinhos[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int l, int c) {
    if (l >= 0 && l < TAMANHO_TABULEIRO && c >= 0 && c < TAMANHO_TABULEIRO && !aberto[l][c]) {
        aberto[l][c] = 1;
        if (vizinhos[l][c] == 0) {
            abrir_celula(jogo, aberto, vizinhos, l - 1, c);
            abrir_celula(jogo, aberto, vizinhos, l + 1, c);
            abrir_celula(jogo, aberto, vizinhos, l, c - 1);
            abrir_celula(jogo, aberto, vizinhos, l, c + 1);
            abrir_celula(jogo, aberto, vizinhos, l - 1, c - 1);
            abrir_celula(jogo, aberto, vizinhos, l - 1, c + 1);
            abrir_celula(jogo, aberto, vizinhos, l + 1, c - 1);
            abrir_celula(jogo, aberto, vizinhos, l + 1, c + 1);
        }
    }
}

// Função para verificar se o jogador ganhou
int ganhou(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int aberto[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO]) {
    int l;
    int c;
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            if (!aberto[l][c] && !jogo[l][c].temBomba) {
                return 0;
            }
        }
    }
    return 1;
}

// Função principal
int main() {
    celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    int aberto[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    int vizinhos[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

    int opcao;
    char opcao_char;

    do {
        iniciar_jogo(jogo);
        contar_bombas(jogo, vizinhos);

        // Reiniciar o estado de aberto
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                aberto[i][j] = 0;
            }
        }

        printf("\n\t\t\tCampo Minado\n");

        int encontrouBomba = 0;

        while (!encontrouBomba && !ganhou(jogo, aberto)) {
            imprimir(jogo, aberto, vizinhos);

            int linha, coluna;
            do {
                printf("\nDigite as coordenadas de linha e coluna: ");
                scanf("%d %d", &linha, &coluna);
                if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO || aberto[linha][coluna]) {
                    printf("\nCoordenada Inválida ou já Aberta!");
                }
            } while (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO || aberto[linha][coluna]);

            abrir_celula(jogo, aberto, vizinhos, linha, coluna);

            if (jogo[linha][coluna].temBomba) {
                encontrouBomba = 1;
                imprimir(jogo, aberto, vizinhos);
                printf("\nVocê encontrou uma bomba! Fim de Jogo!\n");
            } else if (ganhou(jogo, aberto)) {
                imprimir(jogo, aberto, vizinhos);
                printf("\nParabéns! Você ganhou!\n");
            }
        }

        printf("\nDigite 1 para jogar novamente: ");
        scanf(" %c", &opcao_char);
        opcao = opcao_char - '0';
    } while (opcao == 1);

    return 0;
}
