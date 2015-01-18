package gameobjects;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Player extends Entity  {
	
	private BufferedImage image;
	
	//reference to character, and then sprite storage
	public Player(String spriteRef, int x, int y){
		super(spriteRef, x, y);
		
	}
	
	public void move(int delta) {
		// if we're moving left and have reached the left hand side
		// of the screen, don't move
		
		if ((dx < 0) && (x < 10)) {
			return;
		}
		// if we're moving right and have reached the right hand side
		// of the screen, don't move
		if ((dx > 0) && (x > 750)) {
			return;
		}
		if ((dy< 0) && (y < 10)){
			return;
		}
		if ((dy>0)&& (y > 550)){
			return;
		}
		
		super.move(delta);
	}
	
	public void attack(){
		
		super.attack();
		
		
		
	}
	public void block(){
		super.block();
	}
    
}
