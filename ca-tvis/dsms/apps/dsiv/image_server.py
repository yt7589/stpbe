#
import json
import base64
import flask
from flask import Flask, jsonify
from flask_cors import CORS
from flask import request
import urllib

class ImageServer(object):
    def __init__(self):
        self.refl = ''

    @staticmethod
    def display_image():
        print('ImageServer.display_image 1')
        img_file = urllib.parse.unquote(request.args.get('img_file'))
        print('url: {0};'.format(base64.b64decode(request.url)))
        pr = urllib.parse.urlparse(request.url)
        qs = pr.query
        rst_qs = urllib.parse.parse_qs(qs)
        print('qs: {0}; {1};'.format(type(rst_qs), rst_qs))
        print('######### {0};'.format(img_file))
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
    print('displayImage 1')
    return ImageServer.display_image()


app.run(
    host = '0.0.0.0',
    port = 5000
)