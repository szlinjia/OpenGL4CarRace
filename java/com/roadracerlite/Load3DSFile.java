package com.lilin.roadracerlite;

import min3d.core.Object3d;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;

public class Load3DSFile extends RendererActivity {
	private final float CAM_RADIUS_X = 20;
	private final float CAM_RADIUS_Y = 15;
	private final float CAM_RADIUS_Z = 30;
	private final float ROTATION_SPEED = 1;
	private Object3dContainer road;
	private float degrees;

	private final float MAX_ROTATION = 360;
	private final float MAX_CAM_X = 6f;
	private Object3dContainer car;
	private Object3dContainer sky;
	private Object3d tireRR;
	private Object3d tireRF;
	private Object3d tireLR;
	private Object3d tireLF;
	private int rotationDirection;
	private float camDirection;

	@Override
	public void initScene() {

		scene.camera().position.x = MAX_CAM_X;
		scene.camera().position.z = 0.1f;
		scene.camera().position.y = 1.5f;

		Light light = new Light();

		light.ambient.setAll((short)64, (short)64, (short)64, (short)255);
		light.position.setAll(3, 3, 3);
		scene.lights().add(light);

		initCar();
		initRoad();

	}
	public void initRoad(){
		scene.lights().add(new Light());

		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.lilin.roadracerlite:raw/track_obj", true);//
		parser.parse();
		IParser skypaser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.lilin.roadracerlite:raw/sky_obj", true);//
		skypaser.parse();

		road = parser.getParsedObject();
		road.scale().y = road.scale().z  = 0.5f;
		road.scale().z = 2.5f;
		road.rotation().y = 90;
		road.position().x = -60f;

		scene.addChild(road);

	}
	public void initCar(){

		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.lilin.roadracerlite:raw/camaro2_obj", true);//
		parser.parse();

		car = parser.getParsedObject();
		scene.addChild(car);

		tireRR = car.getChildByName("tire_rr");
		tireRF = car.getChildByName("tire_rf");
		tireLR = car.getChildByName("tire_lr");
		tireLF = car.getChildByName("tire_lf");

		tireLF.position().x = -.6f;
		tireLF.position().y = 1.11f;
		tireLF.position().z = .3f;

		tireRF.position().x = .6f;
		tireRF.position().y = 1.11f;
		tireRF.position().z = .3f;

		tireRR.position().x = .6f;
		tireRR.position().y = -1.05f;
		tireRR.position().z = .3f;

		tireLR.position().x = -.6f;
		tireLR.position().y = -1.05f;
		tireLR.position().z = .3f;

		car.scale().x = car.scale().y = car.scale().z = .5f;
		car.rotation().x = -90;
		car.rotation().z = 90;


		rotationDirection = 1;
		camDirection = -.08f;
	}
	@Override
	public void updateScene() {
		tireRF.rotation().x += rotationDirection;
		tireLF.rotation().x += rotationDirection;
		tireRR.rotation().x += rotationDirection;
		tireLR.rotation().x += rotationDirection;

		if(Math.abs(tireRF.rotation().x) >= MAX_ROTATION)
			rotationDirection = -rotationDirection;

		car.position().x += camDirection;
		scene.camera().target.x += camDirection;
		scene.camera().position.x += camDirection;
		if(road.position().x-car.position().x >= -30.0f){
			road.position().x = car.position().x-50;
		}
	}
}