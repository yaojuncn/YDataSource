package com.yaojun.jdbc.mysql;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

import com.yaojun.jdbc.YDataSource;

public class YDataSourceMysqlTestBase {

	protected Properties props;

	protected YDataSource yds;
	

	@Before
	public void prepare() {
		
		
		
		
		
		props = new Properties();
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("myconfig.properties");
			props.load(new InputStreamReader(is));
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		for(Object key: props.keySet()){
			System.out.println(key + ", " + props.getProperty((String)key));
		}

		// first try to start mysql
		startmysql();

		
		String dburl = props.getProperty("dburl");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String driverClzName = props.getProperty("driverClzName");

		int min = Integer.parseInt(props.getProperty("min"));
		int max = Integer.parseInt(props.getProperty("max"));
		
		int idletimeout = Integer.parseInt(props.getProperty("idletimeout"));
		
		String exceptionClzname = props.getProperty("exceptionSorterClzName");
		 
		
		yds = new YDataSource(driverClzName, dburl, username, password, min, max, -1, -1, idletimeout, exceptionClzname);

		
	}
	
	protected void checkCountInfo(int expectedCount, int expectedFree, int expectedActive) throws Exception {
		
		int currentCount = yds.getCurrentCount();
		int free = yds.getFreeConnectionsCount();
		int active = yds.getActiveConnectionsCount();

		System.out.println("currentCount: " + currentCount);
		System.out.println("free: " + free);
		System.out.println("active: " + active);

		
		Assert.assertEquals(expectedCount, currentCount);
		Assert.assertEquals(expectedFree, free);
		Assert.assertEquals(expectedActive, active);
		
		
	}


	protected void startmysql(){
		try {
			
			System.out.println("now to start mysql");
			
			String startcmd = props.getProperty("startmysqlcmd");
			Runtime.getRuntime().exec(startcmd);

			Thread.currentThread().sleep(5 * 1000);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 
	}
	
	protected void stopmysql(){
		try {
			
			System.out.println("now to stop mysql");
			String stopcmd = props.getProperty("stopmysqlcmd");
			Runtime.getRuntime().exec(stopcmd);
			
			Thread.currentThread().sleep(3 * 1000);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void queryWithConn(Connection conn) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select id, name from test");
		
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString(2);
			
			System.out.println(id + ", " + name);
		}
		
		rs.close();
		stat.close();
	}


	@After
	public void cleanup(){
		this.stopmysql();
	}

	
	
}
