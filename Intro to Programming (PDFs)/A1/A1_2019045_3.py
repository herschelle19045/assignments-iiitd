

degree = int(input("Enter degree: "))
coef = []
for i in range(degree+1):
    coef.append(float(input(f"Enter coef {i+1}: ")))

lowerBound = int(input("Enter lower bound of x: "))
upperBound = int(input("Enter upper bound of x: "))

stepSize = int(input("Step size: "))

print("---------------------------------------")

for x in range(lowerBound, upperBound+1, stepSize):
    val = 0
    for i in range(degree, -1, -1):
        val += coef[degree-i] * x ** i

    if val < 0:
        val = 0
    else:
        round(val)

    for i in range(int(val)):
        print("*", end="")
    print()

print("---------------------------------------")
