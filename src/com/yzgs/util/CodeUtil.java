package com.yzgs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CodeUtil {
	
	

	   /*
  * 随机字符字典
  */
 private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
     '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
     'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
 
 /*
  * 随机数
  */
 private static Random random = new Random();
 
 /*
  * 获取6位随机数
  */
 private static String getRandomString()
 {
     StringBuffer buffer = new StringBuffer();
     for(int i = 0; i < 4; i++)
     {
         buffer.append(CHARS[random.nextInt(CHARS.length)]);
     }
     return buffer.toString();
 }
 
 /*
  * 获取随机数颜色
  */
 private static Color getRandomColor()
 {
     return new Color(random.nextInt(255),random.nextInt(255),
             random.nextInt(255));
 }
 
 /*
  * 返回某颜色的反色
  */
 private static Color getReverseColor(Color c)
 {
     return new Color(255 - c.getRed(), 255 - c.getGreen(),
             255 - c.getBlue());
 }
 
 public static void createCode(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException 
 {

     response.setContentType("image/jpeg");

     String randomString = getRandomString();
     request.getSession(true).setAttribute("indentifyCode", randomString);

     int width = 68;
     int height = 40;
  
     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
     // 获取图形上下文
     Graphics g = image.getGraphics();

     //生成随机类
     Random random = new Random();

     // 设定背景色
     g.setColor(getRandColor(200,250));
     g.fillRect(0, 0, width, height);

     //设定字体
     g.setFont(new Font("Times New Roman",Font.PLAIN,25));

     //画边框
     //g.setColor(new Color());
     //g.drawRect(0,0,width-1,height-1);
     // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
     g.setColor(getRandColor(160,200));
     for (int i=0;i<155;i++)
     {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
             int xl = random.nextInt(12);
             int yl = random.nextInt(12);
      g.drawLine(x,y,x+xl,y+yl);
     }

     // 取随机产生的认证码(4位数字)
     String sRand="";
     for (int i=0;i<4;i++){
         String rand=String.valueOf(random.nextInt(10));
         sRand+=rand;
         // 将认证码显示到图象中
         request.getSession(true).setAttribute("indentifyCode", sRand);
         g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
         g.drawString(rand,13*i+8,27);
     }
    

     // 转成JPEG格式
     ServletOutputStream out = response.getOutputStream();
     JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
     encoder.encode(image);
     out.flush();
     
     
     


     
     
     
     
 }

	private static Color getRandColor(int fc, int bc) {
		// TODO Auto-generated method stub
		
	        Random random = new Random();
	        if(fc>255) fc=255;
	        if(bc>255) bc=255;
	        int r=fc+random.nextInt(bc-fc);
	        int g=fc+random.nextInt(bc-fc);
	        int b=fc+random.nextInt(bc-fc);
	        return new Color(r,g,b);
	      
	}
	

}
