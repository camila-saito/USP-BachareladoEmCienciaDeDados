/*
Camila Aya Saito (15635649)
Pedro Henrique Tambara Zanutto (15656517)
Vinicius Moraes de Carvalho (15642432)
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//informações relacionadas aos detalhes dos passageiros
typedef struct{
    char nome_passageiro[40];
    char sobrenome_passageiro[40];
    char cpf_passageiro[20];
    char origem_viagem[10]; 
    char destino_viagem[10];
    char poltrona[10];
    float valor_passagem;
    int dia, mes, ano;
    char id_voo[10];
    char classe[10];
}reserva;

//detalhes do voo e aspectos financeiros do voo.
typedef struct{
    int capacidade_maxima;
    int capacidade_alocada;
    float preco_passagem_executiva;
    float preco_passagem_economica;
    int reservas_feitas;
    float arrecadacao;
    int fechamento_de_voo;
    /*Um ponteiro é utilizado dentro da struct voo para armazenar um array de structs reserva.
     Isso significa que cada struct voo pode conter vários registros de reserva, 
     representando as reservas de passageiros para aquele voo específico.*/
    reserva *reservas; //ponteiro para aloc dinamica
}voo;

//função para carregar os dados do voo em um arquivo
void carregando_dados(voo *viagem){
    FILE *file = fopen("informacoes_do_voo.txt", "r");
    //arquivo nao foi criado ainda
    if(!file){
        viagem->reservas = NULL;
        viagem->capacidade_alocada = 0;
        return;
    }
    fscanf(file, "%d %d %f %f %d %f %d",
    &viagem->capacidade_maxima, 
    &viagem->capacidade_alocada, 
    &viagem->preco_passagem_economica, 
    &viagem->preco_passagem_executiva, 
    &viagem->reservas_feitas,
    &viagem->arrecadacao,
    &viagem->fechamento_de_voo);
    
    if(viagem->reservas_feitas>0){
        //alocando memoria para as reservas
        viagem->reservas = (reserva*)malloc(viagem->capacidade_alocada*sizeof(reserva));

        //carrregando reservas
        for(int i = 0 ;i<viagem->reservas_feitas;i++){
            fscanf(file, "%s %s %s %d %d %d %s %s %s %f %s %s", 
            viagem->reservas[i].nome_passageiro, 
            viagem->reservas[i].sobrenome_passageiro,
            viagem->reservas[i].cpf_passageiro,
            &viagem->reservas[i].dia, &viagem->reservas[i].mes, &viagem->reservas[i].ano,
            viagem->reservas[i].id_voo,
            viagem->reservas[i].poltrona,
            viagem->reservas[i].classe,
            &viagem->reservas[i].valor_passagem,
            viagem->reservas[i].origem_viagem,
            viagem->reservas[i].destino_viagem); 
        }
    }
    else {
        viagem->reservas = NULL;
    }
    fclose(file);
}

//salvando dados do voo em um arquivo
void salvamento_de_dados(voo *viagem){
    
    //Se o arquivo for aberto com sucesso, um ponteiro para o arquivo é armazenado na variável file.
    FILE *file = fopen("informacoes_do_voo.txt","w");

    //gravar os valores dos campos da estrutura voo no arquivo:
    fprintf(file,"%d %d %2.f %2.f %d %2.f %d\n", 
    viagem->capacidade_maxima, 
    viagem->capacidade_alocada, 
    viagem->preco_passagem_economica, 
    viagem->preco_passagem_executiva, 
    viagem->reservas_feitas,
    viagem->arrecadacao,
    viagem->fechamento_de_voo);

    for(int i=0; i<viagem->reservas_feitas;i++){
        fprintf(file, "%s %s %s %d %d %d %s %s %s %.2f %s %s\n", 
        viagem->reservas[i].nome_passageiro, 
        viagem->reservas[i].sobrenome_passageiro, 
        viagem->reservas[i].cpf_passageiro, 
        viagem->reservas[i].dia, viagem->reservas[i].mes, viagem->reservas[i].ano, 
        viagem->reservas[i].id_voo, 
        viagem->reservas[i].poltrona, 
        viagem->reservas[i].classe, 
        viagem->reservas[i].valor_passagem, 
        viagem->reservas[i].origem_viagem, viagem->reservas[i].destino_viagem);
    }
    fclose(file);
}

//abre o voo, indicando precos e numero total de assentos disponiveis
void abertura_de_voo(voo *viagem, int total_assentos, float preco_economica, float preco_executiva){
    viagem->capacidade_maxima = total_assentos;
    viagem->preco_passagem_economica = preco_economica;
    viagem->preco_passagem_executiva = preco_executiva;
    viagem->reservas_feitas = 0;
    viagem->arrecadacao =0.0;
    viagem->fechamento_de_voo= 0;
    
    /*O código verifica se o valor da variável total_poltronas é maior que 10.
    Se total_poltronas for maior que 10:
    O valor 10 é atribuído ao campo capacidade_alocada da estrutura viagem.
    Se total_poltronas não for maior que 10:
    O valor da variável total_poltronas é atribuído ao campo capacidade_alocada da estrutura viagem.*/
    viagem->capacidade_alocada = total_assentos >10 ? 10:total_assentos;

    //aloca memoria para capacidade do aviao
    viagem->reservas = (reserva*)malloc(viagem->capacidade_alocada * sizeof(reserva));

    salvamento_de_dados(viagem);
}

