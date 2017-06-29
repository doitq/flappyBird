package com.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.GameFrame;
import com.ui.MyPanel;

/*
 * 小鸟类
 */
public class Bird implements Runnable{

	private final Image[] BIRD_IMG = {
			new ImageIcon("images/bird1.png").getImage(),
			new ImageIcon("images/bird2.png").getImage(),
			new ImageIcon("images/bird3.png").getImage()		
	};
	
	//小鸟X坐标
	public int x;
	//小鸟Y坐标
	public int y;
	//控制图片速度
	public int count = 0;
	//小鸟状态控制
	public int birdState = 0;
	//速度
	public int speed;
	//是否存活
	public static boolean isLive = true;
	
	public Bird(int x,int y){
		this.x = x;
		this.y = y;
		this.speed = 7;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(MyPanel.gameState == 1){
				y+=speed;
			}
			count++;
			if(count%4==0){
				birdState++;
			}
			if(birdState>=3){
				birdState = 0;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//绘制小鸟图片
	public void draw(Graphics g, MyPanel myPanel) {
		// TODO Auto-generated method stub
		g.drawImage(BIRD_IMG[birdState],this.x, this.y, myPanel);
	}
	
	//是否落地
	public boolean isFall(){
		int h = this.BIRD_IMG[0].getHeight(null);
		if(this.y+h>GameFrame.HEIGHT-60){
			return  true;
		}
		return false;
	}

	//飞行控制
	public void flyUp(int upCount) {
		//点击次数越高飞行幅度越小
		if(upCount <= 4){
			y -= 50;
		}
		if(upCount>4 && upCount<=8){
			y -= 42;
		}
		if(upCount>8 && upCount<=12){
			y -= 34;
		}
		if(upCount>12 && upCount<=16){
			y -= 26;
		}
		if(y<=0){
			y = 0;
		}
	}
	
	//是否碰撞管道
	public boolean isCollision(int x1,int y1,int x2,int y2){
		int h = BIRD_IMG[0].getHeight(null);
		int w = BIRD_IMG[0].getWidth(null);
		System.out.println(x1+" " +y1+" " +x2+" " +y2);
		System.out.println(this.x + " " + this.y);
		if(this.y+h<y1){
			return false;
		}
		if(this.y>y2){
			return false;
		}
		if(this.x+w<x1){
			return false;
		}
		if(this.x>x2){
			return false;
		}	
		return true;
		
	}

}
