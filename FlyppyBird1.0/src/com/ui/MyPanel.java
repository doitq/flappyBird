package com.ui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.entity.BackGround;
import com.entity.Bird;
import com.entity.Ground;
import com.entity.Menu;
import com.entity.Music;
import com.entity.OverMenu;
import com.entity.Pipes;
import com.entity.PlayerBGM;

/*
 * 窗体类
 */
public class MyPanel extends JPanel implements Runnable {

	//游戏的3种状态
	public static final int GAME_STATE_MEMU = 0;//开始画面
	public static final int GAME_STATE_PLAYING = 1;//游戏界面
	public static final int GAME_STATE_OVER = 2;//结束界面

	//游戏状态
	public static int gameState;

	//游戏进程控制
	public static boolean flag = true;

	//主界面对象
	private Menu menu;

	//背景图对象
	private BackGround bg;

	//小鸟对象
	public static Bird bird;

	//地面对象
	private Ground ground;

	//管道集合
	public static Vector<Pipes> pipes = new Vector<Pipes>();

	//记录管道数量
	public int pipeCount = 0;

	//是否是向上管道
	public boolean isUpPipe = true;

	//记录分数
	public static int score = 0;

	//结束界面
	public static OverMenu om;

	//图片缓冲
	public BufferedImage bfImg;

	//背景BGM
	public PlayerBGM playBGM;


	public MyPanel() {
		
		doInit();
	}

	//初始化，Panel设置，对象实例化
	public void doInit() {
		// TODO Auto-generated method stub
		this.setSize(WIDTH, HEIGHT);
		this.setFocusable(true);
		this.setVisible(true);
		this.addMouseListener(new MyMouseListener());

		this.gameState = 0;

		this.menu = new Menu();
		this.bg = new BackGround();
		this.bird = new Bird(95, 240);
		this.ground = new Ground();
		this.om = new OverMenu();
		this.playBGM = new PlayerBGM();

		//开启线程
		new Thread(this).start();
	}

	//绘制图片
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//根据不同的游戏状态绘制不同界面
		switch (gameState) {
		//绘制开始界面
		case GAME_STATE_MEMU:
			menu.draw(g, this);
			break;
		//绘制游戏进行界面
		case GAME_STATE_PLAYING:
			bg.draw(g, this);
			bird.draw(g, this);
			ground.draw(g, this);
			for (int i = 0; i < pipes.size(); i++) {
				Pipes p = pipes.get(i);
				p.draw(g, this);
			}
			break;
		//绘制结束画面
		case GAME_STATE_OVER:
			om.draw(g, this);
			break;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean over = false;
		if (gameState == 0) {
			//开始背景音乐
			playBGM.playerBGM(playBGM.start);
		}
		while (flag) {
			if (gameState != 0) {
				playBGM.stopMusic();
			}
			//结束背景音乐
			if (!over && bird.isFall()) {
				// playBGM = new PlayerBGM();
				PlayerBGM pp = new PlayerBGM();
				pp.playerBGM(playBGM.end);
//				if(gameState == 2 && bird.isLive){
//					pp.stopMusic();
//				}
				over = true;
			}
			
			//管道设计
			pipeCount++;
			if (pipeCount % 30 == 0) {
				int r = (int) (Math.random() * 100);
				if (this.isUpPipe) {
					pipes.add(new Pipes(0, GameFrame.WIDTH, r));
					// System.out.println("111");
				} else {
					pipes.add(new Pipes(1, GameFrame.WIDTH, r));
				}
				// 画一根向上的管道然后画向下的管道
				isUpPipe = !isUpPipe;

				//管道逻辑处理
				for (int i = 0; i < pipes.size(); i++) {
					// System.out.println(pipes.size());
					Pipes p = pipes.get(i);
//					// 管道左上角X坐标
//					int x1 = p.x;
//					int y1 = 0;
//					int y = p.y;
//					if (p.pipeType == 0) {
//						y1 = -y;
//					} else if (p.pipeType == 1) {
//						y1 = p.h + y;
//					}
//					int x2 = x1 + p.w;
//					int y2 = y1 + p.h;
					// System.out.println(bird.isCollision(x1, y1, x2, y2));
					
					// 碰撞判断
					if (!bird.isLive || bird.isFall()) {
						gameState = GAME_STATE_OVER;
					}
					// 得分判断
					if (bird.isLive && p.isThrough == false && bird.x > p.x + p.w) {
						
						p.isThrough = true;
						if (gameState == 1) {
							score++;
						}
						if(gameState == 1){
							playPoint();
						}
						System.out.println(score);
						continue;
					}

				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}

	}

	//得分音效
	private void playPoint() {
		// TODO Auto-generated method stub
		Music m = new Music();
		m.soundEffect(Music.point);
		//new Music().soundEffect(Music.point);

	}

}
