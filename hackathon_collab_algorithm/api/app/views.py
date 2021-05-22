from app import app
from collobarative import collobarative
from murrays_rules import murrays_rules
from flask import request, Flask, render_template
import pandas as pd
from flask_basicauth import BasicAuth

basic_auth = BasicAuth(app)


import sys
import json
import numpy as np

@app.route('/view')
@basic_auth.required
def show():
   return render_template('index.html')

@app.route('/rules/products')

def rules_products():
    result = []
    for item in murrays_rules.rules["antecedents"].to_numpy():
        for froz in item:
            result.append(froz)
    return json.dumps(list(set(result)), indent=4)


@app.route('/with-lift')
def with_lift():
    slugs = request.args.get('q', '')
    slugs = slugs.split(',')
    my_set = {e for e in slugs}

    sets = murrays_rules.rules[murrays_rules.rules['antecedents'] == my_set]
    if(sets.empty):
        murrays_rules.rules["contain_all"] = murrays_rules.rules["antecedents"].apply(lambda x: all(item in list(x) for item in slugs ))
        sets = murrays_rules.rules[murrays_rules.rules['contain_all']]
    if(sets.empty):
        murrays_rules.rules["contain_any"] = murrays_rules.rules["antecedents"].apply(lambda x: any(item in list(x) for item in slugs ))
        sets = murrays_rules.rules[murrays_rules.rules['contain_any']]

    sets = sets.sort_values(by=['lift'], ascending=False)
    result = []
    ressult = []
    for item in sets.to_numpy():
        human = " AND ".join(list(item[1]))
        result.append([ human , "%.2f" % item[2]])
    result
    return json.dumps(result, indent=4)
