from gmpy2 import next_prime, mpz_random, random_state, powmod
from random import randint

start = 10 ** 1022
end = 10 ** 1023 - 1

B = -1
A = -1

def bob_sends_alice(p, g):
    global B
    bob = random_state(randint(1, end))
    b = start + mpz_random(bob, end - start + 1)
    B = powmod(g, b, p)
    print("B = " + str(B))
    print("b = " + str(b))
    return b


def alice_sends_bob(p, g):
    global A
    alice = random_state(randint(1, end))
    a = start + mpz_random(alice, end - start + 1)
    A = powmod(g, a, p)
    print("A = " + str(A))
    print("a = " + str(a))
    return a


def print_shared_secret_alice(B, a, g):
    print("Secret Key = " + str(powmod(B, a, g)))

def print_shared_secret_bob(A, b, g):
    print("Secret Key = " + str(powmod(A, b, g)))


if __name__ == '__main__':
    prime = next_prime(10 ** 100)
    integer = start + mpz_random(random_state(), end - start + 1)

    bb = bob_sends_alice(prime, integer)
    aa = alice_sends_bob(prime, integer)

    print_shared_secret_alice(B, aa, prime)
    print_shared_secret_bob(A, bb, prime)
