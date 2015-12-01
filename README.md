Description
----------------------
This is a project to pratice using OpenGL in Android. The develop framwork is Android Studio 1.3. 
The running target is Android phone with SDK 22 or later.
There are two version in this race game: 2D version and 3D version.<br>

Video
---------
You can watch the game video in this Link:<br>
https://youtu.be/-Fjk2-SHAdY

Loading 3D model in Android
--------------------------------
The most chanllege part is loading 3D model in Android. I used 3D software "LightWave" to Create car and road. The model in LightWave
looks like this:<br>
![](http://debuggingnow.com/wp-content/uploads/2015/12/3dx.jpg)<br>
After finishing model creating, LightWave can export model into obj files, which are some special files that records each vertex information
for OpenGL loading. You can see my obj files locate in res/raw folder. The track_obj is a file to recode race's road such as vertex's cordinate and size
and so on. The track_mtl indicate the texture picture for each triangle. The files camaro2_obj and camaro2_mtl also show the vertexs 
information for 3D car.
Please read this to learn more about obj files. This is the important step to load 3D model in Android:<br>
https://en.wikipedia.org/wiki/Wavefront_.obj_file
I used Google's min3D library to help for loading obj file. <br>This is a piece of loading car model:
```java
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
```
This piece is for updating camaro and scenes:
```java
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
```
You can read the detail source code in ./java/com/roadracerlite/Load3DSFile.java
References
------------------------------
Here are some useful references for my project: <br>
https://code.google.com/p/min3d/ <br>
http://www.rozengain.com/blog/2010/05/19/loading-a-3ds-file-min3d-framework-for-android/ <br>
http://www.ajaybadgujar.com/chapter/creating-2d-car-racing-game-in-android-part-1-setting-up-game-startup/ <br>



