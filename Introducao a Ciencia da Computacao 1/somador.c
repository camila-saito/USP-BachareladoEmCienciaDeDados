#include <stdio.h>

int main() {
	int n, m, bit, n1=0, n2=0;

	scanf("%d %d", &n, &m);

	for (int i=1; i<=n; i++) {
		scanf("%d", &bit);
		bit = bit << n - i;
		n1=n1+bit;
	}
	for (int j=1; j<=m; j++) {
		scanf("%d", &bit);
		bit = bit << m - j;
		n2=n2+bit;
	}
	printf("%d", n1+n2);

	return 0;
}