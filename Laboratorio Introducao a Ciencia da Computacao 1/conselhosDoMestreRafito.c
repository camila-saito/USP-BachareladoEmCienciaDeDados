#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*funcao para identificar e retirar as palavras invasoras >> parametros - buff (texto do arquivo), palavra (palavra invasora)*/
void palavraInvasora(char *buff, char *palavra) {
	char *posicao = buff; //ponteiro para andar pelo texto (buff)
	int indice; //variavel para dizer qual a posicao numerica em que a palavra invasora esta
	int tam = strlen(palavra);
	char temp1[strlen(buff)]; //vetor para ir colocando o texto modificado
	char *temp2 = buff;

	//ha uma palavra no texto >> posicao da 1a letra encontrada eh atribuida para a variavel 'posicao'
	while((posicao = strstr(posicao, palavra)) != NULL) {
		//calcula a posicao numerica da palavra no vetor
		indice = posicao - temp2;

		//copia os 1os caracteres do texto ate encontrar 'palavra'
		strncpy(temp1, temp2, indice);
		temp1[indice] = '\0';
		//elimina 'palavra' e, se tiver, o espaço seguinte (concatena os 1os caracteres copiados mais todos os outros depois de 'palavra')
		if(*(posicao + tam) == ' ') {
			strcat(temp1, posicao + tam + 1);
		}
		else{
			strcat(temp1, posicao + tam);
		}
		strcpy(temp2, temp1);

		//ajusta a posicao do ponteiro do buff (texto original) para depois da palavra
		posicao = posicao + tam;
	}
	//depois de retiradas todas as palavras invasoras, copia a string resultante no buff
	strcpy(buff, temp2);
}

int main() {
	char palavra[50], arquivo[50];
	FILE *fp;

	scanf(" %s", palavra);
	getchar();
	scanf(" %s", arquivo);
	getchar();

	//abrir arquivo para leitura
	if((fp = fopen(arquivo, "rb")) == NULL) {
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
	char *buff = (char *)malloc(sizeof(char) * sz + 1);
	if(buff == NULL) {
		printf("erro na alocacao\n");
		exit(1);
	}

	//le o arquivo inteiro (um unico read) e armazena no buffer >> le bytes como caracter (1 byte)
	fread(buff, 1, sz, fp);
	// troca o \n por \0
	buff[sz] = '\0';

	palavraInvasora(buff, palavra);
	//imprime a string modificada
	printf("%s", buff);

	fclose(fp);
	free(buff);
	buff = NULL;

	return 0;
}