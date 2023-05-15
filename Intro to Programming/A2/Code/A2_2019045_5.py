
dataPath = "data/Q5/"

notes = ["C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"]
n = len(notes)

majorScalePattern = ["R", "W", "W", "H", "W", "W", "W", "H"]
minorScalePattern = ["R", "W", "H", "W", "W", "H", "W", "W"]

def create(scalePattern):
    res = []
    for i in range(n):
        pattern = []
        for j in scalePattern:
            if j == "W":
                i = (i + 2) % n
            elif j == "H":
                i = (i + 1) % n
            pattern.append(notes[i])
        res.append(pattern)
    return res

def write(lines, path):
    f = open(path, "a")
    for entry in lines:
        line = ""
        for item in entry:
            line += item + " "
        line = line.strip() + "'" + "\n"
        f.write(line)
    f.close()

def noteCreate():
    majorScales = create(majorScalePattern)
    minorScales = create(minorScalePattern)

    major = dataPath + "scalemajor.txt"
    minor = dataPath + "scaleminor.txt"
    write(majorScales, major)
    write(minorScales, minor)

def read(root, path):
    f = open(path)
    lines = f.readlines()
    f.close()
    for line in lines:
        first = line.split()[0]
        if first == root:
            return line[:-1]

def majorNotes(root):
    path = dataPath + "scalemajor.txt"
    return read(root, path)

def minorNotes(root):
    path = dataPath + "scaleminor.txt"
    return read(root, path)

# noteCreate()

root = input("Enter root note: ")
noteType = input("Enter type of note (Major/Minor): ").lower()
print("----------------------------------------")
if noteType == "major":
    print(majorNotes(root))
elif noteType == "minor":
    print(minorNotes(root))
else:
    print("Wrong Type")
print("----------------------------------------")