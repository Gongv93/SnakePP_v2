package com.vintech.entity;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.vintech.base.BaseCell;
import com.vintech.constants.Constants;

public abstract class Cell extends Sprite implements BaseCell, Constants {
	public int cellX;
	public int cellY;
	
	public Cell( int pCellX, int pCellY, int pWidth, int pHeight, ITextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		super(pCellX * CELL_WIDTH, pCellY * CELL_HEIGHT, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
		this.cellX = pCellX;
		this.cellY = pCellY;
	}

	public int getCX() {
		return this.cellX;
	}
	
	public int getCY() {
		return this.cellY;
	}
	
	public void setCell( int CellX, int CellY ) {
		this.cellX = CellX;
		this.cellY = CellY;
		this.setPosition(this.cellX * CELL_WIDTH, this.cellY * CELL_HEIGHT);
	}
	
	public void setCell( BaseCell pCell ) {
		this.setCell(pCell.getCX(), pCell.getCY());
	}
	
	@Override
	public boolean sameCell( BaseCell pCellEntity ) {
		return this.cellX == pCellEntity.getCX() && this.cellY == pCellEntity.getCY();
	}
}
