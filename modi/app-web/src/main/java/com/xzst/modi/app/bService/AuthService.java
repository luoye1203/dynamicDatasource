package com.xzst.modi.app.bService;


import com.xzst.modi.app.dModel.user.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthService {
    User loginToken(String username, String password);
    String refreshToken(String oldToken) throws Exception;
    String getYhbhFromToken(HttpServletRequest request);
    /**
     * 获取系统配置
     * @param pid
     * @return
     */
    public List queryTmSysParam(@Param("pid") final String pid);
}
