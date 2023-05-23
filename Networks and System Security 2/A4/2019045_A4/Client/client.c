#include <errno.h>
#include <unistd.h>
#include <string.h>
#include <resolv.h>
#include <netdb.h>
#include <openssl/ssl.h>
#include <openssl/err.h>

const int ERROR_STATUS = -1;

SSL_CTX *InitSSL_CTX(void) {
    const SSL_METHOD *method = TLSv1_2_client_method();
    SSL_CTX *ctx = SSL_CTX_new(method);

    return ctx;
}

int OpenConnection(const char *hostname, const char *port) {
    struct addrinfo hints = {0}, *addrs;
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    hints.ai_protocol = IPPROTO_TCP;

    getaddrinfo(hostname, port, &hints, &addrs);

    int sfd, err;
    for (struct addrinfo *addr = addrs; addr != NULL; addr = addr->ai_next) {
        sfd = socket(addrs->ai_family, addrs->ai_socktype, addrs->ai_protocol);
        if (sfd == ERROR_STATUS) {
            err = errno;
            continue;
        }

        if (connect(sfd, addr->ai_addr, addr->ai_addrlen) == 0) {
            break;
        }

        err = errno;
        sfd = ERROR_STATUS;
        close(sfd);
    }

    freeaddrinfo(addrs);

    if (sfd == ERROR_STATUS) {
        fprintf(stderr, "%s: %s\n", hostname, strerror(err));
        exit(EXIT_FAILURE);
    }
    return sfd;
}

void DisplayCerts(SSL *ssl) {
    X509 *cert = SSL_get_peer_certificate(ssl);
    if (cert != NULL) {
        printf("Server certificates:\n");
        char *line = X509_NAME_oneline(X509_get_subject_name(cert), 0, 0);
        printf("Subject: %s\n", line);
        line = X509_NAME_oneline(X509_get_issuer_name(cert), 0, 0);
        printf("Issuer: %s\n", line);
        X509_free(cert);
    }
    else {
        printf("No server certificates received.\n");
    }
}

int main() {
    SSL_load_error_strings();
    SSL_CTX *ctx = InitSSL_CTX();
    SSL *ssl = SSL_new(ctx);

    const int sfd = OpenConnection("localhost", "7777");
    SSL_set_fd(ssl, sfd);

    SSL_CTX_set_verify(ctx, SSL_VERIFY_NONE, NULL);
    SSL_CTX_load_verify_locations(ctx, "CA/ca_cert.pem", NULL);

    SSL_connect(ssl);

    printf("Connected with %s encryption\n", SSL_get_cipher(ssl));

    DisplayCerts(ssl);

    unsigned int verify = SSL_get_verify_result(ssl);
    if (verify != X509_V_OK) {
        printf("Certificate verification error, code = %d\n", verify);
        printf("Closing the connection.\n");
        SSL_write(ssl, "exit", 4);
        return 0;
    }

    while (1) {
        char input[1024];
        printf("Enter message: ");
        fgets(input, 1024, stdin);
        SSL_write(ssl, input, sizeof (input));
        if (strncmp(input, "exit", 4) == 0) {
            break;
        }
        char buf[1024];
        SSL_read(ssl, buf, 1024);
        printf("Server replied = %s", buf);
    }


    SSL_free(ssl);
    close(sfd);
    SSL_CTX_free(ctx);
    return 0;
}