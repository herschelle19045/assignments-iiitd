import random

key = {'a': '@', 'c': '[', 'e': '3', 'i': '!', 'o': '0', 's': '$', 'A': '4', 'C': '{', 'I': '|', 'S': '5'}

# password = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
password = input("Enter Password: ")

new_password = ""

for i in password:
    n = random.randint(0, 1)
    if n == 1:
        if key.get(i) is None:
            if i.isupper():
                new_password += i.lower()
            else:
                new_password += i.upper()
        else:
            new_password += key.get(i)
    else:
        new_password += i

print(new_password)
