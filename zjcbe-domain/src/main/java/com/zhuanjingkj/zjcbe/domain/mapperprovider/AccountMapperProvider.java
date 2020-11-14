package com.zhuanjingkj.zjcbe.domain.mapperprovider;

import com.zhuanjingkj.zjcbe.model.AccountSearchDTO;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/10/9 10:06
 **/
public class AccountMapperProvider {

    /**
     * 获取账户管理列表
     *
     * @param searchDTO
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String getAccountPageListSql(AccountSearchDTO searchDTO, int pageIndex, int pageSize) {
        String select = "select a.accountId\n" +
                "\t,a.userCode\n" +
                "\t,a.userPassword\n" +
                "\t,a.userName\n" +
                "\t,a.companyName\n" +
                "\t,a.city\n" +
                "\t,a.mobileCode\n" +
                "\t,a.roleId\n" +
                "\t,r.roleCode\n" +
                "\t,r.roleName\n" +
                "\t,a.status\n" +
                "\t,a.createBy\n" +
                "\t,a.createTime\n" +
                "\t,a.updateBy\n" +
                "\t,a.updateTime\n" +
                "\t,a.remark\n" +
                "from account a\n" +
                "join sys_role r on r.roleId=a.roleId\n";
        String where = buildWhereCondition(searchDTO);
        String orderBy = " \n order by a.accountId desc \n";
        String limit = String.format(" limit %d,%d", (pageIndex - 1) * pageSize, pageSize);
        return select + where + orderBy + limit;
    }

    /**
     * 获取账户数量
     *
     * @param searchDTO
     * @return
     */
    public String getAccountCountSql(AccountSearchDTO searchDTO) {
        String select = "select count(0)\n" +
                "from account a\n" +
                "join sys_role r on r.roleId=a.roleId\n";
        String where = buildWhereCondition(searchDTO);
        return select + where;
    }

    /**
     * 拼接where条件
     *
     * @param searchDTO
     * @return
     */
    private String buildWhereCondition(AccountSearchDTO searchDTO) {

        String where = " where a.status = 1 ";
        if (searchDTO.getAccountId() > 0) {
            where += String.format(" and a.accountId =%s ", searchDTO.getAccountId());
        }
        if (!StringUtils.isBlank(searchDTO.getUserCode())) {
            where += String.format(" and a.userCode ='%s' ", searchDTO.getUserCode());
        }
        if (!StringUtils.isBlank(searchDTO.getUserName())) {
            where += String.format(" and a.userName ='%s' ", searchDTO.getUserName());
        }
        if (!StringUtils.isBlank(searchDTO.getMobileCode())) {
            where += String.format(" and a.mobileCode ='%s' ", searchDTO.getMobileCode());
        }
        return where;
    }
}

