def menu():
    print("Menu")
    print("1: Right-angled triangle")
    print("2: Isosceles triangle")
    print("3: Kite")
    print("4: Half Kite")
    print("5: X")
    print("Other: Exit")


def rightAngleTriangle(n):
    for i in range(n):
        for j in range(i + 1):
            print(j + 1, end=" ")
        print()


def isoscelesTriangle(n):
    """
        n is even
    """

    space = n // 2 - 1
    count = 1
    for i in range(1, 2 * n, 2):
        for j in range(space):
            print("", end=" ")

        for j in range(2 * n - i):
            print("", end=" ")

        for j in range(1, i + 1):
            print(j, end=" ")
        print()

        if count % 2 == 0:
            space -= 1
        count += 1


def kite(n):
    """
        n is even
    """

    for i in range(1, 2 * n + 1, 2):
        if i != 2 * n - 1:
            print("", end=" ")

        for j in range(2 * n - i):
            print("", end=" ")

        for j in range(1, i + 1):
            print(j, end=" ")
        print()

    for i in range(2 * n - 3, 0, -2):
        for j in range(2 * n - i + 1):
            print("", end=" ")

        for j in range(1, i + 1):
            print(j, end=" ")
        print()


def halfKite(n):
    for i in range(n):
        for j in range(i + 1):
            print(j + 1, end=" ")
        print()

    for i in range(n - 2, -1, -1):
        for j in range(i + 1):
            print(j + 1, end=" ")
        print()


def X(n):
    """
         n is even
    """

    for i in range(2 * n - 1, -1, -2):
        if i != 2 * n - 1:
            print("", end=" ")

        for j in range(2 * n - i):
            print("", end=" ")

        for j in range(1, i + 1):
            print(j, end=" ")
        print()

    for i in range(3, 2 * n + 1, 2):
        if i != 2 * n - 1:
            print("", end=" ")

        for j in range(2 * n - i):
            print("", end=" ")

        for j in range(1, i + 1):
            print(j, end=" ")
        print()


while True:
    menu()
    option = int(input("Enter Option: "))
    if not (1 <= option <= 5):
        break

    x = int(input("Enter n: "))
    print("---------------------------------------")
    if option == 1:
        rightAngleTriangle(x)
    elif option == 2:
        isoscelesTriangle(x)
    elif option == 3:
        kite(x)
    elif option == 4:
        halfKite(x)
    else:
        X(x)
    print("---------------------------------------")
