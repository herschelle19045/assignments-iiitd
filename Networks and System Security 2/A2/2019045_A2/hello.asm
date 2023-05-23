SYS_WRITE equ 1
SYS_EXIT equ 60
STD_OUTPUT equ 1
                        
section .text
global _start
                        
_start:
	jmp short MainCode
	msg: 
	db "Hello World"

    msglen equ $-msg
                        
MainCode:
    xor rax, rax
    mov al, SYS_WRITE
    xor rdi, rdi
    add di, STD_OUTPUT
    lea rsi, [rel msg]
    xor rdx, rdx
    add rdx, msglen
    syscall
                        
    xor rax, rax
    mov al, SYS_EXIT
    xor rdi, rdi
    syscall
