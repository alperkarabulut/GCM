package com.mbis.gcm.crm.connect;

import com.mbis.gcm.crm.log.Log;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.Repository;

public class NotificationReceivedRFC {
	static CRMConfiguration crm = new CRMConfiguration("200", "aslsapcrm01.aslanoba.com", "gcmuser", "123456", "tr","00");

	public static JCO.Client cnt = crm.connect2CRM();

	public static void NotificationReceived(String notifId) {
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
			
			crm.setFunctionName("ZSYB_NOTIFICATION_RECEIVED");
			
			IFunctionTemplate ft = rep.getFunctionTemplate( crm.getFunctionName().toUpperCase() );
			Function func = ft.getFunction();



			func.getImportParameterList().setValue( notifId ,"I_NOTIFID");
			cnt.execute(func);

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
