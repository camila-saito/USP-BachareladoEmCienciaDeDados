#include <stdio.h>
#include <stdlib.h>

int main() {
	int x, y;

	//leitura do tamanho do vetor
	scanf("%d", &x);

	//leitura dos inteiros
	int a[10], b[10];
	for(int i=0; i<x; i++) {
		scanf("%d", &a[i]);
	}

	scanf("%d", &y);
	for(int j=0; j<y; j++) {
		scanf("%d", &b[j]);
	}

	int intersecao=1, diferenca=1; //conjuntos sao vazios
	printf("intersecao: ");
	for(int i=0; i<x; i++) {
		for(int j=0; j<y; j++) {
			if(a[i]==b[j]) { //quais valores sao iguais nos 2 vetores
				printf("%d ", a[i]);
				intersecao=0; //conjunto nao sera vazio
				break;
			}
		}
	}
	if(intersecao) { 
		//se intersecao=1, nenhum valor eh igual em ambos os vetores (nao entrou no if)
		printf("vazio");
	}

	printf("\na - b: ");
	for(int i=0; i<x; i++) {
		int exclusivo_a = 1;
		for(int j=0; j<y; j++) {
			if(a[i]==b[j]) {
				//verifica se tem algum valor igual no vetor b
				exclusivo_a = 0;
				break;
			}
		}
		if(exclusivo_a) {
			// nao entrou no if >> nao nenhum valor igual em b
			printf("%d ", a[i]);
			diferenca=0; //conjunto nao sera vazio
		}
	}
	if(diferenca) {
		printf("vazio");
	}
	printf("\n");

	return 0;
}