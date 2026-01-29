#include <iostream>
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;

int calculo_desconto(vector<int> precos, int n) {
    int desconto = 0;

    // começo do terceiro item da sequencia e vou de 3 em 3 >> mais barato do trio
    for(int i=2; i<n; i=i+3) {
        desconto += precos[i];
    }

    return desconto;
}

int main() {
    int n;
    cin >> n;
    vector<int> precos; // vetor que armazenara os precos 
    for(int i=0; i<n; i++) {
        int num;
        if(cin >> num) {
            precos.push_back(num);
        }
        else {
            precos.push_back(0); // caso haja menos linhas de entrada do que n
            cin.clear();
        }
    }

    sort(precos.begin(), precos.end(), greater<int>());
    
    int desconto = calculo_desconto(precos, n);

    cout << desconto;

    return 0;
}

/*
ALGORITMOS GULOSOS
ordenar vetor
dividir em grupos de 3 - vezes que passara no caixa
DECISAO GULOSA - pegar o produto mais barato do trio (o que sera descontado)
*/