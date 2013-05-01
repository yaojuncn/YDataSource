package com.yaojun.jdbc;

import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author yaojun
 *
 */
public class YConnection implements Connection{

	private static Logger logger = Logger.getLogger(YConnection.class);
	
	private Connection internalConnection;

	/**
	 * refer to the datasource that holds the connection
	 */
	private YDataSource ds;

	/**
	 * this flag to indicate whether the connection is closed from the normal perspective, for outside usage
	 */
	private volatile boolean isClosedFlag;
	
	/**
	 * this flag is to indicate whether the connection is closed from the internal management perspective, 
	 * usually when we receive a fatal exception, we need to destroy the connection from pool and mark this as true, so that future Connection.close() call
	 * will not return the invalid connection to the pool
	 */
	private volatile boolean reallyClosed;
	
	public YConnection(Connection internalConnection, YDataSource ds) {
		super();
		this.internalConnection = internalConnection;
		this.ds = ds;
		isClosedFlag = false;
		
		reallyClosed = false;
	}
	
	public YDataSource getDs() {
		return ds;
	}
	
	
	public void realClose(){
		
		logger.info("YConnection.realClose called.");
		
		try {
			
			this.internalConnection.rollback();
		}
		catch(SQLException e){
			logger.error("", e);
		}
		
		
		try{
			this.internalConnection.close();
			
			reallyClosed = true;
		} catch (SQLException e) {

			logger.error("", e);
		}
	}
	
	protected void precheck() throws SQLException {
		
		if( (this.isClosedFlag) || ( this.reallyClosed)){
		
			throw new SQLException("The connection is closed or invalid, please try to get a new connection from the pool");
		}
		
		
	}
	
	protected SQLException checkedException(SQLException e) {
		

		try {
			
			
			// use current class's classloader instead of current thread's classloader to avoid OSGI issues, that a caller thread might be from another bundle
			Class clz = this.getClass().getClassLoader().loadClass(ds.getExceptionSorterClzName());
			Object o = clz.newInstance();
			Method m = clz.getMethod("isExceptionFatal", SQLException.class);
			
			Boolean  ret = (Boolean)m.invoke(o, e);
			
			// if it's fatal, destroy the connection from pool 
			if(ret){
				
				logger.info("YStatement.checkedException() get a fatal exception, now to destroy it.");
				ds.destroyOneConnection(this);
			}
			
			
		} catch (Exception e1) {
			
			logger.error("YStatement.checkedException: [FATAL EXCEPTION!Please check immediately]: ", e1);
		}

		
		return e;
	}
	

	public Connection getInternalConnection() {
		return internalConnection;
	}

