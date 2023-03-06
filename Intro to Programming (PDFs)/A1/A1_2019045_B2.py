e = []
d = []

e.append(float(input("Enter x-coordinate of origin of ray: ")))
e.append(float(input("Enter y-coordinate of origin of ray: ")))
e.append(float(input("Enter z-coordinate of origin of ray: ")))

d.append(float(input("Enter x-vector of direction of ray: ")))
d.append(float(input("Enter y-vector of direction of ray: ")))
d.append(float(input("Enter z-vector of direction of ray: ")))

x0 = float(input("Enter X0 of Sphere: "))
y0 = float(input("Enter Y0 of Sphere: "))
z0 = float(input("Enter Z0 of Sphere: "))
r = float(input("Enter radius of Sphere: "))
print("---------------------------------------")


def intersect(x1, y1, z1):
    ans = (x0 - x1) ** 2 + (y0 - y1) ** 2 + (z0 - z1) ** 2 - r ** 2
    if ans < 0:
        return -1
    if ans > 0:
        return 1
    return 0


def getPoint(t):
    res = []
    for i in range(3):
        res.append(round(e[i] + t * d[i], 2))

    return res[0], res[1], res[2]


for t in range(1001):
    x1, y1, z1 = getPoint(t)
    x2, y2, z2 = getPoint(t + 1)

    first = intersect(x1, y1, z1)
    second = intersect(x2, y2, z2)

    if first == 0:
        print(f"({x1}, {y1}, {z1}) is a point of intersection")
        continue

    if first > 0 and second < 0:
        print(f"Some point between ({x1}, {y1}, {z1}) and ({x2}, {y2}, {z2})")
    if first < 0 and second > 0:
        print(f"Some point between ({x1}, {y1}, {z1}) and ({x2}, {y2}, {z2})")
