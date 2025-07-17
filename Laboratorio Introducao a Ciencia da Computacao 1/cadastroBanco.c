#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct p {
	char *nome;
	char *cpf;
	int idade;
	float saldo;
	float credito;
} pessoa;

/*função que faz a leitura de todas as informações de cada pessoa >> parâmetros - cliente (pessoa que esta sendo registrada)*/
void leituraRegistro(pessoa *cliente){
	char vet[300];
	//vet = le nome;
	scanf(" %[^,],", vet);
	//aloca espaço para o nome
	cliente->nome = (char *)malloc((strlen(vet)+1) * sizeof(char));
	//copia vet para nome
	strcpy(cliente->nome, vet);

	cliente->cpf = (char *)malloc(12 * sizeof(char));
	scanf(" %[^,],", cliente->cpf);

	scanf("%d,", &cliente->idade);
	scanf("%f,", &cliente->saldo);
	scanf("%f", &cliente->credito);
}

/*função que imprime os dados de cada pessoa >> parametros - cliente (pessoa que foi registrada), i(a qual pessoa se refere) */
void imprimeRegistro(pessoa cliente, int i) {
	printf("Dados da pessoa %d:\n", i+1);
	printf("Nome: %s\n", cliente.nome);
	printf("CPF: %s\n", cliente.cpf);
	printf("Idade: %d\n", cliente.idade);
	printf("Saldo: %.2f\n", cliente.saldo);
	printf("Credito: %.2f\n\n", cliente.credito);
}

/*função que verifica se uma pessoa está devendo, e se esta, quanto ela deve >> parâmetros - cliente (pessoa que esta sendo analisada), divida
(quanto essa pessoa esta devendo)*/
int devedores(pessoa cliente, float *divida) {
	if(cliente.saldo < cliente.credito) {
		*divida = cliente.credito - cliente.saldo;
		return 1;
	}
	return 0;
}

int main() {
	int n;
	scanf("%d", &n);

	//aloca um vetor de pessoas
	pessoa *listaPessoas = (pessoa *)malloc(n * sizeof(pessoa));

	for(int i=0; i<n; i++) {
		leituraRegistro(&listaPessoas[i]);
		imprimeRegistro(listaPessoas[i], i);
	}

	int qtdDevedores=0;
	printf("Pessoas endividadas:");
	for(int i=0; i<n; i++) {
		float divida;
		//verificar quem esta endividado
		if(devedores(listaPessoas[i], &divida)) {
			printf("\nNome: %s\n", listaPessoas[i].nome);
			printf("CPF: %s\n", listaPessoas[i].cpf);
			printf("Divida: %.2f\n", divida);
			qtdDevedores = qtdDevedores + 1;
		}
	}
	if(qtdDevedores == 0) {
		printf("\nNenhuma pessoa endividada encontrada.\n");
	}

	for(int i=0; i<n; i++) {
		free(listaPessoas[i].nome);
		free(listaPessoas[i].cpf);
	}
	free(listaPessoas);

	return 0;
}