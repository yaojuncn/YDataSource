package com.yaojun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;


/**
 * 
 * @author yaojun
 *
 */
public class DbTest01 {

	@Test
	public void testGetConnection0() throws Exception {
		
		System.out.println("now to test...");
		
		Class.forName("com.mysql.jdbc.Driver");
		
		long s = System.currentTimeMillis();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yds", "yds", "1");
		
		long t = System.currentTimeMillis() - s;
		
		System.out.println("[plain mode] get connection time[ms]:" + t);
		
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select id, name from test");
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString(2);
			
			System.out.println(id + "," + name);
			
		}
		
		rs.close();
		stat.close();
		
		conn.close();
		
	}
}
