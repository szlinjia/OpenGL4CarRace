package com.lilin.roadracerlite;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView {

	private GameRenderer renderer;
	
	public GameView(Context context) {
		super(context);
		
		renderer = new GameRenderer();
		super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
		this.setRenderer(renderer);
	}

}
