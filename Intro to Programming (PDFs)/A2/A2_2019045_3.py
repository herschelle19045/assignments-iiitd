def menu():
    print("1: Decimal to Binary or vice-versa")
    print("2: Decimal to Hexadecimal or vice-versa")
    print("3: Decimal to Octal or vice-versa")
    print("4: Binary to Hexadecimal or vice-versa")
    print("5: Binary to Octal or vice-versa")
    print("6: Hexadecimal to Octal or vice-versa")
    print("7: Radix A to radix B")
    print("Other: Exit")

def getVal(c):
    if c.isdigit():
        return int(c)
    return int(ord(c) - 65) + 10


def getChar(n):
    if n < 10:
        return str(n)
    return chr(n + 55)


def decimalToBinary(s):
    n = int(s)
    res = ""
    while n > 0:
        res = str(n % 2) + res
        n = n // 2
    return res


def binaryToDecimal(s):
    res = 0
    power = len(s) - 1
    for i in s:
        res += int(i) * 2 ** power
        power -= 1
    return res


def decimalToHexadecimal(s):
    n = int(s)
    res = ""
    while n > 0:
        k = n % 16
        res = getChar(k) + res
        n = n // 16
    return res


def hexadecimalToDecimal(s):
    res = 0
    power = len(s) - 1
    for i in s:
        res += getVal(i) * 16 ** power
        power -= 1
    return res


def decimalToOctal(s):
    n = int(s)
    res = ""
    while n > 0:
        k = n % 8
        res = str(k) + res
        n = n // 8
    return str(res)


def octalToDecimal(s):
    res = 0
    power = len(s) - 1
    for i in s:
        res += int(i) * 8 ** power
        power -= 1
    return str(res)


def binaryToHexadecimal(s):
    return decimalToHexadecimal(binaryToDecimal(s))


def hexadecimalToBinary(s):
    return decimalToBinary(hexadecimalToDecimal(s))


def binaryToOctal(s):
    return decimalToOctal(binaryToDecimal(s))


def octalToBinary(s):
    return decimalToBinary(octalToDecimal(s))


def hexadecimalToOctal(s):
    return decimalToOctal(hexadecimalToDecimal(s))


def octalToHexadecimal(s):
    return decimalToHexadecimal(octalToDecimal(s))


def general(s, a, b):
    n = 0
    power = len(s) - 1
    for i in s:
        n += getVal(i) * a ** power
        power -= 1

    res = ""
    while n > 0:
        k = n % b
        res = getChar(k) + res
        n = n // b
    return res


while 1:
    res = "Good Bye"
    menu()
    option = int(input("Enter option: "))
    ip = input("Enter number: ")
    if option == 7:
        a = int(input("Enter radix A: "))
        b = int(input("Enter radix B: "))
        res = general(ip, a, b)
    else:
        print("Vice-versa?\n1: Yes\n2: No")
        choice = int(input("Enter choice: "))
        if choice == 1:
            if option == 1:
                res = binaryToDecimal(ip)
            elif option == 2:
                res = hexadecimalToDecimal(ip)
            elif option == 3:
                res = octalToHexadecimal(ip)
            elif option == 4:
                res = hexadecimalToBinary(ip)
            elif option == 5:
                res = octalToBinary(ip)
            elif option == 6:
                res = octalToHexadecimal(ip)
            else:
                break
        else:
            if option == 1:
                res = decimalToBinary(ip)
            elif option == 2:
                res = decimalToHexadecimal(ip)
            elif option == 3:
                res = decimalToOctal(ip)
            elif option == 4:
                res = binaryToHexadecimal(ip)
            elif option == 5:
                res = binaryToOctal(ip)
            elif option == 6:
                res = hexadecimalToOctal(ip)
            else:
                break

    print(res)

    print("-------------------------------------")
