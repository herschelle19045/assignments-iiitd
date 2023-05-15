from utils import *

ID = 1002
PR_BOB = 43971470488914710569


def get_alice_cert_from_ca():
    udp_socket = udp_client()

    message = "ALICE"
    send(udp_socket, message.encode(), CA_PORT)

    response = udp_socket.recvfrom(1024)
    response = response[0].decode()

    tokens = response.split(';;;')
    ALICE_PU = decrypt_text(tokens[1], PU_CA)
    return int(ALICE_PU)


def main():
    server_socket = setup_server(BOB_PORT)
    while True:
        message, address = listen(server_socket)

        message = decrypt_text(message, PR_BOB)
        print("Message from Alice:", message)

        ALICE_PU = get_alice_cert_from_ca()
        response = encrypt_text(f"ACK{message[-1]}", ALICE_PU).encode()

        server_socket.sendto(response, address)


if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print("Shutting Down")
