package com.zhuanjingkj.zjcbe.facade.service;

import com.zhuanjingkj.zjcbe.common.AppConst;
import com.zhuanjingkj.zjcbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.zjcbe.data.dto.LoginDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.rto.LoginRTO;
import com.zhuanjingkj.zjcbe.data.vo.UserVO;
import com.zhuanjingkj.zjcbe.facade.fcc.FccUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class FacadeService implements IFacadeService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    FccUserService fccUserService;

    Logger logger = LoggerFactory.getLogger(FacadeService.class);

    @Override
    public ResultDTO<LoginDTO> login(LoginRTO rto) {
        ResultDTO<LoginDTO> dto = fccUserService.login(rto);
        LoginDTO data = (LoginDTO)dto.getData();
        data.setJwtToken(generateJwtToken(data));
        UserVO userVo = new UserVO();
        userVo.setUserId(data.getUserId());
        userVo.setUserName(data.getUserName());
        userVo.setRoleId(data.getRoleId());
        userVo.setRoleName(data.getRoleName());
        logger.info("### Yt ###: save userVo to Redis");
        redisTemplate.opsForValue().set(
                AppConst.AUTH_REDIS_USER_PREFIX + userVo.getUserId(),
                userVo, AppConst.REDIS_USER_DURATION, TimeUnit.MILLISECONDS);
        logger.info("### Yt ###: save userVo to Redis Done");
        return dto;
    }

    @Override
    public ResultDTO<GetUserInfoDTO> getUserInfo(String platform, String version, String userIdStr) {
        String uidStr = request.getHeader(AppConst.AUTH_USER_HEADER);
        logger.info("用户编号：" + uidStr + "!");
        return fccUserService.getUserInfo(platform, version, userIdStr);
    }

    public String generateJwtToken(LoginDTO data) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", "" + data.getUserId());
        Long endTime = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15;
        String token = Jwts.builder().setSubject("zjc").setExpiration(new Date(endTime))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, AppConst.JWT_KEY.getBytes()).compact();
        System.out.println("token=" + token + "!");
        return token;
    }
}
