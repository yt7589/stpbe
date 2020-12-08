#
import json
import base64
import flask
from flask import Flask, jsonify
from flask_cors import CORS
from flask import request
import urllib
from apps.dsiv.model.m_mongodb import MMongoDb
from apps.dsiv.controller.c_bmy import CBmy
from apps.dsiv.controller.c_dsiv import CDsiv

class ImageServer(object):
    def __init__(self):
        self.refl = ''
        MMongoDb.initialize()
        CDsiv.initialize()
        app.run(
            host = '0.0.0.0',
            port = 5000
        )

app = Flask(__name__)
CORS(app)

@app.route('/displayImage', methods=['GET'])
def display_image():
    return CDsiv.display_image()

@app.route('/getBmyIdExampleImgFile', methods=['GET'])
def get_bmy_id_example_img_file():
    return CDsiv.get_bmy_id_example_img_file()

@app.route('/test001', methods=['GET'])
def test001():
    ids = CBmy.get_bmy_ids()
    return ''.join(ids)