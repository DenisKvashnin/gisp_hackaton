import pandas as pd
import sys
import numpy as np

from sklearn.metrics.pairwise import cosine_similarity

def similar_users(user_id, interactions_matrix):

    # compute similarity of each user to the provided user
    similarity = []
    for user in range(1, interactions_matrix.shape[0]):
        sim = cosine_similarity([interactions_matrix.loc[user_id]], [interactions_matrix.loc[user]])
        similarity.append((user, sim))

    # sort by similarity
    similarity.sort(key=lambda x: x[1], reverse=True)


    # create list of just the user ids
    most_similar_users = [tup[0] for tup in similarity]

    # create list of similarity score
    similarity_score = [tup[1] for tup in similarity]

    # remove the user's own id
    most_similar_users.remove(user_id)

    # remove the user's own similarity score
    similarity_score.remove(similarity_score[0])

    return most_similar_users, similarity_score

def recommendations(item_list, num_of_movies, user_item_interactions):
    # find the most similar users to the user_id for which we want to recommend movies
    user_item_interactions = user_item_interactions.append(item_list, ignore_index=True).fillna(0)
    most_similar_users_arr = similar_users(user_item_interactions.shape[0] - 1, user_item_interactions)
    most_similar_users = most_similar_users_arr[0]
    interactions_matrix = user_item_interactions
    # find out those movies which this user has already interacted with
    movie_ids = set(list(interactions_matrix.columns[np.where(interactions_matrix.loc[user_item_interactions.shape[0] - 1] > 0)] ))

    # create an empty list to store the recommended movies
    recommendations = []

    # copy those movies which are already interacted by user_id
    already_interacted = movie_ids.copy()

    # loop through each similar user from the list of most_similar_users
    for similar_user in most_similar_users:

        # implement the below code till the length of recommended movies does not become equal to num_of_movies
        if len(recommendations) < num_of_movies:

            # store all the movies interacted by each similar user to user_id
            similar_user_movie_ids = set(list(interactions_matrix.columns[np.where(interactions_matrix.loc[similar_user] > 0)]))

            # add those movies in the recommended list which are present in similar_user_movie_ids but not present in already_interacted
            recommendations.extend(list(similar_user_movie_ids.difference(already_interacted)))

            # now add all those movies into already_interacted which we already added in recommendations
            already_interacted = already_interacted.union(similar_user_movie_ids)
        else:
            break

    return [recommendations, most_similar_users_arr ]
