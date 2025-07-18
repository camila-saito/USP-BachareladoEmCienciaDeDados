#include <stdio.h>

// Definição dos registradores, memória e estados
#define rx 0
#define ry 1
#define rz 2
#define SWP 52
#define STATE_FETCH 1
#define STATE_DECODE 2
#define STATE_EXECUTE 3
#define STATE_EXECUTE2 4
#define STATE_HALTED 5

int reg[8] = {0}; // 8 registradores
int pc = 0;
int state = STATE_FETCH;
int selM4;
int opcode, LoadReg[8] = {0}, LoadFR;
int memoria[256] = {0};

// Função para executar as instruções
void simulacao() {
    while(state != STATE_HALTED) {
        switch(state) {
            case STATE_FETCH:
                opcode = memoria[pc];
                state = STATE_DECODE;
                break;

            case STATE_DECODE:
                switch(opcode) {
                    case SWP:
                        selM4 = reg[rx];
                        reg[rz] = selM4;
					    LoadReg[rz] = 1;
                        state = STATE_EXECUTE;
                        break;

                    default:
                        state = STATE_HALTED;
                        break;
                }
                break;

            case STATE_EXECUTE:
                selM4 = reg[ry];
                reg[rx] = selM4;
				LoadReg[rx] = 1;
                state = STATE_EXECUTE2;
                break;

            case STATE_EXECUTE2:
                selM4 = reg[rz];
                reg[ry] = selM4;
				LoadReg[ry] = 1;
				LoadFR =1;
                state = STATE_HALTED;
                break;
        }
    }
}

int main() {
    reg[rx] = 10;
    reg[ry] = 20;
    reg[rz] = 0;

    // Imprime os valores antes da troca
    printf("Antes da troca:\n");
    printf("rx = %d\n", reg[rx]);
    printf("ry = %d\n", reg[ry]);

    //carregando a funcao
    memoria[pc] = SWP;

    simulacao();

    printf("Depois da troca:\n");
    printf("rx = %d\n", reg[rx]);
    printf("ry = %d\n", reg[ry]);

    return 0;
}