//função para fazer uma reserva
void fazer_reserva(voo *viagem_aerea , char nome[], char sobrenome[], char cpf[], int dia_viagem, int mes_viagem, int ano_viagem, char id_voo[], char poltrona[], char classe[], float valor, char origem[], char destino[]){
    //voo ja esta fechado
    if(viagem_aerea->fechamento_de_voo){
        printf("Voo fechado!\n");
        return;
    }

    //capacidade maxima ja foi atingida
    if(viagem_aerea->reservas_feitas >= viagem_aerea->capacidade_maxima){
        printf("Voo fechado!");
        viagem_aerea-> fechamento_de_voo = 1;
        salvamento_de_dados(viagem_aerea);
        return;
    }

    //verifica se um assento ja esta ocupado
    for(int i = 0; i<viagem_aerea->reservas_feitas;i++){
        if(strcmp(viagem_aerea->reservas[i].poltrona,poltrona)==0){
            printf("Assento já reservado!");
            return;
        }
    }

    //tem assentos disponiveis
    if(viagem_aerea->reservas_feitas>= viagem_aerea->capacidade_alocada){
        viagem_aerea->capacidade_alocada *=2;
        viagem_aerea->reservas = (reserva *)realloc(viagem_aerea->reservas, viagem_aerea->capacidade_alocada * sizeof(reserva));
    }

    reserva nova_reserva;
    strcpy(nova_reserva.nome_passageiro, nome);
    strcpy(nova_reserva.sobrenome_passageiro, sobrenome);
    strcpy(nova_reserva.cpf_passageiro, cpf);
    nova_reserva.dia = dia_viagem;
    nova_reserva.mes = mes_viagem;
    nova_reserva.ano = ano_viagem;
    strcpy(nova_reserva.id_voo, id_voo);
    strcpy(nova_reserva.poltrona, poltrona);
    strcpy(nova_reserva.classe, classe);
    nova_reserva.valor_passagem = valor;
    strcpy(nova_reserva.origem_viagem, origem);
    strcpy(nova_reserva.destino_viagem, destino);

    viagem_aerea->reservas[viagem_aerea->reservas_feitas] = nova_reserva;
    viagem_aerea->reservas_feitas++;
    viagem_aerea->arrecadacao += valor;

    salvamento_de_dados(viagem_aerea); // Salva os dados atualizados do voo

    return;
} 

//função para modificar as informações de uma reserva
void alterar_reserva(voo *viagem, char cpf1[],char nome[], char sobrenome[], char cpf2[], char poltrona[]) {
    for (int i = 0; i < viagem->reservas_feitas; i++) {
        if (strcmp(viagem->reservas[i].cpf_passageiro, cpf1) == 0) { // Verifica se o CPF corresponde à reserva

            // Modifica os dados da reserva encontrada
            strcpy(viagem->reservas[i].nome_passageiro, nome);
            strcpy(viagem->reservas[i].sobrenome_passageiro, sobrenome);
            strcpy(viagem->reservas[i].cpf_passageiro, cpf2);
            strcpy(viagem->reservas[i].poltrona, poltrona);

            printf("Reserva Modificada:\n");
            printf("%s\n", viagem->reservas[i].cpf_passageiro);
            printf("%s %s\n", viagem->reservas[i].nome_passageiro, viagem->reservas[i].sobrenome_passageiro);
            printf("%d/%d/%d\n", viagem->reservas[i].dia, viagem->reservas[i].mes, viagem->reservas[i].ano);
            printf("Voo: %s\n", viagem->reservas[i].id_voo);
            printf("Assento: %s\n", viagem->reservas[i].poltrona);
            printf("Classe: %s\n", viagem->reservas[i].classe);
            printf("Trecho: %s %s\n", viagem->reservas[i].origem_viagem, viagem->reservas[i].destino_viagem);
            printf("Valor: %.2f\n", viagem->reservas[i].valor_passagem);
            printf("--------------------------------------------------\n");

            salvamento_de_dados(viagem); // Salva os dados atualizados do voo

            return;
        }
    }
}

//função para consultar os dados de uma reserva
void consulta_de_reserva(voo *viagem, char cpf[]) {
    for (int i = 0; i < viagem->reservas_feitas; i++) {
        if (strcmp(viagem->reservas[i].cpf_passageiro, cpf) == 0) { // Verifica se o CPF corresponde à reserva

            // Exibe os dados da reserva encontrada
            printf("%s\n",viagem->reservas[i].cpf_passageiro);
            printf("%s %s\n",viagem->reservas[i].nome_passageiro, viagem->reservas[i].sobrenome_passageiro);
            printf("%d/%d/%d\n", viagem->reservas[i].dia, viagem->reservas[i].mes, viagem->reservas[i].ano);
            printf("Voo: %s\n", viagem->reservas[i].id_voo);
            printf("Assento: %s\n",viagem->reservas[i].poltrona);
            printf("Classe: %s\n", viagem->reservas[i].classe);
            printf("Trecho: %s %s\n",viagem->reservas[i].origem_viagem, viagem->reservas[i].destino_viagem);
            printf("Valor: %.2f\n", viagem->reservas[i].valor_passagem);
            printf("--------------------------------------------------\n");

            return;
        }
    }
}

