package com.yaojun.jdbc;

import org.apache.log4j.Logger;

public class YIdleTimeoutThread extends Thread{
	
	private static Logger logger = Logger.getLogger(YIdleTimeoutThread.class);
	
	
	private YDataSource yds;
	
	private volatile long sleepSeconds;

	public YIdleTimeoutThread(YDataSource yds) {
		this.yds = yds;
	}
	
	
	@Override
	public void run() {
		
		
		while(true){
			
			try {
				Thread.sleep(sleepSeconds * 1000);
			} catch (InterruptedException e) {

				logger.error("", e);
			}
			
			
			
			// now to check idle connections
			yds.checkIdleConnections();
			
		}
		
		
	}
	
	



	public long getSleepSeconds() {
		return sleepSeconds;
	}



	public void setSleepSeconds(long sleepSeconds) {
		this.sleepSeconds = sleepSeconds;
		
		logger.info("now to setSleepSeconds: " + sleepSeconds);
	}
	
	
	
}
