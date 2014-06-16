package com.vintech.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.util.math.MathUtils;

import android.util.Log;

import com.vintech.base.BaseScene;
import com.vintech.constants.Constants;
import com.vintech.entity.Food;
import com.vintech.entity.Snake;
import com.vintech.exception.SuicideException;
import com.vintech.managers.Direction;
import com.vintech.managers.ResourceManager;
import com.vintech.managers.SceneManager;
import com.vintech.managers.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener, Constants {

	// ------------------------------
	// Variables
	// ------------------------------
	
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	private boolean isRunning=true;
	
	
	private Snake snake;
	private Food food;
	
	// ------------------------------
	// Overrided Methods
	// ------------------------------
	
	@Override
	public void createScene() {
		createBackground();
		createHUD();
		createSetup();
		startGameLoop();
	}
	
	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene( engine );
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD( null );
		camera.setCenter( 400, 240 );
	}
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// Left screen touched: Counter clockwise rotation
		Log.i("Blah", "pressed");
		if(pSceneTouchEvent.isActionDown()) {
			if( pSceneTouchEvent.getX() < SCREEN_WIDTH/2 ) {
				switch( snake.getDir() ) {
					case UP:
						snake.setDir(Direction.LEFT);
						break;
					case DOWN:
						snake.setDir(Direction.RIGHT);
						break;
					case LEFT:
						snake.setDir(Direction.DOWN);
						break;
					case RIGHT:
						snake.setDir(Direction.UP);
						break;
				}
				
			}
			// Right screen touched: clockwise rotation
			else {
				switch( snake.getDir() ) {
					case UP:
						snake.setDir(Direction.RIGHT);
						break;
					case DOWN:
						snake.setDir(Direction.LEFT);
						break;
					case LEFT:
						snake.setDir(Direction.UP);
						break;
					case RIGHT:
						snake.setDir(Direction.DOWN);
						break;
				}
			}
			return true;
		}	
		return false;
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
	
	private void createSetup() {
		setOnSceneTouchListener(this);
		
		// Set snake in the middle
		snake = new Snake(Direction.RIGHT, CELLS_HORIZONTAL / 2, CELLS_VERTICAL / 2, ResourceManager.getInstance().head_region, ResourceManager.getInstance().tail_region, ResourceManager.getInstance().vbom );
		
		// Create the tail
		snake.grow();
		
		attachChild(snake);
		
		// Create food
		food = new Food( 0, 0, ResourceManager.getInstance().food_region, ResourceManager.getInstance().vbom );
		food.setCell( MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
		attachChild(food);	
}
	
	private void startGameLoop() {
		
		registerUpdateHandler( new TimerHandler( .25f, true, new ITimerCallback() {
			@Override
			public void onTimePassed( final TimerHandler pTimerHandler ) {
				if(GameScene.this.isRunning) {
					try {
						GameScene.this.snake.move();
					} 
					catch (final SuicideException e) {
						// GameOver scene starts
						isRunning = false;
						//activity.setCurrentScene(new GameOverScene(score));
					}
					// Get new snake position
					if(snake.getHead().getCX() < 0 || snake.getHead().getCX() >= CELLS_HORIZONTAL || snake.getHead().getCY() < 0 || snake.getHead().getCY() >= CELLS_VERTICAL) {
						// GameOver scene starts
						isRunning = false;
						//activity.mMusic.pause();
						//activity.setCurrentScene(new GameOverScene(score));
					} 
					else if(snake.getHead().sameCell(food)) {
						// If player eats food
						//activity.mEat.play();
						addToScore( 50 );
						scoreText.setText("Score: " + score);
						snake.grow();
						
						food.setCell(MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
						/*
						while( sameAsTail(food) ) {
							food.setCell(MathUtils.random( 2, CELLS_HORIZONTAL - 2 ), MathUtils.random(1, CELLS_VERTICAL - 2) );
						}
						*/
					}
				}
			}
		}));
	}
	
	private void addToScore( int i ) {
		score += i;
		scoreText.setText( "Score: " + score );
	}
	
}
