package com.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * 音乐类
 */
public class Music{
	
	public static  AudioStream  point ;
	public static  AudioStream  M_BULLET;

	//public static  AudioStream MMonsterGrow;
	{
			try {
				
				point = new AudioStream(new FileInputStream("sound/point.WAV"));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void soundEffect(AudioStream as) {
			AudioPlayer.player.start(as);
		
	}
}
