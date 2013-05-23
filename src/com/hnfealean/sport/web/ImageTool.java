package com.hnfealean.sport.web;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;





public class ImageTool {

public static void main(String[] args){
	changeImge(new File("C:\\08a1d44716960a058794739a.jpg"),200, 300,"C:\\pp\\s\\s\\s\\a.jpg");
	//changeImge(new File("C:\\08a1d44716960a058794739a.jpg"),200, 300,new File("C:\\s\\s\\s\\s\\a.jpg"));
	//changeImge(new File("C:\\08a1d44716960a058794739a.jpg"), 300,false,300,false);
	//changeImge(new File("C:\\08a1d44716960a058794739a.jpg"),false,200, 500);
	//changeImge(new File("C:\\08a1d44716960a058794739a.jpg"), 50);
	//changeImge(new File("C:\\08a1d44716960a058794739a.jpg"), 50, 50);
}
public static void changeImge(File img,int width,int height,String outputPath) {
	changeImge(img,width, height,new File(outputPath));
}
public static void changeImge(File img,int width,int height,File output) {
	if(width==0)width=50;
	if(height==0)height=50;
	BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	Graphics canVas = bufferedImage.getGraphics();
	canVas.setColor(Color.WHITE);
	canVas.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	
	Image image;
	try {
		image = ImageIO.read(img);
	} catch (IOException e) {
		return;
	}
	int srcH = image.getHeight(null);
	int srcW = image.getWidth(null);
	if(width>srcW)	width=srcW;
	if(height>srcH)	height=srcH;
	double iniScale = ((double)srcW)/srcH;
	double newScale = ((double)width)/height;
	if(newScale<iniScale){
		height = (int) (width/iniScale);
	}else if(newScale>iniScale){
		width=(int) (height*iniScale);
	}
	canVas.drawImage(

			image.getScaledInstance(width, height,Image.SCALE_SMOOTH),(bufferedImage.getWidth()-width)/2,

			(bufferedImage.getHeight()-height)/2, null);
			
			System.out.println("width: "+bufferedImage.getWidth()+"\theight:"+bufferedImage.getHeight());
			FileOutputStream fos;
			try {
				String path =output.getAbsolutePath();
				File out = new File(path);
				
				out.getParentFile().mkdirs();
				
			fos = new FileOutputStream(out);
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

			encoder.encode(bufferedImage);

			fos.close();
			} catch (Exception e) {
				return;
				
			}
}
/*public static void changeImge(File img,boolean adapt,int width,int height) {
	BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	Graphics canVas = bufferedImage.getGraphics();
	canVas.setColor(Color.WHITE);
	canVas.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	
	Image image;
	try {
		image = ImageIO.read(img);
	} catch (IOException e) {
		return;
	}
	int srcH = image.getHeight(null);
	int srcW = image.getWidth(null);
	if(width>srcW)	width=srcW;
	if(height>srcH)	height=srcH;
	double iniScale = ((double)srcW)/srcH;
	double newScale = ((double)width)/height;
	if(newScale<iniScale){
		height = (int) (width/iniScale);
	}else if(newScale>iniScale){
		width=(int) (height*iniScale);
	}
	canVas.drawImage(

			image.getScaledInstance(width, height,Image.SCALE_SMOOTH),(bufferedImage.getWidth()-width)/2,

			(bufferedImage.getHeight()-height)/2, null);
			
			System.out.println("width: "+bufferedImage.getWidth()+"\theight:"+bufferedImage.getHeight());
			FileOutputStream fos;
			try {
				String path ="C:\\a\\b\\c\\s.jpg";
				File out = new File(path);
				
				out.getParentFile().mkdirs();
				
			fos = new FileOutputStream(out);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

			encoder.encode(bufferedImage);

			fos.close();
			} catch (Exception e) {
				return;
				
			}
	
}

public static void changeImge(File img,int width,int height,boolean adapt) {
	Image image;
	try {
		image = ImageIO.read(img);
	} catch (IOException e) {
		return;
	}
	int srcH = image.getHeight(null);
	int srcW = image.getWidth(null);
	if(width>srcW)	width=srcW;
	if(height>srcH)	height=srcH;
	double iniSize = ((double)srcW)/srcH;
	double size = ((double)width)/height;
	if(size<iniSize){//新图的宽度不够
		width = height*srcW/srcH;
	}
	if(size>iniSize){//新图的高度不够
		height = width*srcH/srcW;
	}
	
	System.out.println("width: "+width+"\theight:"+height+"\t"+img.getAbsolutePath());
	BufferedImage bufferedImage = new BufferedImage( width,height,

	BufferedImage.TYPE_3BYTE_BGR);

	bufferedImage.getGraphics().drawImage(

	image.getScaledInstance(width, height,Image.SCALE_SMOOTH), 0,

	0, width, height, null);
	
	System.out.println("width: "+bufferedImage.getWidth()+"\theight:"+bufferedImage.getHeight());
	FileOutputStream fos;
	try {
	fos = new FileOutputStream("C:\\08.jpg");
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

	encoder.encode(bufferedImage);

	fos.close();
	} catch (Exception e) {
		return;
		
	}
	
}
public static void changeImge(File img,int width,boolean userWidth, int height,boolean userHeight) {
	Image image;
	try {
		image = ImageIO.read(img);
	} catch (IOException e) {
		return;
	}
	int srcH = image.getHeight(null);
	int srcW = image.getWidth(null);
	if(width>srcW)	width=srcW;
	//height= width*srcH/srcW;
	if(height>srcH)height=srcH;
	System.out.println("width: "+width+"\theight:"+height);
	BufferedImage bufferedImage = new BufferedImage( 400,400,

	BufferedImage.TYPE_3BYTE_BGR);

	Graphics g = bufferedImage.getGraphics();
	g.setColor(Color.WHITE);
	g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	
	g.drawImage(

	image.getScaledInstance(width, height,Image.SCALE_SMOOTH), 0,

	50,  null);
	
		System.out.println("width: "+bufferedImage.getWidth()+"\theight:"+bufferedImage.getHeight());
	FileOutputStream fos;
	try {
	fos = new FileOutputStream("C:\\08a074.jpg");
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

	encoder.encode(bufferedImage);

	fos.close();
	} catch (Exception e) {
		return;
		
	}
}
public static void changeImge(File img, int height) {
	Image image;
	try {
		image = ImageIO.read(img);
	} catch (IOException e) {
		return;
	}
	int srcH = image.getHeight(null);
	int srcW = image.getWidth(null);
	if(height>srcH)	height=srcH;
	int width = height*srcW/srcH;
	System.out.println("width: "+width+"\theight:"+height);
	BufferedImage bufferedImage = new BufferedImage( width,height,

	BufferedImage.TYPE_3BYTE_BGR);

	bufferedImage.getGraphics().drawImage(

	image.getScaledInstance(width, height,Image.SCALE_SMOOTH), 0,

	0, width, height,Color.white, null);
	
		System.out.println("width: "+bufferedImage.getWidth()+"\theight:"+bufferedImage.getHeight());
	FileOutputStream fos;
	try {
	fos = new FileOutputStream("C:\\08a1d44716960a058794.jpg");
	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

	encoder.encode(bufferedImage);

	fos.close();
	} catch (Exception e) {
		return;
		
	}
}
public static void changeImge(File img, int width, int height) {

try {

Image image = ImageIO.read(img);

//图片尺寸的大小处理，如果长宽都小于规定大小，则返回，如果有一个大于规定大小，则等比例缩放

int srcH = image.getHeight(null);

int srcW = image.getWidth(null);

if (srcH <= height && srcW <= width) {

return;

}

int tmpH = width;

int tmpW = height;

//在长度和宽度都做了限制，不能超过设定值

while (srcH > height || srcW > width) {

if(srcW > width) {

tmpH = srcH * width / srcW;

srcH = tmpH;

srcW=width;

}

if(srcH > height) {

tmpW = srcW * height / srcH;

srcW = tmpW;

srcH=height;

}

}

BufferedImage bufferedImage = new BufferedImage(srcW, srcH,

BufferedImage.TYPE_3BYTE_BGR);

bufferedImage.getGraphics().drawImage(

image.getScaledInstance(srcW, srcH, Image.SCALE_SMOOTH), 0,

0, srcW, srcH, null);

FileOutputStream fos = new FileOutputStream(img);

JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);

encoder.encode(bufferedImage);

fos.close();

 System.out.println("转换成功...");

} catch (IOException e) {

e.printStackTrace();

throw new IllegalStateException("图片转换出错！", e);

}

}*/
}