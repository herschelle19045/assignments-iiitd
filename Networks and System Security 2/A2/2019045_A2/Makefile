compile:
	nasm -f elf64 hello.asm -o hello.o
	ld hello.o -o hello

run:
	./hello

generate:
	objcopy -O binary --only-section=.text -I elf64-x86-64 hello opcodes.bin
	hexdump -v -e '"\\""x" 1/1 "%02x" ""' opcodes.bin

payload:
	python2 payload.py > payload

clean:
	rm hello.o hello opcodes.bin
