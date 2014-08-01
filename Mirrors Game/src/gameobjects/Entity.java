package gameobjects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Entity extends GameObject {
	/** The current x location of this entity */ 
	protected double x;
	/** The current y location of this entity */
	protected double y;
	/** The sprite that represents this entity */
	protected Sprite sprite;
	/** The current speed of this entity horizontally (pixels/sec) */
	protected double dx;
	/** The current speed of this entity vertically (pixels/sec) */
	protected double dy;
	/** The rectangle used for this entity during collisions  resolution */
	int inventoryMax;
	int characterSpeed = 5;
	boolean isMoving = false;
	ArrayList<GameObject> inventory = new ArrayList<GameObject>();
	int hp;
	private Point position = new Point(); // might change to a set of coordinates instead Changed it


	
	
	Sprite[] sprites; // will use facing direction to reference a sprite in the array
	Sprite currentSprite; // the current sprite
	
	
	public Entity(){
		
	}
	public Entity(String ref, int x, int y){
		this.sprite = SpriteStorage.get().getSprite(ref);
		this.x =x;
		this.y = y;
		
	}
	
	public void draw(Graphics g) {
		sprite.draw(g,(int) x,(int) y);
	}
	
	public void move(long delta) {
		// update the location of the entity based on move speeds
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
	}

//	public void move(int x, int y) {
//
//		if (isMoving == true) {
//
//			position.setLocation(getPosition().getX() + x, getPosition().getY() + y);
//
//			System.out.println("you pressed a key");
//		}
//		// something for animation and sprite movement also has to exist
//		// here
//		// changes position and position of icon on screen
//
//	}

	public void attack() {
		//Attack Animation +  what it does
	}

	public void addToInventory(GameObject obj) {

		// adds an object to the Character's inventory

		if (inventoryMax < inventory.size()) {

			inventory.add(obj);

		} else {

			// some message for saying your inventory is full.

		}

	}

	public void removeFromInventory(/*
									 * not sure whether it should be a
									 * GameObject or index number yet* Andrew
									 * votes index #
									 */) {

		// removes an object from the Character's inventory

	}

	public void setSprite(Sprite sprite) {

		currentSprite = sprite;

	}

	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return currentSprite;
	}
	/**
	 * Set the horizontal speed of this entity
	 * 
	 * @param dx The horizontal speed of this entity (pixels/sec)
	 */
	public void setHorizontalMovement(double dx) {
		this.dx = dx;
	}

	/**
	 * Set the vertical speed of this entity
	 * 
	 * @param dx The vertical speed of this entity (pixels/sec)
	 */
	public void setVerticalMovement(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Get the horizontal speed of this entity
	 * 
	 * @return The horizontal speed of this entity (pixels/sec)
	 */
	public double getHorizontalMovement() {
		return dx;
	}

	/**
	 * Get the vertical speed of this entity
	 * 
	 * @return The vertical speed of this entity (pixels/sec)
	 */
	public double getVerticalMovement() {
		return dy;
	}
	
	/**
	 * Draw this entity to the graphics context provided
	 * 
	 * @param g The graphics context on which to draw
	 */

	
	public int getX() {
		return (int) x;
	}

	/**
	 * Get the y location of this entity
	 * 
	 * @return The y location of this entity
	 */
	public int getY() {
		return (int) y;
	}




	public void setMovingState(boolean moving) {

		isMoving = moving;

	}

	public boolean getMovingState() {

		return isMoving;

	}

}
