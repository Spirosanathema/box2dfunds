package gr.camtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam, cam2, camera;
	Viewport vp;
	Sprite sprite;
	Vector3 camPoint;
	Vector2 camPos;
	
	final float STEP = 1/60f;
	
	float GAME_WIDTH = 100f;
	float GAME_HEIGHT = 56.25f;
	float lerp;
	float accum;
	
	float aspectRatio;
	
	// LEPR
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("images/backimage.png");
		sprite = new Sprite(img);
		
		aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
		accum = 0;
		
		sprite.setSize(GAME_WIDTH, GAME_HEIGHT);
		
		//cam = new OrthographicCamera(Gdx.graphics.getWidth() * aspectRatio, Gdx.graphics.getHeight() * aspectRatio);
		
		cam = new OrthographicCamera();
		cam2 = new OrthographicCamera(GAME_WIDTH /4, (GAME_WIDTH * aspectRatio) /4);
		cam2.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
		camera = new OrthographicCamera();
		camera = cam2;
		//cam.setToOrtho(false);
		//cam = new OrthographicCamera(GAME_WIDTH, GAME_WIDTH * aspectRatio);
		//cam = new OrthographicCamera(GAME_HEIGHT * aspectRatio, GAME_HEIGHT);
		
		
		//cam.setToOrtho(false);
		//cam.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
		
		//vp = new StretchViewport(GAME_WIDTH * aspectRatio, GAME_HEIGHT * aspectRatio);
		//vp = new FillViewport(GAME_WIDTH * aspectRatio, GAME_HEIGHT * aspectRatio);
		vp = new FillViewport(GAME_WIDTH, GAME_WIDTH * aspectRatio, cam);
		vp.apply();
		//cam.position.set(GAME_WIDTH/2,GAME_HEIGHT/2, 0);
		camPoint = new Vector3();
		camPoint.set(cam2.position.x + cam2.viewportWidth/16, cam2.position.y + cam2.viewportHeight/16, 0);
		lerp = 0.1f;
		
		cam.update();
		cam2.update();
	}
	
	public void update(float dt) {
		//camPoint.set(cam2.position.x + cam2.viewportWidth/2, cam2.position.y + cam2.viewportHeight/2, 0);
		
		cam2.position.x += (camPoint.x - cam2.position.x) * lerp;
		cam2.position.y += (camPoint.y - cam2.position.y) * lerp;
		
		cam.update();
		cam2.update();
		
		System.out.println("camPoint.x = "+camPoint.x+"\ncamPosition.x = "+cam2.position.x+"\n");
	}

	@Override
	public void render () {
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			update(STEP);
		}
		
		handleInput();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		
		sprite.draw(batch);
		batch.end();
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			//cam2.translate(-.5f, 0);
			camPoint.x -= .5f;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//cam2.translate(.5f, 0);
			camPoint.x += .5f;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			//cam2.translate(0, .5f);
			camPoint.y += .5f;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			//cam2.translate(0, -.5f);
			camPoint.y -= .5f;
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			camera = cam2;
			camera.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
			cam2.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
			//camPoint.set(cam2.position.x + cam2.viewportWidth/16, cam2.position.y + cam2.viewportHeight/16, 0);
			camPoint.set(cam2.position.x, cam2.position.y, 0);
			
			//camera.setToOrtho(false, GAME_WIDTH/4, GAME_HEIGHT/4);
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			camera = cam;
		}
		if(Gdx.input.isTouched(0)) {
			camera = cam2;
			camera.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
		vp.update(width,  height);
		cam.position.set(GAME_WIDTH /2, GAME_HEIGHT /2, 0);
		//cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
