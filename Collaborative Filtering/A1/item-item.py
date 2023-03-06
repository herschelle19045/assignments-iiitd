from utils import template, form_rating_matrix
import numpy as np
import os

N_USERS = 943
N_ITEMS = 1682
TEST_SIZE = 20000

user_averages = []


def form_item_similarity_matrix(rating_matrix):
    res = np.zeros(shape=(N_ITEMS, N_ITEMS), dtype=float)
    for i in range(N_ITEMS):
        for j in range(i + 1, N_ITEMS):
            res[i][j] = cosine_similarity_item(i, j, rating_matrix)
            res[j][i] = res[i][j]
    return res


def cosine_similarity_item(i, j, rating_matrix):
    user_average = np.array(user_averages)
    a = np.abs(rating_matrix[:, i] - user_average)
    b = np.abs(rating_matrix[:, j] - user_average)

    res = np.sum(a * b)
    d = (np.sqrt(np.sum(np.square(a))) * np.sqrt(np.sum(np.square(b))))

    if d == 0:
        return 0

    res /= d
    return res


def predict_rating(user, movie, rating_matrix, similarity_matrix, k):
    user_ratings = rating_matrix[user]      # Gets row

    rs_tuples = []  # (Similarity, Rating)
    for index, rating in enumerate(user_ratings):
        if rating > 0:
            rs_tuples.append((similarity_matrix[movie][index], rating))

    sorted_rs_tuples = sorted(rs_tuples, reverse=True)[:min(k, len(rs_tuples))]

    res = np.zeros(shape=(len(sorted_rs_tuples)), dtype=float)
    for i in range(len(sorted_rs_tuples)):
        res[i] = sorted_rs_tuples[i][1]

    if np.sum(res) == 0:
        return round(np.average(user_ratings[user_ratings > 0]))

    return round(np.average(res))


def model(test, rating_matrix, similarity_matrix, k):
    deviations = {0: 0, 1: 0, 2: 0, 3: 0, 4: 0}

    print("\t\t\tPredicting...", end=" ")
    for entry in test:
        user, movie, actual = entry
        predicted = predict_rating(user - 1, movie - 1, rating_matrix, similarity_matrix, k)
        diff = abs(actual - predicted)
        deviations[diff] = deviations[diff] + 1

    print("Done")
    return deviations


def calculate_average(rating_matrix):
    if len(user_averages) == N_USERS:
        return

    for row in rating_matrix:
        user_averages.append(np.average(row))


# --------------------------------------------------------

def write_matrix_file(matrix, path):
    fp = open(path, "w")
    data = ""
    for row in np.array(matrix):
        for val in row:
            data += str(val) + ","
        data = data[:-1] + "\n"
    fp.write(data)
    fp.close()


def write_matrix(rating_matrix, similarity_matrix, fold):
    rating_matrix_path = f"matrices/rating/fold{fold}/rating_matrix.txt"
    if not os.path.isfile(rating_matrix_path):
        write_matrix_file(rating_matrix, rating_matrix_path)

    similarity_matrix_path = f"matrices/similarity/item-item/fold{fold}/similarity_matrix.txt"
    if not os.path.isfile(similarity_matrix_path):
        write_matrix_file(similarity_matrix, similarity_matrix_path)


def read_matrix_file(path):
    fp = open(path, "r")
    lines = fp.readlines()
    fp.close()
    matrix = []
    for line in lines:
        tokens = line.split(",")
        matrix.append([float(i) for i in tokens])

    return np.array(matrix)


def read_matrix(fold, rating, data=None, r_matrix=None):
    if rating:
        path = f"matrices/rating/fold{fold}/rating_matrix.txt"
        if os.path.isfile(path):
            return read_matrix_file(path)
        return form_rating_matrix(data)

    path = f"matrices/similarity/item-item/fold{fold}/similarity_matrix.txt"
    if os.path.isfile(path):
        return read_matrix_file(path)
    return form_item_similarity_matrix(r_matrix)


# --------------------------------------------------------

def fold_n(fold, train, test):
    user_averages.clear()

    # rating_matrix = form_rating_matrix(train)
    rating_matrix = read_matrix(fold, True, train)
    calculate_average(rating_matrix)

    print("\tCalculating Item-Item Similarity Matrix...", end=" ")
    # similarity_matrix = form_item_similarity_matrix(rating_matrix)
    similarity_matrix = read_matrix(fold, False, r_matrix=rating_matrix)
    write_matrix(rating_matrix, similarity_matrix, fold)
    print("Done")

    neighbours = [10, 20, 30, 40, 50]

    deviation = []
    for k in neighbours:
        print(f"\t\tComputing K = {k}:")
        d = model(test, rating_matrix, similarity_matrix, k)
        deviation.append(d)

    return deviation


if __name__ == '__main__':
    template(fold_n)
