package com.oa.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


public class T {
	private static String  sessionId = null;
	
	public static void main(String[] args) throws Exception {
	
	
		// 1.创建URL类型的对象
		URL url = new URL(
				"http://192.168.1.18:8088/jtoa/android/instantMessage/addMessage.mo?content=xxx");
		// 2.打开连接
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		// uc.setRequestProperty("Cookie",
		// "JSESSIONID=CA076E1AAB3C0EF10AF4BEAF28565112; Path=/jtoa/; HttpOnly");
		//uc.setRequestMethod("POST");  
		uc.setInstanceFollowRedirects(true);
		//uc.setDoOutput(true); // Triggers POST.
		//uc.setRequestProperty("Accept-Charset", "utf-8");
		//uc.setRequestProperty("Accept", "application/json");//指定接收数据格式
		//uc.setRequestProperty("Content-Type",
		//		"application/x-www-form-urlencoded;charset=utf-8");

		
		uc.setDoOutput(true);// 发送POST请求必须设置允许输出   
		uc.setDoInput(true);
		uc.setUseCaches(false);// 不使用Cache 
		        
       // conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接 
		uc.setRequestProperty("Charset", "UTF-8"); 
	//	uc.setRequestProperty("Content-Length", String.valueOf(data.length)); 
		uc.setRequestProperty("Content-Type",
                          "application/x-www-form-urlencoded");
	//	uc.addRequestProperty(key, value)
		uc.addRequestProperty("Accept","application/json");
		
		//uc.setInstanceFollowRedirects(false);
		
		uc.setRequestMethod("POST");
		
		
		
		// 3.拿到本地
		InputStream is = uc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String content = "";
		String str = "";
		while ((str = br.readLine()) != null) {
			content += str;
		}
		br.close();
		System.out.println(content);
		//String cookie = uc.getHeaderField("set-cookie");
		//System.out.println("" + cookie);
		
	
		//urlUpdatePost("", "http://192.168.1.18:8088/jtoa/android/login/tologin.mo");
		//urlUpdatePost("name=admin&password=123456", "http://192.168.1.18:8088/jtoa/android/login/go.mo");
		//test();
	
	}
	
	public static void test() throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		
		String url = "http://192.168.1.18:8088/jtoa/android/instantMessage/addMessage.mo?content=xxx";
		PostMethod postMethod = new PostMethod(url);
		// 填入各个表单域的值
		NameValuePair[] data = { new NameValuePair("id", "youUserName"),
		new NameValuePair("passwd", "yourPwd") };
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);
		// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
		// 301或者302
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || 
		statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
		    // 从头中取出转向的地址
		    Header locationHeader = postMethod.getResponseHeader("location");
		    String location = null;
		    if (locationHeader != null) {
		     location = locationHeader.getValue();
		     System.out.println("The page was redirected to:" + location);
		    } else {
		     System.err.println("Location field value is null.");
		    }
		    return;
		}
	}
	
	public static  String urlUpdatePost(String params, String surl) throws Exception {
		String returnCode = null;
		try {
			
			byte[] data = params.getBytes();

			java.util.Random r = new java.util.Random();
			String par = "";
			for (int i = 0; i < 10; i++) {
				par += r.nextInt();
			}
			URL url = new URL(surl + "?r=" + par);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if(sessionId!=null){
				conn.setRequestProperty( "Cookie",  sessionId);
			}
			conn.setDoOutput(true);// 发送POST请求必须设置允许输出
			conn.setDoInput(true);
			conn.setUseCaches(false);// 不使用Cache
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			DataOutputStream outStream = new DataOutputStream(
					conn.getOutputStream());
			outStream.write(data);// 以内容实体方式发送请求参数
			outStream.flush();
			outStream.close();
			// 请求成功
			if (conn.getResponseCode() == 200) {
				InputStream iStream = conn.getInputStream();
				returnCode = readStream(iStream);
				
			}
			if(sessionId==null){
				sessionId = conn.getHeaderField("Set-Cookie" );
				System.out.println("sessionId===="+sessionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("=="+returnCode);
		return returnCode;

	}

	// 获取输入流数据
	private static String readStream(InputStream inStream) throws Exception {
		StringBuffer sb = new StringBuffer();
		String readLine;
		String r = null;
		BufferedReader responseReader;
		// 处理响应流，必须与服务器响应流输出的编码一致
		responseReader = new BufferedReader(new InputStreamReader(inStream,
				"UTF-8"));
		while ((readLine = responseReader.readLine()) != null) {
			// sb.append(readLine).append("\n");
			sb.append(readLine);// 不需要加换行
		}
		r = sb.toString();
		responseReader.close();
		return r;
	}

}
