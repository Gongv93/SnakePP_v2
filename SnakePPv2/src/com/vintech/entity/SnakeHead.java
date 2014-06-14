package com.vintech.entity;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.vintech.constants.Constants;
import com.vintech.managers.Direction;

public class SnakeHead extends Cell implements Constants {
	public SnakeHead( int pCellX, int pCellY, ITextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		super( pCellX, pCellY, CELL_WIDTH, CELL_HEIGHT, pTiledTextureRegion, pVertexBufferObjectManager );

		this.setRotationCenterY( CELL_HEIGHT / 2 );
	}
	
	public void setRotation( Direction direction ) {
		switch( direction ) {
			case LEFT:
				this.setRotation( 90 );
				break;
			case RIGHT:
				this.setRotation( 270 );
				break;
			case UP:
				this.setRotation( 180 );
				break;
			case DOWN:
				this.setRotation( 0 );
				break;
		}
	}
}
