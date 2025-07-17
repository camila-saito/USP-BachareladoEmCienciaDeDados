#include <stdio.h>

void inteiroParaRomano(int n) {
	//vetores com numeros romanos e arabicos
	char romano[13] = {'I', 'a', 'V', 'b', 'X', 'c', 'L', 'd', 'C', 'e', 'D', 'f', 'M'};
	int arabico[13] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

	//ponteiros para vetores de numeros romanos e arabicos
	int *pa;
	char *pr;
	pa = &arabico[12];
	pr = &romano[12];

	while(n>0) {
		if(*pa<=n) {
			//subtrair qtd que ja foi escrita
			n = n - *pa;
			//numeros que exigem 2 letras
			if(*pa==4 || *pa==9 || *pa==40 || *pa==90 || *pa==400 || *pa==900) {
				switch(*pr) {
				case 'a': printf("IV"); break;
				case 'b': printf("IX"); break;
				case 'c': printf("XL"); break;
				case 'd': printf("XC"); break;
				case 'e': printf("CD"); break;
				case 'f': printf("CM"); break;
				}
			}
			else{
				printf("%c", *pr);
			}
		}
		else {
			//conteudo do ponteiro eh maior do que o numero analisado
			pa--;
			pr--;
		}
	}
}

int main() {
	int n, num;

	//leitura da qtd de numeros
	scanf("%d", &n);

	for(int i=0; i<n; i++) {
		//leitura do numero que sera transformado para romano
		scanf("%d", &num);
		inteiroParaRomano(num);
		printf("\n");
	}

	return 0;
}