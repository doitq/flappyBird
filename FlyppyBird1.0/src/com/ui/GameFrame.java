package com.ui;

import javax.swing.JFrame;

/*
 * 窗体类
 */
public class GameFrame extends JFrame{

	//设置窗体长宽
	public static final int WIDTH = 390;
	public static final int HEIGHT = 476;
	
	//设置窗体属性
	public GameFrame(String gamename){
		this.setTitle(gamename);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setContentPane(new MyPanel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	public static void main(String[] args) {
		new GameFrame("FlappyBird");
	}
}
