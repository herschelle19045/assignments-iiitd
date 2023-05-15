from header import *


def permute(_bin, box):
    res = ''
    for i in box:
        res += _bin[i - 1]
    return res


def shift_left(_bin, n):
    return _bin[n:] + _bin[:n]


def SBox(_bin, box):
    row = _bin[0] + _bin[5]
    col = _bin[1:5]

    row = bin2dec(row)
    col = bin2dec(col)

    return box[row][col]


def SBoxes(_bin):
    res = ''
    for i in range(0, 48, 6):
        r = SBox(_bin[i:i + 6], sbox[i // 6])
        r = dec2bin(r)
        r = '0' * (4 - len(r)) + r  # i.e '11' as '0011'
        res += r
    return res


def FBox(_bin, key):
    expanded_text = permute(_bin, expansion_box)
    _xor = xor(expanded_text, key)
    _sbox = SBoxes(_xor)
    res = permute(_sbox, straight_box)
    return res


def DES(text, keys):
    text_bin = hex2bin(text)
    text_bin = permute(text_bin, initial_permutation)

    left = text_bin[:32]
    right = text_bin[32:]

    for i in range(16):
        temp = right
        right = FBox(right, keys[i])
        right = xor(left, right)
        left = temp

        round_output(i, left, right, keys[i])   # Prints output of i_th round

    res = permute(right + left, final_permutation)
    return bin2hex(res)


def key_generation(key):
    bin_key = hex2bin(key)
    bin_key = permute(bin_key, parity_drop)

    keys = []
    left = bin_key[:28]
    right = bin_key[28:]

    for rnd in range(16):
        if rnd == 0 or rnd == 1 or rnd == 8 or rnd == 15:
            shift = 1
        else:
            shift = 2

        left = shift_left(left, shift)
        right = shift_left(right, shift)

        combined = left + right
        keys.append(permute(combined, compression_box))

    return keys


def encrypt(text, key):
    print("Encryption:")
    print(f"\tPlaintext: {text}")
    keys = key_generation(key)
    return DES(text, keys)


def decrypt(cipher, key):
    print("\nDecryption:")
    print(f"\tCiphertext: {cipher}")
    keys = key_generation(key)[::-1]
    return DES(cipher, keys)


def main():
    key = '0f1571c947d9e859'
    plaintext = '2019321aa2019045'

    encrypted = encrypt(plaintext, key)
    print(f"\tCiphertext: {encrypted}")

    decrypted = decrypt(encrypted, key)
    print(f"\tDecrypted Plaintext: {decrypted}")


if __name__ == '__main__':
    main()
