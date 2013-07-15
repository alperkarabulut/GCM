package com.mbis.gcm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mbis.gcm.crm.connect.DeviceListRFC;
import com.mbis.gcm.crm.connect.NotificationReceivedRFC;
import com.mbis.gcm.crm.connect.RegisterDeviceRFC;
import com.mbis.gcm.data.Devices;
import com.sap.mw.jco.JCO.Structure;
import com.sap.mw.jco.JCO.Table;

@WebService
public class Device {
	private static final SendMessageResult SendMessageResult = null;
	@WebMethod 
	@WebResult( name = "DeviceResult")
	public boolean RegisterDevice(@WebParam( name = "DeviceId") String DeviceId,@WebParam( name = "RegId")  String RegId){
		
		RegisterDeviceRFC a = new RegisterDeviceRFC();
		a.Register(DeviceId, RegId);
		return true;
	}
	@WebMethod
	@WebResult( name = "DeviceListResult")
	public List<Devices> DeviceList(){
		List<Devices> devices = new ArrayList<Devices>();
		DeviceListRFC a = new DeviceListRFC();
		Table deviceList = a.GetList();
		do{
			Devices dev = new Devices(deviceList.getField("DEVICEID").getValue().toString(), deviceList.getField("REGID").getValue().toString());
			devices.add(dev);
		}while( deviceList.nextRow());
		
		return (List<Devices>) devices;
	}
	@WebMethod
	@WebResult( name = "ReceivedNotificationResult")
	public boolean NotificationReceived(	@WebParam( name = "NotifId" ) String NotificationID ){
		NotificationReceivedRFC a = new NotificationReceivedRFC();
		a.NotificationReceived(NotificationID);
		return true;
	}
	@WebMethod
	@WebResult ( name = "SendMessageResult")
	public SendMessageResult SendMessage(	@WebParam( name = "DeviceId" ) String devId, 
											@WebParam( name = "Message" ) String MessageText, 
											@WebParam( name = "NotifId" ) String NotificationID ){
		String apiKey = "AIzaSyCtirv5hRlk1WMCjvzP29DmBhp9UuQGGtc";
		Devices device = new Devices();
		Structure str = device.getRegIdByDeviceId( devId );
		boolean returnBuf = false;
		String msgBuf = "";
		
		String regid = str.getValue("REGID").toString();
		if( !regid.equals("") && !MessageText.equals("") ){
			
			
			Sender sender = new Sender(apiKey);
			Message msg = new Message.Builder()
		    .timeToLive(3)
		    .delayWhileIdle(true)
		    .addData("title", MessageText)
		    .addData("message", MessageText)
		    .addData("notifId", NotificationID)
		    .build();
			String Device2Send = regid;
			
			try {
				Result rslt = sender.send(msg, Device2Send, 5);
				if( rslt.getErrorCodeName() != null ){
					returnBuf = false;
					msgBuf = rslt.getErrorCodeName();
				}else{
					returnBuf = true;
					msgBuf = "\"" + MessageText.toString() + "\"" + " mesajı, " + devId + " cihazına gönderildi.";
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				msgBuf = e.getMessage();
				returnBuf = false;
			}
			
		}
		return new  SendMessageResult(returnBuf, msgBuf);
	}
}
