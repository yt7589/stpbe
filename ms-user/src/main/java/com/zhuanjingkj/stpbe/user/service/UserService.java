package com.zhuanjingkj.stpbe.user.service;

import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import com.zhuanjingkj.stpbe.db.DataSourceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private DataSourceRegistry dataSourceRegistry;

    @Override
    public ResultDTO<LoginDTO> login(LoginRTO rto) {
        JdbcTemplate jt = dataSourceRegistry.getJdbcTemplate(
                DataSourceRegistry.JDBC_TEMPLATE_TYPE_USER, 1,
                DataSourceRegistry.JDBC_TEMPLATE_MODE_READ);
        System.out.println("JdbcTemplate=" + jt + "!");
        ResultDTO<LoginDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("");
        LoginDTO data = new LoginDTO();
        data.setUserId(1001L);
        data.setUserName("用户1001");
        data.setRoleId(2001);
        data.setRoleName("普通用户");
        dto.setData(data);
        return dto;
    }
}
