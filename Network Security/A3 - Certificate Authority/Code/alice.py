from utils import *

ID = 1001
PR_ALICE = 310638072474487843


def decrypt_message():
    msg = input("Enter msg to decrypt: ")
    key = int(input("Enter key to use: "))
    res = decrypt_text(msg, key)
    print("Decrypted Msg:", res)


def send_msg_bob():
    msg = input("Enter Message: ")
    key = int(input("Enter Bob's public key: "))
    msg = str(encrypt_text(msg, key))

    udp_socket = udp_client()
    send(udp_socket, msg.encode(), BOB_PORT)

    response = udp_socket.recvfrom(1024)
    response = response[0].decode()

    print("Response from Bob:", response)


def contact_ca():
    udp_socket = udp_client()

    message = "BOB"
    send(udp_socket, message.encode(), CA_PORT)

    response = udp_socket.recvfrom(1024)
    response = response[0].decode()

    tokens = response.split(';;;')
    labels = ['ID', 'PU', 'T', 'DUR', 'ID_CA']
    for i in range(5):
        print(f'{labels[i]}: {tokens[i]}')


def menu():
    print("Menu:")
    print("\t1) Contact CA")
    print("\t2) Message Bob")
    print("\t3) Decrypt Message")


def main():
    while True:
        menu()
        print('-' * 25)
        option = int(input("Enter Choice: "))
        if option == 1:
            contact_ca()
        elif option == 2:
            send_msg_bob()
        elif option == 3:
            decrypt_message()
        else:
            break
        print('-' * 25)


if __name__ == '__main__':
    print("Public:")
    print("\tPU_CA:", PU_CA)
    print("\tPR_ALICE:", PR_ALICE)
    print('-' * 25)
    try:
        main()
    except KeyboardInterrupt:
        print("Shutting Down")
