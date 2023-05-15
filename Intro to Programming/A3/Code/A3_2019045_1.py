from math import sin, cos, radians


def init(n, m):
    res = []
    for i in range(n):
        res.append([0.0] * m)
    return res


def degree(angle):
    return radians(angle)


def scale(query):
    res = init(4, 4)
    for i in range(3):
        res[i][i] = float(query[i])
    res[3][3] = 1
    return res


def translate(query):
    res = init(4, 4)
    for i in range(3):
        res[i][3] = float(query[i])
    for i in range(4):
        res[i][i] = 1
    return res


def rotate(query):
    typ = query[0]
    angle = degree(float(query[1]))
    if typ == "x":
        return rotateX(angle)
    elif typ == "y":
        return rotateY(angle)
    return rotateZ(angle)


def rotateZ(theta):
    res = init(4, 4)
    res[0][0] = cos(theta)
    res[0][1] = -sin(theta)
    res[1][0] = sin(theta)
    res[1][1] = cos(theta)
    res[2][2] = 1
    res[3][3] = 1
    return res


def rotateX(theta):
    res = init(4, 4)
    res[0][0] = 1
    res[1][1] = cos(theta)
    res[2][2] = cos(theta)
    res[1][2] = -sin(theta)
    res[2][1] = sin(theta)
    res[3][3] = 1
    return res


def rotateY(theta):
    res = init(4, 4)
    res[0][0] = cos(theta)
    res[1][1] = 1
    res[2][2] = cos(theta)
    res[0][2] = sin(theta)
    res[2][0] = -sin(theta)
    res[3][3] = 1
    return res


def multiply(T, A):
    res = init(4, n)
    for i in range(4):
        for j in range(n):
            for k in range(4):
                res[i][j] += T[i][k] * A[k][j]
    return res


n = int(input("Enter n: "))

x = [float(i) for i in input("Enter x: ").split()]
y = [float(i) for i in input("Enter y: ").split()]
z = [float(i) for i in input("Enter z: ").split()]

A = [x, y, z, [1] * n]

q = int(input("Enter no. of queries: "))

for _ in range(q):
    query = input("Enter query: ")
    tokens = query.split()
    typ = tokens[0]
    if typ == "S":
        T = scale(tokens[1:])
    elif typ == "T":
        T = translate(tokens[1:])
    else:
        T = rotate(tokens[1:])
    A = multiply(T, A)

print("x =", A[0])
print("y =", A[1])
print("z =", A[2])
print("--------------------------------------------")