//função para cancelar uma reserva
void cancelamento_de_reserva(voo *viagem, char cpf[]) {
    for (int i = 0; i < viagem->reservas_feitas; i++) {
        if (strcmp(viagem->reservas[i].cpf_passageiro, cpf) == 0) { // Verifica se o CPF corresponde à reserva

            viagem->arrecadacao -= viagem->reservas[i].valor_passagem; // Subtrai o valor da reserva do total arrecadado

            // Remove a reserva e ajusta o array de reservas
            for (int j = i; j < viagem->reservas_feitas - 1; j++) {
                viagem->reservas[j] = viagem->reservas[j + 1];
            }
            viagem->reservas_feitas--;
            viagem->reservas = (reserva *)realloc(viagem->reservas, viagem->capacidade_alocada * sizeof(reserva)); // Realoca memória

            salvamento_de_dados(viagem); // Salva os dados atualizados do voo

            return;
        }
    }
}

//função para fechar o dia (voo continuará aberto)
void fechar_dia(voo *viagem) {
    printf("Fechamento do dia:\n");

    // Exibe a quantidade de reservas e o total arrecadado
    printf("Quantidade de reservas: %d\n", viagem->reservas_feitas);
    printf("Posicao: %.2f\n", viagem->arrecadacao);
    printf("--------------------------------------------------\n");

    salvamento_de_dados(viagem); // Salva os dados do voo
}

//função para fechar o voo
void fechar_voo(voo *viagem) {
    printf("Voo Fechado!\n\n");

    // Exibe os dados de todas as reservas
    for (int i = 0; i < viagem->reservas_feitas; i++) {
        printf("%s\n",viagem->reservas[i].cpf_passageiro);
        printf("%s %s\n",viagem->reservas[i].nome_passageiro, viagem->reservas[i].sobrenome_passageiro);
        printf("%s\n\n", viagem->reservas[i].poltrona);
    }
    printf("Valor Total: %.2f\n", viagem->arrecadacao);
    printf("--------------------------------------------------\n");

    viagem->fechamento_de_voo = 1; // Marca o voo como fechado

    salvamento_de_dados(viagem); // Salva os dados do voo
}


int main() {
    voo ICMC_AIRLINES;
    carregando_dados(&ICMC_AIRLINES); // Carrega os dados do voo ao iniciar o programa

    char instrucao[3];

    while (scanf("%s", instrucao) != EOF) { // Loop para receber comandos

        if (strcmp(instrucao, "AV") == 0) {
            int qtd_assentos;
            float preco_economica, preco_executiva;

            scanf("%d %f %f", &qtd_assentos, &preco_economica, &preco_executiva);

            abertura_de_voo(&ICMC_AIRLINES, qtd_assentos, preco_economica, preco_executiva);
        } 
        else if (strcmp(instrucao, "RR") == 0) {
            char nome[40], sobrenome[40], cpf[20], id_voo[10], poltrona[10], classe[10], origem[10], destino[10];
            int dia, mes, ano;
            float valor;

            scanf("%s %s %s %d %d %d %s %s %s %f %s %s", nome, sobrenome, cpf, &dia, &mes, &ano, id_voo, poltrona, classe, &valor, origem, destino);

            fazer_reserva(&ICMC_AIRLINES, nome, sobrenome, cpf, dia, mes, ano, id_voo, poltrona, classe, valor, origem, destino);
        } 
        else if (strcmp(instrucao, "CR") == 0) {
            char cpf[20];
            scanf(" %s", cpf);
            consulta_de_reserva(&ICMC_AIRLINES, cpf);
        } 
        else if (strcmp(instrucao, "MR") == 0) {
            char nome[50], sobrenome[50], cpf[15], assento[5];
            scanf("%s %s %s %s %s",cpf, nome, sobrenome, cpf, assento);
            alterar_reserva(&ICMC_AIRLINES,cpf, nome, sobrenome, cpf, assento);
        } 
        else if (strcmp(instrucao, "CA") == 0) {
            char cpf[15];
            scanf("%s", cpf);
            cancelamento_de_reserva(&ICMC_AIRLINES, cpf);
        } 
        else if (strcmp(instrucao, "FD") == 0) {
            fechar_dia(&ICMC_AIRLINES);
            break;
        } 
        else if (strcmp(instrucao, "FV") == 0) {
            fechar_voo(&ICMC_AIRLINES);
            break;
        }
    }

    // Libera a memória alocada para reservas
    if (ICMC_AIRLINES.reservas != NULL) {
        free(ICMC_AIRLINES.reservas);
    }

    return 0;
}