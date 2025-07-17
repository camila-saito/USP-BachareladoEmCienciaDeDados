#include <stdio.h>
#include <ctype.h>

int main() {
	int vet[100], inteiro, i;
	char comando;

	//leitura dos inteiros ate encontrar o 1;
	for(i=0; i<100 ; i++) {
		scanf("%d", &inteiro);
		if(inteiro==1){
			break;
		}
		//coloca o valor no vetor so depois de verificar que nao eh 1
		vet[i]=inteiro;
	}

	scanf(" %c", &comando);

	switch(comando) {
		case 'a': {
			printf("vetor ordenado:");
			int temp;
			for(int j=0; j<i; j++) {
				for(int k=j+1; k<i; k++) {
					if(vet[j]>vet[k]) {
						//inverte os inteiros de lugar (o menor - que estava depois - passa a estar antes)
						temp = vet[j];
						vet[j] = vet[k];
						vet[k] = temp;
					}
				}
			}
			for(int j=0; j<i; j++) {
				printf("%d ", vet[j]);
			}
			printf("\n");
			break;
		}
		case 'b': {
			printf("Numeros de Fibonacci:");
			for(int j=0; j<i; j++) {
				int num1=1, num2=1, temp, fib=0;
				while(num2 <= vet[j]) {
					if(num2 == vet[j]) {
						fib = 1;
						break;
					}
					//simula a sequencia de fibonacci
					temp = num1 + num2;
					num1 = num2;
					num2 = temp;
				}
				if(fib) {
					printf("%d ", vet[j]);
				}
			}
			printf("\n");
			break;
		}
		case 'c': {
			printf("Vetor maiusculo:");
			char maiusculo, caracter;
			for(int j=0; j<i; j++) {
				if(vet[j]>=65 && vet[j]<=90) { //caracter ja eh maiusculo
					printf("%c ", vet[j]);
				}
				else {
					caracter = (char)vet[j]; //transforma o inteiro em caracter
					maiusculo = toupper(caracter); //faz o caracter minusculo ficar maiusculo
					printf("%c ", maiusculo);
				}
			}
			printf("\n");
			break;
		}
		default: printf("Comando inexistente\n");
	}

	return 0;
}