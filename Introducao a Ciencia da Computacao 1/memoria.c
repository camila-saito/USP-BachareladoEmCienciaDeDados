#include <stdio.h>

int main() {
	unsigned short int a, b, c;
	unsigned long x;

	scanf("%hu %hu %hu", &a, &b, &c);

	x=c;
	x=x<<16;
	x=x+b;
	x=x<<16;
	x=x+a;

	printf("%lu\n", x);

	return 0;
}


/*
short int a, b, c;
leia (a, b, c);
escreva(variavel de memoria);
a - 2 primeiros bytes (16 bits)
b - 2 bytes do meio (16 bits)
c - 2 ultimos bytes (16 bits)
c c b b a a
atribuir valor de c numa variavel maior, deslocar os bits,
e somar com o proximo numero
*/