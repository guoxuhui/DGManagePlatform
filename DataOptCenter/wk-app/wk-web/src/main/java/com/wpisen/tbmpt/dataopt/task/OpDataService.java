package com.wpisen.tbmpt.dataopt.task;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpisen.tbmpt.dataopt.model.PlcModel;
import com.wpisen.tbmpt.dataopt.model.TagModel;

/**
 * 处理数据
 * @author xhguo
 *
 */
public class OpDataService implements Runnable{
	private final Logger log = LoggerFactory.getLogger(OpDataService.class);
	public OpDataService() {
		
	}
	
	public void run() {
		PlcModel pm = null;
		while (true) {
			try {
				
				if((pm = OpDataBuffer.getInstance().poll()) != null){
					optData(pm);
				}

				Thread.sleep(50L);
			} catch (Throwable e) {
				log.error("Thread fail -"+e.getMessage());
				log.error(e.getLocalizedMessage());
				e.printStackTrace();
			}

		}
	}
	
	public void optData(PlcModel plc) {
		// 独立线程, 例如计划任务,定时任务的线程.
		Ioc ioc = Mvcs.ctx().getDefaultIoc();
		Dao dao = ioc.get(Dao.class);
		long start = System.currentTimeMillis();
	    Trans.exec(new Atom(){
	        public void run(){
		        	log.debug("开始处理 "+plc.getTags().size() +" 条数据");
		        	for(TagModel tag : plc.getTags()) {
	    				String id = plc.getTbmCode()+"-"+tag.getTagName();
	        			Record record = dao.fetch("tbmpt.pro_plc_real",Cnd.where("id", "=", id));
	        			
	        			String valueTag = tag.getTagValue();
	        			if(valueTag != null && valueTag.contains(".") &&valueTag.length() > 6) {
	        				if((valueTag.indexOf(".")+3) < valueTag.length()) {
	        					valueTag = valueTag.substring(0,valueTag.indexOf(".")+2);	
	        				}
	        			}
	        			
	        			if(record != null ) {
	        				dao.update("tbmpt.pro_plc_real", Chain.make("tagvalue", tag.getTagValue()).add("tagtime", plc.getTime()), 
	        						Cnd.where("id", "=",id));
	        			}else {
	        				dao.execute(Sqls.create("insert into tbmpt.pro_plc_real(id,tbmcode,tagname,ms,tagvalue,tagtime) " + 
	        						"values(@id,@tbmcode,@tagname,@ms,@tagvalue,@tagtime) ")
	        						.setParam("id", id)
	        						.setParam("tbmcode", plc.getTbmCode())
	        						.setParam("tagname", id)
	        						.setParam("ms", tag.getTagUnit())
	        						.setParam("tagvalue",tag.getTagValue())
	        						.setParam("tagtime", plc.getTime()));
	        			}
	        		}
		        	log.debug("处理结束 ");
	        }
	    });
	    long time = System.currentTimeMillis() - start;
	    if(OpDataBuffer.oneTime == 0) {
	    		OpDataBuffer.oneTime = time;
	    }else if(OpDataBuffer.twoTime == 0) {
	    		OpDataBuffer.twoTime = time;
	    }else if(OpDataBuffer.threeTime == 0) {
	    		OpDataBuffer.threeTime = time;
	    }else {
	    		OpDataBuffer.oneTime = OpDataBuffer.twoTime;
	    		OpDataBuffer.twoTime = OpDataBuffer.threeTime;
	    		OpDataBuffer.threeTime = time;
	    }
	}
}
