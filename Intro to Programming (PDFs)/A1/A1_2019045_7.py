from math import log


def integrate(power, lower, upper):
    p = power + 1
    if p == 0:
        return log(upper) - log(lower)
    return (upper ** p - lower ** p) / p


def calculateArea(powers, a, b, d):
    res = 0
    m = 1
    for i in range(a, b, d):
        for j in range(len(powers)):
            res += integrate(powers[j], a + (m-1) * d, a + m * d)
        m += 1
    return res


powers = []
n = int(input("Enter number of terms in polynomial: "))
for i in range(n):
    powers.append(float(input(f"Enter {i+1} coefficient: ")))

a = int(input("Enter a: "))
b = int(input("Enter b: "))
d = int(input("Enter d: "))


if (b - a) % d == 0:
    print(calculateArea(powers, a, b, d))
else:
    print("d not divisible by b - a")

# print(calculateArea([2, 1], 0, 6, 2))
# print(calculateArea([0.5, -1], 1, 3, 1))