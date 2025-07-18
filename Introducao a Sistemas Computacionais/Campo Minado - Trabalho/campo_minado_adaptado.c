#include "std/io.h"
#include "std/string.h"

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
            jogo[l][c].temBomba = 0; //sem bombas
            jogo[l][c].estaAberta = 0; //tudo fechado
            jogo[l][c].qtdVizinhos = 0; //sem vizinhos
        }
    }

    // Posições fixas das bombas
    jogo[0][6].temBomba = 1;
    jogo[0][0].temBomba = 1;
    jogo[1][1].temBomba = 1;
    jogo[1][9].temBomba = 1;
    jogo[2][0].temBomba = 1;
    jogo[2][5].temBomba = 1;
    jogo[5][3].temBomba = 1;
    jogo[7][4].temBomba = 1;
    jogo[7][8].temBomba = 1;
    jogo[8][1].temBomba = 1;
    jogo[9][2].temBomba = 1;
    jogo[3][9].temBomba = 1;
    jogo[4][3].temBomba = 1;
    jogo[7][5].temBomba = 1;
    jogo[6][8].temBomba = 1;
    jogo[9][5].temBomba = 1;
    jogo[3][7].temBomba = 1;
    jogo[6][5].temBomba = 1;
    jogo[7][6].temBomba = 1;
}

// Função para contar o número de bombas vizinhas
int quantidade_bombas_vizinhas(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int l, int c) {
    int quantidade = 0;
    if (l > 0 && jogo[l - 1][c].temBomba == 1) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && jogo[l + 1][c].temBomba == 1) quantidade++;
    if (c > 0 && jogo[l][c - 1].temBomba==1) quantidade++;
    if (c < TAMANHO_TABULEIRO - 1 && jogo[l][c + 1].temBomba == 1) quantidade++;
    if (l > 0 && c > 0 && jogo[l - 1][c - 1].temBomba == 1) quantidade++;
    if (l > 0 && c < TAMANHO_TABULEIRO - 1 && jogo[l - 1][c + 1].temBomba == 1) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && c > 0 && jogo[l + 1][c - 1].temBomba == 1) quantidade++;
    if (l < TAMANHO_TABULEIRO - 1 && c < TAMANHO_TABULEIRO - 1 && jogo[l + 1][c + 1].temBomba == 1) quantidade++;
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
    prints("\n");

    // Imprimir números das colunas
    prints(" ");
    for (c = 0; c < TAMANHO_TABULEIRO; c++) {
        char s[6];
        itos(c, s, 10);
        prints(" ");
        prints(s);
    }
    prints("\n");
    
    // Linha separadora superior
    prints("----------------------");
    prints("\n");

    // Imprimir linhas do tabuleiro
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        char linhaNum[6];
        itos(l, linhaNum, 10);
        prints(linhaNum); // Número da linha
        prints("|");

        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            if (aberto[l][c] == 1) {
                if (jogo[l][c].temBomba == 1) {
                    prints("s"); // Célula com bomba aberta
                } else {
                    char num[3];
                    itos(vizinhos[l][c], num, 10);
                    prints(num); // Número de bombas vizinhas
                }
            } else {
                prints(" "); // Célula fechada
            }
            prints("|");
        }
        prints("\n");

        // Linha separadora entre as linhas do tabuleiro
        prints("----------------------");
        prints("\n");
    }
}

// Função para abrir células do tabuleiro
void abrir_celula(celula jogo[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int aberto[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int vizinhos[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO], int l, int c) {
    if (l >= 0 && l < TAMANHO_TABULEIRO && c >= 0 && c < TAMANHO_TABULEIRO && aberto[l][c] == 0 ) {
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
    int qtd_casas_vazias = 0;
    for (l = 0; l < TAMANHO_TABULEIRO; l++) {
        for (c = 0; c < TAMANHO_TABULEIRO; c++) {
            if (aberto[l][c] == 1 && !jogo[l][c].temBomba) {
                qtd_casas_vazias++;
            }
        }
    }
    if (qtd_casas_vazias >= (TAMANHO_TABULEIRO * TAMANHO_TABULEIRO - 19)) { // 19 bombas no total
        return 1; // Vitória
    }
    return 0; // Continua o jogo
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

        prints("Campo Minado/n");

        int encontrouBomba = 0;

        while (encontrouBomba == 0 && ganhou(jogo, aberto) == 0) {
            sfill(0);
__cursor=0;
            imprimir(jogo, aberto, vizinhos);

            int linha;
            int coluna;
            
            do {
                prints("\nDigite as coordenadas de linha e coluna: ");
                linha = getc() - '0';
                coluna = getc() - '0';
                if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO || aberto[linha][coluna] == 1) {
                    prints("\nCoordenada Invalida ou ja Aberta!");
                }
                else {
                    abrir_celula(jogo, aberto, vizinhos, linha, coluna);
                }
            } while (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO || aberto[linha][coluna] == 0);

            if (jogo[linha][coluna].temBomba == 1) {
                encontrouBomba = 1;
                sfill(0);
__cursor=0;
                prints("                    \n");
                prints("              ::    \n");
                prints("            ##      \n");
                prints("          ##        \n");
                prints("        @@MMMM      \n");
                prints("        ##MMMM      \n");
                prints("    @@@@mmmm++..##  \n");
                prints("  ##@@MMmmmm::..mm..\n");
                prints("  MM@@mmmmmm++::mmMM\n");
                prints("  @@MMmmmmmmmmmmmmMM\n");
                prints("  @@MMmmmmmmmmmmmmMM\n");
                prints("  MM@@MMmmmmmmmmmm##\n");
                prints("    @@MMmmmmmmmmMM  \n");
                prints("      ##MMMMmmMM    \n");
                prints("\nVoce encontrou uma bomba! Fim de Jogo!");
            } else if (ganhou(jogo, aberto) == 1) {
                sfill(0);
__cursor=0;     
                prints("    ___________  \n");
                prints("   '.=========.' \n");
                prints("   .-\       /-. \n");
                prints("  | (|       |) |\n");
                prints("   '-|       |-' \n");
                prints("     \       /   \n");
                prints("      '.   .'    \n");
                prints("        ) (      \n");
                prints("       .' '.    \n");
                prints("     """"""""   \n");
                prints("\nParabens! Voce ganhou!");
                encontrouBomba = 1; // Termina o loop
            }
        }

        prints("\nDigite 1 para jogar novamente: ");
        opcao_char = getc();
        opcao = opcao_char - '0';
    } while (opcao == 1);

    return 0;
}