package com.yaojun.jdbc;

import java.sql.SQLException;


/**
 * 
 * @author yaojun
 *
 */
public interface ExceptionSorter {
	
	

	
	/**
	 * judges whether an exception is fatal 
	 * if returns true, the YDataSource will destroy the current connection from the pool  
	 * @param e
	 * @return
	 */
	public boolean isExceptionFatal(SQLException e);

}
