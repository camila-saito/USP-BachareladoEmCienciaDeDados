call main
halt
iniciar_jogo:
	push r0
	mov r0, sp
	loadn r7, #4
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r1, #0
	loadn r2, #2
	sub r2, r0, r2
	storei r2, r1
L1:
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L2
	loadn r1, #0
	loadn r2, #3
	sub r2, r0, r2
	storei r2, r1
L4:
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L5
	loadn r1, #0
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #0
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	inc r2
	storei r2, r1
	loadn r1, #0
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #2
	add r2, r2, r3
	storei r2, r1
	loadn r1, #3
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L4
L5:
L6:
	loadn r1, #2
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L1
L2:
L3:
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #0
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #6
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #0
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #0
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #1
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #9
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #0
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #5
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #5
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #7
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #4
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #7
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #8
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #8
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #1
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #9
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #2
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #9
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #4
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #7
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #5
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #6
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #8
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #9
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #5
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #7
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #6
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #5
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #1
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #7
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #6
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
Lend0:
	mov sp, r0
	pop r0
	rts
quantidade_bombas_vizinhas:
	push r0
	mov r0, sp
	loadn r7, #5
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r7, #2
	sub r7, r0, r7
	storei r7, r2
	loadn r7, #3
	sub r7, r0, r7
	storei r7, r3
	loadn r1, #0
	loadn r2, #4
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #0
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L8
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	sub r3, r3, r4
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #1
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L8
	loadn r1, #1
L8:
	jz L7
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L7:
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	loadn r3, #1
	sub r2, r2, r3
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L10
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	add r3, r3, r4
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #1
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L10
	loadn r1, #1
L10:
	jz L9
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L9:
	loadn r1, #0
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L12
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	sub r3, r3, r4
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #1
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L12
	loadn r1, #1
L12:
	jz L11
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L11:
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	loadn r3, #1
	sub r2, r2, r3
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L14
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #2
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	add r3, r3, r4
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #1
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L14
	loadn r1, #1
L14:
	jz L13
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L13:
	loadn r1, #0
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L16
	loadn r2, #0
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	jz L17
	loadn r3, #1
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #2
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #30
	mul r4, r4, r5
	add r3, r3, r4
	loadn r4, #3
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #3
	mul r4, r4, r5
	add r3, r3, r4
	loadi r3, r3
	loadn r4, #1
	cmp r3, r4
	push fr
	pop r3
	loadn r7, #4
	and r3, r3, r7
	mov r2, r3
	jz L17
	loadn r2, #1
L17:
	mov r1, r2
	jz L16
	loadn r1, #1
L16:
	jz L15
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L15:
	loadn r1, #0
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L19
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	loadn r4, #1
	sub r3, r3, r4
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	jz L20
	loadn r3, #1
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #2
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #30
	mul r4, r4, r5
	add r3, r3, r4
	loadn r4, #3
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #3
	mul r4, r4, r5
	add r3, r3, r4
	loadi r3, r3
	loadn r4, #1
	cmp r3, r4
	push fr
	pop r3
	loadn r7, #4
	and r3, r3, r7
	mov r2, r3
	jz L20
	loadn r2, #1
L20:
	mov r1, r2
	jz L19
	loadn r1, #1
L19:
	jz L18
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L18:
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	loadn r3, #1
	sub r2, r2, r3
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L22
	loadn r2, #0
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	jz L23
	loadn r3, #1
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #2
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #30
	mul r4, r4, r5
	add r3, r3, r4
	loadn r4, #3
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #3
	mul r4, r4, r5
	add r3, r3, r4
	loadi r3, r3
	loadn r4, #1
	cmp r3, r4
	push fr
	pop r3
	loadn r7, #4
	and r3, r3, r7
	mov r2, r3
	jz L23
	loadn r2, #1
L23:
	mov r1, r2
	jz L22
	loadn r1, #1
L22:
	jz L21
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L21:
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	loadn r3, #1
	sub r2, r2, r3
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L25
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	loadn r4, #1
	sub r3, r3, r4
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	jz L26
	loadn r3, #1
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #2
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #30
	mul r4, r4, r5
	add r3, r3, r4
	loadn r4, #3
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #3
	mul r4, r4, r5
	add r3, r3, r4
	loadi r3, r3
	loadn r4, #1
	cmp r3, r4
	push fr
	pop r3
	loadn r7, #4
	and r3, r3, r7
	mov r2, r3
	jz L26
	loadn r2, #1
