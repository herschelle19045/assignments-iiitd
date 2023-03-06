class Student:

    def __init__(self, name, grade, branch):
        self.name = name
        self.grade = grade
        self.branch = branch
        self.isPlaced = False

    def isEligible(self, company):
        first = (not self.isPlaced)
        second = self.grade >= company.criteria
        third = company.isHiringDepartment(self.branch)
        return first and second and third

    def apply(self, company):
        if self.isEligible(company):
            print(f"Student {self.name} is eligible for {company.name}")
            company.applicants.append(self)
        else:
            print(f"Student {self.name} is not eligible for {company.name}")

    def getsPlaced(self):
        self.isPlaced = True


class Company:

    def __init__(self, name, criteria, departments, vacancy):
        self.name = name
        self.criteria = criteria
        self.departments = departments
        self.vacancy = vacancy
        self.applicants = []
        self.hired = []

    def isHiringDepartment(self, branch):
        return self.departments.__contains__(branch)

    def hireStudents(self):

        def sortByGrade(s):
            return s.grade

        self.applicants.sort(key=sortByGrade, reverse=True)
        for s in self.applicants:
            if self.vacancy == 0:
                break
            self.hired.append(s)
            s.getsPlaced()
            self.vacancy -= 1

        self.closeApplication()

    def closeApplication(self):
        print("---------------------------------------------------")
        print(f"{self.name} has hired following {len(self.hired)} students:")
        for s in self.hired:
            print(f"\t{s.name}")
        print("---------------------------------------------------")


def main():
    students, companies = [], []

    n = int(input("Enter number of Students: "))
    for i in range(n):
        name = input(f"Enter name of student {i + 1}: ")
        while 1:
            try:
                grade = float(input(f"Enter cgpa of student {i + 1}: "))
                assert 0 <= grade <= 10
                break
            except AssertionError:
                print("Enter cgpa between 0 - 10")

        branch = input(f"Enter branch of student {i + 1}: ")
        s = Student(name, grade, branch)
        students.append(s)

    k = int(input("Enter number of Companies: "))
    for i in range(k):
        name = input(f"Enter name of company {i+1}: ")
        criteria = float(input(f"Enter cgpa criteria of {name}: "))
        branches = input(f"Enter space-separated branches of {name}: ").split()
        vacancy = int(input(f"Enter number of openings of {name}"))
        c = Company(name, criteria, branches, vacancy)
        companies.append(c)

    print("---------------------------------------------------")
    for company in companies:
        for student in students:
            student.apply(company)
        company.hireStudents()


if __name__ == '__main__':
    main()
