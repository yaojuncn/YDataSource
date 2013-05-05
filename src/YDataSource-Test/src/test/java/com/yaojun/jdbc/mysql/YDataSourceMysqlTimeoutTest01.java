package com.yaojun.jdbc.mysql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class YDataSourceMysqlTimeoutTest01 extends YDataSourceMysqlTestBase {

	@Test
	public void testIdleTimeout() throws Exception {
		
		
		int max =  Integer.parseInt(this.props.getProperty("max"));
		
		List<Connection> list = new ArrayList<Connection>();
		
		for(int i = 0;i < max; ++i){
			Connection conn = this.yds.getConnection();
			list.add(conn);
		}
		
		
		
		System.out.println(new Date() + " : before query : ");
		
		for(int i = 0; i < max; ++i){
			Connection conn = list.get(i);
			
			this.queryWithConn(conn);
			
			
			
			conn.close();
			
			System.out.println(new Date() + " finished : " + i);
		}
		
		
		
		// sleep for 10 minutes
		Thread.sleep(10 * 60 * 1000);
		
		
	}
}
