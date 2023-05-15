def menu():
    print("1: Normal Traversal")
    print("2: Alternating Traversal")
    print("3: Spiral Traversal")
    print("4: Boundary Traversal")
    print("5: Diagonal Traversal (Right to Left)")
    print("6: Diagonal Traversal (Left to Right)")


mat = []
n = int(input("Enter n: "))
for i in range(n):
    row = input()
    mat.append([int(i) for i in row.split()])


def normalTraversal():
    for i in range(n):
        for j in range(n):
            print(mat[i][j], end=" ")
    print()


def alternatingTraversal():
    for i in range(n):
        if i % 2 == 0:
            for j in range(n):
                print(mat[i][j], end=" ")
        else:
            for j in range(n - 1, -1, -1):
                print(mat[i][j], end=" ")
    print()


def spiralTraversal():
    a = n
    b = n
    k = 0
    l = 0

    while k < a and l < b:
        for i in range(l, b):
            print(mat[k][i], end=" ")
        k += 1
        for i in range(k, a):
            print(mat[i][b - 1], end=" ")
        b -= 1
        if k < a:
            for i in range(b - 1, (l - 1), -1):
                print(mat[a - 1][i], end=" ")
            a -= 1
        if l < b:
            for i in range(a - 1, k - 1, -1):
                print(mat[i][l], end=" ")
            l += 1

    print()


def boundaryTraversal():
    for i in range(n):
        print(mat[0][i], end=" ")

    for i in range(1, n):
        print(mat[i][n-1], end=" ")

    for i in range(n-2, -1, -1):
        print(mat[n-1][i], end=" ")

    for i in range(n-2, 0, -1):
        print(mat[i][0], end=" ")

    print()


def diagonalTraversalRightToLeft():
    f, s = 0, 0
    for i in range(n):
        for j in range(i + 1):
            print(mat[s + j][f - j], end=" ")
        f += 1

    f -= 1
    s += 1

    for i in range(n - 1, 0, -1):
        for j in range(i):
            print(mat[s + j][f - j], end=" ")
        s += 1
    print()


def diagonalTraversalLeftToRight():
    f, s = n - 1, 0
    for i in range(n):
        for j in range(i + 1):
            print(mat[s + j][f + j], end=" ")
        f -= 1

    f += 1
    s += 1

    for i in range(n - 1, 0, -1):
        for j in range(i):
            print(mat[s + j][f + j], end=" ")
        s += 1
    print()


while 1:
    menu()
    option = int(input("Enter Option: "))
    print("-------------------------------------")
    if option == 1:
        normalTraversal()
    elif option == 2:
        alternatingTraversal()
    elif option == 3:
        spiralTraversal()
    elif option == 4:
        boundaryTraversal()
    elif option == 5:
        diagonalTraversalRightToLeft()
    elif option == 6:
        diagonalTraversalLeftToRight()
    else:
        break
    print("-------------------------------------")
