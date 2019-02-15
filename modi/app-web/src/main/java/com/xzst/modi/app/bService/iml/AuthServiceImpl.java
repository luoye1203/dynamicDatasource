package com.xzst.modi.app.bService.iml;

import com.xzst.modi.app.bService.AuthService;
import com.xzst.modi.app.cDao.UserDao;
import com.xzst.modi.app.dModel.user.User;
import com.xzst.modi.app.gCommon.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author li
 * @desc login create token and refresh token
 */
@Service
public class AuthServiceImpl implements AuthService {
    private Logger log = Logger.getLogger(AuthServiceImpl.class);

    @Autowired
    private JWTUtil jwtTokenUtil;
    @Autowired
    private UserDao userDao;

    @Value("${JWT.tokenHead}")
    private String tokenHead;


    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User loginToken(String username, String password) {
        log.info("loginToken-->"+username+","+password);
        // Reload password post-security so we can generate token
        final User userDetails = userDao.findByUsername(username);
        if(null== userDetails){
            return null;
        }
        User user = new User();
        if(username.equalsIgnoreCase(userDetails.getUsername()) && password.equalsIgnoreCase(userDetails.getPassword())){
            final String token = jwtTokenUtil.generateToken(userDetails);
            user.setToken(token);
            user.setUsername(userDetails.getUsername());
            user.setRole(userDetails.getRole());
            user.setYhbh(userDetails.getYhbh());
            user.setYhxm(userDetails.getYhxm());
            user.setSfzh(userDetails.getSfzh());
        }
        return user;
    }

    @Override
    public String refreshToken(String oldToken) throws Exception{
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user =  userDao.findByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    /***
     * 获取用户编号
     * @return
     */
    public String getYhbhFromToken(HttpServletRequest request){
        return jwtTokenUtil.getYhbhFromToken(request);
    }


    public List queryTmSysParam(@Param("pid") final String pid){
        return userDao.queryTmSysParam(pid);
    }
}
