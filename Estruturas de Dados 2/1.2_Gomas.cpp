#include <iostream>
#include <vector>

using namespace std;

//retorna o numero de trocas feitas durante a ordenacao
int merge(vector<long>& p, long ini, long fim) {
    long meio = (ini + fim)/2;
    long i = ini; //"ponteiro" que percorre a parte da esquerda
    long j = meio; //"ponteiro" que percorre a parte da direita
    long k = 0; //"ponteiro" q percorre vetor auxiliar
    int cont = 0; //contador de trocas

    long tam = fim - ini; //tamanho do vetor resultante da divisao (vetor auxiliar)
    vector<long> vaux(tam); //vetor auxilixar onde sera colocado a ordenacao

    while(k<tam && j<fim && i<meio) { //loop para quando a dir ou a esq tiver sido percorrida inteira
        if(p[i] < p[j]) {
            vaux[k] = p[i];
            k++;
            i++;
        }
        else { //p[i] > p[j]
            vaux[k] = p[j];
            k++;
            j++;
            //se p[i] > p[j], todos os elementos da direita depois de p[i] sao maiores do que p[j] (esta ordenado)
            cont += meio - i;
        }
    }

    while(k<tam && i<meio) { //coloca os elementos restantes da esquerda
        vaux[k] = p[i];
        k++;
        i++;
    }
    while(k<tam && j<fim) { //coloca os elementos restantes da direita
        vaux[k] = p[j];
        k++;
        j++;
    }

    for(k=0; k<tam; k++)  { //copia o vetor ordenado para o original
        p[ini+k] = vaux[k];
    }
    
    return cont;
}

// retorna o total de trocas que foram feitas na ordenacao e ordena o vetor p
int mergesort(vector<long>& p, long ini, long fim) {
    if(ini == fim-1) //parada >> vetor tem 1 elemento (ini), ja q p[fim] fica fora dos limites
        return 0;

    long meio = (ini + fim)/2;

    //divide o vetor ao meio para a ordenacao por partes
    int contEsq = mergesort(p, ini, meio); //esquerda da divisao
    int contDir = mergesort(p, meio, fim); //direita da divisao
    int contMerge = merge(p, ini, fim); //junta a direita e a esquerda

    // resultado eh a soma da qtd de trocas da dir, da esq, e da juncao atual
    return contEsq + contDir + contMerge;
}

int main() {
    long n = 1;
    while(n != 0){
        cin >> n; //entrada do tamanho do vetor
        if(n == 0)
            break;

        vector<long> p; //vetor em que a sequencia P sera armazenada
        for(int i=0; i<n; i++) {
            long num;
            cin >> num;
            p.push_back(num);
        }

        int qtdTrocas = mergesort(p, 0, n);
        if(qtdTrocas % 2 == 0) { // numero de trocas eh par
            cout << "Jaques\n";
        }
        else { //qtdTrocas % 2 == 1 >> numero de trocas eh impar
            cout << "Fino\n";
        }
    }
    
    return 0;
}


/*
n - tamanho da sequencia P; maior numero possivel na sequencia P

troca de gomas que estao em posicoes invertidas
invertida se A esta antes de B mas A>B >> ordenacao em ordem crescente
trocar gomas consecutivas
sempre começa com fino >> qtd impar de trocas
jaques >> qtd par de trocas

contar quantas numeros adiante sao maiores = qtd trocas >> ex. 3 2 1 - 3 eh maior do que 2 numeros, 2 eh maior do que um numero
mergesort >> qtd de trocas sera tam da esq - i (quando v[i] > v[j], todos os proximos de v[i] serao maior do que v[j], pois esta ordendo)
*/