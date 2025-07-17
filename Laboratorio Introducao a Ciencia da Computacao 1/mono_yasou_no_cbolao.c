#include <stdio.h>

/* função que apontará quais lugares estao disponiveis de acordo com as condiçoes dadas >> parâmetros - N(número de linhas), M(numero de 
colunas), mat[20][20] */
void lugar(int N, int M, char mat[][20], char result[][20]) {
	for(int i=0; i<N; i++) {
		for(int j=0; j<M; j++) {
			switch(mat[i][j]) {
				//tem um cosplayer >> modifica valores da linha e da coluna para 'x' (nao disponivel)
				case 'c': {
					for(int k=0; k<N; k++) {
						result[k][j] = 'x';
					}
					for(int l=0; l<M; l++) {
						result[i][l] = 'x';
					}
					break;
				}
				//cadeira esta ocupada
				case 'f': result[i][j] = 'x'; break;
				//cadeira disponivel
				case 'v': {
					//verificar se tem alguma cadeira indisponivel na coluna
					int indisponivel = 0;
					for(int k=0; k<i; k++) {
						if(mat[k][j] == 'c') {
							indisponivel = 1;
							break;
						}
					}
					for(int k=0; k<j; k++) {
						if(mat[i][k] == 'c') {
							indisponivel = 1;
							break;
						}
					}
					if(indisponivel) {
						result[i][j] = 'x';
					}
					else {
						result[i][j] = 'o';;
					} 
					break;
				}
				//cadeira ja foi classificada como disponivel 'o' ou nao 'x'
				default: break;
			}
		}
	}
}

/* funçao que verifica se ha alguma cadeira disponivel ('o') na matriz >> parâmetros - N(número de linhas), M(numero de colunas), 
mat[20][20] */
int disponibilidade(int N, int M, char result[][20]) {
	for(int i=0; i<N; i++) {
		for(int j=0; j<M; j++) {
			//tem cadeira disponivel >> retorna 1 (verdadeiro)
			if(result[i][j] == 'o') {
				return 1;
			}
		}
	}
	//nao entrou no if = nao tem nenhuma disponivel
	return 0;
}

int main() {
	int n, m;
	char matriz[20][20], resultado[20][20];
	
	scanf("%d %d", &n, &m);
	for(int i=0; i<n; i++) {
		for(int j=0; j<m; j++) {
			scanf(" %c", &matriz[i][j]);
		}
	}

	for(int i=0; i<n; i++) {
		for(int j=0; j<m; j++) {
			resultado[i][j] = 0;
		}
	}

	lugar(n, m, matriz, resultado);

	if(disponibilidade(n, m, resultado)) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				printf("%c ", resultado[i][j]);
			}
			printf("\n");
		}
	}
	else {
		printf("Eh um dia triste para os mono Yasuo\n");
	}

	return 0;
}