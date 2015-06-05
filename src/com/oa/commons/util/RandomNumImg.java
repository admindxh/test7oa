/**  
 * @Project: jxoa
 * @Title: RandomNumImg.java
 * @Package com.oa.commons.util
 * @date 2013-5-22 下午5:42:22
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 类名：RandomNumImg
 * 功能：验证码生成工具
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-5-22 下午5:42:22
 *
 */
public class RandomNumImg {
	private ByteArrayInputStream image;
	private String code;
	/**
	 * 随机数生成器类
	 */
	private Random random = new Random();
	
	public RandomNumImg() {
		init();
	}
	private void init() {
		int width = 95; // 宽度
		int height = 23;// 高度
		int codeCount = 4;// 验证码字符个数
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };
		BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.createGraphics();
		// 创建一个随机数生成器类
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定."Times New Roman"/Fixedsys, Font.PLAIN, 20
		Font font = new Font("宋体", Font.ITALIC, 24);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生50条干扰线，使图象中的认证码不易被其它程序探测到。
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(24);
			int yl = random.nextInt(24);
			g.setColor(getRandomColor());
			g.drawLine(x, y, x + xl, y + yl);
		}	
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			g.setColor(getDeepColor());
			g.drawString(strRand, (i + 1) * (width /(codeCount + 1)),height - 4);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		code=randomCode.toString();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOutput = ImageIO.createImageOutputStream(output);
			ImageIO.write(buffImg, "JPEG", imageOutput);
			imageOutput.close();
			input = new ByteArrayInputStream(output.toByteArray());
			this.image = input;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 生成随机颜色值
	 * @return 随机生成的颜色值
	 */
	private Color getRandomColor() {
		int red, green, blue;
		
		red = random.nextInt(255);
		green = random.nextInt(255);
		blue = random.nextInt(255);
		return new Color(red, green, blue);
	}
	
	/**
	 * 生成随机较深颜色，用于显示验证码
	 * @return 随机生成的颜色值
	 */
	private Color getDeepColor() {
		int red, green, blue;
		red = random.nextInt(130);
		green = random.nextInt(130);
		blue = random.nextInt(130);
		return new Color(red, green, blue);
	}
	public void writeImg(HttpServletResponse response){
		try {
			// 清空response  
		    response.reset();
			response.setContentType("image/jpeg"); 
		
			//response.getWriter().print(image);
			
		   byte[] buffer = new byte[image.available()];  
		    image.read(buffer);  
		    image.close(); 
		    OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
		    toClient.write(buffer);  
		    toClient.flush();  
		    toClient.close();
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	
	public String getCode() {
		return code;
	}
	public ByteArrayInputStream getImage() {
		return this.image;
	}
	
	
}
