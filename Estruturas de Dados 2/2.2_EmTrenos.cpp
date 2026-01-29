#include <iostream>
#include <list>
#include <queue>
#include <vector>

using namespace std;

#define infinito 10000.0
#define EPSILON 1e-6 //constante para lidar com maior precisao em calculos com float

// v - vertice atual, grafo - grafo analisado, bsf[i] - melhor caminho da origem a i, pq - fila de prioridade com os menores caminhos
void relaxa(int v, list<pair<float, int>> *grafo, pair<float, int> *bsf, priority_queue<pair<float, int>, vector<pair<float, int>>, greater<pair<float, int>>> *pq) {

    //loop que percorrera toda a lista de grafo[v] (percorrera todos os vizinhos de v)
    for(auto i = grafo[v].begin(); i != grafo[v].end(); i++) {
        float custo_aresta = bsf[v].first + (*i).first; //custo do caminho da origem(0) ate v + de v ate o i-esimo vizinho de v (u)
        int u = (*i).second; //i-esimo vizinho de v

        // se o custo calculado eh melhor do que o de antes de passar por v
        if(custo_aresta < bsf[u].first) {
            bsf[u] = make_pair(custo_aresta, v);
            (*pq).push(make_pair(custo_aresta, u)); //min-heap 
        }
    }
}

//origem - vertice de origem, m - qtd de vertices, grafo - grafo analisado, bsf[i] - melhor caminho da origem a i, aberto[i] - vertice i foi visitado?
void djikstra(int origem, int m, list<pair<float, int>> *grafo, pair<float, int> *bsf, bool *aberto) {
    // lista de prioridade em que armazenara os pares <-custo, vertice> (vertice eh o ultimo do caminho correspondente ao custo)
    priority_queue<pair<float, int>, vector<pair<float, int>>, greater<pair<float, int>>> pq;

    for(int i=0; i<m; i++) {
        bsf[i] = make_pair(infinito, -1); //inicializa todas as distancias como infinito
        aberto[i] = true; //todos os vertices estao abertos (nenhum foi visitado)
    }

    // distancia da origem ate a origem sera 0
    pq.push(make_pair(0.0, origem));
    bsf[origem] = make_pair(0.0, origem);

    while(!pq.empty()) { //ha vertices na pq
        int v = pq.top().second; //pega o vertice com o menor caminho ate o momento
        pq.pop();

        if(!aberto[v]) continue; // se vertice esta fechado, passa para o proximo (item da pq)

        relaxa(v, grafo, bsf, &pq);
        aberto[v] = false;

    }
}

int main() {

    //m - numero de salas/vertices 
    //e - numero de arestas para o jogador
    //n - numero de arestas para o impostor
    //c - quantidade de consultas (quantas vezes sera calculada a "corrida")
    int m, e, n, c;
    cin >> m >> e >> n >> c;

    //u, v - vertices do par
    //d - distancia/peso da aresta
    int u, v;
    float d;
    list<pair<float, int>> jog[m], imp[m]; // vetor estatico, com m posicoes, em que cada jog[i] ha uma lista de pares

    //leitura para o jogador
    for(int i=0; i<e; i++) {
        cin >> u >> v >> d;
        jog[u].push_back(make_pair(d, v));
        jog[v].push_back(make_pair(d, u));
        //jog[i] - representa lista de adjacencia do vertice i, em que estarao os pares (peso, vizinho)
        imp[u].push_back(make_pair(d, v));
        imp[v].push_back(make_pair(d, u));
    }

    //leitura para o impostor
    for(int i=0; i<n; i++) {
        cin >> u >> v;
        imp[u].push_back(make_pair(1.0, v));
        imp[v].push_back(make_pair(1.0, u));
        //imp[i] - representa lista de adjacencia do vertice i, em que estarao os pares (peso(1), vizinho)
    }

    pair<float, int> bsf_jog[m], bsf_imp[m]; // vetores de pares para o best so far (menor caminho ate o momento de 0 ate i)
    // <dist, v_anterior> - dist: distancia ate i; v_anterior: vertice anterior no caminho bsf

    bool aberto_jog[m], aberto_imp[m]; //vetor que armazenara os vertices que ja foram visitados

    // calculo dos caminhos minimos do botao(0) ate cada uma das salas
    djikstra(0, m, jog, bsf_jog, aberto_jog); //djiskstra/caminho minimo para jogador
    djikstra(0, m, imp, bsf_imp, aberto_imp); //djiskstra/caminho minimo para impostor

    for(int i=0; i<c; i++) {
        int fim; //sala em que jogador e impostor se encontraram
        cin >> fim;
        
        if(bsf_jog[fim].first - bsf_imp[fim].first > EPSILON)
            cout << "defeat\n";
        else
            cout << "victory\n";
    }

    return 0;
}

/*
GRAFOS - CAMINHOS MINIMOS
botao de emergencia eh o vertice 0
*/