package com.yaojun.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;


/**
 * 
 * @author yaojun
 *
 */
public class YCallableStatement extends YPreparedStatement implements CallableStatement{

	private CallableStatement internalCallableStatement;
	
	//private YConnection conn;

	public YCallableStatement(CallableStatement stat, YConnection conn, int querytimeout){
		super(stat, conn, querytimeout);
		this.internalCallableStatement = stat;
		
	}
	

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType)
			throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterIndex, sqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale)
			throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterIndex, sqlType, scale);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public boolean wasNull() throws SQLException {
		
		try {
			return this.internalCallableStatement.wasNull();
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getString(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBoolean(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

 
	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getByte(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getShort(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getInt(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		
		
		try {
			return this.internalCallableStatement.getLong(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getFloat(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDouble(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex, int scale)
			throws SQLException {
		
		try {
			return this.internalCallableStatement.getBigDecimal(parameterIndex, scale);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBytes(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDate(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTime(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTimestamp(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getObject(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBigDecimal(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map)
			throws SQLException {
		
		try {
			return this.internalCallableStatement.getObject(parameterIndex, map);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getRef(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBlob(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		
		
		try {
			return this.internalCallableStatement.getClob(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getArray(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDate(parameterIndex, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTime(parameterIndex, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal)
			throws SQLException {
		
		try {
			return this.internalCallableStatement.getTimestamp(parameterIndex, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType,
			String typeName) throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterIndex, sqlType, typeName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType)
			throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterName, sqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType,
			int scale) throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterName, sqlType, scale);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType,
			String typeName) throws SQLException {
		
		try {
			this.internalCallableStatement.registerOutParameter(parameterName, sqlType, typeName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	
	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getURL(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		
		try {
			this.internalCallableStatement.setURL(parameterName, val);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		
		
		try {
			this.internalCallableStatement.setNull(parameterName, sqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		
		try {
			this.internalCallableStatement.setBoolean(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	
	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		
		try {
			this.internalCallableStatement.setByte(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		
		try {
			this.internalCallableStatement.setShort(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		
		try {
			this.internalCallableStatement.setInt(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		
		try {
			this.internalCallableStatement.setLong(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		
		try {
			this.internalCallableStatement.setFloat(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		
		try {
			this.internalCallableStatement.setDouble(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public void setBigDecimal(String parameterName, BigDecimal x)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setBigDecimal(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		
		try {
			this.internalCallableStatement.setString(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		
		try {
			this.internalCallableStatement.setBytes(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		
		try {
			this.internalCallableStatement.setDate(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		
		try {
			this.internalCallableStatement.setTime(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setTimestamp(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setAsciiStream(parameterName, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setBinaryStream(parameterName, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType,
			int scale) throws SQLException {
		
		
		try {
			this.internalCallableStatement.setObject(parameterName, x, targetSqlType, scale);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setObject(parameterName, x, targetSqlType);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		
		try {
			this.internalCallableStatement.setObject(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}
	
	@Override
	public void setCharacterStream(String parameterName, Reader reader,
			int length) throws SQLException {
		
		try {
			this.internalCallableStatement.setCharacterStream(parameterName, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setDate(parameterName, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setTime(parameterName, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setTimestamp(parameterName, x, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setNull(parameterName, sqlType, typeName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getString(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBoolean(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getByte(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getShort(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getInt(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getLong(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getFloat(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDouble(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		
		
		try {
			return this.internalCallableStatement.getBytes(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDate(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTime(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTimestamp(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getObject(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBigDecimal(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map)
			throws SQLException {
		
		try {
			return this.internalCallableStatement.getObject(parameterName, map);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getRef(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getBlob(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getClob(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getArray(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		
		try {
			return this.internalCallableStatement.getDate(parameterName, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		
		try {
			return this.internalCallableStatement.getTime(parameterName, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal)
			throws SQLException {
		
		try {
			return this.internalCallableStatement.getTimestamp(parameterName, cal);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getURL(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getRowId(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getRowId(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		
		try {
			this.internalCallableStatement.setRowId(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setNString(String parameterName, String value)
			throws SQLException {
		
		
		try {
			this.internalCallableStatement.setNString(parameterName, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value,
			long length) throws SQLException {
		
		try {
			this.internalCallableStatement.setNCharacterStream(parameterName, value, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}

	

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		
		try {
			this.internalCallableStatement.setNClob(parameterName, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setClob(String parameterName, Reader reader, long length)
			throws SQLException {
		
		
		try {
			this.internalCallableStatement.setClob(parameterName, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public void setBlob(String parameterName, InputStream inputStream,
			long length) throws SQLException {
		
		try {
			this.internalCallableStatement.setBlob(parameterName, inputStream, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setNClob(String parameterName, Reader reader, long length)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setNClob(parameterName, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getNClob(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getNClob(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setSQLXML(parameterName, xmlObject);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getSQLXML(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {

		try {
			return this.internalCallableStatement.getSQLXML(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public String getNString(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getNString(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public String getNString(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getNString(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		
		return this.internalCallableStatement.getNCharacterStream(parameterIndex);
		
		
	}


	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		
		try {
			return this.internalCallableStatement.getNCharacterStream(parameterName);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		
		try {
			return this.internalCallableStatement.getCharacterStream(parameterIndex);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
		
	}


	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		
		return this.internalCallableStatement.getCharacterStream(parameterName);
	}


	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		
		try {
			this.internalCallableStatement.setBlob(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		
		try {
			this.internalCallableStatement.setClob(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setAsciiStream(parameterName, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setBinaryStream(parameterName, x, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setCharacterStream(String parameterName, Reader reader,
			long length) throws SQLException {
		
		try {
			this.internalCallableStatement.setCharacterStream(parameterName, reader, length);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setAsciiStream(String parameterName, InputStream x)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setAsciiStream(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setBinaryStream(String parameterName, InputStream x)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setBinaryStream(parameterName, x);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setCharacterStream(String parameterName, Reader reader)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setCharacterStream(parameterName, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setNCharacterStream(String parameterName, Reader value)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setNCharacterStream(parameterName, value);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
		
	}


	@Override
	public void setClob(String parameterName, Reader reader)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setClob(parameterName, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setBlob(String parameterName, InputStream inputStream)
			throws SQLException {
		
		
		try {
			this.internalCallableStatement.setBlob(parameterName, inputStream);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


	@Override
	public void setNClob(String parameterName, Reader reader)
			throws SQLException {
		
		try {
			this.internalCallableStatement.setNClob(parameterName, reader);
		} catch (SQLException e) {
			
			throw this.checkedException(e);
		}
	}


}
