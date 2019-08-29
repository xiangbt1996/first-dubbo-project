package com.xiang.provider.util;

import com.xiang.api.util.DateUtil;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/*生成票ID*/
/*
 * 格式定义如下：
 *      时间    			   	 +    seq     +    instanceid
 * 	    yyMMddHHmmss【12】	   	  [4]             [2]
 */
public class IDGenService {
	private static IDGenService instance = new IDGenService();
	public static IDGenService getInstance(){
		return instance;
	}
	
	private  long lasTime = 0;
	private  AtomicInteger idInMs = new AtomicInteger(0);
	public String createID(){

		try{
			int id = 0;
			Date now = new Date();
			long nowTime = now.getTime();
			if((now.getTime() / 1000) == (lasTime /1000)){
			}else{
				synchronized (this) {
					if((new Date().getTime() /1000) != (lasTime / 1000)){
						idInMs.set(0);
						lasTime = nowTime;
					}
				}
			}
			StringBuffer sBuffer = new StringBuffer();
			//时间戳12位
			sBuffer.append(DateUtil.safeFormatDate("yyMMddHHmmss", now));
			//序列号4位
			sBuffer.append(String.format("%04d", idInMs.incrementAndGet()));
			return sBuffer.toString();
		}catch(Exception ex){
			return null;
		}
	}
}
