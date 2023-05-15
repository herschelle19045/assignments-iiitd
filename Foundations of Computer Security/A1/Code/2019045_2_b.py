from gmpy2 import random_state, mpz_random
from random import randint

n = -1
start = 10 ** 1022
end = 10 ** 1023 - 1


def rand():
    global n, start, end
    rs = random_state(randint(1, end))
    n = start + mpz_random(rs, end - start + 1)
    index = mpz_random(rs, 1018)
    num_str = str(n)
    return num_str[index: index+6]


if __name__ == '__main__':
    otp = rand()
    print(n)
    print(otp)





