package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.commondata.enums.CommonStatusEnum;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.AccountDepartmentEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.AccountDepartmentMapper;
import com.zhuanjingkj.zjcbe.domain.po.AccountDepartmentPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDepartmentRepository extends ServiceImpl<AccountDepartmentMapper, AccountDepartmentEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountDepartmentPO> getDeptByRootId(int rootId) {
        return baseMapper.getDeptByRootId(rootId);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean insertDept(AccountDepartmentEntity departmentEntity) {
        return this.insert(departmentEntity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.WRITE)
    public boolean updateDept(AccountDepartmentEntity departmentEntity) {
        return this.updateById(departmentEntity);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public boolean existDept(String deptName) {
        EntityWrapper<AccountDepartmentEntity> ew = new EntityWrapper<>();
        ew.eq("deptName", deptName);
        return this.selectCount(ew) > 0;
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public AccountDepartmentEntity getDeptById(Integer deptId) {
        return baseMapper.selectById(deptId);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public int getChildDeptCount(int deptId) {
        EntityWrapper<AccountDepartmentEntity> ew = new EntityWrapper<>();
        ew.eq("parentId", deptId);
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        return baseMapper.selectCount(ew);
    }

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<AccountDepartmentEntity> getDeptByType(Integer deptType) {
        EntityWrapper<AccountDepartmentEntity> ew = new EntityWrapper<>();
        ew.eq("status", CommonStatusEnum.VALID.getValue());
        ew.eq("deptType", deptType);
        return this.selectList(ew);
    }
}
