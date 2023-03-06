from math import pi


def menu():
    print("Menu")
    print("1. Square input: side s \
           \n2. Rectangle input: length l, breadth b \
           \n3. Rhombus input: side a, diagonals d1,d2 \
           \n4. Parallelogram input: length l, breadth b, height h \
           \n5. Circle input: radius r \
           \n6. Cube input: side s \
           \n7. Cuboid input: length l, breadth b, height h \
           \n8. Right circular cone: slant height l, radius r, height h \
           \n9. Hemisphere input: radius r \
           \n10. Sphere input: radius r \
           \n11. Solid cylinder input: radius r, height h \
           \n12. Hollow cylinder input: Radii R1, R2, height h \
           \nOther: Exit")


def square():
    s = float(input("Enter side: "))
    print("Perimeter = ", 4 * s)
    print("Area = ", s ** 2)


def rectangle():
    l = float(input("Enter length: "))
    b = float(input("Enter breadth: "))
    print("Perimeter = ", 2 * (l + b))
    print("Area = ", l * b)


def rhombus():
    s = float(input("Enter side: "))
    d1 = float(input("Enter diagonal1: "))
    d2 = float(input("Enter diagonal2: "))
    print("Perimeter = ", 4 * s)
    print("Area = ", d1 * d2 / 2)


def parallelogram():
    l = float(input("Enter length: "))
    b = float(input("Enter breadth: "))
    h = float(input("Enter height: "))
    print("Perimeter = ", 2 * (l + b))
    print("Area = ", l * h)


def circle():
    r = float(input("Enter radius: "))
    print("Perimeter = ", 2 * pi * r)
    print("Area = ", pi * r * r)


def cube():
    s = float(input("Enter side: "))
    print("CSA = ", 4 * s * s)
    print("TSA = ", 6 * s * s)
    print("Volume = ", s * s * s)


def cuboid():
    l = float(input("Enter length: "))
    b = float(input("Enter breadth: "))
    h = float(input("Enter height: "))
    print("CSA = ", 2 * (l + b) * h)
    print("TSA = ", 2 * (l * b + b * h + h * l))
    print("Volume = ", l * b * h)


def rightCircularCone():
    r = float(input("Enter radius: "))
    h = float(input("Enter height: "))
    l = float(input("Enter slant height: "))
    print("CSA = ", pi * r * l)
    print("TSA = ", pi * r * (r + l))
    print("Volume = ", pi * r * r * h / 3)


def hemisphere():
    r = float(input("Enter radius: "))
    print("CSA = ", 2 * pi * r * r)
    print("TSA = ", 3 * pi * r * r)
    print("Volume = ", pi * r * r * r * 2 / 3)


def sphere():
    r = float(input("Enter radius: "))
    print("CSA = ", 4 * pi * r * r)
    print("TSA = ", 4 * pi * r * r)
    print("Volume = ", pi * r * r * r * 4 / 3)


def solidCylinder():
    r = float(input("Enter radius: "))
    h = float(input("Enter height: "))
    print("CSA = ", 2 * pi * r * h)
    print("TSA = ", 2 * pi * r * (r + h))
    print("Volume = ", pi * r * r * h)


def hollowCylinder():
    r = float(input("Enter inner radius: "))
    R = float(input("Enter outer radius: "))
    h = float(input("Enter height: "))
    print("CSA = ", 2 * pi * h * (r + R))
    print("TSA = ", 2 * pi * h * (r + R) + 2 * pi * (r * r + R * R))
    print("Volume = ", pi * (R - r) * h)


n = int(input("Enter n: "))
for i in range(n):
    menu()
    option = int(input("Enter Option: "))
    if not (1 <= option <= 12):
        break
    print("---------------------------------------")
    if option == 1:
        square()
    elif option == 2:
        rectangle()
    elif option == 3:
        rhombus()
    elif option == 4:
        parallelogram()
    elif option == 5:
        circle()
    elif option == 6:
        cube()
    elif option == 7:
        cuboid()
    elif option == 8:
        rightCircularCone()
    elif option == 9:
        hemisphere()
    elif option == 10:
        sphere()
    elif option == 11:
        solidCylinder()
    else:
        hollowCylinder()
    print("---------------------------------------")
