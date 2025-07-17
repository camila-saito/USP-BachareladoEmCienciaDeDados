#include <stdio.h>
#include <math.h>

int main() {
	unsigned int n, p1, p2;

	for(n=1000; n<=9999; n++) {
		p1 = n / 100;
		p2 = n % 100;

		if(sqrt(n) == p1+p2) {
			printf("%u\n", n);
		}
	}

	return 0;
}


/*
1234 - 12 e 34
1234 / 100 = 12
1234 % 100 = 34
*/