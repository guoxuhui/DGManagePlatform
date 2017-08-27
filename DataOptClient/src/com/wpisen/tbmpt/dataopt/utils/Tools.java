package com.wpisen.tbmpt.dataopt.utils;

import java.util.List;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpisen.tbmpt.dataopt.model.SourceModel;
import com.wpisen.tbmpt.dataopt.model.TargetModel;
import com.wpisen.utils.ReadXML;

/**
 * 配置文件读取类
 * @author guoxuhui0822
 *
 */
public class Tools { 
	private final Logger logger = LoggerFactory.getLogger(Tools.class);
	private Element root = null;
	private static Tools instance = null;
	
	/**
	 * 构造方法
	 * 
	 */
	private Tools(){
	    	this.root = ReadXML.getInstance().getRootElement();
	}
	
	/**
	 * 获取Tools
	 * @return
	 */
	public static Tools getInstance(){
		if(instance == null){
			instance = new Tools();
		}
		return instance;
	}
	
	/**
	 * 获取SourceModel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public SourceModel getSourceModel(){
		
		List<Element> lst = root.getChildren("Source");
		Element lstf = lst.get(0);
		SourceModel sm = new SourceModel();
		sm.setTbmCode(lstf.getChild("tbmCode").getText());
		sm.setTbmName(lstf.getChild("tbmName").getText());
		sm.setFolderPath(lstf.getChild("folderPath").getText());
		String l = lstf.getChild("uploadCycle").getText();
		long ll = 5;
		try {
			ll = Long.parseLong(l);
		}catch (NumberFormatException e) {
			logger.error("获取数据上传周期配置失败 "+l);
		}
		sm.setUploadCycle(ll);
		return sm;
	}
	
	/**
	 * 获取TargetModel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TargetModel getTargetModel(){
		
		List<Element> lst = root.getChildren("Target");
		Element lstf = lst.get(0);
		TargetModel sm = new TargetModel();
		sm.setServerIp(lstf.getChild("serverIp").getText());
		sm.setServerPort(lstf.getChild("serverPort").getText());
		return sm;
	}
	
}
