
def Fn(b1, b2, b3):
    return (b1 or b2) and (b1 or not (b2 and not b3)) and (not b2 and b3) # Change here

def satisfiable():
    for i in range(2):
        for j in range(2):
            for k in range(2):
                b1, b2, b3 = i == 1, j == 1, k == 1
                if Fn(b1, b2, b3):
                    print("Satisfiable")
                    print(f"{b1} {b2} {b3}")
                    return

    print("Unsatisfiable")


satisfiable()
