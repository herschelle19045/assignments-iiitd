
class LaundryService:
    ID = 1
    def __init__(self, nameOfCustomer, contactOfCustomer, email, typeOfCloth, branded, season):
        self.ID = LaundryService.ID
        self.nameOfCustomer = nameOfCustomer
        self.contactOfCustomer = contactOfCustomer
        self.email = email
        self.typeOfCloth = typeOfCloth
        self.branded = branded
        self.season = season
        LaundryService.ID += 1

    def customerDetails(self):
        print(f"ID: {self.ID}")
        print(f"Name: {self.nameOfCustomer}")
        print(f"Contact: {self.contactOfCustomer}")
        print(f"Email: {self.email}")
        print(f"Cloth: {self.typeOfCloth}")
        print(f"Branded: {self.branded}")

    def calculateCharge(self):
        cloth = self.typeOfCloth
        if cloth == "Cotton":
            res = 50
        elif cloth == "Silk":
            res = 30
        elif cloth == "Woolen":
            res = 90
        else:
            res = 20

        if self.branded:
            res *= 1.5

        if self.season == "Winter":
            res /= 2
        else:
            res *= 2

        return res

    def finalDetails(self):
        self.customerDetails()
        amount = self.calculateCharge()
        print(amount)
        if amount >= 200:
            print("To be returned in 4 days")
        else:
            print("To be returned in 7 days")

n = int(input("Enter number of Customers: "))
for i in range(n):
    name = input("Enter Name: ")
    contact = input("Enter contact: ")
    email = input("Enter email: ")
    clothType = input("Enter cloth type: ")
    branded = True if int(input("Branded?: ")) == 1 else False
    season = input("Enter Season: ")

    print("---------------------------------------------------")
    c = LaundryService(name, contact, email, clothType, branded, season)
    c.finalDetails()
    print("---------------------------------------------------")



