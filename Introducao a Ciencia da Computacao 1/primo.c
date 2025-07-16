#include <stdio.h>
#include <stdlib.h>

int main(void) {
	long q; 

	scanf("%ld", &q);

	long vet[q], *p;
	p = vet;

	for(long i=0; i<q; i++) {
		scanf("%ld", &vet[i]);
		int ehprimo=1;
		if(*p<=1) {
			ehprimo=0; // nao eh primo
		}
		else {
			//verificar se eh primo ou nao
			for(long j=2; j*j<=*p; j++) {
				if(*p%j==0) {
					ehprimo=0; //nao eh primo
					break;
				}
			}
		}
		if(ehprimo) {
			printf("Sim\n");
		}
		else {
			printf("Nao\n");
		}
		p++;
	}

	return 0;
}