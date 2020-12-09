#
import urllib
import flask
from flask import request
from apps.dsiv.controller.c_bmy import CBmy
from apps.dsiv.controller.flask_web import FlaskWeb

class CDsiv(object):
    bmy_id_to_img_files = None
    bmy_id_to_img_file_idx = None

    @staticmethod
    def get_bmy_id_example_img_file():
        bmy_id = int(urllib.parse.unquote(request.args.get('bmyId')))
        print('bmyId={0}'.format(bmy_id))
        print('CDsiv.bmy_id_to_img_files={0};'.format(len(CDsiv.bmy_id_to_img_files)))
        print('bmy_id_to_img_file_idx={0};'.format(len(CDsiv.bmy_id_to_img_file_idx)))
        img_file = CDsiv.bmy_id_to_img_files[bmy_id][CDsiv.bmy_id_to_img_file_idx[bmy_id]]
        resp = FlaskWeb.get_resp_param()
        resp['data'] ={'img_file': img_file[0]}
        return FlaskWeb.generate_response(resp)

    @staticmethod
    def display_image():
        img_file = urllib.parse.unquote(request.args.get('imgFile'))
        with open(img_file, 'rb') as img_fd:
            image_data = img_fd.read()
        response = flask.make_response(image_data)
        postfix = img_file[-4:]
        response.headers['Content-Type'] = 'image/{0}'.format(postfix)
        return response

    @staticmethod
    def initialize():
        CDsiv.bmy_id_to_img_files = CDsiv.read_bmy_id_to_img_files()
        #CDsiv.initialize_bmy_id_to_img_file_idx()
        CDsiv.bmy_id_to_img_file_idx = CDsiv.read_bmy_id_to_img_file_idx()
        #bmy_id = 188
        #bmy_id_to_img_file_idx[bmy_id] = 518
        #CDsiv.save_bmy_id_to_img_file_idx(bmy_id_to_img_file_idx)
        #print('idx={0};'.format(bmy_id_to_img_file_idx[bmy_id]))
        #print('image_file: {0};'.format(bmy_id_to_img_files[bmy_id][bmy_id_to_img_file_idx[bmy_id]]))
        pass

    bmy_sim_org_dict = {}
    @staticmethod
    def get_org_bmy_id_of_sim(sim_bmy_id):
        if len(CDsiv.bmy_sim_org_dict)<2:
            with open('/media/ps/0A9AD66165F33762/yantao/dcl/support/bmy_sim_org_dict.txt', 'r', encoding='utf-8') as fd:
                for line in fd:
                    line = line.strip()
                    arrs = line.split(':')
                    CDsiv.bmy_sim_org_dict[int(arrs[0])] = int(arrs[1])
        return CDsiv.bmy_sim_org_dict[sim_bmy_id]

    @staticmethod
    def read_bmy_id_to_img_files():
        num = 0
        bmy_id_to_img_files = {}
        with open('/media/ps/0A9AD66165F33762/yantao/dcl/datasets/'
                    'CUB_200_2011/anno/sfds_train_ds_20201020.txt', 'r', encoding='utf-8') as fd:
            for line in fd:
                line = line.strip()
                arrs = line.split('*')
                img_file = arrs[0]
                sim_bmy_id = int(arrs[1])
                bmy_id = CDsiv.get_org_bmy_id_of_sim(sim_bmy_id)
                if bmy_id not in bmy_id_to_img_files:
                    bmy_id_to_img_files[bmy_id] = []
                bmy_id_to_img_files[bmy_id].append([img_file])
                num += 1
                if num % 1000000 == 0:
                    print('已经处理{0}条记录...'.format(num))
        return bmy_id_to_img_files

    @staticmethod
    def save_bmy_id_to_img_file_idx(bmy_id_to_img_file_idx):
        with open('./bmy_id_to_img_file_idx.txt', 'w', encoding='utf-8') as fd:
            for k, v in bmy_id_to_img_file_idx.items():
                fd.write('{0}:{1}\r\n'.format(k, v))

    @staticmethod
    def read_bmy_id_to_img_file_idx():
        bmy_id_to_img_file_idx = {}
        with open('./bmy_id_to_img_file_idx.txt', 'r', encoding='utf-8') as fd:
            for line in fd:
                line = line.strip()
                arrs = line.split(':')
                bmy_id_to_img_file_idx[int(arrs[0])] = int(arrs[1])
        return bmy_id_to_img_file_idx


    @staticmethod
    def initialize_bmy_id_to_img_file_idx():
        '''
        '''
        bmy_ids = CBmy.get_bmy_ids()
        with open('./bmy_id_to_img_file_idx.txt', 'w+', encoding='utf-8') as fd:
            for bmy_id in bmy_ids:
                print('bmy_id: {0};'.format(bmy_id))
                fd.write('{0}:{1}\r\n'.format(bmy_id['bmy_id'], 0))