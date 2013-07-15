package com.mbis.gcm.crm.connect;

import com.mbis.gcm.crm.log.Log;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.Repository;

public class RegisterDeviceRFC {

	static CRMConfiguration crm = new CRMConfiguration("200", "aslsapcrm01.aslanoba.com", "gcmuser", "123456", "tr","00");
	
	public static JCO.Client cnt = crm.connect2CRM(); 

	
	public static void main( String[] args){
		Register("123", "789");
	}
	
	public static void Register(String deviceid, String regid) {
		// TODO Auto-generated method stub
		Log.msg("Connecting to " + crm.getHostname() + "...");
		
		
		try{
			cnt.connect();
			Log.msg("Connected to " + crm.getHostname() );
		}catch( Exception exp ){
			Log.e(exp);
		}
		try{
		Log.msg(cnt.getAttributes().toString());
		
		Repository rep = new Repository("MBISRep", cnt);
		
		IFunctionTemplate ft = rep.getFunctionTemplate( crm.getFunctionName().toUpperCase() );
		Function func = ft.getFunction();
		
		
		
		func.getImportParameterList().setValue( regid ,"REGID");
		func.getImportParameterList().setValue( deviceid ,"DEVICEID");
		cnt.execute(func);
		
		String msg   = func.getExportParameterList().getValue("E_MSG").toString();
		Log.msg("E_MSG: " + msg);
		
		String subrc = func.getExportParameterList().getValue("E_SUBRC").toString();
		Log.msg("E_SUBRC: " + subrc);
		
		Log.msg("Disconnecting from " + crm.getHostname() + "...");
		cnt.disconnect();
		Log.msg("Disconnected from " + crm.getHostname() );
		}catch (Exception e) {
			Log.msg("Disconnecting from " + crm.getHostname() + "...");
			Log.e(e);
			cnt.disconnect();
			Log.msg("Disconnected from " + crm.getHostname() );
		}
	}	
}
