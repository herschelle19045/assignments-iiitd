import numpy as np
from utils import template, form_rating_matrix

N_USERS = 943
N_ITEMS = 1682


def form_user_similarity_matrix(rating_matrix):
    res = np.zeros(shape=(N_USERS, N_USERS), dtype=float)
    for i in range(N_USERS):
        for j in range(i + 1, N_USERS):
            res[i][j] = significance_weighting(i, j, rating_matrix)
            res[j][i] = res[i][j]
    return res


def cosine_similarity_item(i, j, rating_matrix):
    a = rating_matrix[i]
    b = rating_matrix[j]

    res = np.sum(a * b)
    d = (np.sqrt(np.sum(np.square(a))) * np.sqrt(np.sum(np.square(b))))

    if d == 0:
        return 0

    res /= d
    return res


def significance_weighting(i, j, rating_matrix):
    a, b = rating_matrix[i], rating_matrix[j]
    p = a * b
    return max(len(p[p > 0]) / 50, 1)


def predict_rating(user, movie, rating_matrix, similarity_matrix, k):
    movie_ratings = rating_matrix[:, movie]     # Gets col

    rs_tuples = []  # (Similarity, Rating)
    for index, rating in enumerate(movie_ratings):
        if rating > 0:
            rs_tuples.append((similarity_matrix[user][index], rating))

    sorted_rs_tuples = sorted(rs_tuples, reverse=True)[:min(k, len(rs_tuples))]

    res = np.zeros(shape=(len(sorted_rs_tuples)), dtype=float)
    for i in range(len(sorted_rs_tuples)):
        res[i] = sorted_rs_tuples[i][1]

    if np.sum(res) == 0:
        u = rating_matrix[user]
        return round(np.average(u[u > 0]))

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


def fold_n(fold, train, test):
    rating_matrix = form_rating_matrix(train)

    print("\tCalculating User-User Similarity Matrix...", end=" ")
    similarity_matrix = form_user_similarity_matrix(rating_matrix)
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
