#include <stdio.h>

// função para fazer a leitura da matriz (tabuleiro) >> parâmetros - n (tamanho da matriz), matriz[26][26] (tabuleiro)
void entradaMatriz(int n, char matriz[][26]){
	for(int i=0; i<n; i++) {
		for(int j=0; j<n; j++) {
			scanf(" %c", &matriz[i][j]);
		}
	}
}

// função para saber se a coordenada acertou um navio >> parâmetros - I (numero de coordenadas), matriz[][26] (tabuleiro), coord[][2] (coordenada)
void acertoErro(int I, char matriz[][26]) {
	for(int i=0; i<I; i++) {
		char coord1;
		int n1, n2, coord2;
		scanf(" %c %d", &coord1, &coord2);

		//passar coordenada (que está em caracteres) para inteiros(posição na matriz/indices)
		n1 = coord1-'A';
		n2 = coord2 - 1;
		//coordenadas coincidem com os indices de um 'N' na matriz
		if(n1>=0 && n1<26 && n2>=0 && n2<26) {
			if(matriz[n1][n2] == 'N') {
				matriz[n1][n2] = 'X';
				printf("acerto!\n");
			}
			else {
				printf("erro!\n");
			}
		}
		else {
			printf("erro!\n");
		}
	}
}

// função para imprimir a matriz resultado (tabuleiro final) >> parâmetros - n (tamanho da matriz), matriz[26][26] (tabuleiro)
void impressao(int n, char matriz[][26]) {
	for(int i=0; i<n; i++) {
		for(int j=0; j<n; j++) {
			printf("%c ", matriz[i][j]);
		}
		printf("\n");
	}
}

int main() {
	int n, I;
	char matriz[26][26];

	scanf("%d", &n);
	entradaMatriz(n, matriz);

	scanf("%d", &I);

	acertoErro(I, matriz);

	impressao(n, matriz);

	return 0;
}