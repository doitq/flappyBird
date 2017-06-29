package com.entity;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.GameFrame;
import com.ui.MyPanel;

/*
 * 结束界面类
 */
public class OverMenu implements Runnable{

	private final Image OVER_IMG = new ImageIcon("images/gameover.png").getImage();
	private final Image OVER_BG_IMG = new ImageIcon("images/bg.png").getImage();
	private final Image SCORE_IMG = new ImageIcon("images/scoreboard.png").getImage();
	private final Image RANK_IMG = new ImageIcon("images/rank.png").getImage();
	private final Image REPLAY_IMG = new ImageIcon("images/replay.png").getImage();
	private final Image MEDAL0_IMG = new ImageIcon("images/medal0.png").getImage();
	private final Image MEDAL1_IMG = new ImageIcon("images/medal1.png").getImage();
	private final Image MEDAL2_IMG = new ImageIcon("images/medal2.png").getImage();
	
	//重新游戏按钮横坐标
	public int btnX;
	//重新游戏按钮纵坐标
	public int btnY;
	//重新游戏按钮图片长度
	public int btnW;
	//重新游戏按钮图片宽度
	public int btnH;
	//是否重新游戏
	public boolean isReplay = false;
	
	public OverMenu(){
		this.btnX = GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 +130;
        this.btnY = GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2+120;
        this.btnW = REPLAY_IMG.getWidth(null);
        this.btnH = REPLAY_IMG.getHeight(null);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	//
	public void draw(Graphics g, MyPanel myPanel) {
		// TODO Auto-generated method stub
		g.drawImage(OVER_BG_IMG, 0, 0, myPanel);
		g.drawImage(OVER_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2, GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2-100, myPanel);
		g.drawImage(SCORE_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 -25 , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2-40, myPanel);
		g.drawImage(RANK_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 -25 ,GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2+120, myPanel);
		g.drawImage(REPLAY_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 +130 , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2+120, myPanel);
		
		//绘制分数
		String scoreString = "";
		scoreString = String.valueOf(MyPanel.score);
		g.setFont(new Font("宋体",Font.BOLD,40));
		g.drawString(scoreString, GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 + 210 , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2 + 30 );
		
		//奖牌绘制
		if(MyPanel.score<10){
			g.drawImage(MEDAL2_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 +10  , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2 + 10 , myPanel);
		}
		if(MyPanel.score>=10&&MyPanel.score<30){
			g.drawImage(MEDAL1_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 +10  , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2 + 10,myPanel);
		}
		if(MyPanel.score>=30){
			g.drawImage(MEDAL0_IMG,GameFrame.WIDTH/2-OVER_IMG.getWidth(null)/2 +10  , GameFrame.HEIGHT/2-OVER_IMG.getHeight(null)/2 + 10 , myPanel);
		}
		


		
	}

}
