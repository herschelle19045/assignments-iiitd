from datetime import datetime


class BankAccount:

    def __init__(self, username, password, balance):
        self.username = username
        self.password = password
        self.balance = balance

        f = open("userdata/" + username + ".txt", "w")
        f.write("")
        f.close()

    def authenticate(self):
        password = input("Enter Password: ")
        if password == self.password:
            return True
        return False

    def verify(self):
        attempts = 0
        try:
            while 1:
                if self.authenticate():
                    break
                attempts += 1
                assert attempts < 2
        except AssertionError:
            print("Too many wrong attempts")
            return False
        return True

    def deposit(self, amount):
        if not self.verify():
            return
        self.balance += amount
        f = open("userdata/" + self.username + ".txt", "a")
        f.write(f"{datetime.now()} The amount of Rs {amount} added, balance = {self.balance}\n")
        f.close()

    def withdraw(self, amount):
        if not self.verify():
            return
        if amount > self.balance:
            print("Not enough funds")
            return
        self.balance -= amount
        f = open("userdata/" + self.username + ".txt", "a")
        f.write(f"{datetime.now()} The amount of Rs {amount} debited, balance = {self.balance}\n")
        f.close()

    def generateStatement(self):
        if not self.verify():
            return
        f = open("userdata/" + self.username + ".txt", "r")
        lines = f.readlines()
        f.close()
        print(f"Hey {self.username}, your bank statement:")
        for line in lines:
            print(line[:-1])


def options():
    print("Select Option: ")
    print("\t1: Deposit")
    print("\t2: Withdraw")
    print("\t3: Bank Statement")
    print("\tOther: Quit")


name = input("Enter Name: ")
password = input("Enter password: ")
startBal = float(input("Enter starting balance: "))

account = BankAccount(name, password, startBal)

while 1:
    options()
    option = int(input("Enter choice: "))
    print("---------------------------------------------------")
    if option == 1:
        amount = float(input("Enter amount: "))
        account.deposit(amount)
    elif option == 2:
        amount = float(input("Enter amount: "))
        account.withdraw(amount)
    elif option == 3:
        account.generateStatement()
    else:
        break
    print("---------------------------------------------------")