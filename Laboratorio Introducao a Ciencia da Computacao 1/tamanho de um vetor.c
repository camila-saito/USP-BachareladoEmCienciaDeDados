#include <stdio.h>
#include <math.h>

int main() {
	int n;
	double l, area, PI;
	PI=3.14159265358979323846;

	scanf("%d %lf", &n, &l);
	
	area=(n*l*l)/(4*(tan(PI/n)));
	printf("%lf\n", area);

	return 0;
}