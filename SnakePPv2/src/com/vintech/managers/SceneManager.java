package com.vintech.managers;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.vintech.base.BaseScene;
import com.vintech.scenes.GameScene;
import com.vintech.scenes.LoadingScene;
import com.vintech.scenes.MainMenuScene;
import com.vintech.scenes.SplashScene;

	/**
	 * Manages all the scenes
	 * From here we can switch between scenes
	 */


public class SceneManager {
    
	private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
	
    private static final SceneManager INSTANCE = new SceneManager();
    
    private SceneType currentSceneType;
    
    private BaseScene currentScene;
    
    private Engine engine = ResourceManager.getInstance().engine;
    
    public enum SceneType {
    	SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourceManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    private void disposeSplashScene() {
        ResourceManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    public void createMenuScene()
    {
        ResourceManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(menuScene);
        disposeSplashScene();
    }

    public void loadGameScene( final Engine mEngine ) {
    	setScene( loadingScene );
        ResourceManager.getInstance().unloadMenuTextures();
        mEngine.registerUpdateHandler( new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed( final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler( pTimerHandler );
                ResourceManager.getInstance().loadGameResources();
                gameScene = new GameScene();
                setScene( gameScene );
            }
        }));
    }
    
    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType) {
    	switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    public static SceneManager getInstance() {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene() {
        return currentScene;
    }
}
