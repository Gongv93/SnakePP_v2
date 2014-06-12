package com.vintech.managers;

import org.andengine.engine.Engine;

import com.vintech.base.BaseScene;

	/**
	 * Manages all the scenes
	 * From here we can switch between scenes
	 */


public class SceneManager {
    
	// Declare scences here
	
    private static final SceneManager INSTANCE = new SceneManager();
    
    private SceneType currentSceneType;
    
    private BaseScene currentScene;
    
    private Engine engine = ResourceManager.getInstance().engine;
    
    public enum SceneType {
        /*
         *  Scence
         *   - Splash screen
         *   - Menu
         *   - SubMenu
         *   - Game
         *   - Gameover
         */
    }
    
    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType) {
        /*
         * Sets Scene
         */
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
