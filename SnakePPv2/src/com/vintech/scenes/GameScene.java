package com.vintech.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.vintech.base.BaseScene;
import com.vintech.managers.SceneManager.SceneType;

public class GameScene extends BaseScene {

	// ------------------------------
	// Variables
	// ------------------------------
	
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	
	
	// ------------------------------
	// Overrided Methods
	// ------------------------------
	
	@Override
	public void createScene() {
		createBackground();
		createHUD();
	}
	
	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	// ------------------------------
	// Methods
	// ------------------------------
	
	private void createBackground() {
		setBackground( new Background( Color.BLACK ) );
	}
	
	private void createHUD() {
		gameHUD = new HUD();
		
		scoreText = new Text( 20, 420, resourceManager.font, "Score: 000000000", new TextOptions (HorizontalAlign.LEFT), vbom );
		scoreText.setSkewCenter( 0, 0 );
		scoreText.setText( "Score: 0" );
		gameHUD.attachChild( scoreText );
		
		camera.setHUD( gameHUD );
	}
	
	private void addToScore( int i ) {
		score += i;
		scoreText.setText( "Score: " + score );
	}
	
}
