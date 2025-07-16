#include <stdio.h>
#include <stdlib.h>

int main() {
	int n, zeros=0;
	scanf("%d", &n);

	for(int i=5; n/i > 0; i=i*5) {
		zeros = zeros + (n/i);
	}

	printf("%d\n", zeros);

	return 0;
}