#include <stdio.h>
#include <stdlib.h> 
#include <math.h>

/*funçao que le os elementos da matriz >> parâmetros - tamMat (tamanho da matriz quadrada), mat[] (matriz em formato de vetor - linear)*/
void leitura(int tamMat, int mat[]) {
	for(int i=0; i<tamMat; i++) {
		for(int j=0; j<tamMat; j++) {
			//encontra a posição do elemento na sequencia linear (linhas estao uma seguida da outra)
			//i (numero da linha) * tamMat (quantos elementos tem cada linha) + j (em qual coluna esta)
			scanf("%d", &mat[i*tamMat + j]);
		}
	}
}

/*funçao que imprima a matriz em formato de matriz >> parâmetros - tamMat (tamanho da matriz quadrada), mat[] (matriz em formato de vetor - linear)*/
void impressao(int tamMat, int mat[]) {
	for(int i=0; i<tamMat; i++) {
		for(int j=0; j<tamMat; j++) {
			printf("%d ", mat[i*tamMat + j]);
		}
		printf("\n");
	}
}

int main() {
	//le a quantidade de elementos
	int n;
	scanf("%d", &n);

	//aloca um vetor de tamanho n (matriz linear)
	int *mat = (int *)malloc(n * sizeof(int));

	//define o tamanho da matriz quadrada
	int tamMat = sqrt(n);

	leitura(tamMat, mat);
	impressao(tamMat, mat);

	free(mat);
	mat = NULL;

	return 0;
}	