L26:
	mov r1, r2
	jz L25
	loadn r1, #1
L25:
	jz L24
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L24:
	loadn r1, #4
	sub r1, r0, r1
	loadi r1, r1
	mov r7, r1
	jmp Lend1
Lend1:
	mov sp, r0
	pop r0
	rts
contar_bombas:
	push r0
	mov r0, sp
	loadn r7, #5
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r7, #2
	sub r7, r0, r7
	storei r7, r2
	loadn r1, #0
	loadn r2, #3
	sub r2, r0, r2
	storei r2, r1
L27:
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L28
	loadn r1, #0
	loadn r2, #4
	sub r2, r0, r2
	storei r2, r1
L30:
	loadn r1, #4
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L31
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #4
	sub r3, r0, r3
	loadi r3, r3
	call quantidade_bombas_vizinhas
	mov r4, r7
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	mul r2, r2, r3
	add r1, r1, r2
	storei r1, r4
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L30
L31:
L32:
	loadn r1, #3
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L27
L28:
L29:
Lend2:
	mov sp, r0
	pop r0
	rts
imprimir:
	push r0
	mov r0, sp
	loadn r7, #21
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r7, #2
	sub r7, r0, r7
	storei r7, r2
	loadn r7, #3
	sub r7, r0, r7
	storei r7, r3
	loadn r1, #str0
	call prints
	mov r2, r7
	loadn r1, #str1
	call prints
	mov r2, r7
	loadn r1, #0
	loadn r2, #5
	sub r2, r0, r2
	storei r2, r1
L33:
	loadn r1, #5
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L34
	loadn r1, #5
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #11
	sub r2, r0, r2
	loadn r3, #10
	call itos
	mov r4, r7
	loadn r1, #str2
	call prints
	mov r2, r7
	loadn r1, #11
	sub r1, r0, r1
	call prints
	mov r2, r7
	loadn r1, #5
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L33
L34:
L35:
	loadn r1, #str3
	call prints
	mov r2, r7
	loadn r1, #str4
	call prints
	mov r2, r7
	loadn r1, #str5
	call prints
	mov r2, r7
	loadn r1, #0
	loadn r2, #4
	sub r2, r0, r2
	storei r2, r1
L36:
	loadn r1, #4
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L37
	loadn r1, #4
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #17
	sub r2, r0, r2
	loadn r3, #10
	call itos
	mov r4, r7
	loadn r1, #17
	sub r1, r0, r1
	call prints
	mov r2, r7
	loadn r1, #str6
	call prints
	mov r2, r7
	loadn r1, #0
	loadn r2, #5
	sub r2, r0, r2
	storei r2, r1
L39:
	loadn r1, #5
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L40
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #5
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #1
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L42
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #30
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #5
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #1
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L44
	loadn r1, #str7
	call prints
	mov r2, r7
	jmp L45
L44:
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #5
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #20
	sub r2, r0, r2
	loadn r3, #10
	call itos
	mov r4, r7
	loadn r1, #20
	sub r1, r0, r1
	call prints
	mov r2, r7
L45:
	jmp L43
L42:
	loadn r1, #str8
	call prints
	mov r2, r7
L43:
	loadn r1, #str9
	call prints
	mov r2, r7
	loadn r1, #5
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L39
L40:
L41:
	loadn r1, #str10
	call prints
	mov r2, r7
	loadn r1, #str11
	call prints
	mov r2, r7
	loadn r1, #str12
	call prints
	mov r2, r7
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L36
L37:
L38:
Lend3:
	mov sp, r0
	pop r0
	rts
