# v0.0.1
from apps.dsiv.image_server import ImageServer

def main(args={}):
    print('自动聚类服务器')
    image_server = ImageServer(5000)
    

if '__main__' == __name__:
    main()