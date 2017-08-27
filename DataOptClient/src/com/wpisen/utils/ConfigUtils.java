package com.wpisen.utils;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

/**
 * 跨平台处理配置文件路径工具类
 * @author guoxuhui0822
 *
 */
public class ConfigUtils {
	private static Logger log = Logger.getLogger(ConfigUtils.class);
	public static String getPath() {
		URL url = ConfigUtils.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
			// 截取路径中的jar包名
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		}else{
			filePath = filePath.substring(0, filePath.lastIndexOf("/bin") + 1);
		}
		
		File file = new File(filePath);
		
		// /If this abstract pathname is already absolute, then the pathname
		// string is simply returned as if by the getPath method. If this
		// abstract pathname is the empty abstract pathname then the pathname
		// string of the current user directory, which is named by the system
		// property user.dir, is returned.
		filePath = file.getAbsolutePath();//得到windows下的正确路径
		log.info("get Lacal Path："+filePath);
		return filePath;
	}
	
}