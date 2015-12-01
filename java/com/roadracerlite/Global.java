package com.lilin.roadracerlite;

import android.content.Context;
import android.util.Log;
import android.view.Display;

public class Global {
	// Constants
	public static final int GAME_THREAD_FPS_SLEEP = (1000/60);
	public static final int CONTROL_RELEASED = 0;
	public static final int ACCELERATOR_PRESSED = 1; 
	public static final int BREAKS_PRESSED = 2;
	
	// Variables
	public static Context context;
	public static int ROAD = R.drawable.road;	
	public static int CAR = R.drawable.player_car;
	public static int ACCELERATOR = R.drawable.accelerator;
	public static int BREAKS = R.drawable.breaks;	
	public static int GAME_SCREEN_WIDTH = 0;
	public static int GAME_SCREEN_HEIGHT = 0;	
	public static int PLAYER_ACTION = 0;
	public static double SENSORE_ACCELEROMETER_X = 0.0; 
	public static Display display;
	public static Thread musicThread;
	
	public static float getProportionateHeight(float width){
		
		float ratio = (float)GAME_SCREEN_WIDTH/GAME_SCREEN_HEIGHT;
		float height = ratio * width;		
		return height;
	}
	
}
