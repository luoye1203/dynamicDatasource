package com.xzst.modi.app.http;


import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 使用管理器，管理HTTP连接池 无效链接定期清理功能</p>
 * @author andy 2017年8月28日
 */
@Component
public class HttpClientConnectionMonitorThread extends Thread {
	private static Logger log=Logger.getLogger(HttpClientConnectionMonitorThread.class);
	@Autowired
	private final HttpClientConnectionManager connManager;
	private volatile boolean shutdown;

	public HttpClientConnectionMonitorThread(HttpClientConnectionManager connManager) {
		super();
		this.setName("http-connection-monitor");
		this.setDaemon(true);
		this.connManager = connManager;
		this.start();
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000); // 等待5秒
//					log.info("*****************************我是守护进程");
					// 关闭过期的链接
					connManager.closeExpiredConnections();
					// 选择关闭 空闲30秒的链接
					connManager.closeIdleConnections(30, TimeUnit.SECONDS);
				}
			}
		} catch (InterruptedException ex) {
		}
	}

	/**
	 * 方法描述: 停止 管理器 清理无效链接  (该方法当前暂时关闭)
	 * @author andy 2017年8月28日 下午1:45:18
	 */
	@Deprecated
	public void shutDownMonitor() {
		synchronized (this) {
			shutdown = true;
			notifyAll();
		}
	}

}
