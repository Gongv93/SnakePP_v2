package com.vintech.entity;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Food extends Cell {
	public Food( int cellX, int cellY,  ITextureRegion pTiledTextureRegion,  VertexBufferObjectManager pVertexBufferObjectManager) {
		super(cellX, cellY, CELL_WIDTH, CELL_HEIGHT, pTiledTextureRegion, pVertexBufferObjectManager);
	}
}
