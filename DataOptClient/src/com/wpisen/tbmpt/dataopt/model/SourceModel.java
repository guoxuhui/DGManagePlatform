package com.wpisen.tbmpt.dataopt.model;

public class SourceModel {

	private String tbmCode;
	private String tbmName;
	private String folderPath;
	private long uploadCycle;
	
	public String getTbmCode() {
		return tbmCode;
	}
	public void setTbmCode(String tbmCode) {
		this.tbmCode = tbmCode;
	}
	public String getTbmName() {
		return tbmName;
	}
	public void setTbmName(String tbmName) {
		this.tbmName = tbmName;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public long getUploadCycle() {
		return uploadCycle;
	}
	public void setUploadCycle(long uploadCycle) {
		this.uploadCycle = uploadCycle;
	}

}
