#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n, m;

	scanf("%d %d", &n, &m);

	int vet_n[n], vet_m[m];

	//leitura dos bits no vetor
	for(int i=0; i<n; i++) {
		scanf("%d", &vet_n[i]);
	}

	for(int j=0; j<m; j++) {
		scanf("%d", &vet_m[j]);
	}

	int num_n=0, num_m=0;
	for(int *p = vet_n; p < vet_n + n; p++) {
		//desloca uma casa decimal do numero
		num_n = num_n << 1;
		//adiciona o numero correspondente ao binario (de acordo com o vetor)
		num_n = num_n + *p;
	}

	for(int *p = vet_m; p < vet_m + m; p++) {
		num_m = num_m << 1;
		num_m = num_m + *p;
	}

	printf("%d\n", num_n+num_m);

	return 0;
}