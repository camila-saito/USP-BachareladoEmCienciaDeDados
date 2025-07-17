#include <stdio.h>

int main() {
	unsigned int n;
	float resultado=0.0;

	scanf("%u", &n);

	for (int i=1; i<=n; i++) {
		if ((i % 2) == 1) {
			resultado = resultado + (1.0/i);
		}
		else {
			resultado = resultado - (1.0/i);
		}
	}
	printf("%.4f\n", resultado);

	return 0;
}


/*
if else OU *(-1) >> fica trocando sinal

CUIDADO COM CASTING!!
float >> 1.0/i OU (1/(float)i)
*/