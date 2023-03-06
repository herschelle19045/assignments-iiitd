def menu():
    print("Menu")
    print("1: getReverse")
    print("2: checkPalindrome")
    print("3: checkNarcissistic")
    print("4: findDigitSum")
    print("5: findSquareDigitSum")
    print("Other: Exit")


def getReverse(s):
    l = len(s)
    res = ""
    for i in range(l):
        res = s[i] + res
    return int(res)


def checkPalindrome(s):
    l = len(s)
    for i in range(0, l // 2):
        if s[i] != s[l - i - 1]:
            return False
    return True


def checkNarcissistic(s):
    l = len(s)
    n = int(s)
    res = 0
    while n > 0:
        res += (n % 10) ** l
        n = n // 10
    return res == int(s)


def findDigitSum(s):
    n = int(s)
    res = 0
    while n > 9:
        curr = 0
        while n > 0:
            curr += n % 10
            n = n // 10
        n = curr
        res += curr
    return res


def findSquareDigitSum(s):
    n = int(s)
    res = 0
    while n > 9:
        curr = 0
        while n > 0:
            curr += (n % 10) ** 2
            n = n // 10
        n = curr
        res += curr
    return res


while True:
    menu()
    opt = int(input("Enter Option: "))
    if not (1 <= opt <= 5):
        break

    x = input("Enter number: ")
    ans = None
    if opt == 1:
        ans = getReverse(x)
    elif opt == 2:
        ans = checkPalindrome(x)
    elif opt == 3:
        ans = checkNarcissistic(x)
    elif opt == 4:
        ans = findDigitSum(x)
    else:
        ans = findSquareDigitSum(x)

    print("---------------------------------------")
    print(ans)
    print("---------------------------------------")
