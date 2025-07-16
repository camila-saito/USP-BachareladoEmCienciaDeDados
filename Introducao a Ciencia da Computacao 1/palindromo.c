#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n;

	//digitar numero de caracteres
	scanf("%d", &n);

	char palavra[n+1]; // +1 para adicionar espaço para o '\0'
	for(int i=0; i<n; i++) {
		scanf(" %c", &palavra[i]);
	}
	palavra[n] = '\0'; // ultimo elemento sera '\0'

	//contagem para cada letra do alfabeto
	int contagem[26];
	for(int j=0; j<n; j++) {
		contagem[palavra[j]-'a']++;
	}

	//verifivar as letras que tem representacao impar na contagem
	int impar=0;
	for(int k=0; k<26; k++) {
		if(contagem[k] % 2 != 0) {
			impar++;
		}
	}

	if(impar > 1) { //maximo de 1 letra com representacao impar
		printf("Nao\n");
	}
	else {
		printf("Sim\n");
	}

	return 0;
}