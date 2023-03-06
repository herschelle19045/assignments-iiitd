def menu():
    print("1: Display specific word count")
    print("2: Display unique words")
    print("3: Display all word counts")
    print("4: Replace word")
    print("Other: Exit")

def printAligned(item1, item2):
    spaces = 20 - len(item1)

    print(item1, end="")
    for i in range(spaces):
        print("", end=" ")
    print(str(item2) + " times")


def getWords():
    fd = open("data/Q1/question1_input.txt", "r")
    data = fd.read().split()
    words = dict()
    for word in data:
        if word in words:
            words[word] += 1
        else:
            words[word] = 1
    return words

def unique():
    words = getWords()
    for idx, word in enumerate(words):
        print(" ; " + word, end=" ")
        if (idx+1) % 10 == 0:
            print()
    print()

def wordCount():
    word = input("Enter word: ")
    words = getWords()
    if word in words:
        printAligned(word, words[word])
    else:
        print("Word does not exist")

def allWordCounts():
    words = getWords()
    for word in words:
        printAligned(word, words[word])

def replaceWords():
    oldWord = input("Enter word to be replaced: ")
    newWord = input("Enter new word: ")
    fd = open("data/Q1/question1_input.txt", "r+")
    l = fd.readlines()
    idx = 0
    for line in l:
        if oldWord in line:
            replacement = line.replace(oldWord, newWord)
            l[idx] = replacement
        idx += 1
    fd.truncate(0)

    fd = open("data/Q1/question1_input.txt", "a")
    for line in l:
        fd.write(line)

    fd.close()

while 1:
    menu()
    option = int(input("Enter option: "))
    print("-------------------------------------")
    if option == 1:
        wordCount()
    elif option == 2:
        unique()
    elif option == 3:
        allWordCounts()
    elif option == 4:
        replaceWords()
    else:
        break
    print("-------------------------------------")

