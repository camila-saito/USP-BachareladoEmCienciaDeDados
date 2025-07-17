#include <stdio.h>

int main() {
	int x, contador=0;
	int n200, n100, n50, n20, n10, n5, n2, n1;
	n200=n100=n50=n20=n10=n5=n2=n1=0;

	scanf("%d", &x);

	while(contador<x) {
		if((x-contador)>=200) {
			n200=n200+1;
			contador=contador+200;
		}
		else if((x-contador)>=100) {
			n100=n100+1;
			contador=contador+100;
		}
		else if((x-contador)>=50) {
			n50=n50+1;
			contador=contador+50;
		}
		else if((x-contador)>=20) {
			n20=n20+1;
			contador=contador+20;
		}
		else if((x-contador)>=10) {
			n10=n10+1;
			contador=contador+10;
		}
		else if((x-contador)>=5) {
			n5=n5+1;
			contador=contador+5;
		}
		else if((x-contador)>=2) {
			n2=n2+1;
			contador=contador+2;
		}
		else {
			n1=n1+1;
			contador=contador+1;
		}
	}
	printf("%d nota(s) de R$ 200\n", n200);
	printf("%d nota(s) de R$ 100\n", n100);
	printf("%d nota(s) de R$ 50\n", n50);
	printf("%d nota(s) de R$ 20\n", n20);
	printf("%d nota(s) de R$ 10\n", n10);
	printf("%d nota(s) de R$ 5\n", n5);
	printf("%d nota(s) de R$ 2\n", n2);
	printf("%d moeda(s) de R$ 1\n", n1);

	return 0;
}