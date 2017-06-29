package com.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.ui.GameFrame;
import com.ui.MyPanel;
/*
 * 地面类
 */
public class Ground {

	private final Image G_IMG = new ImageIcon("images/ground.png").getImage();
	
	//绘制地面
	public void draw(Graphics g, MyPanel myPanel) {
		// TODO Auto-generated method stub
		g.drawImage(G_IMG,0,GameFrame.HEIGHT-63,myPanel);
	}

	

}
