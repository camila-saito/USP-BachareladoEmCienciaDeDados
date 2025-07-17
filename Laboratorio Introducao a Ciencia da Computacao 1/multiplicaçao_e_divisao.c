#include <stdio.h>

//função para fazer a leitura da matriz >> parâmetros - a(tamanho da matriz), matriz[a][a] (quadrada)
void leitura(int a, int matriz[][a]) {
	for(int i=0; i<a; i++) {
		for (int j=0; j<a; j++) {
			scanf("%d", &matriz[i][j]);
		}
	}
}

//função para fazer a impressao da matriz >> parâmetros - a(tamanho da matriz), matriz[a][a] (quadrada)
void impressao(int a, int matriz[][a]) {
	for(int i=0; i<a; i++) {
		for (int j=0; j<a; j++) {
			printf("%d ", matriz[i][j]);
		}
		printf("\n");
	}
}

//função para verificar se uma matriz é diagonal(tem só zeros fora da diagonal principal >> parâmetros - a(tamanho da matriz), matriz[a][a])
int diagonal(int a, int matriz[][a]) {
	for(int i=0; i<a; i++) {
		for(int j=0; j<a; j++) {
			//se estiver na diagonal principal, vai para próxima iteração
			if(i==j) {
				continue;
			}
			//se algum dos valores fora da diagonal principal nao for 0, matriz nao eh diagonal
			if(matriz[i][j]!=0) {
				return 0;
			}
		}
	}
	return 1;
}

//função para fazer a multiplicação de matrizes >> parâmetros - a(tamanho da matriz), matriz1[a][a] (1a matriz), matriz2[a][a] (2a matriz)
void multiplicacao(int a, int matriz1[][a], int matriz2[][a]) {
	//matriz3 - matriz resultado
	int matriz3[a][a];

	for(int i=0; i<a; i++) {
		for(int j=0; j<a; j++) {
			int temp = 0;
			for(int k=0; k<a; k++) {
				//k - para andar uma linha em uma matriz e andar uma coluna na outra matriz
				temp = temp + (matriz1[i][k] * matriz2[k][j]);
				matriz3[i][j] = temp;
			}
		}
	}

	//impressao da matriz resultado
	for(int i=0; i<a; i++) {
		for(int j=0; j<a; j++) {
			printf("%d ", matriz3[i][j]);
		}
		printf("\n");
	}
}

int main() {
	int n, m;

	scanf("%d %d", &n, &m);

	int mat1[n][n], mat2[m][m];

	leitura(n, mat1);
	leitura(m, mat2);

	printf("Matrizes:\n");
	impressao(n, mat1);
	impressao(m, mat2);

	if(diagonal(n, mat1)) {
		printf("A matriz 1 eh diagonal\n");
	}
	else {
		printf("A matriz 1 nao eh diagonal\n");
	}
	if(diagonal(m, mat2)) {
		printf("A matriz 2 eh diagonal\n");
	}
	else {
		printf("A matriz 2 nao eh diagonal\n");
	}

	//se as matrizes tiverem o mesmo tamanho >> condição para a multiplicação ser possivel
	if(n==m) {
		printf("Matriz multiplicada:\n");
		multiplicacao(n, mat1, mat2);
	}
	else {
		printf("Matrizes de tamanho incompativel\n");
	}

	return 0;
}