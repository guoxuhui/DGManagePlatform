package com.wpisen.tbmpt.dataopt.model;

import java.util.List;

import com.wpisen.utils.DateUtils;

public class PlcModel {

	private String tbmCode;
	private String tbmName;
	private String time;
	private List<TagModel> tags;
	
	public PlcModel(){
		time = DateUtils.getInstance().getCurYearMonthDayHMS();
	}

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

	public List<TagModel> getTags() {
		return tags;
	}

	public void setTags(List<TagModel> tags) {
		this.tags = tags;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
