#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <openssl/ssl.h>
#include <openssl/err.h>
#include <ctype.h>

int create_socket(int port) {
    int s;
    struct sockaddr_in addr;

    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);

    s = socket(AF_INET, SOCK_STREAM, 0);

    bind(s, (struct sockaddr *) &addr, sizeof(addr));
    listen(s, 1);

    return s;
}

SSL_CTX *create_context() {
    const SSL_METHOD *method = TLSv1_2_server_method();
    SSL_CTX *ctx = SSL_CTX_new(method);
    return ctx;
}

void configure_context(SSL_CTX *ctx) {
    SSL_CTX_use_certificate_file(ctx, "Server/server_cert.pem", SSL_FILETYPE_PEM);
    SSL_CTX_use_PrivateKey_file(ctx, "Server/server_key.pem", SSL_FILETYPE_PEM);
    if ( !SSL_CTX_check_private_key(ctx) ) {
        fprintf(stderr, "Private key does not match the public certificate\n");
        abort();
    }
}

int main() {
    SSL_CTX *ctx = create_context();
    configure_context(ctx);

    int sock = create_socket(7777);

    struct sockaddr_in addr;
    unsigned int len = sizeof(addr);

    int client = accept(sock, (struct sockaddr *) &addr, &len);

    SSL *ssl = SSL_new(ctx);
    SSL_set_fd(ssl, client);
    SSL_accept(ssl);

    while (1) {
        char buf[1024];
        SSL_read(ssl, buf, 1024);

        if (strncmp(buf, "exit", 4) == 0) {
            break;
        }

        for (int i = 0; i < strlen(buf); ++i) {
            buf[i] = toupper(buf[i]);
        }
        SSL_write(ssl, buf, sizeof(buf));
    }


    SSL_shutdown(ssl);
    SSL_free(ssl);
    close(client);


    close(sock);
    SSL_CTX_free(ctx);
}