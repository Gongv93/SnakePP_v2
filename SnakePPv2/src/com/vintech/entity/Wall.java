package com.vintech.entity;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Wall extends Cell {
	public Wall( int cellX, int cellY,  ITextureRegion pTiledTextureRegion,  VertexBufferObjectManager pVertexBufferObjectManager) {
		super(cellX, cellY, CELL_WIDTH, CELL_HEIGHT, pTiledTextureRegion, pVertexBufferObjectManager);
	}
}
