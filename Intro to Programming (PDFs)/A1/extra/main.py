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
    if ans <= 0:
        return True
    return False


def getPoint(t):
    res = []
    for i in range(3):
        res.append(e[i] + t * d[i])

    return res[0], res[1], res[2]


points = []
for t in range(1001):
    x1, y1, z1 = getPoint(t)

    if intersect(x1, y1, z1):
        points.append((x1, y1, z1))

if len(points) == 0:
    print("Line does not intersect the sphere")
else:
    for point in points:
        print(point)