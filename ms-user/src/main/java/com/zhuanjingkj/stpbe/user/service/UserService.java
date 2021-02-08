package com.zhuanjingkj.stpbe.user.service;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import com.zhuanjingkj.stpbe.db.DaoEngine;
import com.zhuanjingkj.stpbe.db.DataSourceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Value("${stp.saas.system-id}")
    private String systemId;
    /*@Autowired
    private DataSourceRegistry dataSourceRegistry;*/

    @Override
    public ResultDTO<LoginDTO> login(LoginRTO rto) {
        String currentMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String currentClassName = Thread.currentThread().getStackTrace()[1].getClassName();
        String opsName = currentClassName + "." + currentMethodName;
        String sql = "select user_id, user_name from t_user where login_name=? and login_pwd=?";
        Object[] params = new Object[2];
        params[0] = rto.getLoginName();
        params[1] = rto.getLoginPwd();
        RowMapper<LoginDTO> rowMapper = (rs, num) -> {
            LoginDTO dto = new LoginDTO();
            dto.setUserId(rs.getLong(1));
            dto.setUserName(rs.getString(2));
            return dto;
        };
        DaoEngine dao = new DaoEngine();
        List<LoginDTO> recs = dao.query(systemId, opsName, sql, params, rowMapper);
        ResultDTO<LoginDTO> dto = new ResultDTO<>();
        if (recs.size() < 1) {
            dto.setCode(1);
            dto.setMsg("用户名或密码错误");
        } else {
            dto.setCode(0);
            dto.setMsg("");
            LoginDTO data = recs.get(0);
            data.setRoleId(2001);
            data.setRoleName("普通用户");
            dto.setData(data);
        }
        return dto;
    }
}
