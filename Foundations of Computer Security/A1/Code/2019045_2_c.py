gmp = __import__('2019045_2_b')

mySet = set()

for i in range(100):
    otp = gmp.rand()

    while mySet.__contains__(otp):
        otp = gmp.rand()

    mySet.add(otp)

    print(otp)