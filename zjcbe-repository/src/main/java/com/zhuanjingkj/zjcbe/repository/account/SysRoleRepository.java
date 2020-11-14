package com.zhuanjingkj.zjcbe.repository.account;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuanjingkj.zjcbe.commondata.enums.RoleTypeEnum;
import com.zhuanjingkj.zjcbe.dbengine.annotation.SingleDataSource;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBBusinessType;
import com.zhuanjingkj.zjcbe.dbengine.datasource.DBVisitType;
import com.zhuanjingkj.zjcbe.domain.entity.SysRoleEntity;
import com.zhuanjingkj.zjcbe.domain.mapper.account.SysRoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRoleRepository extends ServiceImpl<SysRoleMapper, SysRoleEntity> {

    @SingleDataSource(businessType = DBBusinessType.BUS_LINE, visitType = DBVisitType.READ)
    public List<SysRoleEntity> getRoleList() {
        EntityWrapper<SysRoleEntity> ew = new EntityWrapper<>();
        ew.ne("rolecode", RoleTypeEnum.SUPER_ADMIN.getValue());
        return selectList(ew);
    }
}
