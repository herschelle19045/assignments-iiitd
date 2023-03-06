import numpy as np

N_USERS = 943
N_ITEMS = 1682
TEST_SIZE = 20000


def form_rating_matrix(data):
    res = np.zeros(shape=(N_USERS, N_ITEMS), dtype=float)

    for line in data:
        user, movie, rating = line
        res[user - 1][movie - 1] = rating

    return res


def read_data(path):
    fp = open(path, "r")
    lines = fp.readlines()
    fp.close()

    res = []
    for entry in lines:
        line = entry[:-1]
        tokens = line.split('\t')
        l = []
        for i in tokens:
            l.append(int(i))
        res.append(l[:-1])
    return res


def dataset():
    train, test = [], []
    for i in range(5):
        train_path = f'ml-100k/u{i + 1}.base'
        test_path = f'ml-100k/u{i + 1}.test'
        train.append(read_data(train_path))
        test.append(read_data(test_path))
    return train, test


def calc_error(dev):
    err = 0
    for i in dev:
        err += dev[i] * i
    return err / TEST_SIZE


def template(fold_n):
    train, test = dataset()

    deviations = []
    for fold in range(5):
        print(f"Computing fold {fold + 1}:")
        deviation = fold_n(fold, train[fold], test[fold])
        deviations.append(deviation)

    table = np.zeros(shape=(5, 5), dtype=float)
    for i in range(5):
        for j in range(5):
            table[i][j] = round(calc_error(deviations[i][j]), 4)

    average = np.zeros(shape=(5), dtype=float)
    for i in range(5):
        average[i] = round(np.average(table[:, i]), 4)

    print("--- Results ---")
    print("Table:")
    for i in range(5):
        for j in range(5):
            print(table[i][j], end=" ")
        print()

    print("Average:")
    for i in range(5):
        print(average[i], end=" ")
