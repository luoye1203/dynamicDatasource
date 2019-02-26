package com.xzst.modi.app.fFilter.loginFilter;

import com.xzst.modi.app.dModel.BaseResponse;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "loginFilter",
        urlPatterns = { "/kafka/*"})
//        urlPatterns = {"/swagger/*"})
public class LoginFilter implements Filter {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info(this.getClass().getName()+" 登陆认证失败");
        String token = request.getParameter("token");
        if (StringUtils.isNoneBlank(token)) {
            chain.doFilter(request, response);
        } else {
            BaseResponse baseresponse = BaseResponse.buildResponse().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("登录认证失败").build();
            this.sendResponse((HttpServletResponse) response, baseresponse, HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public void destroy() {

    }

    //过滤后，返回非法状态
    private void sendResponse(HttpServletResponse response, BaseResponse data, int status) {

        String content = JSON.toJSONString(data);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
