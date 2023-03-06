
def printAligned(item1, item2):
    spaces = 15 - len(item1)

    print(item1, end="")
    for i in range(spaces):
        print("", end=" ")
    print(item2)

items = []

for i in range(3):
    items.append(float(input(f"Enter price of item{i+1}: ")))

discounts = []

for i in range(3):
    discounts.append(float(input(f"Enter discount(In %) for saver combo {i+1}: ")))

phone = input("Enter phone number: ")

print("---------------------------------------")
print("Delhi Days")
print("R-Block, Model Town 3")
print("Delhi: 110009")
print("---------------------------------------")

printAligned("Item", "Price(per Pack)")

for i in range(3):
    first = "Item" + str(i+1)
    second = str(items[i])
    printAligned(first, second)

pack = 1
for i in range(2):
    for j in range(i+1, 3):
        first = "ComboPack" + str(pack)
        total = items[i] + items[j]
        second = str(total - total * discounts[pack-1] / 100)
        printAligned(first, second)
        pack += 1

f = "SuperSaver"
t = 0
for item in items:
    t += item
s = str(t - t * 28 / 100)
printAligned(f, s)

print("---------------------------------------")
print("For home delivery contact: ", phone)