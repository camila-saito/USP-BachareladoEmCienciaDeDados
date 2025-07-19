import numpy as np
from math import radians, sin, cos

n = int(input()) # quantidade de transformações geométricas

A = [[1.0, 0.0],
     [0.0, 1.0]]
A = np.array(A)

for i in range(0, n):
    transf = input().split() # entrada da descrição da transformação
    if transf[0] == 'R':
        transf[1] = radians(float(transf[1]))
        rotacao = [[cos(transf[1]), -(sin(transf[1]))],
                   [sin(transf[1]), cos(transf[1])]] # formação da matriz da transformação
        rotacao = np.array(rotacao)
        A = rotacao.dot(A) # rotaciona a matriz A
    else:
        transf = list(map(lambda x, j: float(x) if j > 0 else x, transf, range(len(transf))))
        escalamento = [[transf[1], 0.0],
                  [0.0, transf[2]]] # formação da matriz de transformação
        escalamento = np.array(escalamento)
        A = escalamento.dot(A) # escala a matriz A

det = np.linalg.det(A) # calcula o determinante de A para ver se é invertível ou não

if det == 0: # matriz não é invertível
    print('SemVerTia')
else:
    print('SimTia, te vejo!')
