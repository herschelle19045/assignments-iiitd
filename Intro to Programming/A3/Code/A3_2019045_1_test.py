from math import sin, cos, radians


def init(n, m):
    res = []
    for i in range(n):
        res.append([0.0] * m)
    return res


def degree(angle):
    return radians(angle)


def getScaleMat(query):
    res = init(4, 4)
    for i in range(3):
        res[i][i] = float(query[i])
    res[3][3] = 1
    return res


def getTranslateMat(query):
    res = init(4, 4)
    for i in range(3):
        res[i][3] = float(query[i])
    for i in range(4):
        res[i][i] = 1
    return res


def getRotateMat(query):
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
    r1 = len(T)
    r2 = len(A)
    c2 = len(A[0])
    res = init(r1, c2)
    for i in range(r1):
        for j in range(c2):
            for k in range(r2):
                res[i][j] += T[i][k] * A[k][j]

    return res


def scale(x, y, z, sx, sy, sz):
    n = len(x)
    A = [x, y, z, [1] * n]
    T = getScaleMat([sx, sy, sz])
    return multiply(T, A)[:-1]


def translate(x, y, z, tx, ty, tz):
    n = len(x)
    A = [x, y, z, [1] * n]
    T = getTranslateMat([tx, ty, tz])
    return multiply(T, A)[:-1]


def rotate(x, y, z, axis, phi):
    n = len(x)
    A = [x, y, z, [1] * n]
    T = getRotateMat([axis, phi])
    return multiply(T, A)[:-1]


# Previous code
# ----------------------------------------------------------------------------
# Testing code begins


def test(M, True_M):
    try:
        for i in range(len(M)):
            for j in range(len(M[0])):
                assert abs(M[i][j] - True_M[i][j]) < 0.01
    except AssertionError:
        return False

    return True


def test_matmul(A, B, True_C):
    C = multiply(A, B)
    return test(C, True_C)


def test_scale(x, y, z, sx, sy, sz, true_x, true_y, true_z):
    res = scale(x, y, z, sx, sy, sz)
    true_res = [true_x, true_y, true_z]
    return test(res, true_res)


def test_translate(x, y, z, tx, ty, tz, true_x, true_y, true_z):
    res = translate(x, y, z, tx, ty, tz)
    true_res = [true_x, true_y, true_z]
    return test(res, true_res)


def test_rotate(x, y, z, axis, phi, true_x, true_y, true_z):
    res = rotate(x, y, z, axis, phi)
    true_res = [true_x, true_y, true_z]
    return test(res, true_res)


def getTrueScale(a, l):
    return [a * i for i in l]


def getTrueTranslating(a, l):
    return [(a + i) for i in l]


def print_result(name, num, res):
    print(f"{name} test {num}: {'Passed' if res else 'Failed'}")


def test_1_matmul():
    A = [[1, 0, 0],
         [0, 1, 0],
         [0, 0, 1]]

    B = [[1, 2, 3],
         [4, 5, 6],
         [7, 8, 9]]

    True_C = B
    res = test_matmul(A, B, True_C)
    print_result("Mat Mul", 1, res)


def test_2_matmul():
    A = [[3.13, 435.3, 135.53, 753.55],
         [7536.36, 62462.43, 2464.24, 6429.86],
         [2425.46, 6488.25, 73557, 6424]]

    B = [[1.52, 2.14, 3.13],
         [4.63, 0.45, 6.63],
         [7.64, 8.97, 9.32],
         [25.25, 35.23, 325.53]]

    True_C = [[22082.78, 27965.85, 249462.11],
              [481837.08, 292864.10, 2553793.76],
              [757908.78, 894234.01, 2827364.75]]

    res = test_matmul(A, B, True_C)
    print_result("Mat Mul", 2, res)


def test_3_matmul():
    A = [[0, 0, 0],
         [0, 0, 0],
         [0, 0, 0]]

    B = [[1, 2, 3],
         [4, 5, 6],
         [7, 8, 9]]

    True_C = A

    res = test_matmul(A, B, True_C)
    print_result("Mat Mul", 3, res)


