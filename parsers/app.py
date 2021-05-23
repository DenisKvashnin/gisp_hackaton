# Inbox modules
import json
import sys
from pprint import pprint

# External modules
from flask import Flask

# Internal modules
import parsers.rusprofile.rusprofile
import tools


app = Flask(__name__)


@app.route("/OGRN/<OGRN>")
def getInfo(OGRN):
    return parsers.rusprofile.rusprofile.getEGRULInfo(OGRN)
