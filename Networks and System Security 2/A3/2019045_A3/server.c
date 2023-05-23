#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <fcntl.h>
#include <netinet/in.h>
#include <sys/wait.h>
#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>

#define PORT 8080

int decrypt(unsigned char *, int, unsigned char *, unsigned char *, unsigned char *);

int main() {
    int server_fd, new_socket;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);

    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    bind(server_fd, (struct sockaddr *) &address, sizeof(address));
    listen(server_fd, 3);

    new_socket = accept(server_fd, (struct sockaddr *) &address, (socklen_t *) &addrlen);

    /* A 256 bit key */
    unsigned char *key = (unsigned char *) "01234567890123456789012345678901";
    int keylen = sizeof(key);

    /* A 128 bit IV */
    unsigned char *iv = (unsigned char *) "0123456789012345";

    int fd[2];
    pipe(fd);

    pid_t pid = fork();

    if (pid < 0) {
        return 0;
    }
    else if (pid == 0) {
        close(fd[0]);
        close(1);
        dup(fd[1]);

        unsigned char buffer[10240] = {0};
        read(new_socket, buffer, 10240);

        unsigned char decryptedtext[10240];

        decrypt(buffer, sizeof(buffer), key, iv, decryptedtext);


        write(1, decryptedtext, sizeof(decryptedtext));
        close(fd[1]);
    }
    else {
        wait(NULL);
        close(0);
        close(fd[1]);
        dup(fd[0]);

        char buffer[10240] = {0};
        read(fd[0], buffer, sizeof(buffer));
        close(fd[0]);

        unsigned char *msg = (unsigned char *) strtok(buffer, ";;;");
        unsigned char *hmac = (unsigned char *) strtok(NULL, ";;;");

        unsigned char myhmac[10240];

        HMAC(EVP_sha256(), key, keylen, msg, strlen(msg), myhmac, 0);

        int res = strcmp(myhmac, hmac);

        if (res == 0) {
            printf("Message successfully written\n");
            FILE *fp = fopen("received.txt", "w");
            fputs(msg, fp);
            fclose(fp);
        }
        else {
            printf("HMAC Authentication Failed\n");
        }

    }

    return 0;
}

int decrypt(unsigned char *ciphertext, int ciphertext_len, unsigned char *key,
            unsigned char *iv, unsigned char *plaintext) {

    EVP_CIPHER_CTX *ctx;
    int len, plaintext_len;

    ctx = EVP_CIPHER_CTX_new();
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len);

    plaintext_len = len;

    EVP_DecryptFinal_ex(ctx, plaintext + len, &len);

    plaintext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    return plaintext_len;
}