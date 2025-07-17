#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*funcao para deixar todos os caracteres de uma string minusculos*/
void tudoMinusculo(char *str) {
	for(int i=0; i<strlen(str); i++) {
		//muda para minusculo caracter por caracter
		str[i] = tolower(str[i]);
	}
}

/*funcao para encontrar quantas vezes uma substring aparece no texto >> parametros - buff (texto), substring*/
int encontrarSubstring(char *buff, char *substring) {
	char *posicao = buff; //ponteiro para andar pelo texto (buff)
	int i; //variavel para dizer qual a posicao numerica em que a substring esta
	int qtd = 0;
	int tam = strlen(substring);

	//ha uma palavra no texto >> posicao da 1a letra encontrada eh atribuida para a variavel 'posicao'
	while((posicao = strstr(posicao, substring)) != NULL) {
		i = posicao - buff;
		//garante que seja uma palavra isolada
		if((i == 0 || buff[i-1] == ' ') && 
			(buff[i+tam] == ' ' || buff[i+tam] == '\n' || buff[i+tam] == '\0' || buff[i+tam] == '\r' || buff[i+tam] == '.' || buff[i+tam] == ',' || buff[i+tam] == '!' || buff[i+tam] == '?' || buff[i+tam] == ';' || buff[i+tam] == ':')) {
			qtd++;
		}
		//ajusta o ponteiro para depois da palavra
		posicao = posicao + tam;
	}

	return qtd;
}

int main() {
	FILE *fp;
	char arquivo[50];
	char substring[11];
	char *buff;

	//le nome do arq
	scanf(" %s", arquivo);
	scanf(" %s", substring);

	//abrir arquivo para leitura
	if((fp = fopen(arquivo, "r")) == NULL) {
		printf("erro na abertura do arquivo\n");
		exit(1);
	}

	//CALCULAR TAMANHO DO ARQUIVO
	//ponteiro aponta para o fim do arquivo (nº de bytes)
	fseek(fp, 0, SEEK_END);
	long long sz = ftell(fp);
	//volta para o inicio (para ler o arquivo)
	rewind(fp);

	//aloca uma string gigante
	buff = (char *)malloc(sizeof(char) * (sz + 1));
	if(buff == NULL) {
		printf("erro na alocacao\n");
		exit(1);
	}

	//le o arquivo inteiro (um unico read) e armazena no buffer
	fread(buff, 1, sz, fp);
	// troca o \n por \0
	buff[sz] = '\0';
	fclose(fp);

	//deixa tanto o texto como a substring tudo minusculo para garantir que nao seja sensitive case
	tudoMinusculo(buff);
	tudoMinusculo(substring);

	int qtd = encontrarSubstring(buff, substring);
	printf("quantidade: %d\n", qtd);

	free(buff);
	buff = NULL;

	return 0;
}