from app import app
from collobarative import cosine
from flask import request, Flask, render_template
import pandas as pd

import sys
import json
import numpy as np

print('coolobarative', file=sys.stderr)
interactions_matrix = pd.read_pickle("app/data/final_interactions.pckl")
print('coolobarative', file=sys.stderr)


@app.route('/cosine_rec')
def cosine_rec():
    inn = request.args.get('q', '')
    try:
        result = cosine.recommendations(50,
                interactions_matrix.drop(['inn'], axis = 1),
                interactions_matrix[interactions_matrix['inn'] == inn ].drop(['inn'], axis = 1)[0:1].squeeze())
        inns = list( map(lambda x: x[2], result) )
        a = pd.read_csv("app/data/vyta.csv", sep=";")
        list_id = list(map(lambda x: (a[a["inn"] == int(x) ]["id_subsidii"].to_numpy()[0]) if len((a[a["inn"] == int(x) ]["id_subsidii"].to_numpy())) > 0 else 0 , inns ))
        recomend = list(filter(lambda x: x != 0, list_id))
        r = list(map(lambda x: str(x), recomend))
        return "-".join(r)
    except:
        return ""
