package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.AccountEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.AccountMapper;
import com.zhuanjingkj.zjcbe.domain.po.AccountPO;
import com.zhuanjingkj.zjcbe.model.AccountSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository extends ServiceImpl<AccountMapper, AccountEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean insertAccount(AccountEntity entity) {
        return this.insert(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean updateAccount(AccountEntity entity) {
        return this.updateById(entity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public boolean existAccountByUserCode(String userCode) {
        EntityWrapper<AccountEntity> ew = new EntityWrapper<>();
        ew.eq("userCode", userCode);
        return this.selectCount(ew) > 0;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public boolean existAccountByMobileCode(String mobileCode) {
        EntityWrapper<AccountEntity> ew = new EntityWrapper<>();
        ew.eq("mobileCode", mobileCode);
        return this.selectCount(ew) > 0;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public AccountEntity getAccountEntityById(Integer accountId) {
        return this.selectById(accountId);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public int getAccountCount(AccountSearchDTO searchDTO) {
        return baseMapper.getAccountCount(searchDTO);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountPO> getAccountPageList(AccountSearchDTO searchDTO, int pageIndex, int pageSize) {
        return baseMapper.getAccountPageList(searchDTO, pageIndex, pageSize);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public AccountPO getAccountInfoById(int accountId) {
        AccountSearchDTO searchDTO = new AccountSearchDTO();
        searchDTO.setAccountId(accountId);
        List<AccountPO> accountManagePOS = getAccountPageList(searchDTO, 1, 1);
        if (accountManagePOS != null && accountManagePOS.size() > 0) {
            return accountManagePOS.get(0);
        }
        return null;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public AccountPO getAccountInfoByCode(String userCode) {
        AccountSearchDTO searchDTO = new AccountSearchDTO();
        searchDTO.setUserCode(userCode);
        List<AccountPO> accountManagePOS = getAccountPageList(searchDTO, 1, 1);
        if (accountManagePOS != null && accountManagePOS.size() > 0) {
            return accountManagePOS.get(0);
        }
        return null;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public int getUserCountByDeptId(int deptId) {
        EntityWrapper<AccountEntity> ew = new EntityWrapper<>();
        ew.eq("deptId", deptId);
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        return baseMapper.selectCount(ew);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountEntity> getAccountListByDeptId(int deptId) {
        EntityWrapper<AccountEntity> ew = new EntityWrapper<>();
        ew.eq("deptId", deptId);
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        return baseMapper.selectList(ew);
    }
}
