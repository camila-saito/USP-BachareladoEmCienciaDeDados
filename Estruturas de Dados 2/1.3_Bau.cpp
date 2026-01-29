#include <iostream>
#include <vector>

using namespace std;

// C++ program for implementation of Insertion Sort (GeeksforGeeks)
/* This code is contributed by Hritik Shah. */
void insertionSort(vector<int>& vetor, int n) {
    for (int i=1; i<n; i++) {
        int pont = vetor[i];
        int j = i - 1;

        while (j >= 0 && vetor[j] > pont) {
            vetor[j + 1] = vetor[j];
            j = j - 1;
        }
        vetor[j + 1] = pont;
    }
}

int somaSubconjunto(vector<int> w, int n, int particao, vector<vector<int>>& memo) {
    if(particao == 0) { //achou um subconjunto -> somatoria = 0; subconjunto com 0 elementos
        return 1;
    }

    if(n <= 0) { //nao tem mais elementos/moedas disponiveis
        return 0;
    }

    if(memo[n][particao] != -1) { //verificar se valor ja foi calculado
        return memo[n][particao];
    }

    if(w[n-1] > particao) { // verifica se valor da moeda eh maior do q a particao necessaria
        memo[n][particao] = somaSubconjunto(w, n-1, particao, memo); //ignora o valor da moeda e calcula para a moeda anterior
        return memo[n][particao];
    }
    else { //valor da moeda eh menor do que a particao
        //inclui ou nao a ultima moeda ao subconjunto/particao
        //basta que um seja verdadeiro para que a soma seja verdadeira
        return memo[n][particao] = somaSubconjunto(w, n - 1, particao, memo) || somaSubconjunto(w, n - 1, particao - w[n - 1], memo);
    }
}


int main() {
    int n;
    cin >> n;
    int s = 0; //somatoria do peso de todas as moedas
    vector<int> w; //vetor com o peso de cada moeda
    for(int i=0; i<n; i++) {
        int num;
        cin >> num;
        w.push_back(num);
        s += num;
    }
    int ideal = s/2; //divisao ideal: valor proximo de s/2

    // ordena o vetor do peso de moedas
    insertionSort(w, w.size()); // w.size() = n

    // matriz com todos os pesos das moedas e todas as somatórias -> inicializada inteira com -1(nenhum valor foi calculado)
    vector<vector<int>> memo(n+1, vector<int>(ideal+1, -1)); 
    int dif = 0; // representa a diferenca entre a qtd de ouro de cada um

    for(int particao=ideal; particao>=0; particao--) { //particao - sera a somatoria dos pesos de um dos meninos
        if(somaSubconjunto(w, n, particao, memo) >= 1) { //existe um subconjunto cuja soma resulta em 'particao'
            dif = s - 2*particao;
            break;
        }
    }
    cout << dif;

    return 0;
}

/*
PROGRAMACAO DINAMICA
dividir moedas em 2 grupos
menor diferença possivel entre a somatoria dos pesos de cada grupo
n moedas encontradas
w - vetor com o peso de cada moeda
*/