package com.mbis.gcm.crm.connect;

import com.mbis.gcm.crm.log.Log;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.Repository;
import com.sap.mw.jco.JCO.Table;

public class DeviceListRFC {

	static CRMConfiguration crm = new CRMConfiguration("200", "aslsapcrm01.aslanoba.com", "gcmuser", "123456", "tr","00");
	
	public static JCO.Client cnt = crm.connect2CRM(); 

	
	public static void main( String[] args){
		Table a = GetList();
	}
	
	public static Table GetList() {
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
		crm.setFunctionName("ZSYB_GET_DEVICE_LIST");
		IFunctionTemplate ft = rep.getFunctionTemplate( crm.getFunctionName().toUpperCase() );
		Function func = ft.getFunction();
		cnt.execute(func);
		Log.msg("Disconnecting from " + crm.getHostname() + "...");
		cnt.disconnect();
		Log.msg("Disconnected from " + crm.getHostname() );
		return 	func.getTableParameterList().getTable("DEVICE");
		}catch (Exception e) {
			Log.msg("Disconnecting from " + crm.getHostname() + "...");
			Log.e(e);
			cnt.disconnect();
			Log.msg("Disconnected from " + crm.getHostname() );
		}
		return null;
	}	
}
