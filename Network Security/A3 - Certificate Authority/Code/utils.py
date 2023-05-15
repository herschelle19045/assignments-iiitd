import socket


class Certificate:
    def __init__(self, ID, PU, T, DUR, ID_CA):
        self.ID = ID
        self.PU = PU
        self.T = T
        self.DUR = DUR
        self.ID_CA = ID_CA

    def __str__(self) -> str:
        return f'{self.ID};;;{self.PU};;;{self.T};;;{self.DUR};;;{self.ID_CA}'


n = 5915587277 * 9576890767
# phi = 56652933158991493416
PU_CA = 214848199

BOB_PORT = 20000
CA_PORT = 25000


def apply_rsa(msg_text, key):
    msg_int = encode_char(msg_text)
    msg_int = int(msg_int)
    rsa = pow(msg_int, key, n)
    convert_back = decode_char(rsa)
    return convert_back


def encrypt_text(msg_text, PU):
    return apply_rsa(msg_text, PU)


def decrypt_text(msg_text, PR):
    return apply_rsa(msg_text, PR)


# def encode_char(msg_text):
#     msg_text = str(msg_text)
#     res = ""
#     for char in msg_text:
#         c = str(ord(char))
#         l = len(c)
#         c = (3 - l) * '0' + c
#         res += c
#     return res
#
#
# def decode_char(msg_int):
#     msg_text = str(msg_int)
#     leading_zeros = len(msg_text) % 3
#     msg_text = ((3 - leading_zeros) % 3) * '0' + msg_text
#
#     res = ""
#     for i in range(0, len(msg_text), 3):
#         num = msg_text[i:i + 3]
#         char = chr(int(num))
#         res += char
#     return res

def encode_char(msg_text):
    res = ''
    for c in msg_text:
        res += encodings[c]
    return res

def decode_char(msg_int):
    msg_test = str(msg_int)
    l = len(msg_test)
    if l & 1:
        msg_test = '0' + msg_test
    res = ''
    for i in range(0, len(msg_test), 2):
        num = msg_test[i:i+2]
        char = decodings[num]
        res += char
    return res


def encrypt_cert_algo(CERT, x):
    CERT.ID = encrypt_text(CERT.ID, x)
    CERT.PU = encrypt_text(CERT.PU, x)
    CERT.T = encrypt_text(CERT.T, x)
    CERT.DUR = encrypt_text(CERT.DUR, x)
    CERT.ID_CA = encrypt_text(CERT.ID_CA, x)
    return CERT


def encrypt_cert(CERT, e):
    return encrypt_cert_algo(CERT, e)


def decrypt_cert(CERT, d):
    return encrypt_cert_algo(CERT, d)


def setup_server(port):
    localIP = "127.0.0.1"
    localPort = port
    UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    UDPServerSocket.bind((localIP, localPort))

    print("UDP server up and listening on port", port)

    return UDPServerSocket


def listen(udp_socket):
    bufferSize = 1024
    bytesAddressPair = udp_socket.recvfrom(bufferSize)
    message = bytesAddressPair[0]
    address = bytesAddressPair[1]

    return message.decode(), address


def udp_client():
    UDPClientSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)
    return UDPClientSocket


def send(UDPClientSocket, message, port):
    serverAddressPort = ("127.0.0.1", port)
    UDPClientSocket.sendto(message, serverAddressPort)


