package com.yaojun.jdbc.vendor;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.yaojun.jdbc.ExceptionSorter;


/**
 * 
 * @author yaojun
 * 
 * these honors belong to jboss, the known error code are copied from Jboss  IRONJACAMAR_1_0_17_FINAL
 * <a href="http://anonsvn.jboss.org/repos/jbossas/projects/jboss-jca/tags/IRONJACAMAR_1_0_17_FINAL/adapters/src/main/java/org/jboss/jca/adapters/jdbc/extensions/mysql/MySQLExceptionSorter.java">IRONJACAMAR_1_0_17_FINAL MySQLExceptionSorter</a>
 * 
 *  but i added dynamic error code and flag part, which are learned from recent project issues
 *
 */
public class MysqlExceptionSorter implements ExceptionSorter{
	
	private Logger logger = Logger.getLogger(MysqlExceptionSorter.class);
	
	/*
	 * the flag of whether we should destroy the connection whenever there's a SQLException
	 */
	private boolean alwaysDestroyFlag;
	
	
	/**
	 * 
	 */
	private Set<Integer> fatalErrorCodeSet;
	
	
	/**
	 * add error code dynamically so that when vendor driver does throw exception as expected we can modify the YDataSource behavior in runtime 
	 * @param errorcode
	 */
	public void addFatalErrorCode(int errorcode){
		
		logger.info("add one error code: " + errorcode);
		fatalErrorCodeSet.add(errorcode);
	}
	
	
	/**
	 * set the flag to indicate whether we should destroy the connection whenever there's a SQLException
	 * [note1]: this method should ONLY be called at the last case; for most cases you should always try to call the method addFatalErrorCode(int errorcode)
	 * [note2]: once set to true, remember to set it back to false later 
	 * @param flag
	 */
	public void setAlwaysDestroyFlag(boolean flag){
		
		logger.info("MysqlExceptionSorter.setAlwaysDestroyFlag: " + flag);
		
		this.alwaysDestroyFlag = flag;
		
	}
	
	public MysqlExceptionSorter() {
		
		init();
		
		logger.info("MysqlExceptionSorter.alwaysDestroyFlag: " + alwaysDestroyFlag);
		for(Integer code: fatalErrorCodeSet){
			logger.info("MysqlExceptionSorter has an fatal error code: " + code);	
		}
		
	}
	
	private void init(){
		
		alwaysDestroyFlag = false;
		
		fatalErrorCodeSet = new HashSet<Integer>();
		
		// Communications Errors
		fatalErrorCodeSet.add(1040); // ER_CON_COUNT_ERROR
		fatalErrorCodeSet.add(1042); // ER_BAD_HOST_ERROR
		fatalErrorCodeSet.add(1043); // ER_HANDSHAKE_ERROR
		fatalErrorCodeSet.add(1047); // ER_UNKNOWN_COM_ERROR
		fatalErrorCodeSet.add(1081); // ER_IPSOCK_ERROR
		fatalErrorCodeSet.add(1129); // ER_HOST_IS_BLOCKED
		fatalErrorCodeSet.add(1130); // ER_HOST_NOT_PRIVILEGED

		// Authentication Errors
		fatalErrorCodeSet.add(1045); // ER_ACCESS_DENIED_ERROR
		
		// Resource errors
		fatalErrorCodeSet.add(1004); // ER_CANT_CREATE_FILE
		fatalErrorCodeSet.add(1005); // ER_CANT_CREATE_TABLE
		fatalErrorCodeSet.add(1015); // ER_CANT_LOCK
		fatalErrorCodeSet.add(1021); // ER_DISK_FULL
		fatalErrorCodeSet.add(1041); // ER_OUT_OF_RESOURCES 
		
		// Out-of-memory errors
		fatalErrorCodeSet.add(1037); // ER_OUTOFMEMORY
		fatalErrorCodeSet.add(1038); // ER_OUT_OF_SORTMEMORY
	}
	
	
	@Override
	public boolean isExceptionFatal(SQLException e) {

		logger.info("get a SQLException: " + e.getErrorCode() + ", " + e.getSQLState());
		logger.info("current alwaysDestroyFlag: " + alwaysDestroyFlag);
		
		if(alwaysDestroyFlag){
			return true;
		}
		
	     if (e.getSQLState() != null)
	      {
	         if (e.getSQLState().startsWith("08"))
	         {
	            return true;
	         }
	      }

	     if(fatalErrorCodeSet.contains(e.getErrorCode())){
	    	 return true;
	     }

		
		return false;
	}

	
	public Set<Integer> getFatalErrorCodeSet() {
		return fatalErrorCodeSet;
	}
	
	public boolean isAlwaysDestroyFlag() {
		return alwaysDestroyFlag;
	}
}
