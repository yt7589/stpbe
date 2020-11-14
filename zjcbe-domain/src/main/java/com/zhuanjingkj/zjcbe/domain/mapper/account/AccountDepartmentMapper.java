package com.zhuanjingkj.zjcbe.domain.mapper.account;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.AccountDepartmentEntity;
import com.zhuanjingkj.zjcbe.domain.po.AccountDepartmentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface AccountDepartmentMapper extends BaseMapper<AccountDepartmentEntity> {

    @Select("SELECT deptId,deptType,deptName,parentId,createTime FROM account_department WHERE FIND_IN_SET(deptid,getAccountDeptTree(#{rootId})) and status=1")
    List<AccountDepartmentPO> getDeptByRootId(@Param("rootId") int rootId);
}
