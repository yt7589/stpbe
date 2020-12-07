#
import pymongo
from apps.dsiv.model.m_mongodb import MMongoDb

class MBmy(object):
    @staticmethod
    def get_bmy_ids():
        query_cond = {}
        fields = {'bmy_id': 1}
        return MMongoDb.db['t_bmy'].find_one(query_cond, fields)