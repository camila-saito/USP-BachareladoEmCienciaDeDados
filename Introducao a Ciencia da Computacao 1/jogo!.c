#include <stdio.h>
#include <stdlib.h> 

void leituraMatriz(int lin, int col, char **mat) {
	for(int i=0; i<lin; i++) {
		for(int j=0; j<col; j++) {
			scanf(" %c", &mat[i][j]);
		}
	}
}

void marcarPosicao(int lin, int col, char **mat) {
	for(int i=0; i<lin; i++) {
		for(int j=0; j<col; j++) {
			int vizinhos = 0;
			if(mat[i][j] == '.') {
				if(j+1 < col && mat[i][j+1] == 'X') {
					vizinhos = vizinhos + 1;
				}
				if(j-1 >= 0 && mat[i][j-1] == 'X') {
					vizinhos = vizinhos + 1;
				}
				if(i+1 < lin && mat[i+1][j] == 'X') {
					vizinhos = vizinhos + 1;
				}
				if(i-1 >= 0 && mat[i-1][j] == 'X') {
					vizinhos = vizinhos + 1;
				}
				if(vizinhos % 2 == 0) {
					mat[i][j] = '*';
				}
			}
			
		}
	}
}

void imprimir(int lin, int col, char **mat) {
	for(int i=0; i<lin; i++) {
		for(int j=0; j<col; j++) {
			printf("%c", mat[i][j]);
		}
		printf("\n");
	}
}

int main() {
	int n, m;
	scanf("%d %d", &n, &m);

	char **tabuleiro = (char **)malloc(n * sizeof(char*));
	for(int i=0; i<n; i++) {
		tabuleiro[i] = (char *)malloc(m * sizeof(char));
	}

	leituraMatriz(n, m, tabuleiro);

	marcarPosicao(n, m, tabuleiro);

	imprimir(n, m, tabuleiro);

	for(int i=0; i<n; i++) {
		free(tabuleiro[i]);
		tabuleiro[i] = NULL;
	}
	free(tabuleiro);
	tabuleiro = NULL;

	return 0;
}