def test_1_scale():
    x = [1, 2, 3, 4]
    y = [3, 4, 5, 6]
    z = [6, 7, 8, 9]
    sx, sy, sz = 3, 6, 9

    true_x = [3, 6, 9, 12]
    true_y = [18, 24, 30, 36]
    true_z = [54, 63, 72, 81]

    res = test_scale(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Scale", 1, res)


def test_2_scale():
    x = [0, 0, 0]
    y = [0, 0, 0]
    z = [0, 0, 0]
    sx, sy, sz = 13, 96, 69

    true_x = x
    true_y = y
    true_z = z

    res = test_scale(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Scale", 2, res)


def test_3_scale():
    x = [1.7, 6.71, 92.45, 3434, 5.68]
    y = [34.6, 35353, 0.356, 325.6, 2467.7]
    z = [462.6, 3552.3524, 235.6, 68468, 4577.9964]
    sx, sy, sz = 5325.6, 5225.6, 98696.97

    true_x = getTrueScale(sx, x)  # i_th true val is i_th val in x * sx
    true_y = getTrueScale(sy, y)
    true_z = getTrueScale(sz, z)

    res = test_scale(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Scale", 3, res)


def test_1_translate():
    x = [1, 2, 3]
    y = [4, 5, 6]
    z = [7, 8, 9]
    sx, sy, sz = 7, 12, 2

    true_x = [8, 9, 10]
    true_y = [16, 17, 18]
    true_z = [9, 10, 11]

    res = test_translate(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Translate", 1, res)


def test_2_translate():
    x = [0, 0]
    y = [0, 0]
    z = [0, 0]
    sx, sy, sz = 43, 6, 149

    true_x = [sx] * 2
    true_y = [sy] * 2
    true_z = [sz] * 2

    res = test_translate(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Translate", 2, res)


def test_3_translate():
    x = [1.7, 6.71, 92.45, 3434, 5.68]
    y = [34.6, 35353, 0.356, 325.6, 2467.7]
    z = [462.6, 3552.3524, 235.6, 68468, 4577.9964]
    sx, sy, sz = 5325.6, 5225.6, 98696.97

    true_x = getTrueTranslating(sx, x)  # i_th true val is i_th val in x + sx
    true_y = getTrueTranslating(sy, y)
    true_z = getTrueTranslating(sz, z)

    res = test_translate(x, y, z, sx, sy, sz, true_x, true_y, true_z)
    print_result("Translate", 3, res)


def test_1_rotate():
    x = [1, 2, 3, 4]
    y = [3, 4, 5, 6]
    z = [6, 7, 8, 9]
    axis, phi = "x", 90

    true_x = [1, 2, 3, 4]
    true_y = [-6, -7, -8, -9]
    true_z = [3, 4, 5, 6]

    res = test_rotate(x, y, z, axis, phi, true_x, true_y, true_z)
    print_result("Rotate", 1, res)


def test_2_rotate():
    x = [7.3, 2.7, 3.3]
    y = [1.5, 2.44, 5.67]
    z = [9.75, 8.45, 6.36]
    axis, phi = "y", 45

    true_x = [12.06, 7.88, 6.83]
    true_y = [1.5, 2.44, 5.67]
    true_z = [1.73, 4.07, 2.16]

    res = test_rotate(x, y, z, axis, phi, true_x, true_y, true_z)
    print_result("Rotate", 2, res)


def test_3_rotate():
    x = [1.7, 6.71, 92.45, 3434, 5.68]
    y = [34.6, 35353, 0.356, 325.6, 2467.7]
    z = [462.6, 3552.3524, 235.6, 68468, 4577.9964]
    axis, phi = "z", 67.34

    true_x = [-31.27, -32621.42, 35.29, 1022.52, -2275.02]
    true_y = [14.90, 13626.36, 85.45, 3294.36, 955.95]
    true_z = [462.6, 3552.35, 235.6, 68468.0, 4578]

    res = test_rotate(x, y, z, axis, phi, true_x, true_y, true_z)
    print_result("Rotate", 3, res)


def main():
    test_1_matmul()
    test_2_matmul()
    test_3_matmul()
    test_1_scale()
    test_2_scale()
    test_3_scale()
    test_1_translate()
    test_2_translate()
    test_3_translate()
    test_1_rotate()
    test_2_rotate()
    test_3_rotate()


if __name__ == '__main__':
    main()
