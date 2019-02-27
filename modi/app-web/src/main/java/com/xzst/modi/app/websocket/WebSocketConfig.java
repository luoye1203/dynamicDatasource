package com.xzst.modi.app.websocket;


import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Configuration
public class WebSocketConfig extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    /*如果没有监听器,那么这里获取到的HttpSession是null*/
		StandardSessionFacade ssf = (StandardSessionFacade) request.getHttpSession();
		if (ssf != null) {
			HttpSession httpSession = (HttpSession) request.getHttpSession();
			//关键操作
			String clientIP=""+httpSession.getAttribute("clientIP");
			sec.getUserProperties().put("clientIP", clientIP);
			sec.getUserProperties().put("sessionId", httpSession.getId());
			//获取用户编码
			String userId=request.getParameterMap().get("userId").get(0);
			sec.getUserProperties().put("userId", userId);
			String roleNo=request.getParameterMap().get("roleNo").get(0);
			sec.getUserProperties().put("roleNo", roleNo);


		}
	}

	/**
	 * 引入shiro框架下的session，获取session信息
	 */
  /*
  @Override
  public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    Session shiroSession = ShiroUtils.getSubjct().getSession();
    sec.getUserProperties().put("sessionId", shiroSession.getId());
  }
  */


	//这个对象说一下，springboot开发时使用,打包时注释掉

//	@Bean
//	public ServerEndpointExporter serverEndpointExporter() {
//
//		return new ServerEndpointExporter();
//	}




}