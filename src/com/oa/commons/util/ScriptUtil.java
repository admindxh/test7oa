/**  
 * @Project: jxoa
 * @Title: ScriptUtil.java
 * @Package com.oa.commons.util
 * @date 2013-7-31 下午2:26:48
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 
 * 类名：ScriptUtil
 * 功能：
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-31 下午2:26:48
 *
 */
public class ScriptUtil {
	
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException{
		
		ScriptEngineManager scriptManager = new ScriptEngineManager();
		ScriptEngine engine = scriptManager.getEngineByName("JavaScript");
		
		engine.eval("navigator=new Object();navigator.appName==\"\";");
		
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/hash.js"));
		
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/rsa.js"));
		
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/jsbn.js"));
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/random.js"));
	    engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/aes.js"));
		
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/api.js"));
		
		engine.eval(new FileReader("E:/java/前端/JS加密/cryptico-master/my.js"));
		
		//engine.eval("function do_encrypt(){return 'this is test js in java'}");     
		
		Invocable inv = (Invocable) engine;     
		
		long start=System.currentTimeMillis();
		
		//String value = String.valueOf(inv.invokeFunction("getPublicKey"));  
		
		String value = String.valueOf(inv.invokeFunction("decryptPassword","CevD7OuWRdsdoPDYMUBWCe0gehDDq7+vSzkUhOSUA9RWYQ7EzdKAm+lXJrP4VBdsXCyux0C3pfEdH3Ukv7vJRQ==?wxgoiGY+Jid4djpGsYbfMSDNdswl8rr6yEWyL3UDH/s="));  
		
		System.out.println(value);
		
		
		System.out.println("============"+((System.currentTimeMillis()-start)/1000)+"秒");
		
	}
	
	
}
