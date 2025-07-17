#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*função que retorna os caracteres antes da palavra parâmetro ("palavra") da string A >> parâmetros - strA (string A), pA (pedaço da string A a 
partir de "palavra")*/
char* stringA(char *strA, char *pA) {
    // Tamanho da string resultante (sem "palavra")
    int tam = pA - strA;
    // Se pA terminar com espaço, retirar esse espaço
    if (*(pA - 1) == ' ') {
        tam = tam - 1;
    }
	char *inicio = (char *)malloc((tam + 1) * sizeof(char));
	if(inicio == NULL) {
		printf("erro na alocacao\n");
		exit(1);
	}
	//copia os primeiros (tam) caracteres da string A
	strncpy(inicio, strA, tam);
	inicio[tam] = '\0';

	return inicio;
}

/*função que retorna os caracteres depois da palavra parâmetro ("palavra") da string B >> parâmetros - strB (string B), pB (pedaço da string B a 
partir de "palavra"), palavra (palavra parâmetro)*/
char* stringB(char *strB, char *pB, char *palavra) {
	//avança o ponteiro para depois de "palavra"
	pB = pB + strlen(palavra);
	//acha o tamanho de pB
	int tam = strlen(pB);
	char *fim = (char *)malloc((tam + 1) * sizeof(char));
	if(fim == NULL) {
		printf("erro na alocacao\n");
		exit(1);
	}
	strcpy(fim, pB);

	return fim;
}

/*função para tirar os \n das strings >> parâmetros - str (string analisada)*/
void tirarEnter(char *str) {
	for(int i=0; i<=strlen(str); i++) {
		if(str[i] == '\n' || str[i] == '\r') {
			str[i] = '\0';
		}
	}
}

int main() {
	char strA[131], strB[131], palavra[21];
	char *pA, *pB;

	fgets(strA, 130, stdin);
	tirarEnter(strA);
	fgets(strB, 130, stdin);
	tirarEnter(strB);
	fgets(palavra, 20, stdin);
	tirarEnter(palavra);

	//ponteiro para a posição do primeiro caracter de "palavra" encontrado na string
	pA = strstr(strA, palavra);
	pB = strstr(strB, palavra);
	char *pfinal = (char *)malloc(261 * sizeof(char));
	pfinal[0] = '\0';

	if(pA != NULL) {
		char *pcomeço = stringA(strA, pA);
		strcpy(pfinal, pcomeço);
		free(pcomeço);
		if(pB != NULL) {
			char *pfim = stringB(strB, pB, palavra);
			strcat(pfinal, pfim);
			free(pfim);
		}
	}
	else {
		strcpy(pfinal, strA);
		if(pB != NULL) {
			char *pfim = stringB(strB, pB, palavra);
			strcat(pfinal, pfim);
			free(pfim);
		}
	}
	//tirar espaço em branco, se houver
	if(pfinal[0] == ' ') {
		memmove(pfinal, pfinal + 1, strlen(pfinal));
	}
	printf("%s\n", pfinal);
	free(pfinal);

	return 0;
}