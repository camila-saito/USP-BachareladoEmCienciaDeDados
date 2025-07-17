#include <stdio.h>

int main() {
	int n;

	scanf("%d", &n);

	float produtos[n][4];

	if(n==0) {
		printf("Sem atividades registradas hoje\n");
	}
	else { //leitura dos valores dos produtos
		for(int i=0; i<n; i++) {
			for(int j=0; j<4; j++) {
				scanf("%f", &produtos[i][j]);
			}
		}

		//calculo do lucro total
		float lucro=0.0, lucro_prod[n];
		for(int i=0; i<n; i++){
			//lucro = preco_venda*qtd_vendida - preco_compra*qtd_comprada
			lucro_prod[i] = produtos[i][1]*produtos[i][3]-produtos[i][0]*produtos[i][2];
			lucro = lucro + lucro_prod[i];
		}

		if(lucro >= 0) {
			printf("lucro: %.2f\n", lucro);
		}
		else {
			printf("prejuizo: %.2f\n", (-1.0)*(lucro));
		}

		//verificar qual produto foi mais lucrativo
		float mais_lucrativo = lucro_prod[0];
		for(int i=1; i<n; i++) {
			if(mais_lucrativo < lucro_prod[i]) {
				mais_lucrativo = lucro_prod[i];
			}
		}

		for(int i=0; i<n; i++) {
			if(lucro_prod[i] == mais_lucrativo) {
				printf("produto: %d lucro relativo: %.2f\n", i+1, mais_lucrativo);
			}
		}
	}

	return 0;
}