encodings = {'φ': '00', 'ω': '01', 'β': '02', 'δ': '03', 'ε': '04', 'π': '05', 'Σ': '06', 'λ': '07', 'μ': '08',
             '`': '09',
             '!': '10', '@': '11', '#': '12', '$': '13', '%': '14', '^': '15', '&': '16', '*': '17', '(': '18',
             ')': '19', '-': '20', '_': '21', '+': '22', '=': '23', '{': '24', '}': '25', '[': '26', ']': '27',
             ':': '28', ';': '29', '<': '30', '>': '31', '.': '32', ',': '33', '?': '34', '/': '35', ' ': '36',
             '~': '37', 'a': '38', 'b': '39', 'c': '40', 'd': '41', 'e': '42', 'f': '43', 'g': '44', 'h': '45',
             'i': '46', 'j': '47', 'k': '48', 'l': '49', 'm': '50', 'n': '51', 'o': '52', 'p': '53', 'q': '54',
             'r': '55', 's': '56', 't': '57', 'u': '58', 'v': '59', 'w': '60', 'x': '61', 'y': '62', 'z': '63',
             '0': '64', '1': '65', '2': '66', '3': '67', '4': '68', '5': '69', '6': '70', '7': '71', '8': '72',
             '9': '73', 'A': '74', 'B': '75', 'C': '76', 'D': '77', 'E': '78', 'F': '79', 'G': '80', 'H': '81',
             'I': '82', 'J': '83', 'K': '84', 'L': '85', 'M': '86', 'N': '87', 'O': '88', 'P': '89', 'Q': '90',
             'R': '91', 'S': '92', 'T': '93', 'U': '94', 'V': '95', 'W': '96', 'X': '97', 'Y': '98', 'Z': '99'}

decodings = {'00': 'φ', '01': 'ω', '02': 'β', '03': 'δ', '04': 'ε', '05': 'π', '06': 'Σ', '07': 'λ', '08': 'μ',
             '09': '`',
             '10': '!', '11': '@', '12': '#', '13': '$', '14': '%', '15': '^', '16': '&', '17': '*', '18': '(',
             '19': ')', '20': '-', '21': '_', '22': '+', '23': '=', '24': '{', '25': '}', '26': '[', '27': ']',
             '28': ':', '29': ';', '30': '<', '31': '>', '32': '.', '33': ',', '34': '?', '35': '/', '36': ' ',
             '37': '~', '38': 'a', '39': 'b', '40': 'c', '41': 'd', '42': 'e', '43': 'f', '44': 'g', '45': 'h',
             '46': 'i', '47': 'j', '48': 'k', '49': 'l', '50': 'm', '51': 'n', '52': 'o', '53': 'p', '54': 'q',
             '55': 'r', '56': 's', '57': 't', '58': 'u', '59': 'v', '60': 'w', '61': 'x', '62': 'y', '63': 'z',
             '64': '0', '65': '1', '66': '2', '67': '3', '68': '4', '69': '5', '70': '6', '71': '7', '72': '8',
             '73': '9', '74': 'A', '75': 'B', '76': 'C', '77': 'D', '78': 'E', '79': 'F', '80': 'G', '81': 'H',
             '82': 'I', '83': 'J', '84': 'K', '85': 'L', '86': 'M', '87': 'N', '88': 'O', '89': 'P', '90': 'Q',
             '91': 'R', '92': 'S', '93': 'T', '94': 'U', '95': 'V', '96': 'W', '97': 'X', '98': 'Y', '99': 'Z'}

if __name__ == '__main__':
    k = 0

    others = ['φ', 'ω', 'β', 'δ', 'ε', 'π', 'Σ', 'λ', 'μ', '`',
              '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
              '-', '_', '+', '=', '{', '}', '[', ']', ':', ';',
              '<', '>', '.', ',', '?', '/', ' ', '~']

    for c in others:
        encodings[c] = str(k)
        decodings[str(k)] = str(c)
        k += 1

    for i in range(ord('a'), ord('z') + 1):
        encodings[chr(i)] = str(k)
        decodings[str(k)] = chr(i)
        k += 1

    for i in range(0, 10):
        encodings[str(i)] = str(k)
        decodings[str(k)] = str(i)
        k += 1

    for i in range(ord('A'), ord('Z') + 1):
        encodings[chr(i)] = str(k)
        decodings[str(k)] = chr(i)
        k += 1

    print(encodings)
    print(decodings)
