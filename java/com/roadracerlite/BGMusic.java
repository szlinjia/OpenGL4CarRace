package com.lilin.roadracerlite;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BGMusic extends Service {
	public static MediaPlayer player;	
	private static Context context; 
	
	
	@Override
	public void onCreate() {		
		super.onCreate();
		context = this;		
		player = MediaPlayer.create(context, R.raw.bgmusic);
		player.setLooping(true);
		player.setVolume(100, 100);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try{
			player.start();
		}catch (Exception e){
			player.stop();
		}
		return 1;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {		
		super.onStart(intent, startId);		
	}
	
	@Override
	public void onDestroy() {		
		player.stop();		
		player.release();
	}
	
	public void onPause(){
		player.stop();
	}
	
	public void onStop(){
		player.stop();
	}
	
	@Override
	public IBinder onBind(Intent intent) {		
		return null;
	}

}
