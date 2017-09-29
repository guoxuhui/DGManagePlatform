package com.wpisen.tbmpt.dataopt.model;

public class TargetModel {

	public final static String U_PATH =  "dataCenter/api/uploadData";
	public final static String U_PARAM = "json";
	private String serverIp;
	private String serverPort;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getServicePath() {
		String path = "http://"+this.serverIp+":"+this.serverPort+"/"+U_PATH;
		return path;
	}
}
