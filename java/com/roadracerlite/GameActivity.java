package com.lilin.roadracerlite;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;

public class GameActivity extends Activity implements SensorEventListener{
	
	private SensorManager sensorManager;
	
	private GameView gameView;	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {			
			super.onCreate(savedInstanceState);			
			gameView = new GameView(this);			
			setContentView(gameView);	
			
			sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
	        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		@Override
		protected void onResume() {			
			super.onResume();
			gameView.onResume();
		}
		
		@Override
		protected void onPause() {			
			super.onPause();
			gameView.onPause();
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
	        float y = event.getY();
	        int height = Math.round(Global.display.getHeight() * Global.getProportionateHeight(0.25f));
	        int excludedArea = Global.display.getHeight() - height;
	        if(y > excludedArea){
	        	switch(event.getAction()){
	        		case MotionEvent.ACTION_DOWN:
	        			if(x < Global.display.getWidth() / 3){
	        				Global.PLAYER_ACTION = Global.BREAKS_PRESSED;	        				
	        			}else if(x > (Global.display.getWidth() / 3) * 2){
	        				Global.PLAYER_ACTION = Global.ACCELERATOR_PRESSED;
	        			}
	        			break;
	        		case MotionEvent.ACTION_UP:
	        			Global.PLAYER_ACTION = Global.CONTROL_RELEASED;
	        			break;
	        	}
	        }
			return false;
		}

		@Override
		public void onSensorChanged(SensorEvent event) {			
			if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				Global.SENSORE_ACCELEROMETER_X = event.values[0];
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
						
		}		
		
}
