%PARTICIPANTES
% Ana Paula de Abreu Batista - Nº USP -12688424
% Camila Aya Saito - Nº USP - 15635649
% Victoria Fávero Nunes - Nº USP - 15698302


temVarinha(hermione).
temVarinha(harry).
temVassoura(harry).
jogadorQuadribol(X) :- temVassoura(X).
bruxo(X) :- temVassoura(X), temVarinha(X)

/**
a) bruxo(ron) - RESPOSTA: false
b) bruxo(hermione). - RESPOSTA: false
c) bruxo(harry). - RESPOSTA: true
d) bruxo(Y). -  RESPOSTA: Y=harry
*/


