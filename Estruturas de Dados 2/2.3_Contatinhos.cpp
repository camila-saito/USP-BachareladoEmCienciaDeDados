#include <iostream>
#include <vector>
#include <functional>
#include <string>
#include <algorithm> 

using namespace std;

struct contato {
    string nome;
    string telefone;

    bool operator==(const contato& c1) const {
        return nome == c1.nome && telefone == c1.telefone;
    }
    
};

// struct para tabela hash >> nomes serao convertidos em chaves (se houver 2 ou mais nomes com a mesma chave, elas serao empilhadas naquela chave)
struct Hash {
    int tamanho; // quantas chaves terao
    vector<vector<contato>> tabela; // tabala em que, se houver 2 ou mais nomes com a mesma chave, elas serao empilhadas naquela chave

    // construtor
    Hash(int tam) {
        tamanho = tam;
        tabela.resize(tam);
    }

    // nomes serao convertidos em chaves a partir da funcao da biblioteca padrao
    int funcaoHash(const string& nome) {
        size_t chave = hash<string>{}(nome);
        return chave % tamanho;
    }
    
    // funcao para inserir um novo contato (desde que nao tenha um com o mesmo nome)
    bool inserir(contato ctt) {
        int chave = funcaoHash(ctt.nome);
        if(busca(ctt.nome) == nullptr) {
            tabela[chave].push_back(ctt);
            return true;
        }
        else return false;
    }

    // funcao para remover um contato
    bool remover(string nome) {
        int chave = funcaoHash(nome);
        auto& lista = tabela[chave]; 
        auto i = find_if(lista.begin(), lista.end(),
            [&](const contato& c) {
                return c.nome == nome;
            });

        if(i != lista.end()) {
            lista.erase(i);
            return true;
        }

        return false;
    }

    // busca um contato na tabela
   contato* busca(string nome) {
        int chave = funcaoHash(nome);
        for(auto& c : tabela[chave]) {
            if(c.nome == nome) {
                return &c;
            }
        }
        return nullptr;
    }

    // atualiza o telefone de algum contato na tabela
    bool atualizar_tel(string nome, string tel) {
        contato *c = this->busca(nome);
        if(c != nullptr) {
            (*c).telefone = tel;
            return true;
        }
        else return false;
    }
};

int main() {
    char op = '1';
    contato contatinho;
    string nome, tel;

    Hash h(110);

    while(op != '0') {
        cin >> op;
        cin.ignore();

        switch (op) {
            case 'I':
            cin >> contatinho.nome >> contatinho.telefone;
            if(!h.inserir(contatinho)) {
                cout << "Contatinho ja inserido\n";
            }
            break;

            case 'P':
            cin >> nome;
            if(h.busca(nome) != nullptr) {
                contatinho = *(h.busca(nome));
                cout << "Contatinho encontrado: telefone " << contatinho.telefone << "\n";
            }
            else {
                cout << "Contatinho nao encontrado\n";
            }
            break;

            case 'R':
            cin >> nome;
            if(!h.remover(nome)) {
                cout << "Operacao invalida: contatinho nao encontrado\n";
            }
            break;

            case 'A':
            cin >> nome >> tel;
            if(!h.atualizar_tel(nome, tel)) {
                cout << "Operacao invalida: contatinho nao encontrado\n";
            }
            break;

            case '0':
            break;
        
            default:
            cout << "Operacao invalida!\n";
            break;
        }
    }

    return 0;
}

/*
HASH TABLE
transformar nome (string) em uma chave
nome[11] - 10 caracteres + \0
telefone[9]
*/