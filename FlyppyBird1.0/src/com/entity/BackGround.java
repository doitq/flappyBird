package com.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.MyPanel;

/*
 * 背景图片类
 */
public class BackGround implements Runnable{

	private final Image BG_IMG = new ImageIcon("images/bg.png").getImage();
	
	//第一张图片X坐标
	private int firstX;
	//第二张图片X坐标
	private int secX;
	//速度
	private int speed;
	
	public BackGround(){
		
		//界面左边一个图片长度坐标
		this.firstX = -BG_IMG.getWidth(null);
		//界面开始坐标
		this.secX = 0;
		this.speed = 6;
		new Thread(this).start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			firstX += speed;
			secX += speed;
			//两张图同时移动实现背景走动
			if(firstX > 0){
				this.firstX = -BG_IMG.getWidth(null);
				this.secX = 0;
			}
//			if(firstX == 0){
//				secX = -BG_IMG.getWidth(null);
//			}
//			if(firstX == BG_IMG.getWidth(null)){
//				firstX = -BG_IMG.getWidth(null);
//			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//绘制背景图
	public void draw(Graphics g, MyPanel myPanel) {
		// TODO Auto-generated method stub
		g.drawImage(BG_IMG,firstX,0,myPanel);
		g.drawImage(BG_IMG,secX,0,myPanel);
	}

}
