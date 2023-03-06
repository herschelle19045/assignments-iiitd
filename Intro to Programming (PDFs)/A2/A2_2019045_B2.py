def getMax():
    for i in range(m):
        for j in range(n):
            if skyscrapers[i][j] == "1" and j in available:
                available.remove(j)
                return j, m - i

p, q = map(int, input().split())

m, n = map(int, input().split())

skyscrapers = []
for i in range(m):
    skyscrapers.append(input())

available = set()
for i in range(n):
    available.add(i)

d, s = [], []
rep_d, rep_s = 0, 0
for i in range(n):
    index, height = getMax()
    if i % 2 == 0:
        d.append(index)
        rep_d += p * height
    else:
        s.append(index)
        rep_s += q * height

    p += 1; q += 1

res = []
for i in range(m):
    tmp = ""
    for j in range(n):
        if skyscrapers[i][j] == "1":
            if j in d:
                tmp += "D"
            else:
                tmp += "S"
        else:
            tmp += "0"
    res.append(tmp)

print(rep_d, rep_s)

for i in range(m):
    for j in range(n):
        print(res[i][j], end="")
    print()