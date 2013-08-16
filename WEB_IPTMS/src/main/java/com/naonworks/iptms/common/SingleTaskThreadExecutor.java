package com.naonworks.iptms.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class SingleTaskThreadExecutor implements FactoryBean<Object>,
		InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(SingleTaskThreadExecutor.class);
	
	private LoofTimerTask loofTimerTask;
	private TaskThread thread;
	private boolean daemon = true;
	
	public final void afterPropertiesSet() throws IOException {
		if( loofTimerTask != null ) {
			thread = new TaskThread(loofTimerTask);
			thread.setDaemon(daemon);

			thread.start();
			
			if( logger.isInfoEnabled() ) {
				logger.info(loofTimerTask.getClass().getName() + " TaskThread started...");
			}
		}
	}

	public final Object getObject() throws IOException {
		return thread;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getObjectType() {
		return (this.thread != null ? this.thread.getClass() : Thread.class);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return false;
	}

	/**
	 * @return the daemon
	 */
	public boolean isDaemon() {
		return daemon;
	}

	/**
	 * @param daemon the daemon to set
	 */
	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}

	@Override
	public void destroy() throws Exception {
		if( this.thread != null ) {
			this.thread.stopThread();
			this.thread = null;
		}
	}

	public LoofTimerTask getLoofTimerTask() {
		return loofTimerTask;
	}

	public void setLoofTimerTask(LoofTimerTask loofTimerTask) {
		this.loofTimerTask = loofTimerTask;
	}
}
