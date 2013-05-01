package com.yaojun.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 
 * core YDataSource class, managing a pool of connections
 * 
 * @author yaojun
 *
 */
public class YDataSource implements DataSource{

	private static Logger logger = Logger.getLogger(YDataSource.class);
	
	// *** start of configuration properties ******>
	private String dburl;
	
	private String driverClzName;
	
	private String username;
	private String password;
	
	// min and max number of connections in the datasource
	private int min;
	private int max;
	
	// all the timeout are in ms unit
	private int querytimeout;
	private int blockingtimeout;
	
	
	private String exceptionSorterClzName;
	
	// *** now start of internal control properties
	
	/**
	 * current count of all the connections in the pool, including free and in use, 
	 * only use volatile should be fine, even for currentCount++ operations, because the [[ACCURACY of this value at that time]] is not that important
	 */
	private volatile int currentCount;
	
	
 	
	private BlockingQueue<YConnection> connectionQueue;

	
	
	
	public YDataSource(String driverClzName, String dburl, String username,
				String password, int min, int max, int querytimeout,
				int blockingtimeout, String exceptionSorterClzName) {
			super();
			this.dburl = dburl;
			this.driverClzName = driverClzName;
			this.username = username;
			this.password = password;
			this.min = min;
			this.max = max;
			this.querytimeout = querytimeout;
			this.blockingtimeout = blockingtimeout;
			this.exceptionSorterClzName = exceptionSorterClzName;
			
			
			currentCount = 0;
			
			init();
		}

 	
	/**
	 * init the datasource with min number of connections 
	 */
	private void init(){
		
		try {
			
			Class.forName(driverClzName);
			
			connectionQueue = new ArrayBlockingQueue<YConnection>(max);
			
			
			for(int i = 0; i < min; ++i){
			
				Connection conn = DriverManager.getConnection(dburl, username, password);
				YConnection yconn = new YConnection(conn, this);
				
				connectionQueue.add(yconn);
			}
			
			currentCount = min;
		} catch (Exception e) {
			logger.error("failed to create connection", e);
		}
	}
	
	public void returnConnection(YConnection conn){
		
		// use add, so that if our code has an error it will throw IllegalStateException  
		connectionQueue.add(conn);
		
		
	}
	
	public void destoryAll(){
		
		synchronized (connectionQueue) {
		
			while(connectionQueue.size() > 0){
				YConnection conn = connectionQueue.poll();
				
				destroyOneConnection(conn);
			}
		}
		
	}
	
	public void destroyOneConnection(YConnection conn){
		
		if(null != conn){
			
			conn.realClose();
			
			currentCount--;
		}
	}
	
	/**
	 * get current number of connections in the pool, including those at usage and not
	 * @return
	 */
	public int getCurrentCount() {
		return currentCount;
	}
	
	/**
	 * get current number of active connections in the pool
	 * @return
	 */
	public int getActiveConnectionsCount(){
		
		int free = this.connectionQueue.size();
		int active = currentCount - free;
		
		return active;
	}
	
	/**
	 * get current number of free connections in  the pool 
	 * @return
	 */
	public int getFreeConnectionsCount(){
		
		int free = this.connectionQueue.size();
		return free;
	}
	

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		Connection conn = internalGetConnection(this.username, this.password);
		
		return conn;
		
	}
	
	private Connection internalGetConnection(String username, String password) throws SQLException{
		// 1. first try to get one from queue with timeout
		try {
			Connection conn = connectionQueue.poll(blockingtimeout, TimeUnit.MILLISECONDS);
			
			// 2. if it's valid, just return
			if(null != conn){
				return conn;
			}
			
			
		} catch (InterruptedException e) {

			logger.error("", e);
		}
		
		// to make sure it will not exceed max 
		synchronized (connectionQueue) {

			if(connectionQueue.size() >= max){
				throw new YDataSourceException("Connection pool full, No connections available now");
			}
			else{
				Connection conn = DriverManager.getConnection(dburl, this.username, password);
				
				YConnection yconn = new YConnection(conn, this);
				connectionQueue.add(yconn);
				
				currentCount++;
				
				return yconn;
			}
		}
		
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {

		Connection conn = this.internalGetConnection(username, password);
		
		return conn;
	}
	
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getQuerytimeout() {
		return querytimeout;
	}

	public void setQuerytimeout(int querytimeout) {
		this.querytimeout = querytimeout;
	}

	public int getBlockingtimeout() {
		return blockingtimeout;
	}

	public void setBlockingtimeout(int blockingtimeout) {
		this.blockingtimeout = blockingtimeout;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getDriverClzName() {
		return driverClzName;
	}

	public void setDriverClzName(String driverClzName) {
		this.driverClzName = driverClzName;
	}

	public String getExceptionSorterClzName() {
		return exceptionSorterClzName;
	}
 	
	
}