abrir_celula:
	push r0
	mov r0, sp
	loadn r7, #6
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r7, #2
	sub r7, r0, r7
	storei r7, r2
	loadn r7, #3
	sub r7, r0, r7
	storei r7, r3
	loadn r7, #4
	sub r7, r0, r7
	storei r7, r4
	loadn r7, #5
	sub r7, r0, r7
	storei r7, r5
	loadn r1, #0
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #6
	and r1, r1, r7
	jz L47
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	jz L48
	loadn r3, #0
	loadn r4, #5
	sub r4, r0, r4
	loadi r4, r4
	cmp r3, r4
	push fr
	pop r3
	loadn r7, #6
	and r3, r3, r7
	jz L49
	loadn r4, #5
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #10
	cmp r4, r5
	push fr
	pop r4
	loadn r7, #2
	and r4, r4, r7
	jz L50
	loadn r5, #2
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #4
	sub r6, r0, r6
	loadi r6, r6
	loadn r7, #10
	mul r6, r6, r7
	add r5, r5, r6
	loadn r6, #5
	sub r6, r0, r6
	loadi r6, r6
	loadn r7, #1
	mul r6, r6, r7
	add r5, r5, r6
	loadi r5, r5
	loadn r6, #0
	cmp r5, r6
	push fr
	pop r5
	loadn r7, #4
	and r5, r5, r7
	mov r4, r5
	jz L50
	loadn r4, #1
L50:
	mov r3, r4
	jz L49
	loadn r3, #1
L49:
	mov r2, r3
	jz L48
	loadn r2, #1
L48:
	mov r1, r2
	jz L47
	loadn r1, #1
L47:
	jz L46
	loadn r1, #1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #4
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #10
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #5
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #5
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #0
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L51
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	sub r5, r5, r6
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	add r5, r5, r6
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	sub r5, r5, r6
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	sub r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	add r5, r5, r6
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	sub r5, r5, r6
	call abrir_celula
	mov r6, r7
	loadn r1, #1
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #2
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #4
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #1
	add r4, r4, r5
	loadn r5, #5
	sub r5, r0, r5
	loadi r5, r5
	loadn r6, #1
	add r5, r5, r6
	call abrir_celula
	mov r6, r7
L51:
L46:
Lend4:
	mov sp, r0
	pop r0
	rts
ganhou:
	push r0
	mov r0, sp
	loadn r7, #6
	sub r7, r0, r7
	mov sp, r7
	loadn r7, #1
	sub r7, r0, r7
	storei r7, r1
	loadn r7, #2
	sub r7, r0, r7
	storei r7, r2
	loadn r1, #0
	loadn r2, #5
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #0
	loadn r2, #3
	sub r2, r0, r2
	storei r2, r1
L52:
	loadn r1, #3
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L53
	loadn r1, #0
	loadn r2, #4
	sub r2, r0, r2
	storei r2, r1
L55:
	loadn r1, #4
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L56
	loadn r1, #2
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #3
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #10
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #4
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #1
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #1
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L59
	loadn r2, #1
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #30
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #4
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #3
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #0
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L59
	loadn r1, #1
L59:
	jz L58
	loadn r1, #5
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
L58:
	loadn r1, #4
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L55
L56:
L57:
	loadn r1, #3
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L52
L53:
L54:
	loadn r1, #10
	loadn r2, #10
	mul r1, r1, r2
	loadn r2, #19
	sub r1, r1, r2
	loadn r2, #5
	sub r2, r0, r2
	loadi r2, r2
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #6
	and r1, r1, r7
	jz L60
	loadn r1, #1
	mov r7, r1
	jmp Lend5
L60:
	loadn r1, #0
	mov r7, r1
	jmp Lend5
Lend5:
	mov sp, r0
	pop r0
	rts
main:
	push r0
	mov r0, sp
	loadn r7, #508
	sub r7, r0, r7
	mov sp, r7
L61:
	loadn r1, #300
	sub r1, r0, r1
	call iniciar_jogo
	mov r2, r7
	loadn r1, #300
	sub r1, r0, r1
	loadn r2, #500
	sub r2, r0, r2
	call contar_bombas
	mov r3, r7
	loadn r1, #0
	loadn r2, #503
	sub r2, r0, r2
	storei r2, r1
L63:
	loadn r1, #503
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L64
	loadn r1, #0
	loadn r2, #504
	sub r2, r0, r2
	storei r2, r1
