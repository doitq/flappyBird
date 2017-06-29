package com.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.entity.Bird;

/*
 * 鼠标监听类
 */
public class MyMouseListener extends MouseAdapter{

	public static int upCount=0;
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
		if(Bird.isLive){
			switch(MyPanel.gameState){
			case MyPanel.GAME_STATE_MEMU:
				MyPanel.gameState = MyPanel.GAME_STATE_PLAYING;
				break;
			case MyPanel.GAME_STATE_PLAYING:
				upCount++;
				MyPanel.bird.flyUp(upCount);
				if(upCount>16){
					upCount = 0;
				}
				break;
			case MyPanel.GAME_STATE_OVER:
				Bird.isLive = true;
				int x = e.getX();
				int y = e.getY();
				if(x>=MyPanel.om.btnX&&x<=MyPanel.om.btnX+MyPanel.om.btnW&&y>=MyPanel.om.btnY&&y<=MyPanel.om.btnY+MyPanel.om.btnH){
//					MyPanel.flag = false;
					MyPanel.gameState = 0;
					new GameFrame("FlappyBird");
				}
				break;
			}
		}
		
	}

	
	
}