	public void setInternalConnection(Connection internalConnection) {
		this.internalConnection = internalConnection;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		precheck();
		
		try{
			return internalConnection.unwrap(iface);
		}
		catch(SQLException e){
			throw this.checkedException(e);			
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {

		precheck();
		
		try {
			return this.internalConnection.isWrapperFor(iface);
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public Statement createStatement() throws SQLException {

		precheck();
		
		try {
			Statement stat = this.internalConnection.createStatement();
			YStatement ystat = new YStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		
		precheck() ;
		
		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql);
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {

		precheck();
		
		try {
			CallableStatement stat = this.internalConnection.prepareCall(sql);
			
			YCallableStatement ystat = new YCallableStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		
		precheck();
		
		try {
			String nativesql = this.internalConnection.nativeSQL(sql);
			return nativesql;
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {

		precheck();
		
		try {
			this.internalConnection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public boolean getAutoCommit() throws SQLException {

		precheck();
		
		try {
			boolean autocommit = this.internalConnection.getAutoCommit();
			return autocommit;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void commit() throws SQLException {

		precheck();
		
		try {
			this.internalConnection.commit();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void rollback() throws SQLException {

		precheck();
		
		try {
			this.internalConnection.rollback();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void close() throws SQLException {

		// do not call precheck for isClosedFlag or this.reallyClosed 

		
		if(!reallyClosed){
			ds.returnConnection(this);
		}
			
		isClosedFlag = true;
	}

	@Override
	public boolean isClosed() throws SQLException {
		
		
		return isClosedFlag;
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {

		try {
			DatabaseMetaData meta = this.internalConnection.getMetaData();
			
			return meta;
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {

		try {
			this.internalConnection.setReadOnly(readOnly);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public boolean isReadOnly() throws SQLException {

		try {
			boolean ret = this.internalConnection.isReadOnly();
			return ret;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {

		try {
			this.internalConnection.setCatalog(catalog);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public String getCatalog() throws SQLException {

		try {
			String catalog = this.internalConnection.getCatalog();
			return catalog;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {

		try {
			this.internalConnection.setTransactionIsolation(level);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getTransactionIsolation() throws SQLException {

		try {
			int level = this.internalConnection.getTransactionIsolation();
			
			return level;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {

		try {
			SQLWarning warning= this.internalConnection.getWarnings();
			
			return warning;
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public void clearWarnings() throws SQLException {
		
		
		try {
			this.internalConnection.clearWarnings();
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {

		try {
			Statement stat = this.internalConnection.createStatement(resultSetType, resultSetConcurrency);
			
			YStatement ystat = new YStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {
			throw this.checkedException(e);
		}
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {

		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {

		try {
			CallableStatement stat = this.internalConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
			YCallableStatement ystat = new YCallableStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		
		try {
			Map<String, Class<?>> map = this.internalConnection.getTypeMap();
			
			return map;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

		try {
			this.internalConnection.setTypeMap(map);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {

		try {
			this.internalConnection.setHoldability(holdability);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getHoldability() throws SQLException {

		try {
			int holdability = this.internalConnection.getHoldability();
			
			return holdability;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {

		try {
			Savepoint sp = this.internalConnection.setSavepoint();
			
			return sp;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {

		try {
			Savepoint sp = this.internalConnection.setSavepoint(name);
			
			return sp;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {

		try {
			this.internalConnection.rollback(savepoint);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {

		try {
			this.internalConnection.releaseSavepoint(savepoint);
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		try {
			Statement stat = this.internalConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
			YStatement ystat = new YStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
			
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		try {
			CallableStatement stat = this.internalConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
			YCallableStatement ystat = new YCallableStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		
		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql, autoGeneratedKeys);
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {

		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql, columnIndexes);
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		
		try {
			PreparedStatement stat = this.internalConnection.prepareStatement(sql, columnNames);
			YPreparedStatement ystat = new YPreparedStatement(stat, this);
			return ystat;
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Clob createClob() throws SQLException {
		
		try {
			Clob clob = this.internalConnection.createClob();
			return clob;
			
		} catch (SQLException e) {

			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Blob createBlob() throws SQLException {

		try {
			Blob blob = this.internalConnection.createBlob();
			return blob;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public NClob createNClob() throws SQLException {
		
		try {
			NClob nclob = this.internalConnection.createNClob();
			return nclob;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		
		try {
			SQLXML xml = this.internalConnection.createSQLXML();
			return xml;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {

		try {
			boolean flag = this.internalConnection.isValid(timeout);
			return flag;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		
		try {
			this.internalConnection.setClientInfo(name, value);
		} catch (SQLException e) {
			
			throw (SQLClientInfoException)this.checkedException(e);
		}
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {

		try {
			this.internalConnection.setClientInfo(properties);
		} catch (SQLException e) {
			
			throw (SQLClientInfoException)this.checkedException(e);
		}
		
	}

	@Override
	public String getClientInfo(String name) throws SQLException {

		try {
			String str = this.internalConnection.getClientInfo(name);
			return str;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		
		try {
			Properties props = this.internalConnection.getClientInfo();
			return props;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		
		try {
			Array arr = this.internalConnection.createArrayOf(typeName, elements);
			return arr;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		
		try {
			Struct struct = this.internalConnection.createStruct(typeName, attributes);
			return struct;
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

}
