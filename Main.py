import numpy as np

def pivot(tableau, row, col):
    tableau[row] = tableau[row] / tableau[row, col]
    for i in range(len(tableau)):
        if i != row:
            tableau[i] -= tableau[i, col] * tableau[row]

def simplex():
    # Variables : x1, x2, e1, a1, a2
    # Ligne 1 : x1 + x2 - e1 + a1 = 2
    # Ligne 2 : x1 + 2x2       + a2 = 4

    tableau = np.array([
        [ 1, 1, -1, 1, 0, 2],   # contrainte 1
        [ 1, 2,  0, 0, 1, 4],   # contrainte 2
        [-1,-3,  0,-1,-1, 0]    # fonction W = -(a1 + a2)
    ], dtype=float)

    # Initialisation : ajouter les lignes artificielles à W
    tableau[2] += tableau[0] + tableau[1]

    # Phase 1 : rendre W = 0
    while np.min(tableau[2, :-1]) < 0:
        col = np.argmin(tableau[2, :-1])
        ratios = []
        for i in range(2):  # lignes des contraintes
            if tableau[i, col] > 0:
                ratios.append(tableau[i, -1] / tableau[i, col])
            else:
                ratios.append(np.inf)
        row = np.argmin(ratios)
        if ratios[row] == np.inf:
            raise Exception("🚨 Problème non borné (Phase 1)")
        pivot(tableau, row, col)

    # Vérification de faisabilité
    if round(tableau[2, -1], 6) != 0:
        raise Exception("❌ Problème sans solution faisable")

    # Phase 2 : maximiser Z = 2x1 + 3x2
    tableau = tableau[:3, [0,1,2,5]]  # On garde x1, x2, e1 et valeurs
    tableau = np.vstack([
        tableau[:2],
        [-2, -3, 0, 0]  # Z = 2x1 + 3x2
    ])

    # Itérations de la Phase 2
    while np.min(tableau[2, :-1]) < 0:
        col = np.argmin(tableau[2, :-1])
        ratios = []
        for i in range(2):
            if tableau[i, col] > 0:
                ratios.append(tableau[i, -1] / tableau[i, col])
            else:
                ratios.append(np.inf)
        row = np.argmin(ratios)
        if ratios[row] == np.inf:
            raise Exception("🚨 Problème non borné (Phase 2)")
        pivot(tableau, row, col)

    print("✅ Tableau final :\n", np.round(tableau, 2))
    print("✅ Valeur maximale de Z :", round(tableau[2, -1], 2))

simplex()
