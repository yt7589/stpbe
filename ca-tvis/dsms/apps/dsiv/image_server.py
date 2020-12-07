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

class ImageServer(object):
    def __init__(self):
        self.refl = ''
        MMongoDb.initialize()
        #self.read_bmy_id_to_img_files()
        bmy_ids = CBmy.get_bmy_ids()
        app.run(
            host = '0.0.0.0',
            port = 5000
        )

    def read_bmy_id_to_img_files(self):
        num = 0
        bmy_id_to_img_files = {}
        with open('/media/ps/0A9AD66165F33762/yantao/dcl/datasets/'
                    'CUB_200_2011/anno/sfds_train_ds_20201020.txt', 'r', encoding='utf-8') as fd:
            for line in fd:
                line = line.strip()
                arrs = line.split('*')
                img_file = arrs[0]
                bmy_id = arrs[1]
                if bmy_id not in bmy_id_to_img_files:
                    bmy_id_to_img_files[bmy_id] = []
                bmy_id_to_img_files[bmy_id].append(img_file)
                num += 1
                if num % 10000 == 0:
                    print('已经处理{0}条记录...'.format(num))

    def read_bmy_id_to_img_file_idx(self):
        pass

    def initialize_bmy_id_img_file_idx(self):
        '''
        '''

    @staticmethod
    def display_image():
        img_file = urllib.parse.unquote(request.args.get('img_file'))
        with open(img_file, 'rb') as img_fd:
            image_data = img_fd.read()
        response = flask.make_response(image_data)
        postfix = img_file[-4:]
        response.headers['Content-Type'] = 'image/{0}'.format(postfix)
        return response

app = Flask(__name__)
CORS(app)

@app.route('/displayImage', methods=['GET'])
def display_image():
    return ImageServer.display_image()