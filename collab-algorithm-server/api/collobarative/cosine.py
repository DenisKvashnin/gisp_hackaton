import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import systemtools.number as sysnumber
import sys

from sklearn.metrics.pairwise import cosine_similarity

def similar_users(target, user_item):
    similarity = []
    user_item.index.map(lambda user: similarity.append((user, cosine_similarity([target], [user_item.loc[user]]))))
    similarity.sort(key=lambda x: x[1], reverse=True)
    most_similar_users = [tup[0] for tup in similarity]
    similarity_score = [tup[1] for tup in similarity]
    similarity_score.remove(similarity_score[0])
    return similarity
#recomendation function
def recommendations( num_of_movies, user_item_interactions, target):
    similarity = similar_users(target, user_item_interactions)
    movie_ids = set(list(target[target > 0].index ))
    recommendations = []

    already_interacted = movie_ids.copy()
    count = 0
    for similar in similarity:
        if count < num_of_movies:
            similar_user_movie_ids = set(list(user_item_interactions.loc[similar[0]].sort_values(ascending = False).where(user_item_interactions.loc[similar[0]] > 0).dropna().index))
            if len(list(similar_user_movie_ids.difference(already_interacted))) > 0:
                recommendations.append([list(similar_user_movie_ids.difference(already_interacted)), similar[1][0][0], similar[0]  ])
                count = len(already_interacted)
            already_interacted = already_interacted.union(similar_user_movie_ids)
        else:
            break
    return recommendations[:num_of_movies]