L66:
	loadn r1, #504
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #10
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L67
	loadn r1, #0
	loadn r2, #400
	sub r2, r0, r2
	loadn r3, #503
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #10
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #504
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	mul r3, r3, r4
	add r2, r2, r3
	storei r2, r1
	loadn r1, #504
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L66
L67:
L68:
	loadn r1, #503
	sub r1, r0, r1
	loadi r2, r1
	inc r2
	storei r1, r2
	dec r2
	jmp L63
L64:
L65:
	loadn r1, #str13
	call prints
	mov r2, r7
	loadn r1, #0
	loadn r2, #505
	sub r2, r0, r2
	storei r2, r1
L69:
	loadn r1, #505
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #0
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L72
	loadn r2, #300
	sub r2, r0, r2
	loadn r3, #400
	sub r3, r0, r3
	push r3
	call ganhou
	mov r4, r7
	pop r3
	loadn r2, #0
	cmp r4, r2
	push fr
	pop r4
	loadn r7, #4
	and r4, r4, r7
	mov r1, r4
	jz L72
	loadn r1, #1
L72:
	jz L70
	loadn r1, #0
	call sfill
	mov r2, r7
	loadn r1, #0
	loadn r2, #__cursor
	storei r2, r1
	loadn r1, #300
	sub r1, r0, r1
	loadn r2, #400
	sub r2, r0, r2
	loadn r3, #500
	sub r3, r0, r3
	call imprimir
	mov r4, r7
L73:
	loadn r1, #str14
	call prints
	mov r2, r7
	call getc
	mov r1, r7
	loadn r2, #48
	sub r1, r1, r2
	loadn r2, #506
	sub r2, r0, r2
	storei r2, r1
	call getc
	mov r1, r7
	loadn r2, #48
	sub r1, r1, r2
	loadn r2, #507
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #506
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #0
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L83
	loadn r1, #1
	jmp L84
L83:
	loadn r2, #10
	loadn r3, #506
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #6
	and r2, r2, r7
	mov r1, r2
	jz L84
	loadn r1, #1
L84:
	jz L81
	loadn r1, #1
	jmp L82
L81:
	loadn r2, #507
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #0
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	mov r1, r2
	jz L82
	loadn r1, #1
L82:
	jz L79
	loadn r1, #1
	jmp L80
L79:
	loadn r2, #10
	loadn r3, #507
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #6
	and r2, r2, r7
	mov r1, r2
	jz L80
	loadn r1, #1
L80:
	jz L77
	loadn r1, #1
	jmp L78
L77:
	loadn r2, #400
	sub r2, r0, r2
	loadn r3, #506
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #10
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #507
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #1
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L78
	loadn r1, #1
L78:
	jz L75
	loadn r1, #str15
	call prints
	mov r2, r7
	jmp L76
L75:
	loadn r1, #300
	sub r1, r0, r1
	loadn r2, #400
	sub r2, r0, r2
	loadn r3, #500
	sub r3, r0, r3
	loadn r4, #506
	sub r4, r0, r4
	loadi r4, r4
	loadn r5, #507
	sub r5, r0, r5
	loadi r5, r5
	call abrir_celula
	mov r6, r7
L76:
	loadn r1, #506
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #0
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #2
	and r1, r1, r7
	jz L91
	loadn r1, #1
	jmp L92
L91:
	loadn r2, #10
	loadn r3, #506
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #6
	and r2, r2, r7
	mov r1, r2
	jz L92
	loadn r1, #1
L92:
	jz L89
	loadn r1, #1
	jmp L90
L89:
	loadn r2, #507
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #0
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #2
	and r2, r2, r7
	mov r1, r2
	jz L90
	loadn r1, #1
L90:
	jz L87
	loadn r1, #1
	jmp L88
L87:
	loadn r2, #10
	loadn r3, #507
	sub r3, r0, r3
	loadi r3, r3
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #6
	and r2, r2, r7
	mov r1, r2
	jz L88
	loadn r1, #1
L88:
	jz L85
	loadn r1, #1
	jmp L86
