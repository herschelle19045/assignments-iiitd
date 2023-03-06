class Person:

    def __init__(self, firstName, lastName, age):
        self.firstName = firstName
        self.lastName = lastName
        self.age = age

    def object_info(self):
        return f"Details: {self.firstName} {self.lastName} {self.age}"


def sort_by_firstname(p):
    return p.firstName


def sort_by_lastname(p):
    return p.lastName


def sort_by_age(p):
    return p.age


def sort_attribute(attribute):
    if attribute == "firstname":
        return sorted(persons, key=sort_by_firstname)

    elif attribute == "lastname":
        return sorted(persons, key=sort_by_lastname)

    return sorted(persons, key=sort_by_age)


persons = []

while 1:
    n, k = map(int, input("Enter space seperated n, k: ").split())

    for i in range(n):
        f, l, a = map(str, input(f"Space seperated details of person {i+1}: ").split())
        a = int(a)
        p = Person(f, l, a)
        persons.append(p)

    for i in range(k):
        query = input(f"Enter query {i + 1}: ")
        res = sort_attribute(query)
        for p in res:
            print(p.object_info())
        print("---------------------------------------------------")

    option = input("Enter Y to restart: ")
    if option != "Y":
        break