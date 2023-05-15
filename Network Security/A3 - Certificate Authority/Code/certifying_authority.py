import time

from utils import *

ID_CA = 1000
PR_CA = 2878147650800948743
ids = {"ALICE": 1001, "BOB": 1002}
public_keys = {1001: 3578035, 1002: 8573017}


def gen_cert(name):
    ID = ids[name]
    T = str(time.strftime("%m/%Y"))
    DUR = '3 Years'
    CERT = Certificate(str(ID), str(public_keys[ID]), T, DUR, str(ID_CA))
    return CERT


def main():
    server_socket = setup_server(CA_PORT)
    while True:
        message, address = listen(server_socket)

        CERT = gen_cert(message)
        CERT_ENC = encrypt_cert(CERT, PR_CA)
        response = str(CERT_ENC).encode()

        server_socket.sendto(response, address)


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print("Shutting Down")
