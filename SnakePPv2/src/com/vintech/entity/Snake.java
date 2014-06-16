package com.vintech.entity;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.vintech.managers.Direction;
import com.vintech.exception.SuicideException;

public class Snake extends Entity {
	private SnakeHead snakehead;
	private LinkedList<SnakeTail> tail = new LinkedList<SnakeTail>();

	private Direction curDir;
	private Direction prevDir;
	boolean extend;
	private  ITextureRegion TailTextureRegion;
	
	public Snake(Direction initialDir, int cellX, int cellY, ITextureRegion pHeadTextureRegion, ITextureRegion tailPartTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0);
		this.TailTextureRegion = tailPartTextureRegion;
		this.snakehead = new SnakeHead( cellX, cellY, pHeadTextureRegion, pVertexBufferObjectManager );
		this.attachChild( this.snakehead );
		this.setDir( initialDir );
	}
	
	public Direction getDir() {
		return curDir;
	}

	public void setDir(final Direction direction) {
		if(this.prevDir != Direction.opposite(direction)) {
			curDir = direction;
			snakehead.setRotation(direction);
		}
	}

	public int getTailLength() {
		return tail.size();
	}

	public SnakeHead getHead() {
		return snakehead;
	}
	
	public LinkedList<SnakeTail> getTail() {
		return tail;
	}
	
	public void grow() {
		this.extend = true;
	}

	public int getNextX() {
		return Direction.addToX(curDir, snakehead.getCX());
	}

	public int getNextY() {
		return Direction.addToY(curDir, snakehead.getCY());
	}

	public void move() throws SuicideException {
		prevDir = curDir;
		if(extend) {
			extend = false;
			/* 
			 * If the snake should grow,
			 * simply add a new part in the front of the tail,
			 * where the head currently is. 
			 */
			SnakeTail newTail = new SnakeTail(snakehead, TailTextureRegion, snakehead.getVertexBufferObjectManager());
			this.attachChild(newTail);
			tail.addFirst(newTail);
		} else {
			if(tail.isEmpty() == false) {
				/* First move the end of the tail to where the head currently is. */
				SnakeTail tailEnd = tail.removeLast();
				tailEnd.setCell(snakehead);
				tail.addFirst(tailEnd);
			}
		}

		/* The move the head into the direction of the snake. */
		this.snakehead.setCell(this.getNextX(), this.getNextY());
		
		/* Check if head collides with tail. */
		for(int i = 0; i < tail.size(); i++) {
			if(snakehead.sameCell(tail.get(i))) {
				throw new SuicideException();
			}
		}
	}
}
