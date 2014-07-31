package gameobjects;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Input;

public abstract class Character extends GameObject implements Serializable {

	int inventoryMax;
	int characterSpeed = 5;
	boolean isMoving = false;
	ArrayList<GameObject> inventory = new ArrayList<GameObject>();
	int hp;
	private Point position = new Point(); // might change to a set of coordinates instead
	int facingDirection = 0; // 0 is facing front, 1 is left, 2 is facing away, 3 is right
	Sprite[] sprites; // will use facing direction to reference a sprite in the array
	Sprite currentSprite; // the current sprite

	public void move(int x, int y) {

		if (isMoving == true) {

			position.setLocation(getPosition().getX() + x, getPosition().getY() + y);

			System.out.println("you pressed a key");
		}
		// something for animation and sprite movement also has to exist
		// here
		// changes position and position of icon on screen

	}

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

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setDirection(int direction) {

		facingDirection = direction;

	}

	public int getDirection() {

		return facingDirection;

	}

	public void setSpeed(int speed) {

		characterSpeed = speed;

	}

	public int getSpeed() {

		return characterSpeed;

	}

	public void setMovingState(boolean moving) {

		isMoving = moving;

	}

	public boolean getMovingState() {

		return isMoving;

	}

}
