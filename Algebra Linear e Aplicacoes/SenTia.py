import numpy as np

d = int(input()) # dimensao

B1 = [] # bases de B1
for i in range(0, d):
  vet = input().split()
  for j in range(0, len(vet)):
    vet[j] = float(vet[j])
  B1.append(vet)

B2 = [] # bases de B2
for i in range(0, d):
  vet = input().split()
  for j in range(0, len(vet)):
    vet[j] = float(vet[j])
  B2.append(vet)

v1 = input().split() # vetor v em B1
for i in range(0, len(v1)):
  v1[i] = float(v1[i])

B1 = np.array(B1)
B2 = np.array(B2)
v1 = np.array(v1)

inversa = np.linalg.inv(B1) # calcula a inversa de B1

coordenada = inversa.dot(v1) # acha as coordenadas de v1 na base B1 >> multiplica a inversa de B1 com v1

resposta = B2.dot(coordenada) # acha as coordenadas de v1 na base B2 >> multiplica B2 pelas coordenadas de v1 na base B1

print(" ".join([f"{coordenada:.4f}" for coordenada in resposta]))