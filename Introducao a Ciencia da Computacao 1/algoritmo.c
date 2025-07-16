#include <stdio.h>

int main() {
	int i;
	char nome1, nome2, c;

	scanf("%d", &i);
	scanf("%c", &nome1);
	scanf("%c", &nome2);
	scanf("%c", &c);

	int resposta;
	resposta=i;

	printf("a resposta eh %d \n", resposta);

	return 0;
}

/* INICIO
	inteiro: i;
	caracter: nome1, nome2, c;

	leia(i);
	leia(nome1);
	leia(nome2);
	leia(c);

	inteiro: rssposta;
	resposta <- i

	escreva("a resposta eh ");
	escreva(resposta);

FIM. */