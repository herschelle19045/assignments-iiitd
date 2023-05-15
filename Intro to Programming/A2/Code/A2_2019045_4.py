

registeredStudents = dict()

f = open("data/Q4/Admin/RegisteredStudents.txt")
lines = f.readlines()
f.close()

for line in lines:
    split = line.split()    # Name, Roll
    student = split[0] + "_" + split[1]
    registeredStudents[student] = 0

answerKey = dict()

f = open("data/Q4/Admin/AnswerKey.txt")
lines = f.readlines()
f.close()

for line in lines:
    split = line.split()    # QuesNo, Answer
    answerKey[split[0]] = split[1]

studentScore = dict()

for student in registeredStudents:
    f = open("Data/Q4/Student/" + student + ".txt")
    lines = f.readlines()
    score = 0
    for line in lines:
        split = line.split()    # QuesNo, OptionMarked
        if split[1] == "-":
            continue
        if split[1] == answerKey[split[0]]:
            score += 4
        else:
            score -= 1
    studentScore[student] = score

f = open("data/Q4/Admin/FinalReport.txt", "a")
for item in studentScore:
    split = item.split("_")
    name = split[0]
    roll = split[1]
    f.write(name + " " + roll + " " + str(studentScore[item]) + "\n")

f.close()





