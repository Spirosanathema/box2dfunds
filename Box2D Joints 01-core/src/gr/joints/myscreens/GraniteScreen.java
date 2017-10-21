package gr.joints.myscreens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import gr.joints.MyGame;
import gr.joints.managers.MyScreenManager;

public abstract class GraniteScreen {
	private SpriteBatch sb;
	private MyGame game;
	private OrthographicCamera cam, b2dcam;
	private World world;
	private Box2DDebugRenderer renderer;
	
	//........................... End Of Fields ..................................
	
	public GraniteScreen(MyScreenManager msm) {
		this.game = msm.getGame();		
		this.sb = game.getSpriteBatch();
		this.cam = game.getCam();
		this.b2dcam = game.getb2dCam();
		this.world = game.getWorld();
		this.renderer = game.getBox2DDebugRenderer();
	}
	
	//============================== Getters - Setters ==================================
	public SpriteBatch getSb() { return sb; }
	public OrthographicCamera getCam() { return cam; }
	public OrthographicCamera getb2dCam() { return b2dcam; }
	public World getWorld() { return world; }
	public Box2DDebugRenderer gerBox2DDebugRenderer() { return renderer; }
	//................................... Abstract Methods ........................
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	public abstract void resize(int width, int height);
	
	
}//END