package com.mbis.gcm.crm.log;

public class Log {
	public static void msg( String msg ){
		System.out.print(msg +  "\n");
	}
	public static void e(Exception exp) {
		msg("Error: " + exp.getMessage());		
	}
}
