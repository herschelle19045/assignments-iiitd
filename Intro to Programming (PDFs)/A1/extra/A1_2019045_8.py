cost = float(input("Initial cost: "))
r = float(input("Depreciation rate: "))
depreciationRate = r
value = 50
maintenanceRate = 1

profit = [0]
sellingPrice = [cost]

for year in range(1, 16):
    p = value * 6000 - cost * maintenanceRate / 100
    profit.append(p)
    sellingPrice.append(max(0.0, cost - cost * depreciationRate / 100))
    value = 11 * value / 10
    depreciationRate += r
    if year <= 5:
        maintenanceRate += 1
    else:
        maintenanceRate = 3 * maintenanceRate / 2

res = 0
Max = 0
for i in range(1, 16):
    p = profit[i] + sellingPrice[i]
    if p > Max:
        Max = p
        res = i

print("Year = ", res)
print("Profit = ", Max)
