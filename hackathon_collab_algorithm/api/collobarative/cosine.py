import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import systemtools.number as sysnumber
import sys

from sklearn.metrics.pairwise import cosine_similarity

def similar_users(target, user_item):
    # compute similarity of each user to the provided user
    similarity = []
    user_item.index.map(lambda user: similarity.append((user, cosine_similarity([target], [user_item.loc[user]]))))

    # sort by similarity
    similarity.sort(key=lambda x: x[1], reverse=True)


    # create list of just the user ids
    most_similar_users = [tup[0] for tup in similarity]

    # create list of similarity score
    similarity_score = [tup[1] for tup in similarity]

    # remove the user's own id
    # most_similar_users.remove(user_id)

    # remove the user's own similarity score
    similarity_score.remove(similarity_score[0])

    return similarity

#recomendation function
#recomendation function
def recommendations( num_of_movies, user_item_interactions, target):
    # find the most similar users to the user_id for which we want to recommend movies
    similarity = similar_users(target, user_item_interactions)
    # find out those movies which this user has already interacted with
    movie_ids = set(list(target[target > 0].index ))

    # create an empty list to store the recommended movies
    recommendations = []

    already_interacted = movie_ids.copy()
    count = 0
    # loop through each similar user from the list of most_similar_users
    for similar in similarity:
        # implement the below code till the length of recommended movies does not become equal to num_of_movies
        if count < num_of_movies:

            # store all the movies interacted by each similar user to user_id
            similar_user_movie_ids = set(list(user_item_interactions.loc[similar[0]].sort_values(ascending = False).where(user_item_interactions.loc[similar[0]] > 0).dropna().index))

            # add those movies in the recommended list which are present in similar_user_movie_ids but not present in already_interacted
            if len(list(similar_user_movie_ids.difference(already_interacted))) > 0:
                recommendations.append([list(similar_user_movie_ids.difference(already_interacted)), similar[1][0][0], similar[0]  ])
                count = len(already_interacted)
            # now add all those movies into already_interacted which we already added in recommendations
            already_interacted = already_interacted.union(similar_user_movie_ids)
        else:
            break

    return recommendations[:num_of_movies]
