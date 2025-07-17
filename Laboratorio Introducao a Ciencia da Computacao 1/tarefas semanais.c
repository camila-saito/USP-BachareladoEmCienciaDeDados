#include <stdio.h>

int main() {
	unsigned int dia, valor;

	scanf("%u %u", &dia, &valor);

	if(valor==0){
		switch(dia) {
			case 1: printf("Segunda: Aula de computacao\n");
			case 2: printf("Terca: Treino de volei\n");
			case 3: printf("Quarta: Aula de calculo\n");
			case 4: printf("Quinta: Lista de GA\n");
			case 5: printf("Sexta: Festa da Sacim\n");
			case 6: printf("Sabado: Jantar em familia\n");
			case 7: printf("Domingo: Depressao pos final de semana\n"); break;
			default: printf("Dia nao registrado\n"); break;
		}
	}
	else {
		switch(dia) {
			case 1: printf("Segunda: Aula de computacao\n"); break;
			case 2: printf("Terca: Treino de volei\n"); break;
			case 3: printf("Quarta: Aula de calculo\n"); break;
			case 4: printf("Quinta: Lista de GA\n"); break;
			case 5: printf("Sexta: Festa da Sacim\n"); break;
			case 6: printf("Sabado: Jantar em familia\n"); break;
			case 7: printf("Domingo: Depressao pos final de semana\n"); break;
			default: printf("Dia nao registrado\n"); break;
		}
	}
}