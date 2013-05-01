package com.yaojun.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;


/**
 * 
 * @author yaojun
 *
 */
public class YPreparedStatement extends YStatement implements PreparedStatement{
	
	private PreparedStatement internalstat;
	
	public YPreparedStatement(PreparedStatement stat, YConnection conn) {
		
		super(stat, conn);
		this.internalstat = stat;
		
	}
	


	@Override
	public ResultSet executeQuery(String sql) throws SQLException {

		try {
			return this.internalstat.executeQuery(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e); 
		}
		

	}

	@Override
	public int executeUpdate(String sql) throws SQLException {

		try {
			return this.internalstat.executeUpdate(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void close() throws SQLException {
		
		try {
			this.internalstat.close();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		
		try {
			return this.internalstat.getMaxFieldSize();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		
		try {
			this.setMaxFieldSize(max);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getMaxRows() throws SQLException {
		
		try {
			return this.internalstat.getMaxRows();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		
		try {
			this.internalstat.setMaxRows(max);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		
		try {
			this.internalstat.setEscapeProcessing(enable);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		
		try {
			return this.internalstat.getQueryTimeout();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		
		try {
			this.internalstat.setQueryTimeout(seconds);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void cancel() throws SQLException {
		
		try {
			this.internalstat.cancel();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		
		try {
			return this.internalstat.getWarnings();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void clearWarnings() throws SQLException {
		
		try {
			this.internalstat.clearWarnings();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		
		
		try {
			this.internalstat.setCursorName(name);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		
		try {
			return this.internalstat.execute(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		
		try {
			return this.internalstat.getResultSet();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getUpdateCount() throws SQLException {
		
		try {
			return this.internalstat.getUpdateCount();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		
		try {
			return this.internalstat.getMoreResults();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		
		try {
			this.internalstat.setFetchDirection(direction);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getFetchDirection() throws SQLException {
		
		try {
			return this.internalstat.getFetchDirection();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		
		try {
			this.internalstat.setFetchSize(rows);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public int getFetchSize() throws SQLException {
		
		try {
			return this.internalstat.getFetchSize();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		
		try {
			return this.internalstat.getResultSetConcurrency();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetType() throws SQLException {
		
		try {
			return this.internalstat.getResultSetType();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		
		try {
			this.internalstat.addBatch(sql);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void clearBatch() throws SQLException {
		
		try {
			this.internalstat.clearBatch();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public int[] executeBatch() throws SQLException {
		
		try {
			return this.internalstat.executeBatch();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public boolean getMoreResults(int current) throws SQLException {
		
		
		try {
			return this.internalstat.getMoreResults(current);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		
		try {
			return this.internalstat.getGeneratedKeys();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		
		try {
			return this.internalstat.executeUpdate(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		
		try {
			return this.internalstat.executeUpdate(sql, columnIndexes);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		
		try {
			return this.internalstat.executeUpdate(sql, columnNames);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		
		
		try {
			return this.internalstat.execute(sql, autoGeneratedKeys);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		
		
		try {
			return this.internalstat.execute(sql, columnIndexes);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		
		try {
			return this.internalstat.execute(sql, columnNames);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		
		try {
			return this.internalstat.getResultSetHoldability();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean isClosed() throws SQLException {
		
		try {
			return this.internalstat.isClosed();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		
		try {
			this.internalstat.setPoolable(poolable);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public boolean isPoolable() throws SQLException {
		
		try {
			return this.internalstat.isPoolable();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		try {
			return this.internalstat.unwrap(iface);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		
		try {
			return this.internalstat.isWrapperFor(iface);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		
		try {
			return this.internalstat.executeQuery();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int executeUpdate() throws SQLException {
		
		try {
			return this.internalstat.executeUpdate();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		
		try {
			this.internalstat.setNull(parameterIndex, sqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		
		try {
			this.internalstat.setBoolean(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		
		try {
			this.internalstat.setByte(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		
		try {
			this.internalstat.setShort(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		
		try {
			this.internalstat.setInt(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		
		try {
			this.internalstat.setLong(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		
		try {
			this.internalstat.setFloat(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		
		try {
			this.internalstat.setDouble(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		
		try {
			this.internalstat.setBigDecimal(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		
		try {
			this.internalstat.setString(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		
		try {
			this.internalstat.setBytes(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		
		try {
			this.internalstat.setDate(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		
		try {
			this.internalstat.setTime(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		
		try {
			this.internalstat.setTimestamp(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		
		try {
			this.internalstat.setAsciiStream(parameterIndex, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	
	/**
	 * @deprecated
	 */
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		
		try {
			this.internalstat.setUnicodeStream(parameterIndex, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		
		try {
			this.internalstat.setBinaryStream(parameterIndex, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void clearParameters() throws SQLException {
		
		try {
			this.internalstat.clearParameters();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		
		try {
			this.internalstat.setObject(parameterIndex, x, targetSqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		
		try {
			this.internalstat.setObject(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public boolean execute() throws SQLException {
		
		
		try {
			return this.internalstat.execute();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void addBatch() throws SQLException {
		
		try {
			this.internalstat.addBatch();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		
		try {
			this.internalstat.setCharacterStream(parameterIndex, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		
		try {
			this.internalstat.setRef(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		
		try {
			this.internalstat.setBlob(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		
		try {
			this.internalstat.setClob(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		
		try {
			this.internalstat.setArray(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		
		try {
			return this.internalstat.getMetaData();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalstat.setDate(parameterIndex, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalstat.setTime(parameterIndex, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalstat.setTimestamp(parameterIndex, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName)
			throws SQLException {
		
		try {
			this.internalstat.setNull(parameterIndex, sqlType, typeName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		
		try {
			this.internalstat.setURL(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		
		try {
			return this.internalstat.getParameterMetaData();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		
		try {
			this.internalstat.setRowId(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNString(int parameterIndex, String value)
			throws SQLException {
		
		try {
			this.internalstat.setNString(parameterIndex, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) throws SQLException {
		
		try {
			this.internalstat.setNCharacterStream(parameterIndex, value, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		
		try {
			this.internalstat.setNClob(parameterIndex, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		
		try {
			this.internalstat.setClob(parameterIndex, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length)
			throws SQLException {
		
		try {
			this.internalstat.setBlob(parameterIndex, inputStream, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length)
			throws SQLException {
		
		try {
			this.internalstat.setNClob(parameterIndex, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
			throws SQLException {
		
		try {
			this.internalstat.setSQLXML(parameterIndex, xmlObject);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) throws SQLException {
		
		try {
			this.internalstat.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		
		try {
			this.internalstat.setAsciiStream(parameterIndex, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length)
			throws SQLException {
		
		try {
			this.internalstat.setBinaryStream(parameterIndex, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) throws SQLException {
		
		try {
			this.internalstat.setCharacterStream(parameterIndex, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x)
			throws SQLException {
		
		try {
			this.internalstat.setAsciiStream(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x)
			throws SQLException {
		
		try {
			this.internalstat.setBinaryStream(parameterIndex, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader)
			throws SQLException {
		
		try {
			this.internalstat.setCharacterStream(parameterIndex, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value)
			throws SQLException {
		
		try {
			this.internalstat.setNCharacterStream(parameterIndex, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		
		try {
			this.internalstat.setClob(parameterIndex, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream)
			throws SQLException {
		
		try {
			this.internalstat.setBlob(parameterIndex, inputStream);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		
		try {
			this.internalstat.setNClob(parameterIndex, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

}