L85:
	loadn r2, #400
	sub r2, r0, r2
	loadn r3, #506
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #10
	mul r3, r3, r4
	add r2, r2, r3
	loadn r3, #507
	sub r3, r0, r3
	loadi r3, r3
	loadn r4, #1
	mul r3, r3, r4
	add r2, r2, r3
	loadi r2, r2
	loadn r3, #0
	cmp r2, r3
	push fr
	pop r2
	loadn r7, #4
	and r2, r2, r7
	mov r1, r2
	jz L86
	loadn r1, #1
L86:
	jnz L73
L74:
	loadn r1, #300
	sub r1, r0, r1
	loadn r2, #506
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #30
	mul r2, r2, r3
	add r1, r1, r2
	loadn r2, #507
	sub r2, r0, r2
	loadi r2, r2
	loadn r3, #3
	mul r2, r2, r3
	add r1, r1, r2
	loadi r1, r1
	loadn r2, #1
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jz L93
	loadn r1, #1
	loadn r2, #505
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #0
	call sfill
	mov r2, r7
	loadn r1, #0
	loadn r2, #__cursor
	storei r2, r1
	loadn r1, #str16
	call prints
	mov r2, r7
	loadn r1, #str17
	call prints
	mov r2, r7
	loadn r1, #str18
	call prints
	mov r2, r7
	loadn r1, #str19
	call prints
	mov r2, r7
	loadn r1, #str20
	call prints
	mov r2, r7
	loadn r1, #str21
	call prints
	mov r2, r7
	loadn r1, #str22
	call prints
	mov r2, r7
	loadn r1, #str23
	call prints
	mov r2, r7
	loadn r1, #str24
	call prints
	mov r2, r7
	loadn r1, #str25
	call prints
	mov r2, r7
	loadn r1, #str26
	call prints
	mov r2, r7
	loadn r1, #str27
	call prints
	mov r2, r7
	loadn r1, #str28
	call prints
	mov r2, r7
	loadn r1, #str29
	call prints
	mov r2, r7
	loadn r1, #str30
	call prints
	mov r2, r7
	jmp L94
L93:
	loadn r1, #300
	sub r1, r0, r1
	loadn r2, #400
	sub r2, r0, r2
	call ganhou
	mov r3, r7
	loadn r1, #1
	cmp r3, r1
	push fr
	pop r3
	loadn r7, #4
	and r3, r3, r7
	jz L95
	loadn r1, #0
	call sfill
	mov r2, r7
	loadn r1, #0
	loadn r2, #__cursor
	storei r2, r1
	loadn r1, #str31
	call prints
	mov r2, r7
	loadn r1, #str32
	call prints
	mov r2, r7
	loadn r1, #str33
	call prints
	mov r2, r7
	loadn r1, #str34
	call prints
	mov r2, r7
	loadn r1, #str35
	call prints
	mov r2, r7
	loadn r1, #str36
	call prints
	mov r2, r7
	loadn r1, #str37
	call prints
	mov r2, r7
	loadn r1, #str38
	call prints
	mov r2, r7
	loadn r1, #str39
	call prints
	mov r2, r7
	loadn r1, #str40
	call prints
	mov r2, r7
	loadn r1, #str41
	call prints
	mov r2, r7
	loadn r1, #1
	loadn r2, #505
	sub r2, r0, r2
	storei r2, r1
L95:
L94:
	jmp L69
L70:
L71:
	loadn r1, #str42
	call prints
	mov r2, r7
	call getc
	mov r1, r7
	loadn r2, #502
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #502
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #48
	sub r1, r1, r2
	loadn r2, #501
	sub r2, r0, r2
	storei r2, r1
	loadn r1, #501
	sub r1, r0, r1
	loadi r1, r1
	loadn r2, #1
	cmp r1, r2
	push fr
	pop r1
	loadn r7, #4
	and r1, r1, r7
	jnz L61
L62:
	loadn r1, #0
	mov r7, r1
	jmp Lend6
Lend6:
	mov sp, r0
	pop r0
	rts
