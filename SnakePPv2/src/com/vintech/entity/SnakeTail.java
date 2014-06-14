package com.vintech.entity;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.vintech.constants.Constants;

public class SnakeTail extends Cell implements Constants{
	public SnakeTail( SnakeHead SnakeHead, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		this(SnakeHead.cellX, SnakeHead.cellY, pTextureRegion, pVertexBufferObjectManager);
	}

	public SnakeTail( int pCellX, int pCellY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pCellX, pCellY, CELL_WIDTH, CELL_HEIGHT, pTextureRegion, pVertexBufferObjectManager);
	}
}
