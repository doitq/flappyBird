package com.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ui.MyPanel;

/*
 * 开始界面类
 */
public class Menu implements Runnable{

	//开始界面图
	private static final Image[] IMG_GUID = {
			new ImageIcon("images/guid1.png").getImage(),
			new ImageIcon("images/guid2.png").getImage(),
			new ImageIcon("images/guid3.png").getImage(),
	};
	
	//界面切换状态
	private int menuState=0;
	
	//控制速度
	private int count=0;
	
	public Menu(){
		new Thread(this).start();
	}

	//绘制开始界面方法
	public void draw(Graphics g, MyPanel myPanel){
		g.drawImage(IMG_GUID[menuState], 0, 0, myPanel);
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			count++;
			if(count%4==0){
				menuState++;
			}
			//循环切换
			if(menuState>=3){
				menuState = 0;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
