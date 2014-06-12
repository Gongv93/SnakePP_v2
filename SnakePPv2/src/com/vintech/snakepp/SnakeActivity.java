package com.vintech.snakepp;

import com.vintech.constants.Constants;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

public class SnakeActivity extends BaseGameActivity implements Constants {

	private Camera camera;
	
	@Override
	public Engine onCreateEngine( EngineOptions pEngineOptions ) {
		return new LimitedFPSEngine( pEngineOptions, 60 );
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new Camera( 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT );
		EngineOptions engineOptions = new EngineOptions( true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy( SCREEN_WIDTH, SCREEN_HEIGHT ), this.camera );
		
		engineOptions.getAudioOptions().setNeedsMusic( true ).setNeedsSound( true );
		engineOptions.setWakeLockOptions( WakeLockOptions.SCREEN_ON );
		
		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
