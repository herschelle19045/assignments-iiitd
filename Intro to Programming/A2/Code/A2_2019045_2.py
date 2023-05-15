from math import sin, cos, radians


def init(n, m):
    res = []
    for i in range(n):
        res.append([0.0] * m)
    return res


def degree(angle):
    return radians(angle)


def scaling(query):
    res = init(4, 4)
    for i in range(3):
        res[i][i] = float(query[i])
    res[3][3] = 1
    return res


def translating(query):
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


def concat(a):
    return " ".join(str(i) for i in a)


def write():
    res.append("Input:")
    res.append(f"Enter n: {n}")
    res.append("Enter x: " + concat(x))
    res.append("Enter y: " + concat(y))
    res.append("Enter z: " + concat(z))
    res.append(f"Enter no. of queries: {q}")
    res.append("--------------------------------------------")


def writeQuery():
    res.append(f"Enter query: {query}")
    res.append("Output:")
    res.append("x = " + concat(x))
    res.append("y = " + concat(y))
    res.append("z = " + concat(z))
    res.append("--------------------------------------------")


n = int(input("Enter n: "))

x = [float(i) for i in input("Enter x: ").split()]
y = [float(i) for i in input("Enter y: ").split()]
z = [float(i) for i in input("Enter z: ").split()]

A = [x, y, z, [1] * n]

q = int(input("Enter no. of queries: "))

res = []
write()

for _ in range(q):
    query = input("Enter query: ")
    tokens = query.split()
    typ = tokens[0]
    if typ == "S":
        T = scaling(tokens[1:])
    elif typ == "T":
        T = translating(tokens[1:])
    else:
        T = rotate(tokens[1:])
    mat = multiply(T, A)

    for i in range(4):
        for j in range(n):
            mat[i][j] = round(mat[i][j], 2)

    print("x =", mat[0])
    print("y =", mat[1])
    print("z =", mat[2])
    print("--------------------------------------------")
    writeQuery()


for i in range(len(res)):
    res[i] += "\n"

f = open("data/Q2/io.txt", "w")
f.truncate(0)
for line in res:
    f.write(line)
f.close()
