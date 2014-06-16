package com.vintech.managers;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.util.Log;

import com.vintech.snakepp.SnakeActivity;

public class ResourceManager {
	
	// ------------------------------
	// Variables
	// ------------------------------

	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public Engine engine;
	public SnakeActivity activity;
	public Camera camera;
	public VertexBufferObjectManager vbom;
	public Font font;
	
	// ------------------------------
	// Resources
	// ------------------------------
	
	// Splash Screen
	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;
	
	// Menu Screen
	public ITextureRegion menu_background_region;
	public ITextureRegion start_region;
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	// game Screen
	public BuildableBitmapTextureAtlas gameTextureAtlas;
	public ITextureRegion head_region;
	public ITextureRegion tail_region;
	public ITextureRegion food_region;
	public ITextureRegion wall_region;
	
	// ------------------------------
	// Methods
	// ------------------------------	
	
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
		
		loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
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
		
		loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
	}
	
	public void loadSplashScreen() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash_screen.png", 0, 0);
		splashTextureAtlas.load();
    }
    
    public void unloadSplashScreen() {
    	splashTextureAtlas.unload();
    	splash_region = null;
    }
	
	// Menu Resources ************************
	private void loadMenuAudio() {
		// TODO Auto-generated method stub
		
	}

	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_screen.png");
		start_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "start_button.png");
		       
		try 
		{
		    menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
		    menuTextureAtlas.load();
		} 
		catch (final TextureAtlasBuilderException e)
		{
		        Debug.e(e);
		}
		
	}
	
	private void loadMenuFonts() {
		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "LCD.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
		font.load();
	}
	
	public void loadMenuTextures() {
	    menuTextureAtlas.load();
	}
	
	public void unloadMenuTextures() {
	    menuTextureAtlas.unload();
	}
	
	// Game Resources ************************
	private void loadGameAudio() {
		// TODO Auto-generated method stub
		
	}

	private void loadGameFonts() {
		// TODO Auto-generated method stub
		
	}

	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath( "gfx/game/" );
		gameTextureAtlas = new BuildableBitmapTextureAtlas( activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR );
		head_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset( gameTextureAtlas, activity, "SnakeHead.png" );
		tail_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset( gameTextureAtlas, activity, "SnakeTail.png" );
		food_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset( gameTextureAtlas, activity, "Food.png" );
		wall_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset( gameTextureAtlas, activity, "wall.png" );
		
		try {
			gameTextureAtlas.build( new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>( 0, 1, 0 ) );
			gameTextureAtlas.load();
		} catch ( final TextureAtlasBuilderException e ) {
			Debug.e(e);
		}
	}
	
	public void unloadGameTextures() {
		
	}

	public static void prepareManager( Engine engine, SnakeActivity activity, Camera camera, VertexBufferObjectManager vbom ) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}
	
	// ------------------------------
	// Getters and Setters
	// ------------------------------
	
	public static ResourceManager getInstance() {
		return INSTANCE; 
	}
	
}
