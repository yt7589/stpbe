package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.AccountAuthTokenEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.AccountAuthtokenMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountAuthTokenRepository extends ServiceImpl<AccountAuthtokenMapper, AccountAuthTokenEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean addAccountAuth(AccountAuthTokenEntity entity) {
        return this.insert(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public AccountAuthTokenEntity getAccountAuthToken(String accessToken) {
        EntityWrapper<AccountAuthTokenEntity> ew = new EntityWrapper<>();
        ew.eq("token", accessToken);
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        List<AccountAuthTokenEntity> lst = this.selectList(ew);
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }
}
