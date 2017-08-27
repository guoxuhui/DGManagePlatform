package com.wpisen.tbmpt.dataopt.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.http.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpisen.tbmpt.dataopt.model.PlcModel;
import com.wpisen.tbmpt.dataopt.model.SourceModel;
import com.wpisen.tbmpt.dataopt.model.TagModel;
import com.wpisen.tbmpt.dataopt.model.TargetModel;
import com.wpisen.tbmpt.dataopt.utils.Tools;
import com.wpisen.utils.FileUtils;

import net.sf.json.JSONObject;

public class OptMain {
	private final Logger logger = LoggerFactory.getLogger(OptMain.class);
	//循环采集数据周期
	private long sleepTime = 5 * 1000;
	private SourceModel source = null;
	private TargetModel target = null;
	
	/**
	 * 开始执行
	 */
	public void optRun(){
		//1、初始化配置文件
		source = Tools.getInstance().getSourceModel();
		target = Tools.getInstance().getTargetModel();
		sleepTime = source.getUploadCycle() * 1000;
		while(true) {
			try {
				long start = System.currentTimeMillis();
				//2、获取最近一个文件
				File file = FileUtils.getLastModifiedFile(source.getFolderPath());
				if(file == null) {
					logger.error("读取文件getLastModifiedFile为空异常");
					continue;
				}
				//3、获取第一行、第二行、倒数第二行数据
				String row = FileUtils.readFristLine(file,"GBK");
				String row2 = FileUtils.readFrist2Line(file,"GBK");
				String rowLast2 = FileUtils.readLast2Line(file,"GBK");
				if(row == null || row.length() == 0 ||
						row2 == null || row2.length() == 0 ||
						rowLast2 == null || rowLast2.length() == 0) {
					logger.error("读取文件readLine为空异常");
					continue;
				}
				String[] tagNames = row.split(",");
				String[] tagUnits = row2.split(",");
				String[] tagValues = rowLast2.split(",");
				if(tagNames.length == 0 || tagNames.length != tagUnits.length || tagUnits.length != tagValues.length) {
					logger.error("tag标题、单位、数值数量不一致异常");
					continue;
				}
				//4、拼装PlcModel
				PlcModel plc = new PlcModel();
				plc.setTbmCode(source.getTbmCode());
				plc.setTbmName(source.getTbmName());
				List<TagModel> list = new ArrayList<TagModel>();
				for(int i = 0;i<tagNames.length;i++) {
					TagModel tag = new TagModel();
					tag.setTagName(tagNames[i]);
					tag.setTagUnit(tagUnits[i]);
					tag.setTagValue(tagValues[i]);
					list.add(tag);
				}
				plc.setTags(list);
				
				//5、发送数据
				Map<String, Object> parms = new HashMap<String, Object>();
				String json = JSONObject.fromObject(plc).toString();
				parms.put(TargetModel.U_PARAM,json);
				try {
					String response = Http.post(target.getServicePath(),parms,5 * 1000); 
					logger.info("数据上传成功:" + response);
				}catch (Exception e) {
					logger.error("数据上传异常:" + e.getMessage());
				}
				
				//6、计算处理时间
				long delt = System.currentTimeMillis() - start;
				logger.info("数据处理时间:" + delt);
				
				Thread.sleep(sleepTime);
			}catch (IOException e) {
				logger.error("读取文件异常 "+e.getMessage());
			}catch (InterruptedException e) {
				logger.error("线程运行异常 "+e.getMessage());
			}
		}
	}
}