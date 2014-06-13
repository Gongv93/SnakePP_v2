package com.vintech.snakepp;

import android.view.KeyEvent;

import com.vintech.constants.Constants;
import com.vintech.managers.ResourceManager;
import com.vintech.managers.SceneManager;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

public class SnakeActivity extends BaseGameActivity implements Constants {

	private ResourceManager resourceManager;
	
	private Camera camera;
	
	@Override
	public Engine onCreateEngine( EngineOptions pEngineOptions ) {
		return new LimitedFPSEngine( pEngineOptions, 60 );
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		// Set up screen
		camera = new Camera( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );
		EngineOptions engineOptions = new EngineOptions( true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy( SCREEN_WIDTH, SCREEN_HEIGHT ), this.camera );
		
		// Enable audio
		engineOptions.getAudioOptions().setNeedsMusic( true ).setNeedsSound( true );
		engineOptions.setWakeLockOptions( WakeLockOptions.SCREEN_ON );
		
		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		
		// Load needed resources
		ResourceManager.prepareManager( mEngine, this, camera, getVertexBufferObjectManager() );
		resourceManager = ResourceManager.getInstance();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
	    {
	            public void onTimePassed(final TimerHandler pTimerHandler) 
	            {
	            	mEngine.unregisterUpdateHandler(pTimerHandler);
	                SceneManager.getInstance().createMenuScene();
	            }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	        System.exit(0);	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}

}
