#include <stdio.h>

int main() {
	float p1, p2, nlistas, nfinal, l1, l2, l3, l4;

	scanf("%f %f %f %f %f %f", &p1, &p2, &l1, &l2, &l3, &l4);

	nlistas = (l1+l2+l3+l4)/4;
	nfinal = (p1+p2+nlistas)/3;

	if(nlistas==0){
		printf("Reprovado\n");
	}
	else {
	    if(nfinal >= 5) {
	    	printf("Aprovado\n");
    	}
    	else {
	    	if(nfinal >= 3) {
	    		printf("Recuperacao\n");
	    	}
	    	else {
		    	printf("Reprovado\n");
	    	}
    	}
	}

	return 0;
}