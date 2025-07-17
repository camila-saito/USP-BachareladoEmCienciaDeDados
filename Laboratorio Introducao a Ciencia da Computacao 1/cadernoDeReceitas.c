#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*estrutura de uma receita*/
typedef struct Receita {
	char *nome;
	int farinha;
	int ovos;
	int oleo;
	int acucar;
	int chocolate;
} receita;

/*estrutura do estoque de ingredientes*/
typedef struct Ingredientes {
	int qtdFarinha;
	int qtdOvos;
	int qtdOleo;
	int qtdAcucar;
	int qtdChocolate;
} ingredientes;

/*funçao que registra uma nova receita >> parâmetros - listaReceitas (vetor de structs com as receitas), nReceitas (numero da receita)*/
void registrar(receita **listaReceitas, int *nReceitas) {
	//realoca memoria para que caiba a nova receita
	*listaReceitas = (receita *)realloc(*listaReceitas, (*nReceitas + 1) * sizeof(receita));

	//vet = le nome;
	char vet[300];
	scanf(" %s", vet);
	//aloca espaço para o nome da receita
	(*listaReceitas)[*nReceitas].nome = (char *)malloc((strlen(vet)+1) * sizeof(char));
	//copia vet para nome da receita
	strcpy((*listaReceitas)[*nReceitas].nome, vet);

	scanf("%d", &(*listaReceitas)[*nReceitas].farinha);
	scanf("%d", &(*listaReceitas)[*nReceitas].ovos);
	scanf("%d", &(*listaReceitas)[*nReceitas].oleo);
	scanf("%d", &(*listaReceitas)[*nReceitas].acucar);
	scanf("%d", &(*listaReceitas)[*nReceitas].chocolate);
}

/*função para registrar a compra de ingredientes >> parâmetros - qtdIngredientes (struct do estoque de ingredientes)*/
void comprar(ingredientes *qtdIngredientes) {
	int compra; //variavel para representar a quantidade comprada do ingrediente

	scanf("%d", &compra);
	qtdIngredientes->qtdFarinha = qtdIngredientes->qtdFarinha + compra;

	scanf("%d", &compra);
	qtdIngredientes->qtdOvos = qtdIngredientes->qtdOvos + compra;

	scanf("%d", &compra);
	qtdIngredientes->qtdOleo = qtdIngredientes->qtdOleo + compra;

	scanf("%d", &compra);
	qtdIngredientes->qtdAcucar = qtdIngredientes->qtdAcucar + compra;

	scanf("%d", &compra);
	qtdIngredientes->qtdChocolate = qtdIngredientes->qtdChocolate + compra;
}

/*função que registra que uma receita foi feita ou caso nao tenha sido possivel faze-la >> parâmetros - listaReceitas (vetor de structs com as 
receitas, qtdIngredientes (struct com o estoque de ingredientes), nReceitas (numero de receitas registradas)*/
void fazer(receita *listaReceitas, ingredientes *qtdIngredientes, int nReceitas) {
	// numero da receita que se deseja fazer
	int n;
	scanf("%d", &n);

	//caso o numero nao exista no registro (registro começa no 0)
	if(n >= nReceitas) {
		printf("Receita nao encontrada!\n");
	}
	else{
		if(qtdIngredientes->qtdFarinha >= listaReceitas[n].farinha) {
			if(qtdIngredientes->qtdOvos >= listaReceitas[n].ovos) {
				if(qtdIngredientes->qtdOleo >= listaReceitas[n].oleo) {
					if(qtdIngredientes->qtdAcucar >= listaReceitas[n].acucar) {
						if(qtdIngredientes->qtdChocolate >= listaReceitas[n].chocolate) {
							//tem todos os ingredientes
							printf("%s feita com sucesso!\n", listaReceitas[n].nome);
							qtdIngredientes->qtdFarinha = qtdIngredientes->qtdFarinha - listaReceitas[n].farinha;
							qtdIngredientes->qtdOvos = qtdIngredientes->qtdOvos - listaReceitas[n].ovos;
							qtdIngredientes->qtdOleo = qtdIngredientes->qtdOleo - listaReceitas[n].oleo;
							qtdIngredientes->qtdAcucar = qtdIngredientes->qtdAcucar - listaReceitas[n].acucar;
							qtdIngredientes->qtdChocolate = qtdIngredientes->qtdChocolate - listaReceitas[n].chocolate;
						}
						else {
							printf("Chocolate insuficiente\n");
						}
					}
					else {
						printf("Acucar insuficiente\n");
					}
				}
				else {
					printf("Oleo insuficiente\n");
				}
			}
			else {
				printf("Ovos insuficientes\n");
			}
		}
		else {
			printf("Farinha insuficiente\n");
		}
	}
}

/*função para sair do programa >> parâmetros - qtdIngredientes(struct com o estoque de ingredientes), nReceitas(numero de receitas registradas)*/
void sair(ingredientes qtdIngredientes, int nReceitas) {
	printf("Quantidade no estoque:\n");
	printf("Acucar: %d\n", qtdIngredientes.qtdAcucar);
	printf("Chocolate: %d\n", qtdIngredientes.qtdChocolate);
	printf("Farinha: %d\n", qtdIngredientes.qtdFarinha);
	printf("Oleo: %d\n", qtdIngredientes.qtdOleo);
	printf("Ovos: %d\n", qtdIngredientes.qtdOvos);
	printf("Receitas cadastradas: %d\n", nReceitas);
}

int main() {
	char comando;
	int nReceitas = 0;

	//estoque esta vazio inicialmente
	ingredientes qtdIngredientes;
	qtdIngredientes.qtdFarinha = 0;
	qtdIngredientes.qtdOvos = 0;
	qtdIngredientes.qtdOleo = 0;
	qtdIngredientes.qtdAcucar = 0;
	qtdIngredientes.qtdChocolate = 0;

	//nao ha nenhuma receita registrada inicialmente
	receita *listaReceitas = NULL;
	
	do {
		scanf(" %c", &comando);
		switch(comando) {
			case 'R': {
				registrar(&listaReceitas, &nReceitas);
				nReceitas = nReceitas + 1;
				break;
			}
			case 'C': {
				comprar(&qtdIngredientes);
				break;
			}
			case 'F': {
				fazer(listaReceitas, &qtdIngredientes, nReceitas);
				break;
			}
			case 'S': {
				sair(qtdIngredientes, nReceitas);
				break;
			}
			default: printf("Codigo invalido\n");
		}
	} while(comando != 'S');

	for(int i=0; i<nReceitas; i++) {
		free(listaReceitas[i].nome);
	}
	free(listaReceitas);

	return 0;
}