/**  
 * @Project: jxoa
 * @Title: MasUtil.java
 * @Package com.oa.commons.util
 * @date 2013-6-24 下午03:22:24
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.util.HashMap;
import java.util.Map;

import com.huawei.eie.api.sm.DBSMProxy;
import com.huawei.eie.api.sm.SmSendBean;

/**
 * 
 * 类名：MasUtil
 * 功能：
 * 详细：
 * 作者：zcl
 * 版本：1.0
 * 日期：2013-6-24 下午03:22:24
 *
 */
public class MasUtil {
	
	 @SuppressWarnings("unchecked")
	public static void send(String[] number, String content) throws Exception
	  {
	    DBSMProxy smproxy = new DBSMProxy();
	    Map paras = new HashMap();
	    paras
	      .put("url", 
	      "jdbc:microsoft:sqlserver://218.201.155.100:1433;DatabaseName=db_customsms");
	    paras.put("user", "CustomSMS");
	    paras.put("password", "SqlMsde@InfoxEie2000");
	    smproxy.initConn(paras);
	    smproxy.login("admin", "dwndm");
	    SmSendBean bean = new SmSendBean();

	    bean.setSmDestAddrs(number);
	    bean.setSmMsgContent(content);
	    bean.setSmOrgAddr("106573160039");
	    bean.setTaskName("wztest");
	    bean.setSmServiceId("MSD1210101");

	    smproxy.sendSm(bean);
	    smproxy.destroy();
	  }

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
      
		String[] phones = {"12","1"};
		String content ="testmas";
		try {
			MasUtil.send(phones, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
