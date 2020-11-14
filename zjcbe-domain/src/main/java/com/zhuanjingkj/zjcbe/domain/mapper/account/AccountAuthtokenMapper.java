package com.zhuanjingkj.zjcbe.domain.mapper.account;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuanjingkj.zjcbe.domain.entity.AccountAuthTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/9/26 9:28
 **/
@Repository
@Mapper
public interface AccountAuthtokenMapper extends BaseMapper<AccountAuthTokenEntity> {

}
