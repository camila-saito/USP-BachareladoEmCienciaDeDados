#include <stdio.h>

long long soma_esperada(unsigned int tam) {
	long long s;
	s = (tam*(1+tam))/2;
	return s;
}

long long soma(unsigned int tam) {
	unsigned int vetor[tam-1];

	//leitura do vetor
	for(int i=0; i<tam-1; i++) {
		scanf("%u", &vetor[i]);
	}

	//soma dos elementos do vetor
	long long acm = 0;
	for(int i=0; i<tam-1; i++) {
		acm = acm + vetor[i];
	}
	return acm;
}

int main() {
	unsigned int n;

	//leitura do numero de inteiros
	scanf("%u", &n);

	long long soma1, soma2;
	soma1 = soma_esperada(n);
	soma2 = soma(n);

	unsigned int subt = soma1 - soma2;

	printf("Numero desaparecido: %u\n", subt);

	return 0;
}