str0 : string "\n"
str1 : string " "
str2 : string " "
str3 : string "\n"
str4 : string "----------------------"
str5 : string "\n"
str6 : string "|"
str7 : string "s"
str8 : string " "
str9 : string "|"
str10 : string "\n"
str11 : string "----------------------"
str12 : string "\n"
str13 : string "Campo Minado/n"
str14 : string "\nDigite as coordenadas de linha e coluna: "
str15 : string "\nCoordenada Invalida ou ja Aberta!"
str16 : string "                    \n"
str17 : string "              ::    \n"
str18 : string "            ##      \n"
str19 : string "          ##        \n"
str20 : string "        @@MMMM      \n"
str21 : string "        ##MMMM      \n"
str22 : string "    @@@@mmmm++..##  \n"
str23 : string "  ##@@MMmmmm::..mm..\n"
str24 : string "  MM@@mmmmmm++::mmMM\n"
str25 : string "  @@MMmmmmmmmmmmmmMM\n"
str26 : string "  @@MMmmmmmmmmmmmmMM\n"
str27 : string "  MM@@MMmmmmmmmmmm##\n"
str28 : string "    @@MMmmmmmmmmMM  \n"
str29 : string "      ##MMMMmmMM    \n"
str30 : string "\nVoce encontrou uma bomba! Fim de Jogo!"
str31 : string "    ___________  \n"
str32 : string "   '.=========.' \n"
str33 : string "   .-       /-. \n"
str34 : string "  | (|       |) |\n"
str35 : string "   '-|       |-' \n"
str36 : string "            /   \n"
str37 : string "      '.   .'    \n"
str38 : string "        ) (      \n"
str39 : string "       .' '.    \n"
str40 : string "        \n"
str41 : string "\nParabens! Voce ganhou!"
str42 : string "\nDigite 1 para jogar novamente: "
; getc   : aguarda e le um caracter do teclado
; out r7 : caracter lido
getc:
  push r1

  loadn r1, #255

  getc_loop:
    inchar r7
    cmp r7, r1
    jeq getc_loop

  getc_rts:
    pop r1
    rts

; scans   : aguarde e le uma string do teclado
; in * r1 : string de destino
; in r2   : numero de caracteres a serem lidos
scans:
  push r3
  push r4
  push r5
  push r6

  xor r3, r3, r3
  loadn r4, #13 ; '\r'
  loadn r5, #255  

  scans_loop:
    cmp r2, r3
    jel scans_rts

    scans_loop_inchar:
      inchar r6
      cmp r6, r5
      jeq scans_loop_inchar
    cmp r6, r4
    jeq scans_rts

    storei r1, r6
    inc r1
    dec r2
    jmp scans_loop

  scans_rts:
    storei r1, r3
    
    pop r3
    pop r4
    pop r5
    pop r6
    rts

; puts    : imprime uma string em uma posição da tela
; in * r1 : string
; in r2   : posição
puts:
  push r3
  push r4
  push r5
  push r6
  push r7

  xor r5, r5, r5
  loadn r6, #'\n'
  loadn r7, #40

  puts_loop:
    loadi r3, r1

    cmp r3, r5
    jel puts_rts

    cmp r3, r6
    jne puts_loop_ne
    mod r4, r2, r7
    sub r4, r7, r4
    add r2, r2, r4
    dec r2

    puts_loop_ne:
    outchar r3, r2
    inc r1
    inc r2
    jmp puts_loop

  puts_rts:
    pop r7
    pop r6
    pop r5
    pop r4
    pop r3
    rts

; printc : imprime um caracter na posição do cursor
; in r1  : caracter
printc:
  push r2

  load r2, __cursor
  outchar r1, r2
  inc r2
  store __cursor, r2

  printc_rts:
    pop r2
    rts

; prints  : imprime uma string na posição do cursor
; in * r1 : string
prints:
  push r2

  load r2, __cursor
  call puts
  store __cursor, r2

  prints_rts:
    pop r2
    rts

; sfill : preenche a tela com um caracter
; in r1 : caracter
sfill:
  push r2

  loadn r2, #1200

  sfill_loop:
    dec r2
    outchar r1, r2
    jnz sfill_loop

  sfill_rts:
    pop r2
    rts

