#include <stdio.h>
#include <stdlib.h>

int main() {
	FILE *fp;
	char arquivo[20];
	//leitura do nome do arquivo
	scanf("%[^\n]", arquivo);
	if((fp = fopen(arquivo, "rb")) == NULL) {
		printf("erro na abertura do arquivo\n");
		exit(1);
	}

	//leitura de caracter por caracter e printa caracter por caracter
	char c;
	while(fscanf(fp, "%c", &c) != EOF) {
		printf("%c", c);
	}

	fclose(fp);

	return 0;
}


/*
1000 fgetc(1 char) eh menos eficiente do que 1 fread (de 1000 char)

>>>> opcao 1 <<<<
abre arquivo
enquanto tiver caracter
	leia char
	imprime char
fecha arquivo

>>>> opcao 2 (mais eficiente) <<<<
abre arquivo
calcula o tamanho do arquivo e cria um buffer
leia o buffer de uma vez so
imprime buffer
*/