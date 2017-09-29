
package com.wpisen.tbmpt.dataopt.task;

import java.util.LinkedList;
import java.util.Queue;

import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpisen.tbmpt.dataopt.model.PlcModel;

/**
 * @author xhgui
 * @version 1.0
 * @since 1.0
 */

public class OpDataBuffer {
	
	private final Logger logger = LoggerFactory.getLogger(OpDataBuffer.class);
	//执行线程
	Thread t = null;
	//执行Dao
	Dao dao = null;
	//记录链表的长度
	public static int queueLength = 0;
	//记录舍弃的长度
	public static int abortLength = 0;
	//上次执行时间毫秒
	public static long oneTime = 0;
	//上次执行时间毫秒
	public static long twoTime = 0;
	//上次执行时间毫秒
	public static long threeTime = 0;
	//缓存数据
	private Queue<PlcModel> queue = new LinkedList<PlcModel>();
	
	private static OpDataBuffer instance = new OpDataBuffer();
	private OpDataBuffer() {}
	public static OpDataBuffer getInstance(){
		return instance;
	}
	
	//写入出栈
	public PlcModel poll() {
		PlcModel msg = null;
		synchronized (this.queue) {
			msg = ((LinkedList<PlcModel>)this.queue).poll();
			if (msg != null)
				queueLength -= 1;
		}
		return msg;
	}
	
	//写入入栈
	public void offer(PlcModel msg){
		try {
			if (msg != null) {
				if(t==null) {
					t = new Thread(new OpDataService());
					t.setName("OptGPushService");
					t.start();
				}
				synchronized (this.queue) {
					if(queueLength > 100){
						this.queue.clear();
						abortLength = abortLength + queueLength;
						queueLength = 0;
					}
					if(this.queue.offer(msg))
					queueLength += 1;
				}
			}
		} catch (Exception e) {
			logger.error("Add GPushBuffer Error.", e);
		}
	}
	
}

