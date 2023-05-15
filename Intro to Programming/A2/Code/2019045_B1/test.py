import cases


def sum_of_naturals(n):
    f = open("input/test" + str(n), "r")
    num = int(f.readline())
    Sum = 0
    for i in range(num + 1):
        Sum += i
    f.close()

    return Sum


def testing():
    n = cases.generateData()

    for i in range(n):
        f = open("output/test" + str(i), "r")
        res = int(f.readline())
        f.close()

        Sum = sum_of_naturals(i)

        if Sum != res:
            return "FAILED"

    return "SUCCESS"


print(testing())
