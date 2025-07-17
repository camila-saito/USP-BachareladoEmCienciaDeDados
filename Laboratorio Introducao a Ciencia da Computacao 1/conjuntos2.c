#include <stdio.h>
#include <stdlib.h>

/*função para fazer a leitura dos elementos dos vetores/conjuntos >> parâmetros - tam (tamanho do vetor), vet[] */
void leitura(int tam, int vet[]) {
	for(int i=0; i<tam; i++) {
		scanf("%d", &vet[i]);
	}
}

/*funçao para imprimir os elementos dos vetores/conjuntos >> parâmetros - tam (tamanho do vetor), vet[]*/
void impressao(int tam, int vet[]) {
	for(int i=0; i<tam; i++) {
		printf("%d ", vet[i]);
	}
}

/*funçao que verifica se há elementos iguais nos vetores >> parâmetros - vet[] (vetor que será comparado), tam (tamanho de vet), x (elemento que 
esta sendo verificado se há algum elemento igual/de mesmo valor em vet*/
int elementosIguais(int vet[], int tam, int x) {
	for(int i=0; i<tam; i++) {
		//retorna falso(0) se tem um elemento igual a x em vet
		if(vet[i]==x) {
			return 0;
		}
	}
	return 1;
}

/*funçao que faz a uniao de 2 vetores/conjuntos >> parâmetros - n1 (tamanho do vetor 1), n2 (tamanho do vetor 2), vet1[], vet2[], *tamUniao 
(ponteiro que aponta para uma variavel que será modificada dentro da funçao e que representa o tamanho do vetor/conjunto resultante) */
int *uniao(int n1, int n2, int vet1[], int vet2[], int *tamUniao) {
	//aloca um vetor de tamanho n1+n2 (tamanho maximo que o conjunto pode ter)
	int *vetUniao = (int *)malloc((n1+n2) * sizeof(int));

	int i;
	//coloca todos os elementos de vet1 no conjunto uniao
	for(i=0; i<n1; i++) {
		vetUniao[i] = vet1[i];
	}
	//coloca somente os elementos de vet2 que ainda nao estao no conjunto uniao (nao sao repetidos)
	for(int j=0; j<n2; j++) {
		if(elementosIguais(vetUniao, n1+n2, vet2[j])) {
			vetUniao[i] = vet2[j];
			i++;
		}
	}

	*tamUniao = i;

	return vetUniao;
}

/*funçao que calcula a intersecçao entre dois vetores/conjuntos >> parâmetros - n1 (tamanho do vetor 1), vet1[], vet2[], *tamInter (ponteiro que 
aponta para uma variavel que será modificada dentro da funçao e que representa o tamanho do vetor/conjunto resultante) */
int *inter(int n1, int n2, int vet1[], int vet2[], int *tamInter) {
	//aloca um vetor de tamanho n1 (tamanho maximo do vetor - conjunto intersecção nao sera maior que nenhum dos dois vetores)
	int *vetInter = (int *)malloc((n1+n2) * sizeof(int));

	//coloca no conjunto intersecçao somente os elementos que sao iguais em ambos os conjuntos
	int indice = 0;
	for(int i=0; i<n1; i++) {
		if(!elementosIguais(vet2, n2, vet1[i])) {
			vetInter[indice] = vet1[i];
			indice++;
		}
	}

	*tamInter = indice;

	return vetInter;
}

/*funçao que calcula a diferença entre um vetor/conjunto e outro >> parâmetros - parâmetros - n1 (tamanho do vetor 1), n2 (tamanho do vetor 2), 
vet1[], vet2[], *tamDif (ponteiro que aponta para uma variavel que será modificada dentro da funçao e que representa o tamanho do vetor/conjunto 
resultante) */
int *diferenca(int n1, int n2, int vet1[], int vet2[], int *tamDif) {
	//aloca um vetor de tamanho n1 (tamanho maximo que o conjunto pode ter)
	int *vetDif = (int *)malloc(n1 * sizeof(int));

	int indice = 0;
	for(int i=0; i<n1; i++) {
		//coloca no conjunto diferença somente os elementos que estao somente em vet1
		if(elementosIguais(vet2, n2, vet1[i])) {
			vetDif[indice] = vet1[i];
			indice++;
		}
	}

	*tamDif = indice;

	return vetDif;
}

/*função para ordenar os valores do vetor em ordem crescente >> parâmetros - vet[], tam (tamanho de vet) */
void ordenar(int vet[], int tam) {
	int temp;
	//for para etapas de classificacao (quantas vezes o vetor sera conferido)
	for(int i=0; i<tam-1; i++) {
		//for para comparar pares (pares = total - i(quanto ja foi classificado) - 1(descarta ultimo termo, q ja foi confirido))
		//ordena do final para o inicio
		for(int j=0; j<tam-i-1; j++) {
			//compara um valor e o proximo
			if(vet[j]>vet[j+1]) {
				//inverte as posicoes
				temp = vet[j];
				vet[j] = vet[j+1];
				vet[j+1] = temp;
			}
		}
	}
}

int main() {
	int a, b;

	// lê o tamanho do 1o vetor, aloca um vetor desse tamanho, lê seus elementos, ordena-os e imprime-os
	scanf("%d", &a);
	int *vetA = (int *)malloc(a * sizeof(int));
	leitura(a, vetA);
	printf("conjunto A: ");
	if(a==0) {
		printf("vazio");
	}
	else {
		ordenar(vetA, a);
		impressao(a, vetA);
	}

	// lê o tamanho do 2o vetor, aloca um vetor desse tamanho, lê seus elementos, ordena-os e imprime-os
	scanf("%d", &b);
	int *vetB = (int *)malloc(b * sizeof(int));
	leitura(b, vetB);
	printf("\nconjunto B: ");
	if(b==0) {
		printf("vazio");
	}
	else {
		ordenar(vetB, b);
		impressao(b, vetB);
	}

	//calcula os vetores/conjuntos uniao, intersecçao e diferença
	int tamUniao, tamInter, tamDif;
	int *vetUniao = uniao(a, b, vetA, vetB, &tamUniao);
	int *vetInter = inter(a, b, vetA, vetB, &tamInter);
	int *vetDif = diferenca(tamUniao, tamInter, vetUniao, vetInter, &tamDif);

	//ordena o vetor uniao e imprime-o
	printf("\nA uniao B: ");
	if(tamUniao==0) {
		printf("vazio");
	}
	else{
		ordenar(vetUniao, tamUniao);
		impressao(tamUniao, vetUniao);
	}

	//ordena o vetor diferença e imprime-o
	printf("\n(A uniao B) - (A interseccao B): ");
	if(tamDif==0) {
		printf("vazio");
	}
	else{
		ordenar(vetDif, tamDif);
		impressao(tamDif, vetDif);
	}
	printf("\n");

	free(vetA);
	free(vetB);
	free(vetUniao);
	free(vetInter);
	free(vetDif);

	return 0;
}

/*
realocar >> alocar um a um os elementos de um vetor de inteiros
int *vet = NULL;	//vet nao tem elementos!!
//um a um, ALOCA ESPAÇO no vetor e acrescenta um elemento a ele
for(int i=0; i<tam; ++i) {
	vet = (int *)realloc(vet, (i+1)*sizeof(int));
	vet[i] = i;
}
return vet;

memoria heap - variaveis PERMANECEM depois de retornadas
memoria stack - variaveis LOCAIS >> memoria estatica: variaveis somem depois >> *nao da pra retornar o endereço se utilizada no exemplo
*/