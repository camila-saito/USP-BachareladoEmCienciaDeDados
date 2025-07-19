import numpy as np

d = int(input()) # dimensao

base = [] # inicializa uma lista vazia, onde serão colocados os vetores da base
for i in range(0, d):
    vet = input().split()
    vet = list(map(float, vet)) # transforma todos os componentes do vetor em float
    base.append(vet) # coloca os vetores na matriz 'base'

base = np.array(base)

det = np.linalg.det(base) #calcula o determinante da base

# se o determinate for diferente de 0, 'base' é LI, senão é LD
if det != 0:
    print('SimTia, o Retorno')
else:
    print('Não')
