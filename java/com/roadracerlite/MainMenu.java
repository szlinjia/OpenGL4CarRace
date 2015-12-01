package com.lilin.roadracerlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);		
		
		Global.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		Global.musicThread = new Thread(){
			@Override
			public void run() {
				Intent musicIntent = new Intent(getApplicationContext(), BGMusic.class);
				startService(musicIntent);
			}
		};
		
		Global.musicThread.start();
		
		ImageButton btn_startrace = (ImageButton) findViewById(R.id.btn_startrace);
		btn_startrace.getBackground().setAlpha(0);
		
		Global.context = getApplicationContext();
		
		btn_startrace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/* Start Game */
				//Intent intent = new Intent(MainMenu.this, ExampleRotatingPlanets.class);
				//Intent intent = new Intent(MainMenu.this, ExampleLoadObjFileMultiple.class);
				Intent intent = new Intent(MainMenu.this, Load3DSFile.class);
				MainMenu.this.startActivity(intent);
			}
		});

		Button btn_2d = (Button)findViewById(R.id.btn_2d);
		btn_2d.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenu.this, GameActivity.class);
				MainMenu.this.startActivity(intent);
			}
		});
	}
}
