package com.yaojun.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import junit.framework.Assert;

import org.junit.Test;

import com.yaojun.jdbc.YDataSource;

public class YDataSourceMysqlPerfTest01 extends YDataSourceMysqlTestBase{
	
	
	@Test
	public void testGetConnectionThread1() throws Exception{
		
		testGetConnectionTime(1);
	}

	
	@Test
	public void testGetConnectionThread2() throws Exception{
		
		testGetConnectionTime(2);
	}

	@Test
	public void testGetConnectionThread5() throws Exception{
		
		testGetConnectionTime(5);
	}

	@Test
	public void testGetConnectionThread10() throws Exception{
		
		testGetConnectionTime(10);
	}

	
	public void testGetConnectionTime(int tno ) throws Exception {
		
		int count = 1000 * 1000;
		

		System.out.println("threadno: " + tno);
		
		final CountDownLatch c = new CountDownLatch(tno);
		
		List<Thread> tlist = new ArrayList<Thread>();
		
		for(int i = 0; i < tno; ++i){
			
			final int onec = count /tno;
			final YDataSource theds = this.yds;
			
			Thread t = new Thread(new Runnable(){

				@Override
				public void run() {
					
					for(int i = 0; i < onec; ++i){
						Connection conn;
						try {
							conn = theds.getConnection();
							conn.close();
						} catch (SQLException e) {

							e.printStackTrace();
							
							Assert.assertTrue(false);
						}
						
					}
					
					c.countDown();
				}
				
			});
			
			tlist.add(t);
		}
		
		
		System.out.println(new Date() + " now start to get connecion for count: " + count);		
		long s = System.currentTimeMillis();
		
		for(int i = 0; i < tno; ++i){
			Thread t = tlist.get(i);
			t.start();
		}
		
		c.await();
		
		long t = System.currentTimeMillis() - s;
		
		
		
		System.out.println(new Date() + " time(ms):" + t);
		
	}

}
