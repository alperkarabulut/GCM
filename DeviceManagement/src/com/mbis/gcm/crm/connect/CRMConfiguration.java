package com.mbis.gcm.crm.connect;

import com.sap.mw.jco.JCO;

public class CRMConfiguration extends JCO {
	private String Hostname;
	private String Client;
	private String Username;
	private String Password;
	private String Language;
	private String SysNr;
	private String FunctionName = "ZSYB_REGISTER_DEVICE";
	private String apiKey = "AIzaSyBl0fkBaeIwyV6F2bhZ-TePZ_XHuKyZois";
	public String getFunctionName() {
		return FunctionName;
	}
	public void setFunctionName(String functionName) {
		FunctionName = functionName;
	}
	public CRMConfiguration(String Client, String Hostname, String Username, String Password, String Language, String SysNr) {		
		this.setClient(Client);
		this.setHostname(Hostname);
		this.setUsername(Username);
		this.setPassword(Password);
		this.setLanguage(Language);
		this.setSysNr(SysNr);
	}
	public CRMConfiguration() {
		this.setClient("200");		
		this.setHostname("aslsapcrm01.aslanoba.com");
		this.setUsername("gcmuser");
		this.setPassword("123456");
		this.setLanguage("tr");
		this.setSysNr("00");
	}
	public String getHostname() {
		return Hostname;
	}
	public String getSysNr() {
		return SysNr;
	}
	public void setSysNr(String sysNr) {
		SysNr = sysNr;
	}
	public void setHostname(String hostname) {
		this.Hostname = hostname;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public Client connect2CRM(){
		
		return CRMConfiguration.createClient(Client, Username, Password, Language, Hostname, SysNr);
	}
	
}
