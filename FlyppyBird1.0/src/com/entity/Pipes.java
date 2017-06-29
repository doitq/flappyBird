package com.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.GameFrame;
import com.ui.MyPanel;

/*
 * 管道类
 */
public class Pipes implements Runnable{

	//管道图片
	private final Image UP_PIPE_IMG = new ImageIcon("images/pipe1.png").getImage(); 
	private final Image DOWN_PIPE_IMG = new ImageIcon("images/pipe2.png").getImage();
	//管道类型
	public int pipeType=0;
	//管道向上
	public final int PIPE_TYPE_UP = 0;
	//管道向下
	public final int PEPE_TYPE_DOWN = 1;
	
	//管道X坐标
	public int x;
	//管道Y坐标
	public int y;
	
	//管道长度与宽度
	public final int w = UP_PIPE_IMG.getWidth(null);
	public final int h = UP_PIPE_IMG.getHeight(null);
	//速度
	public int speed = 8;
	//管道数量
	public int pipeCount=0;
	//是否死掉
	public boolean isDead = false;
	//是否被通过
	public boolean isThrough = false;
	
	public Pipes(int pipeType,int x,int y){
		this.pipeType = pipeType;
		this.x = x;
		this.y = y;
		
		new Thread(this).start();
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			//循环向左移
			this.x-=speed;	
			//超出界面移除
			if(x<-100){
				this.isDead = true;
				MyPanel.pipes.remove(this);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	//绘制管道
	public void draw(Graphics g, MyPanel myPanel) {
		// TODO Auto-generated method stub
		if(this.pipeType == PIPE_TYPE_UP){
			//绘制向上的管道并进行碰撞检测
			g.drawImage(UP_PIPE_IMG,x,-y,myPanel );
			int bx = MyPanel.bird.x;
			int by = MyPanel.bird.y;
			if(bx > x && bx < x+UP_PIPE_IMG.getWidth(null) && by > -y && by < -y+ UP_PIPE_IMG.getHeight(null)){
//				System.out.println("***000");	
				//碰撞小鸟死亡
				Bird.isLive = false;
			}
//			System.out.println("00");
		}
		if(this.pipeType == PEPE_TYPE_DOWN){
			//绘制向下的管道并进行碰撞检测
			
			g.drawImage(DOWN_PIPE_IMG,x,h+y,myPanel );
			int bx = MyPanel.bird.x;
			int by = MyPanel.bird.y;
			if(bx > x && bx < x+w && by > h+y && by < h+y+h){
//				System.out.println("***111");
				//碰撞则小鸟死亡
				Bird.isLive = false;
			}
		}
	}
	
}
