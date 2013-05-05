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
	
	// in second unit
	private int querytimeout;
	
	// in ms unit
	private int blockingtimeout;
	
	
	// idle timeout in minutes, after the timeout the connection will be destroyed
	private int idletimeout;
	
	
	private String exceptionSorterClzName;
	
	// *** now start of internal control properties
	
	/**
	 * current count of all the connections in the pool, including free and in use, 
	 * only use volatile should be fine, even for currentCount++ operations, because the [[ACCURACY of this value at that time]] is not that important
	 */
	private volatile int currentCount;
	
	
 	
	private BlockingQueue<YConnection> connectionQueue;

	
	private YIdleTimeoutThread timeoutThread;
	
	
	/**
	 * 
	 * @param driverClzName
	 * @param dburl
	 * @param username
	 * @param password
	 * @param min
	 * @param max
	 * @param querytimeout: in seconds
	 * @param blockingtimeout: in ms, 
	 * @param idletimeout  : in minutes; after the time the connection will be destroyed
	 * @param exceptionSorterClzName: the exception sorter class
	 */
	public YDataSource(String driverClzName, String dburl, String username,
				String password, int min, int max, int querytimeout,
				int blockingtimeout, int idletimeout, String exceptionSorterClzName) {
			super();
			this.dburl = dburl;
			this.driverClzName = driverClzName;
			this.username = username;
			this.password = password;
			this.min = min;
			this.max = max;
			this.querytimeout = querytimeout;
			this.blockingtimeout = blockingtimeout;
			this.idletimeout = idletimeout;
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
			
			
			timeoutThread = new YIdleTimeoutThread(this);
			
			
		} catch (Exception e) {
			logger.error("failed to create connection", e);
		}
	}
	
	
	public void checkIdleConnections(){
		
		logger.info("entering checkIdleConnections[currentCount,active,free]: " + this.getCurrentCount() + ", " + this.getActiveConnectionsCount() + "," + this.getFreeConnectionsCount() );
		
		// for thread safe now, in the future we can try to reduce the lock level
		synchronized (connectionQueue) {

			YConnection first = this.connectionQueue.peek();
			while((null != first) && (currentCount > min)){
				long t = System.currentTimeMillis() - first.getLastUsedTime();
				
				long expectedT =  idletimeout* 60 * 1000;
				
				// if it's passed the idle timeout 
				if(t > expectedT){
					
					logger.info("now to destroy a connection with idle time(ms): " + t);
					
					// because we're in a synchronized block, call queue.remove must success
					first = this.connectionQueue.remove();
					
					
					this.destroyOneConnection(first);
					
					first = this.connectionQueue.peek();
				}
				else{
					
					long nt = (expectedT - t)/1000;
					
					// update the sleep time and let it sleep again
					this.timeoutThread.setSleepSeconds(nt);
					
					break;
				}
			}
			
		}
		
	}
	
	public void returnConnection(YConnection conn){
		
		// use add, so that if our code has an error it will throw IllegalStateException  
		connectionQueue.add(conn);
		
		if(currentCount > min){
			
			// if it's new 
			if(this.timeoutThread.getState().equals(Thread.State.NEW)){
				
				long sleepSeconds = idletimeout * 60 ;
				
				timeoutThread.setSleepSeconds(sleepSeconds);
				timeoutThread.start();
			}
			
		}
		
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
			YConnection conn = connectionQueue.poll(blockingtimeout, TimeUnit.MILLISECONDS);
			
			// 2. if it's valid, just return
			if(null != conn){
				
				conn.openConnection();
				
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
				
				// add this line will cause a bug, next call to getConnection will return a duplicate one, in multiple threads this is an error
				//connectionQueue.add(yconn);
				
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


	public int getIdletimeout() {
		return idletimeout;
	}


	public void setIdletimeout(int idletimeout) {
		this.idletimeout = idletimeout;
	}


	public void setExceptionSorterClzName(String exceptionSorterClzName) {
		this.exceptionSorterClzName = exceptionSorterClzName;
	}
 	
	
	
}
