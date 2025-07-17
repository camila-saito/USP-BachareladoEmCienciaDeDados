#include <stdio.h>
#include <string.h>

int ehpalindromo (int x, char *vet) {
	char *p1, *p2;
	//ponteiro para inicio da palavra
	p1 = vet;
	//ponteiro para final da palavra
	p2 = &vet[x-1];

	while(p2>p1) {
		if(*p1 != *p2) {
			//tem alguma letra diferente
			return 0;
		}
		else {
			p1++;
			p2--;
		}
	}
	return 1;
}

int main() {
	char palavra[100];
	int n, a;
	palavra[100] = '\0';

	//leitura do numero de palavras
	scanf("%d", &n);

	for(int i=0; i<n; i++) {
		//leitura da palavra
		scanf("%s", palavra);
		//definir tamanho da palavra
		a = strlen(palavra);

		if(ehpalindromo(a, palavra)) {
			printf("%s eh um palindromo\n", palavra);
		}
		else {
			printf("%s nao eh um palindromo\n", palavra);
		}
	}

	return 0;
}