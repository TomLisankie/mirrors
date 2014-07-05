package gameobjects;

import java.util.*;
import java.awt.*;
import java.io.Serializable;

public abstract class Character extends GameObject implements Serializable {
	
	int inventoryMax;
	ArrayList<GameObject> inventory = new ArrayList<GameObject>();
	int hp;
	Point position = new Point(); //might change to a set of coordinates instead
	int facingDirection = 0; //0 is facing front, 1 is left, 2 is facing away, 3 is right
	Sprite sprite;
	
	public void move(int x, int y){
		
		position.setLocation(position.getX() + x, position.getY() + y);
		
		//something for animation and sprite movement also has to exist here
		//changes position and position of icon on screen
		
	}
	
	public void addToInventory(GameObject obj){
		
		//adds an object to the Character's inventory
		
		if(inventoryMax < inventory.size()){
		
			inventory.add(obj);
		
		}else{
			
			//some message for saying your inventory is full.
			
		}
		
	}
	
	public void removeFromInventory(/*not sure whether it should be a GameObject or index number yet*/){
		
		//removes an object from the Character's inventory
		
	}
	
}
