import pandas as pd
import sys
import numpy as np

from sklearn.metrics.pairwise import cosine_similarity

def similar_users(user_id, interactions_matrix):
    similarity = []
    for user in range(1, interactions_matrix.shape[0]):
        sim = cosine_similarity([interactions_matrix.loc[user_id]], [interactions_matrix.loc[user]])
        similarity.append((user, sim))
    similarity.sort(key=lambda x: x[1], reverse=True)
    most_similar_users = [tup[0] for tup in similarity]
    similarity_score = [tup[1] for tup in similarity]
    most_similar_users.remove(user_id)
    similarity_score.remove(similarity_score[0])
    return most_similar_users, similarity_score

def recommendations(item_list, num_of_movies, user_item_interactions):
    user_item_interactions = user_item_interactions.append(item_list, ignore_index=True).fillna(0)
    most_similar_users_arr = similar_users(user_item_interactions.shape[0] - 1, user_item_interactions)
    most_similar_users = most_similar_users_arr[0]
    interactions_matrix = user_item_interactions
    movie_ids = set(list(interactions_matrix.columns[np.where(interactions_matrix.loc[user_item_interactions.shape[0] - 1] > 0)] ))
    recommendations = []
    already_interacted = movie_ids.copy()
    for similar_user in most_similar_users:
        if len(recommendations) < num_of_movies:
            similar_user_movie_ids = set(list(interactions_matrix.columns[np.where(interactions_matrix.loc[similar_user] > 0)]))
            recommendations.extend(list(similar_user_movie_ids.difference(already_interacted)))
            already_interacted = already_interacted.union(similar_user_movie_ids)
        else:
            break
    return [recommendations, most_similar_users_arr ]
