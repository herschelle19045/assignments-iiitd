import math

from utils import *


def ALS(B, U, V):
    for i in range(100):
        U_inv_T = np.linalg.pinv(U).T
        V = np.matmul(B.T, U_inv_T)
        V_inv_T = np.linalg.pinv(V).T
        U = np.matmul(B, V_inv_T)
    return U, V.T


def model(test, Y, R, F):
    deviations = {0: 0, 1: 0, 2: 0, 3: 0, 4: 0}

    print("\t\t\tPredicting...", end=" ")

    h = 1 / math.sqrt(F)
    X = np.random.uniform(size=(N_USERS, N_ITEMS), low=1, high=5)
    U = np.random.uniform(size=(N_USERS, F), low=0, high=h)
    V = np.random.uniform(size=(F, N_ITEMS), low=0, high=h)

    for i in range(100):
        B = X + Y - R * np.dot(U, V)
        U, V = ALS(B, U, V)
        X = np.dot(U, V)

    for entry in test:
        user, movie, actual = entry
        predicted = round(X[user - 1][movie - 1])
        if predicted > 5:
            predicted = 5
        elif predicted < 1:
            predicted = 1
        diff = abs(actual - predicted)
        deviations[diff] = deviations[diff] + 1

    print("Done")
    return deviations


def fold_n(train, test):
    Y = form_rating_matrix(train)
    R = get_mask(Y)

    features = [2, 3, 4]

    deviation = []
    for f in features:
        print(f"\t\tComputing F = {f}:")
        d = model(test, Y, R, f)
        deviation.append(d)

    return deviation


def main():
    train, test = dataset()

    deviations = []
    for fold in range(5):
        print(f"Computing fold {fold + 1}:")
        deviation = fold_n(train[fold], test[fold])
        deviations.append(deviation)

    table = np.zeros(shape=(5, 5), dtype=float)
    for i in range(5):
        for j in range(3):
            table[i][j] = round(calc_error(deviations[i][j]), 4)

    average = np.zeros(shape=5, dtype=float)
    for i in range(3):
        average[i] = round(np.average(table[:, i]), 4)

    print("--- Results ---")
    print("Table:")
    for i in range(5):
        for j in range(3):
            print(table[i][j], end=" ")
        print()

    print("Average:")
    for i in range(3):
        print(average[i], end=" ")


if __name__ == '__main__':
    main()
