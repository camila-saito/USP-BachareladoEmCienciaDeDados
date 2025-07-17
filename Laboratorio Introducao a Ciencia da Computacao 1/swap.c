#include <stdio.h>
#include <stdlib.h>

int main() {
	int num1, num2, temp, *p1, *p2;

	scanf("%d %d", &num1, &num2);

	p1 = &num1;
	p2 = &num2;
	temp = *p1;
	*p1 = *p2;
	*p2 = temp;

	printf("%d %d\n", num1, num2);

	return 0;
}