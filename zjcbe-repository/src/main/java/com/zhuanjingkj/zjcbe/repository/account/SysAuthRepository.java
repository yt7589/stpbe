package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.SysAuthEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.SysAuthMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysAuthRepository extends ServiceImpl<SysAuthMapper, SysAuthEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public SysAuthEntity getSysAuth(String appId, String secretKey) {
        EntityWrapper<SysAuthEntity> ew = new EntityWrapper<>();
        ew.eq("appId", appId);
        ew.eq("secretKey", secretKey);
        List<SysAuthEntity> lst = this.selectList(ew);
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public SysAuthEntity getSysAuthById(String appId) {
        EntityWrapper<SysAuthEntity> ew = new EntityWrapper<>();
        ew.eq("appId", appId);
        List<SysAuthEntity> lst = this.selectList(ew);
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }
}
