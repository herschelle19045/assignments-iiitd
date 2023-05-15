import random

n = -1
start = 10 ** 1022
end = 10 ** 1023 - 1

def rand():
    global n, start, end
    n = random.randint(start, end)
    index = random.randint(0, 1017)
    num_str = str(n)
    return num_str[index: index + 6]


if __name__ == '__main__':
    otp = rand()
    print(n)
    print(otp)
