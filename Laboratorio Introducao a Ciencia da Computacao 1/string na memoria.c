#include <stdio.h>
#include <stdlib.h>

int main() {
	char palavra[26], *p;

	scanf("%[^\n]s", palavra); // ler até encontrar enter
	p = palavra; // ponteiro aponta para string

	while(*p != '\0') {
		printf("%c\n", *p);
		p++; // passa para endereço do proximo caracter
	}

	return 0;
}

/*
ultimo caracter da string >> \0
char *ps = palavra
while(*ps != '\0')

for(*ps = palavra; *ps != '\0'; ps++)

p = palavra >> ponteiro aponta para string

IMPRIMIR 1º CARACTER VIA PONTEIRO
printf("%c\n", *p) >> conteudo do primeiro item da string
printf("%c\n", p[0])
p++ >> passa para proximo item > [1]
*/