package com.zhuanjingkj.zjcbe.domain.mapper.account;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.AccountEntity;
import com.zhuanjingkj.zjcbe.domain.mapperprovider.AccountMapperProvider;
import com.zhuanjingkj.zjcbe.domain.po.AccountPO;
import com.zhuanjingkj.zjcbe.model.AccountSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {
    @SelectProvider(type = AccountMapperProvider.class, method = "getAccountPageListSql")
    List<AccountPO> getAccountPageList(AccountSearchDTO searchDTO, int pageIndex, int pageSize);

    @SelectProvider(type = AccountMapperProvider.class, method = "getAccountCountSql")
    Integer getAccountCount(AccountSearchDTO searchDTO);
}
