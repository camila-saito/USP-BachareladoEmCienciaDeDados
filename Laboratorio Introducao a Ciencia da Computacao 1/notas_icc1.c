#include <stdio.h>

/*função para calcular a média de 1 aluno >> parâmetros - matriz[100][25], int a (que representa a linha/aluno calculado), int M (número de 
exercicios*/
float media_aluno(float mat[][25], int a, int M) {
	float acm=0.0, m;

	//soma de todas as notas de um aluno
	for(int j=0; j<M; j++) {
		acm = acm + mat[a][j];
	}
	m = acm/(float)M;
	return m;
}

/*função para calcular a média de um exercicio >> parâmetros - matriz[100][25], int N (número de alunos), int b (que rpresenta a coluna/
exercício calculado)*/
float media_ex(float mat[][25], int N, int b) {
	float acm = 0.0, m;

	//soma das notas de todos os alunos em um exercicio
	for(int i=0; i<N; i++) {
		acm = acm + mat[i][b];
	}
	m = acm/(float)N;

	return m;
}

/*função para ordenar os valores do vetor em ordem decrescente >> parâmetros - vetor (no caso, com as medias dos exercicios*/
void ordenar(float vet[], int M) {
	float temp;
	//for para etapas de classificacao (quantas vezes o vetor sera conferido)
	for(int i=0; i<M-1; i++) {
		//for para comparar pares (pares = total - i(quanto ja foi classificado) - 1(descarta ultimo termo, q ja foi confirido))
		//ordena do final para o inicio
		for(int j=0; j<M-i-1; j++) {
			//compara um valor e o proximo
			if(vet[j]<vet[j+1]) {
				//inverte as posicoes
				temp = vet[j];
				vet[j] = vet[j+1];
				vet[j+1] = temp;
			}
		}
	}
}

int main() {
	int n, m;

	float matriz[100][25];

	scanf("%d %d", &n, &m);

	//leitura da matriz
	for(int i=0; i<n; i++) {
		for(int j=0; j<m; j++) {
			scanf("%f", &matriz[i][j]);
		}
	}

	//calculo das medias dos alunos atraves da função 'media_aluno', e depois se foi aprovado ou nao
	for(int i=0; i<n; i++) {
		float m_aluno = media_aluno(matriz, i, m);
		printf("Media do aluno %d = %.2f : ", i+1, m_aluno);
		if(m_aluno >= 5.0) {
			printf("Aprovado\n");
		}
		else {
			printf("Reprovado\n");
		}
	}

	//criação de um vetor para colocar as medias dos exercicios e depois ordená-los
	float exercicios[m];
	for(int i=0; i<m; i++) {
		exercicios[i] = media_ex(matriz, n, i);
	}
	ordenar(exercicios, m);

	//impressao das medias ja ordenadas
	printf("\n");
	for(int j=0; j<m; j++) {
		printf("%.2f ", exercicios[j]);
	}
	printf("\n");

	return 0;
}