import numpy as np

np.random.seed(100)

N_USERS = 943
N_ITEMS = 1682
TEST_SIZE = 20000
RANGE = 4


def calc_error(dev):
    err = 0
    for i in dev:
        err += dev[i] * i
    return err / TEST_SIZE / 4


def get_mask(rating_matrix):
    mask = np.zeros(shape=(N_USERS, N_ITEMS))
    for i in range(N_USERS):
        for j in range(N_ITEMS):
            if rating_matrix[i][j] != 0:
                mask[i][j] = 1
    return mask


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
