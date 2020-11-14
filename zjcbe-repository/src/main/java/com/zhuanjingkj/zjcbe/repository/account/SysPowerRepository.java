package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.SysPowerEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.SysPowerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysPowerRepository extends ServiceImpl<SysPowerMapper, SysPowerEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<SysPowerEntity> getAllPower() {
        EntityWrapper<SysPowerEntity> ew = new EntityWrapper<>();
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        return selectList(ew);
    }
}
