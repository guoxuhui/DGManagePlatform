package com.wpisen.utils;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件读取类
 * @author guoxuhui0822
 *
 */
public class ReadXML { 
	private final Logger logger = LoggerFactory.getLogger(ReadXML.class);
	private String conf = null;
	private Element root = null;
	private static ReadXML instance = null;
	
	/**
	 * 构造方法
	 * 
	 */
	private ReadXML(){
		String url = ConfigUtils.getPath() + "/conf/config.xml";
		this.conf = url.toString();
		//用SAX方式读取XML数据
		SAXBuilder sb = new SAXBuilder();
		try{   
			//创建XML文档     
	    		Document doc = sb.build(conf);
	    		//获得根元素
	    		this.root = doc.getRootElement();
	    		logger.info("初始化 config.xml 配置文件成功 "+this.conf);
	    }catch(Exception e){
	    		logger.info("初始化 config.xml 配置文件失败 "+e.getMessage());
	    		e.printStackTrace();
	    }
	}
	
	/**
	 * 获取ReadXML类
	 * @return
	 */
	public static ReadXML getInstance(){
		if(instance == null){
			instance = new ReadXML();
		}
		return instance;
	}
	
	/**
	 * 获取Element
	 * @return
	 */
	public Element getRootElement(){
		return root;
	}
	
	/**
	 * 读取子元
	 * @param child
	 * @return
	 */
	public String getDicChild(String child){
		//用SAX方式读取XML数据
		SAXBuilder sb = new SAXBuilder();
		String text = "";
		try{   
			//创建XML文档     
	    		Document doc = sb.build(conf);
	    		//获得根元素
	    		Element root = doc.getRootElement();
	    		text = root.getChildText(child);
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
		return text;
	}
}
