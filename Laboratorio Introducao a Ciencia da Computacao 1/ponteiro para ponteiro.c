#include <stdio.h>
#include <stdlib.h>

int main() {
	int x, *p1, **p2;
	p1 = &x;
	p2 = &p1;

	scanf("%d", &x);

	printf("Valor inicial da variavel: %d\n", x);
	printf("Valor do ponteiro antes do incremento: %d\n", *p1);
	*p1 = x + 10;
	printf("Valor do ponteiro depois do incremento: %d\n", *p1);
	printf("Valor do ponteiro para ponteiro antes do incremento: %d\n", **p2);
	**p2 = *p1 + 10;
	printf("Valor do ponteiro para ponteiro depois da mudanca: %d\n", **p2);
	printf("Valor final da variavel: %d\n", x);

	return 0;
}