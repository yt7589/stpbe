#
from apps.dsiv.model.m_bmy import MBmy

class CBmy(object):
    @staticmethod
    def get_bmy_ids():
        return MBmy.get_bmy_ids()