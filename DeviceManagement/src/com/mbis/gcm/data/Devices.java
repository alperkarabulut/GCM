package com.mbis.gcm.data;


import com.mbis.gcm.crm.connect.CRMConfiguration;
import com.mbis.gcm.crm.log.Log;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Client;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.Repository;
import com.sap.mw.jco.JCO.Structure;

public class Devices {
	String DeviceId;
	String RegId;
	public Devices() {

	}
	public Devices(String devId, String regId){
		DeviceId = devId;
		RegId = regId;
	}
	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	public String getRegId() {
		return RegId;
	}
	public void setRegId(String regId) {
		RegId = regId;
	}
	public void getDevice(){
		
	}
	public Structure getRegIdByDeviceId(String dev) {
		CRMConfiguration crm = new CRMConfiguration();
		crm.setFunctionName("ZSYB_GET_DEVICE");
		Client cnt = crm.connect2CRM();
		
		Repository rep = new Repository("MBISRep", cnt);
		IFunctionTemplate ft = rep.getFunctionTemplate( crm.getFunctionName().toUpperCase() );
		Function func = ft.getFunction();
		func.getImportParameterList().setValue( dev ,"DEVICEID");
		cnt.execute(func);
		return func.getExportParameterList().getStructure("DEVICE");		
	}
	
}
