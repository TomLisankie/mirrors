package gameobjects;

import java.awt.Image;
import java.awt.event.*;

public class Player extends Character implements KeyListener {

	public void keyPressed(KeyEvent e) {
		//moves player around screen. Or, if in a menu, allows for navigating menu, etc.
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
			//move, flip sprite if necessary, sprite animation, move character on screen
			
			move(-1, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			
			move(1, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
			
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		move(0,0);
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//same as Character but can be controlled by keyboard

}
