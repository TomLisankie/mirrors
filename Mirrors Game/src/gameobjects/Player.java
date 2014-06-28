package gameobjects;

import java.awt.event.*;

public class Player extends Character implements KeyListener {

	public void keyPressed(KeyEvent e) {
		//moves player around screen. Or, if in a menu, allows for navigating menu, etc.
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
			//move, flip sprite if necessary, sprite animation, move character on screen
			
			if(facingDirection == true){
				
				facingDirection = false;
				
			}
			
			move(-1, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			
			if(facingDirection == false){
				
				facingDirection = true;
				
			}
			
			move(1, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
			jump();
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		move(0,0);
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void jump(){
		
		//performs jump action. changes position based on time system.
		
	}
	
	//same as Character but can be controlled by keyboard

}
