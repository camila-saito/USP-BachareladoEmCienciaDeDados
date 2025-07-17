#include <stdio.h>

//funcao que define a colocacao
int colocacao(int n, int v, int vet_copia[]) {
	//verifica quantidade de empates
	int empate = 0;
	
	for(int i=0; i<n; i++) {
		if(vet_copia[i]==vet_copia[i-1]){
			//teve um empate >> posicao deve diminuir
			empate=empate+1;
		}
		//verifica qual a posicao do numero no vetor ordenado
		if(v==vet_copia[i]) {
			return i+1-empate;
		}
	}
}

//funcao para colocar numeros em ordem decrescente
void BubbleSwap(int n, int v[]) {
	int temp;
	//for para etapas de classificacao (quantas vezes o vetor sera conferido)
	for(int i=0; i<n-1; i++) {
		//for para comparar ate chegar onde nao foi classificado
		//ordena do fim para o começo
		for(int j=0; j<n-i-1; j++) {
			if(v[j] < v[j+1]) {
				//inverte as posicoes
				temp = v[j];
				v[j] = v[j+1];
				v[j+1] = temp;
			}
		}
	}
}

int main() {
	int n;

	//leitura do numero de atletas
	scanf("%d", &n);

	int vet[n], vet_copia[n];

	//leitura das pontuacoes
	for(int i=0; i<n; i++) {
		scanf("%d", &vet[i]);
		//cria um vetor igual(que depois sera ordenado)
		vet_copia[i] = vet[i];
	}

	//ordena o vetor copiado em ordem decrescente
	BubbleSwap(n, vet_copia);

	for(int i=0; i<n; i++) {
		printf("%dº Atleta, %d Pontos, %dº Colocado\n", i+1, vet[i], colocacao(n, vet[i], vet_copia));
	}

	return 0;
}