__cursor : var #1 
static __cursor, #0
; stoi    : converte uma string para um inteiro, seguindo a base especificada
; in * r1 : string
; in r2   : base
; out r7  : numero
stoi:
  push r3
  push r4
  push r5
  push r6

  xor r4, r4, r4
  loadn r5, #'0'
  loadn r6, #'W'
  xor r7, r7, r7

  stoi_loop:
    loadi r3, r1
    cmp r3, r4
    jeq stoi_rts

    cmp r3, r6
    jeg stoi_loop_eg  
    
    sub r3, r3, r5
    jmp stoi_loop_le
    stoi_loop_eg:
    sub r3, r3, r6
    stoi_loop_le:

    mul r7, r7, r2
    add r7, r7, r3

    inc r1
    jmp stoi_loop

  stoi_rts:
    pop r6
    pop r5
    pop r4
    pop r3
    rts

; itos    : converte um inteiro para uma string, seguindo a base especificada
; in r1   : numero
; in * r2 : string de destino
; in r3   : base
itos:
  push r4
  push r5
  push r6
  push r7
  push r2

  loadn r5, #'0'
  loadn r6, #10
  loadn r7, #39

  itos_loop:
    mod r4, r1, r3

    cmp r4, r6
    jle itos_loop_le
    add r4, r4, r7

    itos_loop_le:
    add r4, r4, r5
    storei r2, r4

    inc r2
    div r1, r1, r3
    jnz itos_loop

  storei r2, r1

  pop r2
  mov r1, r2
  call strrev

  itos_rts:
    pop r7
    pop r6
    pop r5
    pop r4
    rts

; memset  : preenche um bloco de memoria continuo com um valor
; in * r1 : endereco do bloco de memoria
; in r2   : valor a ser escrito
; in r3   : tamanho do bloco
memset:
  push r4

  xor r4, r4, r4

  memset_loop:
    cmp r3, r4
    jel memset_rts

    storei r1, r2

    inc r1
    dec r3
    jmp memset_loop

  memset_rts:
    pop r4
    rts

; memcpy  : copia um bloco de memoria continuo para um endereco de destino
; in * r1 : destino
; in * r2 : origem
; in r3   : tamanho a ser copiado
memcpy:
  push r4
  push r5

  xor r4, r4, r4

  memcpy_loop:
    cmp r3, r4
    jel memcpy_rts

    loadi r5, r2
    storei r1, r5

    inc r1
    inc r2
    dec r3
    jmp memcpy_loop

  memcpy_rts:
    pop r5
    pop r4
    rts

; strcmp  : compara duas strings terminadas em '\0'
; in * r1 : primeira string
; in * r2 : segunda string
; out r7  : 0 caso forem diferentes, 1 caso forem iguais
strcmp:
  push r3
  push r4
  push r5

  xor r3, r3, r3

  strcmp_loop:
    loadi r4, r1
    loadi r5, r2
    
    cmp r4, r3
    jeq strcmp_rts
    cmp r4, r5
    jne strcmp_rts

    inc r1
    inc r2
    jmp strcmp_loop
  
  strcmp_rts:
    sub r7, r4, r5
    
    pop r5
    pop r4
    pop r3
    rts

; strrev  : reverte uma string (inplace)
; in * r1 : string
strrev:
  push r2
  push r3
  push r7

  ; endereco de memoria do fim da string - 1
  ; r1 + (strlen(r1) - 1)
  push r1
  call strlen
  pop r1
  
  dec r7
  add r7, r7, r1

  strrev_loop:
    ; troca a posicao entre os caracteres
    loadi r2, r1
    loadi r3, r7
    storei r1, r3 
    storei r7, r2

    dec r7
    inc r1

    ; r1 >= r7 ? return
    cmp r1, r7
    jle strrev_loop

  strrev_rts:
    pop r7
    pop r3
    pop r2
    rts

; strlen  : calcula o numero de caracteres de uma string (ignorando '\0')
; in * r1 : string
; out r7  : numero de caracteres
strlen:
  push r2 ; caractere da string apontado por r2
  push r3 ; caractere que termina a string ('\0')

  xor r2, r2, r2
  xor r7, r7, r7

  strlen_loop:
    loadi r3, r1

    cmp r3, r2
    jeq strlen_rts
    
    inc r1
    inc r7
    jmp strlen_loop

  strlen_rts:
    pop r3
    pop r2
    rts
