package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.AccountPowerEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.AccountPowerMapper;
import com.zhuanjingkj.zjcbe.domain.po.AccountPowerPO;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountPowerRepository extends ServiceImpl<AccountPowerMapper, AccountPowerEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean batchAddPower(List<AccountPowerEntity> lst) {
        return this.insertBatch(lst);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountPowerPO> getAccountPowerList(List<Integer> list) {
        String accountIds = StringUtils.join(list, ',');
        return baseMapper.getAccountPowerList(accountIds);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountPowerPO> getAccountPowerList(int accountId) {
        return baseMapper.getAccountPowerList(String.valueOf(accountId));
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean deleteByAccountId(int accountId) {
        EntityWrapper<AccountPowerEntity> ew = new EntityWrapper<>();
        ew.eq("accountId", accountId);
        return this.delete(ew);
    }
}
