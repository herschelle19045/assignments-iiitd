from random import randint

def sumOfNaturals(n):

     f = open("input/test" + str(n), "r")
     num = int(f.readline())
     Sum = str(num * (num+1) // 2)
     f.close()

     f = open("output/test" + str(n), "w")
     f.write(Sum)
     f.close()


def generateData():

     n = int(input("Enter n: "))

     for i in range(n):
          f = open("input/test" + str(i), "w")
          t = str(randint(1, 1000))
          f.write(t)
          f.close()

     for i in range(n):
          sumOfNaturals(i)

     return n