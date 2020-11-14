package com.zhuanjingkj.zjcbe.domain.mapper.account;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.AccountPowerEntity;
import com.zhuanjingkj.zjcbe.domain.po.AccountPowerPO;
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
public interface AccountPowerMapper extends BaseMapper<AccountPowerEntity> {
    @Select("select a.id \n" +
            ",a.accountId\n" +
            ",p.powerId\n" +
            ",p.powerName\n" +
            ",p.powerPath\n" +
            ",p.powerType\n" +
            ",p.icon\n" +
            ",p.parentId\n" +
            " from account_power a\n" +
            " join sys_power p on a.powerId=p.powerId\n" +
            " where p.status=1 and a.accountId in(${accountIds})")
    List<AccountPowerPO> getAccountPowerList(@Param("accountIds") String accountIds);
}
