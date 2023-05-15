from gmpy2 import next_prime, mpz_random, random_state, powmod, invert
from random import randint

low = 10 ** 1000
high = 10 ** 1020 - 1

def encrypt(m, p, q):
    n = p * q
    phi = (p-1) * (q-1)
    e = next_prime(10**100)
    d = invert(e, phi)
    c = powmod(m, e, n)

    print("\nd = " + str(d))
    print("e = " + str(e))
    print("n = " + str(n))
    print("Ciphertext, c = " + str(c))

    decrypt(n, c, d)


def decrypt(n, c, d):
    m = powmod(c, d, n)
    print("\nDecrypted message = " + str(m))


if __name__ == '__main__':
    ps = random_state(randint(1, 10**100))
    qs = random_state(randint(1, 10**100))
    ms = random_state(randint(1, 10**100))

    p = next_prime(mpz_random(ps, low))
    # p = int(input())
    q = next_prime(low + mpz_random(qs, high))
    # q = int(input())
    m = mpz_random(ms, high)
    # m = int(input())

    print("Original message = " + str(m))


    encrypt(m, p, q)



