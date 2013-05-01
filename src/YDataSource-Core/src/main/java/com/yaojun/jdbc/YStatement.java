package com.yaojun.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import org.apache.log4j.Logger;


/**
 * 
 * @author yaojun
 *
 */
public class YStatement implements Statement{
	
	private static Logger logger = Logger.getLogger(YStatement.class); 
	
	private Statement internalStatement;
	
	protected YConnection conn;
	
	public YStatement(Statement statement, YConnection conn) {
		this.internalStatement = statement;
		this.conn = conn;
	}
	
	protected SQLException checkedException(SQLException e){
		
		try {
			
			YDataSource ds = conn.getDs();
			
			// use current class's classloader instead of current thread's classloader to avoid OSGI issues, that a caller thread might be from another bundle
			Class clz = this.getClass().getClassLoader().loadClass(ds.getExceptionSorterClzName());
			Object o = clz.newInstance();
			Method m = clz.getMethod("isExceptionFatal", SQLException.class);
			
			Boolean  ret = (Boolean)m.invoke(o, e);
			
			// if it's fatal, destroy the connection from pool 
			if(ret){
				
				logger.info("YStatement.checkedException() get a fatal exception, now to destroy it.");
				ds.destroyOneConnection(conn);
			}
			
			
		} catch (Exception e1) {
			
			logger.error("YStatement.checkedException: [FATAL EXCEPTION!Please check immediately]: ", e1);
		}
		
		return e;
	}
	

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		try {
			return this.internalStatement.unwrap(iface);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		
		try {
			return this.internalStatement.isWrapperFor(iface);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		
		try {
			return this.internalStatement.executeQuery(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		
		try {
			return this.internalStatement.executeUpdate(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void close() throws SQLException {
		
		try {
			this.internalStatement.close();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		
		try {
			return this.internalStatement.getMaxFieldSize();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		
		try {
			this.internalStatement.setMaxFieldSize(max);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public int getMaxRows() throws SQLException {
		
		try {
			return this.internalStatement.getMaxRows();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		
		try {
			this.internalStatement.setMaxRows(max);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		
		try {
			this.internalStatement.setEscapeProcessing(enable);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		
		try {
			return  this.internalStatement.getQueryTimeout();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		
		try {
			this.internalStatement.setQueryTimeout(seconds);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void cancel() throws SQLException {
		
		try {
			this.internalStatement.cancel();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		
		try {
			return this.internalStatement.getWarnings();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void clearWarnings() throws SQLException {
		
		try {
			this.internalStatement.clearWarnings();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		
		try {
			this.internalStatement.setCursorName(name);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		
		try {
			return this.internalStatement.execute(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		
		try {
			return this.internalStatement.getResultSet();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getUpdateCount() throws SQLException {
		
		try {
			return this.internalStatement.getUpdateCount();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		
		try {
			return this.internalStatement.getMoreResults();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		
		try {
			this.internalStatement.setFetchDirection(direction);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getFetchDirection() throws SQLException {
		
		try {
			return this.internalStatement.getFetchDirection();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		
		try {
			this.internalStatement.setFetchSize(rows);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getFetchSize() throws SQLException {
		
		try {
			return this.internalStatement.getFetchSize();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		
		try {
			return this.internalStatement.getResultSetConcurrency();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetType() throws SQLException {
		
		try {
			return this.internalStatement.getResultSetType();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		
		try {
			this.internalStatement.addBatch(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void clearBatch() throws SQLException {
		
		try {
			this.internalStatement.clearBatch();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int[] executeBatch() throws SQLException {
		
		try {
			return this.internalStatement.executeBatch();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		return this.conn;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		
		try {
			return this.internalStatement.getMoreResults(current);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		
		try {
			return this.internalStatement.getGeneratedKeys();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		
		try {
			return this.internalStatement.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		
		try {
			return this.internalStatement.executeUpdate(sql, columnIndexes);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		
		try {
			return this.internalStatement.executeUpdate(sql, columnNames);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		
		try {
			return this.internalStatement.execute(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		
		try {
			return this.internalStatement.execute(sql, columnIndexes);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		
		try {
			return this.internalStatement.execute(sql, columnNames);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		
		try {
			return this.internalStatement.getResultSetHoldability();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean isClosed() throws SQLException {
		
		try {
			return this.internalStatement.isClosed();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		
		try {
			this.internalStatement.setPoolable(poolable);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public boolean isPoolable() throws SQLException {
		
		try {
			return this.internalStatement.isPoolable();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

}
