#include <stdio.h>

int ordenar(int tam, int *v1) {
	int temp;

	//funcao para ordenar numeros em ordem crescente
	for(int i=0; i<tam; i++) {
		for(int j=i+1; j<tam; j++) {
			if(v1[i]>v1[j]) {
				//inverte os numeros de lugar
				temp = v1[i];
				v1[i] = v1[j];
				v1[j] = temp;
			}
		}
	}
}

void ordem(int tam, int *v2) {
	//cria um vetor com os numeros que deveriam aparecer
	for(int i=0; i<tam; i++) {
		v2[i] = i+1;
	}
}

int comparar(int tam, int *vet1) {
	int vet2[tam];

	ordenar(tam, vet1);
	ordem(tam, vet2);

	//compara se os elementos do vetor ordenado sao os mesmos que deveriam aparecer
	for(int i=0; i<tam; i++) {
		if(vet1[i]!=vet2[i]) {
			//quando for diferente, encontrou o numero faltante
			printf("%d", vet2[i]);
			break;
		}
	}
}

int main() {
	int n;

	//leitura do numero de inteiros
	scanf("%d", &n);

	int vet[n-1];

	//leitura dos numeros
	for(int i=0; i<n-1; i++) {
		scanf("%d", &vet[i]);
	}

	printf("Numero desaparecido: ");
	comparar(n, vet);
	printf("\n");

	return 0;
}