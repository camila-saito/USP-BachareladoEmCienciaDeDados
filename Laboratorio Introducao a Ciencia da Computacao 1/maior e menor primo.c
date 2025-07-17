#include <stdio.h>
#include <stdlib.h>

int main() {
	int a, b, ehprimo=1, maior, menor, c;

	scanf("%d %d", &a, &b);
	maior=a;
	menor=b;
	c=a;

	while(c<=b){
		if(c<=1) {
			ehprimo=0;
		}
		else {
			for(int i=2; i*i<=c; i++) {
				if((c%i)==0) {
					ehprimo=0; //não é primo
					break;
				}
			}
		}
		if(ehprimo){
			if(c<menor) {
				menor=c;
			}
			if(c>maior) {
				maior=c;
			}
		}
		c++;
		ehprimo=1;
	}
	if(menor>maior) {
		printf("nenhum primo no intervalo\n");
	}
	else {
		printf("menor primo: %d\n", menor);
		printf("maior primo: %d\n", maior);
	}

	return 0;
}