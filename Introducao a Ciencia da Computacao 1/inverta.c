#include <stdio.h>
#include <stdlib.h>

int main() {
	int x;

    scanf("%d", &x);

    int a[x];

	//ler sequencia de digitos do vetor
	for(int i=0; i<x; i++) {
		scanf("%d", &a[i]);
	}

	int *p;

    //ponteiro para o ultimo item do vetor
	p = &a[x-1];
		
	while(p>=&a[0]) {

		//escrever sequencia a partir da ultima posição 'a[x-1]' até 'a[0]'
		printf("%d ", *p);
		p--;
	}
	printf("\n");

	return 0;
}