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
 */
public class OracleExceptionSorter implements ExceptionSorter {

	private Logger logger = Logger.getLogger(this.getClass());
	
	/*
	 * the flag of whether we should destroy the connection whenever there's a SQLException
	 */
	private boolean alwaysDestroyFlag;
	
	
	/**
	 * 
	 */
	private Set<Integer> fatalErrorCodeSet;

	
	public OracleExceptionSorter() {
		
		init();
		logger.info("OracleExceptionSorter.alwaysDestroyFlag: " + alwaysDestroyFlag);
		for(Integer code: fatalErrorCodeSet){
			logger.info("OracleExceptionSorter has an fatal error code: " + code);	
		}

	}
	
	private void init(){
		
		alwaysDestroyFlag = false;
		fatalErrorCodeSet = new HashSet<Integer>();
		
		fatalErrorCodeSet.add(28); //session has been killed
		fatalErrorCodeSet.add(600); //Internal oracle error
		fatalErrorCodeSet.add(1012); //not logged on
		fatalErrorCodeSet.add(1014); //Oracle shutdown in progress
		fatalErrorCodeSet.add(1033); //Oracle initialization or shutdown in progress
		fatalErrorCodeSet.add(1034); //Oracle not available
		fatalErrorCodeSet.add(1035); //ORACLE only available to users with RESTRICTED SESSION privilege
		fatalErrorCodeSet.add(1089); //immediate shutdown in progress - no operations are permitted
		fatalErrorCodeSet.add(1090); //shutdown in progress - connection is not permitted
		fatalErrorCodeSet.add(1092); //ORACLE instance terminated. Disconnection forced
		fatalErrorCodeSet.add(1094); //ALTER DATABASE CLOSE in progress. Connections not permitted
		fatalErrorCodeSet.add(2396); //exceeded maximum idle time, please connect again
		fatalErrorCodeSet.add(3106); //fatal two-task communication protocol error
		fatalErrorCodeSet.add(3111); //break received on communication channel
		fatalErrorCodeSet.add(3113); //end-of-file on communication channel
		fatalErrorCodeSet.add(3114); //not connected to ORACLE
		fatalErrorCodeSet.add(17002); //connection reset
		fatalErrorCodeSet.add(17008); //connection closed
		fatalErrorCodeSet.add(17410); //No more data to read from socket
		fatalErrorCodeSet.add(17447); //OALL8 is in an inconsistent state
		
	}
	

	@Override
	public boolean isExceptionFatal(SQLException e) {
	
		logger.info("OracleExceptionSorter.isExceptionFatal get a SQLException: " + e.getErrorCode() + "," + e.getSQLState());
		final int errorCode = Math.abs(e.getErrorCode());
		
		if(fatalErrorCodeSet.contains(errorCode)){
			return true;
		}
		
		if(errorCode >= 12100 && errorCode <= 12299 ){ // TNS issues
			return true;
		}
		
		final String errorText = (e.getMessage()).toUpperCase();
		
		
		// TODO: below still needs to be confirmed more
       if ((errorCode < 20000 || errorCode >= 21000) &&
              ((errorText.indexOf("SOCKET") > -1)     //for control socket error
            	||	  (errorText.indexOf("套接字") > -1)	  // TODO: need to be confirmed
               || (errorText.indexOf("CONNECTION HAS ALREADY BEEN CLOSED") > -1)
               || (errorText.indexOf("连接已关闭") > -1 )  // TODO, need to be confirmed
               || (errorText.indexOf("BROKEN PIPE") > -1)
               || (errorText.indexOf("管道已结束") > -1)  // TODO: need to be confirmed, this is refered from http://www.myexception.cn/java-web/182678.html
            		  ))
          {
             return true;
          }
	          

		
		return false;
	}

}
