#include <stdio.h>
#include <math.h>

int main() {
	unsigned int a, b, c, d;

	scanf("%u %u %u %u", &a, &b, &c, &d);

	if(a==0 || b==0 || c==0 || d==0){
		printf("nenhum!");
	}
	else {
		if(a==b && b==c && c==d) {
			printf("quadrado!");
		}
		else {
			if(a==c && b==d) {
				printf("retangulo!");
			}
			else {
				printf("nenhum!");
			}
		}
	}
}