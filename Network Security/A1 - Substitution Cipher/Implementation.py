import itertools
import time

# --------------- GLOBALS -------------------

# ONLY PERMUTE THE RIGHT SIDE VALUES
KEY = {
    "AA": "BC",
    "AB": "AC",
    "AC": "BB",
    "BA": "CA",
    "BB": "AB",
    "BC": "CB",
    "CA": "AA",
    "CB": "CC",
    "CC": "BA"
}

BLOCK_SIZE = 16

# KEEP LENGTH OF EACH PLAINTEXT SAME AND IN MULTIPLES OF "BLOCK_SIZE"
INPUT_TEXT = [
    "AABBCCAABBCCABBCCAABBCCAABBCCAABBCCAABCABCABCABC",
    "ABBCCAABBCCAABCABCABCABCABCABCABCABCCBACBACBACBA",
    "ABCABCABCABCCBACBACBACBAACBCBABACACBAABBCCAABBCC",
    "CBACBACBACBAACBCBABACACBAABBCCAABBCCABBCCAABBCCA",
    "ACBCBABACACBAABBCCAABBCCCBACBACBACBAACBCBABACACB",
]

LEN_ENCODED_BITS = 2

# ENTER LENGTH OF PLAINTEXTS HERE
LEN = 48


# ------------------------------------------------------------------


def encode(text):
    encode_key = {"A": "00", "B": "01", "C": "10"}
    encoded_value = ""
    for char in text:
        encoded_value += encode_key[char]
    return encoded_value


def decode(bin_text):
    decode_key = {"00": "A", "01": "B", "10": "C"}

    res = ""
    count = 0

    for i in range(0, len(bin_text), 2):
        val = bin_text[i] + bin_text[i + 1]
        if val == "11":
            res += chr(ord('A') + count)
            count += 1
        else:
            res += decode_key[val]
        count %= 3
    return res


def hash_function(bin_text):
    l = len(bin_text)
    res = bin_text[0:BLOCK_SIZE]
    for i in range(BLOCK_SIZE, l, BLOCK_SIZE):
        new_res = ""
        for j in range(0, BLOCK_SIZE):
            new_res += str(int(bin_text[i + j]) ^ int(res[j]))
        res = new_res[1:] + new_res[0]

    return decode(res)


def convert_to_hash(text):
    binary_text = encode(text)
    res = hash_function(binary_text)
    return res


def encrypt(text):
    l = len(text)
    encrypted_text = ""
    for i in range(0, l, 2):
        encrypted_text += KEY[text[i] + text[i + 1]]  # eg: encrypt AC
    return encrypted_text


def decrypt(ciphertext, decrypt_key):
    l = len(ciphertext)
    decrypted_text = ""
    for i in range(0, l, 2):
        decrypted_text += decrypt_key[ciphertext[i] + ciphertext[i + 1]]
    return decrypted_text


def generate_decrypt_key(permutation):
    # 9! = 362880 Keys possible
    possible = ["AA", "AB", "AC", "BA", "BB", "BC", "CA", "CB", "CC"]

    key = dict()
    for i in range(0, 9):
        key[possible[permutation[i]]] = possible[i]
    return key


def check_property(ciphertext, offset):
    text = ciphertext[:offset]
    hash_text = ciphertext[offset:]

    return convert_to_hash(text) == hash_text


def brute_force(ciphertexts):
    sequence = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    permutations = list(itertools.permutations(sequence))  # all permutations of sequence

    iterations, collisions = 0, -1
    key = None

    print("0% Complete")
    for permutation in permutations:
        candidate_key = generate_decrypt_key(permutation)

        recognisable = True
        for ciphertext in ciphertexts:
            decrypted_text = decrypt(ciphertext, candidate_key)
            offset = LEN

            if not check_property(decrypted_text, offset):
                recognisable = False
                break

        if recognisable:
            collisions += 1
            key = candidate_key

        iterations += 1

        if iterations % 45000 == 0:
            print(f"{round(iterations * 100 / 362_880)}% Complete")

    print("100% Completed\n")
    print("Total keys processed:", len(permutations))
    return collisions, key


def main():
    ciphertexts = []
    for text in INPUT_TEXT:
        ciphertext = encrypt(text + convert_to_hash(text))
        ciphertexts.append(ciphertext)

    print("Launching Attack...")
    print()
    start_time = time.time()
    collisions, key = brute_force(ciphertexts)
    end_time = time.time()

    if key is not None:
        print("\nStatistics:")
        print("Total time taken:", round(end_time - start_time), "s")
        print("Key collisions:", collisions)
        print()
        print("KEY:")
        for i in key:
            print(key[i], "-", i)
    else:
        print("\nAttack Failed")


if __name__ == '__main__':
    main()
