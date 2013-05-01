package com.yaojun.jdbc;

import java.sql.SQLException;


/**
 * 
 * wrap own exception so that it's easier to troubleshoot
 * @author yaojun
 *
 */
public class YDataSourceException extends SQLException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1946492462730959552L;

	public YDataSourceException(String reason, String SQLState, int vendorCode) {
		
		super(reason, SQLState, vendorCode);
	}
	
	public YDataSourceException(String reason, String SQLState) {
		super(reason, SQLState);
	}
	
	public YDataSourceException(String reason) {
		super(reason);
	}
	
	public YDataSourceException(Throwable cause) {
		super(cause);
	}
	
	public YDataSourceException(String reason, Throwable cause) {
		super(reason, cause);
	}
	
	public YDataSourceException(String reason, String sqlState, Throwable cause) {
		super(reason, sqlState, cause);
	}
	
	public YDataSourceException(String reason, String sqlState, int vendorCode, Throwable cause) {
		super(reason, sqlState, vendorCode, cause);
	}
	
	public YDataSourceException() {
	}
	
}
