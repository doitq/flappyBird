package com.entity;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
/*
 * 背景音乐类
 */
public class PlayerBGM{
	
	private Player player;
	
	public String start;
	public String end;
	public PlayerBGM() {
		this.start = "sound/start.wav";
		this.end = "sound/end.wav";
	}
	public void playerBGM(final String M) {
		try {
			player = Manager.createPlayer(new File(M).toURI().toURL());
		} catch (NoPlayerException e) {
			e.printStackTrace();
			return;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if(player == null) {
			System.err.println("播放文件为空！");
			return;
		}
		player.addControllerListener(new ControllerListener() {
			
			public void controllerUpdate(ControllerEvent e) {
				if(e instanceof EndOfMediaEvent) {
					if(M.equals(start)){
						playerBGM(start);
					}
				}
				if(e instanceof PrefetchCompleteEvent) {
					player.start();
					return;
				}
				if( e instanceof RealizeCompleteEvent) {
					return;
				}
			}
		});
		player.prefetch();
	}
	public void stopMusic(){
		player.stop();
//		playerBGM(end);
	}
}
