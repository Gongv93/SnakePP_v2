package com.vintech.managers;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.vintech.snakepp.SnakeActivity;

public class ResourceManager {
	
	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public Engine engine;
	public SnakeActivity activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	
	public void loadMenuResources() {
		/**
		 * Menu Resources:
		 * 
		 * - Sounds
		 *   - Button pressed
		 *   
		 * - Graphics
		 *   - Buttons
		 *   - Background
		 */
	}
	
	public void loadGameResources() {
		/**
		 *  Game Resources
		 *  
		 *  - Sounds
		 *   - Eating
		 *   - song
		 *   
		 *  - Graphics
		 *   - Snake
		 *   - Background
		 */
	}
	
	
	
	public static void prepareManager( Engine engine, SnakeActivity activity, Camera camera, VertexBufferObjectManager vbom ) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}
	
	public static ResourceManager getInstance() {
		return INSTANCE; 
	}
	
}
