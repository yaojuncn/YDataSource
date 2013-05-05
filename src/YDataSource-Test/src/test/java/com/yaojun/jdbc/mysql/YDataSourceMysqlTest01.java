package com.yaojun.jdbc.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author yaojun
 * 
 * so far this class only support running test cases one by one 
 *
 */
public class YDataSourceMysqlTest01 extends YDataSourceMysqlTestBase {
	
	
	
	
	@Test
	public void testGet5NoStop() throws Exception {
		
		this.testGet5(-1);
	}
	
	
	@Test
	public void testGet5Stop0() throws Exception{
		
		// stop before doing query
		this.testGet5(0);
	}
	
	
	@Test
	public void testGet5Stop2() throws Exception {
		
		this.testGet5(2);
	}
	
	
	public void testGet5(int stopindex) throws Exception {
		
		int min = Integer.parseInt(props.getProperty("min"));

		
		checkCountInfo(min, min, 0);
		
		List<Connection> connList = new ArrayList<Connection>();
		
		System.out.println("-------------------now to get connections--------------------------");
		
		int count = min;
		for(int i = 0; i < count; ++i){
			Connection conn = yds.getConnection();
			connList.add(conn);
		}
		
		
		boolean stopped = false;
		
		for(int i = 0; i < count; ++i){
			
			if(i == stopindex){
				this.stopmysql();
				
				stopped = true;
			}
			
			Connection conn = connList.get(i);
			
			try {
				this.queryWithConn(conn);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			
			if(stopped){
				
				int expectedCount = min - (i-stopindex) - 1; 
				int expectedFree = 0;
				int expectedActive = expectedCount;

				checkCountInfo(expectedCount, expectedFree, expectedActive);

			}
			else{
				int expectedCount = min; 
				int expectedFree = 0; 
				int expectedActive = expectedCount;

				checkCountInfo(expectedCount, expectedFree, expectedActive);

			}
			
		}
		
		
		
	}
	
	@Test
	public void testGet10() throws Exception{
		
		int c = Integer.parseInt(props.getProperty("max"));
		
		List<Connection> connList = new ArrayList<Connection>();
		for(int i = 0; i < c; ++i){
			Connection conn = yds.getConnection();
			
			connList.add(conn);
		}
		
		
		
		
		for(int i = 0; i < c; ++i){
		
			Connection conn = connList.get(i);
			this.queryWithConn(conn);
			
			conn.close();
		}
		
		
		
		
		
	}
	
	
	
	@Test
	public void testNormal0() throws Exception {
		
		
		long s = System.currentTimeMillis();
		Connection conn = yds.getConnection();
		
		long t = System.currentTimeMillis() - s;
		
		System.out.println("get a connection time(ms): " + t);
		
		queryWithConn(conn);
		
		conn.close();
		
	}

	
	
	
	
}
