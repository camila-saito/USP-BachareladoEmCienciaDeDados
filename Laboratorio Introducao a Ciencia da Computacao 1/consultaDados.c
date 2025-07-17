#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*funçao para descobrir se um numero é primo >> parâmetro - primo (numero que esta sendo verificado)*/
int ehPrimo(int primo) {
	if(primo <= 1) {
		return 0;
	}
	else {
		for(int i=2; i*i<=primo; i++) {
			if((primo%i)==0) {
				return 0;
				}
			}
	}
	return 1;
}

/*funçao para fazer a decomposição de um numero em fatores primos >> parâmetros - num(numero que esta sendo decomposto), *ind (tamanho do vetor
dos primos usados para a decomposição)*/
int *decompor(int num, int *ind) {
	//aloca um vetor para colocar os fatores primos
	int *decomposicao = (int *)malloc(num/2 * sizeof(int));
	int indice = 0;
	if(num > 1) {
		for(int i=2; i<=num; i++) {
			if(ehPrimo(i)) {
				while(num % i == 0) {	
					decomposicao[indice] = i;
					indice++;
					num = num / i;
				}
			}
		}
	}
	//numero de fatores primos
	*ind = indice;
	//retorna o vetor com os fatores primos
	return decomposicao;
}

/*função para ordenar os valores do vetor em ordem decrescente >> parâmetros - vet1[] (IP), vet2[] (codigo), *vet3[] (conteudo), tam (tamanho de 
vet1) */
void ordenar(int vet1[], long long vet2[], char *vet3[], int tam) {
	int temp1;
	long long temp2;
	char *temp3;

	//for para etapas de classificacao (quantas vezes o vetor sera conferido)
	for(int i=0; i<tam-1; i++) {
		//for para comparar pares (pares = total - i(quanto ja foi classificado) - 1(descarta ultimo termo, q ja foi confirido))
		//ordena do final para o inicio
		for(int j=0; j<tam-i-1; j++) {
			//compara um valor e o proximo
			if(vet1[j]<vet1[j+1]) {
				//inverte as posicoes
				temp1 = vet1[j];
				vet1[j] = vet1[j+1];
				vet1[j+1] = temp1;

				temp2 = vet2[j];
				vet2[j] = vet2[j+1];
				vet2[j+1] = temp2;

				temp3 = vet3[j];
				vet3[j] = vet3[j+1];
				vet3[j+1] = temp3;
			}
		}
	}
}

/*funçao para decobrir o tamanho da string (decifrar o codigo que contem o tamanho da string) >> parâmetros - num (codigo), *ind (quantidade de 
fatores primos do codigo)*/
int tamString(int num, int *ind) {
	int tamStr = 0;
	// se for primo, aquele sera o tamanho da string
	if(ehPrimo(num)) {
		return num;
	}
	else {
		int *decomposicao = decompor(num, ind);
		int i;
		for(i=0; i<*ind-1; i++) {
			// tamanho da string = soma dos fatores primos nao repetidos
			if(decomposicao[i] != decomposicao[i+1]) {
				tamStr = tamStr + decomposicao[i];
			}
		}
		//soma do ultimo fator (caso ele nao seja repetido)
		if(*ind > 0 && decomposicao[*ind-1] != decomposicao[*ind-2]) {
			tamStr = tamStr + decomposicao[*ind-1];
		}
		free(decomposicao);
		decomposicao = NULL;
	}
	return tamStr;
}

int main() {
	int n;
	scanf("%d", &n);

	if(n==0) {
		printf("Sem produtos a serem cadastrados\n");
	}
	else {
		//aloca memoria para os vetores
		int *IP = (int *)malloc(n * sizeof(int));
		long long *codigo = (long long *)malloc(n * sizeof(long long));
		char **conteudo = (char **)malloc(n * sizeof(char *)); 

		for(int i=0; i<n; i++) {
			scanf("%d", &IP[i]);
			scanf("%lld", &codigo[i]);
			//decobre tamanho da string, aloca memoria para ela e le ela
			int ind;
			int tamStr = tamString(codigo[i], &ind);
			conteudo[i] = (char *)malloc((tamStr+1) * sizeof(char));
			scanf(" %s", conteudo[i]);

		}

		//ordena os vetores de acordo com o IP (ordem de importancia)
		ordenar(IP, codigo, conteudo, n);

		for(int j=0; j<n; j++) {
			printf("%s", conteudo[j]);
			printf(" %lld\n", codigo[j]);
			free(conteudo[j]);
			conteudo[j] = NULL;
		}

		free(IP);
		free(codigo);
		free(conteudo);
		IP = NULL;
		codigo = NULL;
		conteudo = NULL;
	}

	return 0;
}