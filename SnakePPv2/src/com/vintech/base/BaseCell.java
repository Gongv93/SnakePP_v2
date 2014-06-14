package com.vintech.base;


public interface BaseCell {
	
	// ---------------------------------------------
	// Getters and setters
	// ---------------------------------------------
	public abstract int getCX();
	public abstract int getCY();
	
	public abstract void setCell( BaseCell pCell );
	public abstract void setCell( int pCellX, int pCellY );

	// ---------------------------------------------
	// Methods
	// ---------------------------------------------
	public abstract boolean sameCell( BaseCell pCell );
}
