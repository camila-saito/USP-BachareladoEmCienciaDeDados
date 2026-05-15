%PARTICIPANTES
% Ana Paula de Abreu Batista - Nº USP -12688424
% Camila Aya Saito - Nº USP - 15635649
% Victoria Fávero Nunes - Nº USP - 15698302


/**
 * a) Considere as estruturas de dados que você aprendeu no decorrer do curso de
graduação. Qual você utilizaria para resolver esse problema? Explique e dê pelo menos um
exemplo de como isso seria possível.

RESPOSTA:
Para resolver esse problema, utilizaríamos uma estrutura de dados do tipo grafo,
pois ela permite representar e analisar relações bilaterais entre diferentes pontos, como origem e destino.

Por exemplo, em um problema de rotas entre cidades, cada cidade seria um vértice, 
e as estradas que conectam as cidades seriam as arestas. Assim, é possível encontrar o caminho 
mais curto, verificar se existe uma rota entre duas cidades ou analisar a conectividade geral do sistema.
*/

/**
b) Construa uma base de conhecimento no Prolog que represente a Tabela 1 acima
*/

onibus(araraquara, saoCarlos).
onibus(saoCarlos, araraquara).
onibus(barretos,franca).
onibus(franca,barretos).
onibus(bauru,botucatu).
onibus(botucatu,bauru).
onibus(botucatu,sorocaba).
onibus(franca,ribeiraoPreto).
onibus(marilia,bauru).
onibus(ribeiraoPreto, araraquara).
onibus(saoCarlos,bauru).


viagem(ORIGEM,DESTINO) :- onibus(ORIGEM,DESTINO). 



/** 
c) A base de conhecimento gerada no exercício a) possui fatos sobre cidades que possuem ônibus
 direto entre elas. Fazendo conexões é possível viajar entre duas cidades distantes.
 */

onibus(araraquara, saoCarlos).
onibus(saoCarlos, araraquara).
onibus(barretos,franca).
onibus(franca,barretos).
onibus(bauru,botucatu).
onibus(botucatu,bauru).
onibus(botucatu,sorocaba).
onibus(franca,ribeiraoPreto).
onibus(marilia,bauru).
onibus(ribeiraoPreto, araraquara).
onibus(saoCarlos,bauru).


viagem(ORIGEM,DESTINO) :- onibus(ORIGEM,DESTINO). 
viagem(ORIGEM,DESTINO) :- onibus(ORIGEM,Y), viagem(Y,DESTINO).


/**
d) Agora considere que a empresa de ônibus decidiu oferecer não apenas a ida como
viagem direta, mas também a volta. Por exemplo: São Carlos para Araraquara passa a ser
possível porque Araraquara para São Carlos é uma viagem direta já oferecida pela empresa.
Modifique apenas as regras de seu programa, de modo que, também reponda yes para a
viagem de volta. Por exemplo: O programa deve retornar yes tanto para
viagemEntre(araraquara, barretos) quanto para viagemEntre(barretos, araraquara). Você
precisa utilizar o corte (!)? Se positivo, explique o motivo.
**/


onibus(araraquara, saoCarlos).
onibus(barretos,franca).
onibus(bauru,botucatu).
onibus(botucatu,sorocaba).
onibus(franca,ribeiraoPreto).
onibus(marilia,bauru).
onibus(ribeiraoPreto, araraquara).
onibus(saoCarlos,bauru).

direto(ORIGEM, DESTINO) :- onibus(ORIGEM, DESTINO).
direto(ORIGEM, DESTINO) :- onibus(DESTINO, ORIGEM).

% lista de visitados vazia
viagem(ORIGEM, DESTINO) :- viagem_aux(ORIGEM, DESTINO, [ORIGEM]).

viagem_aux(ORIGEM, DESTINO, _) :- direto(ORIGEM, DESTINO).

viagem_aux(ORIGEM, DESTINO, VISITADOS) :-
    direto(ORIGEM, Y),
    Y \== DESTINO,
    % evita ciclo - verifica só se ainda não visitou essa cidade
    \+ member(Y, VISITADOS),   
    viagem_aux(Y, DESTINO, [Y|VISITADOS]).