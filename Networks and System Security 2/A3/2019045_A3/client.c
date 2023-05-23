#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/hmac.h>

#define PORT 8080

int encrypt(unsigned char *, int, unsigned char *, unsigned char *, unsigned char *);


int main() {
    int sock = 0;
    struct sockaddr_in serv_addr;


    sock = socket(AF_INET, SOCK_STREAM, 0);

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);

    inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr);

    connect(sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr));

    /* A 256 bit key */
    unsigned char *key = (unsigned char *) "01234567890123456789012345678901";
    int keylen = sizeof (key);

    /* A 128 bit IV */
    unsigned char *iv = (unsigned char *) "0123456789012345";



    int fd[2];
    pipe(fd);

    pid_t pid = fork();

    if(pid < 0) {
        return 0;
    }
    else if(pid == 0) {
        close(fd[0]);
        close(1);
        dup(fd[1]);

        FILE *fp;
        fp = fopen("message.txt", "r");
        unsigned char input[10240];
        int index = 0;
        char ch;
        while ((ch = fgetc(fp)) != EOF)
            input[index++] = ch;
//        input[index] = '\0';

        unsigned char ciphertext[10240];
        unsigned char hmac[10240];

        HMAC(EVP_sha256(), key, keylen, input, strlen(input), hmac, 0);

        unsigned char *seperator = ";;;";

        strcat(input, seperator);
        strcat(input, hmac);


        encrypt(input, sizeof (input), key, iv, ciphertext);

        write(1, ciphertext, sizeof (ciphertext));
        close(fd[1]);
    }
    else {
        wait(NULL);
        close(0);
        close(fd[1]);
        dup(fd[0]);


        unsigned char input[1024];
        read(fd[0], input, sizeof(input));

        send(sock, input, sizeof (input), 0);
        close(fd[0]);
    }

    return 0;
}

int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,
            unsigned char *iv, unsigned char *ciphertext) {

    EVP_CIPHER_CTX *ctx;
    int len, ciphertext_len;

    ctx = EVP_CIPHER_CTX_new();
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len);

    ciphertext_len = len;

    EVP_EncryptFinal_ex(ctx, ciphertext + len, &len);

    ciphertext_len += len;
    EVP_CIPHER_CTX_free(ctx);
    return ciphertext_len;
}
