import gameobjects.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	BufferedImage[] playerSprites = new BufferedImage[4];
	BufferedImage[] layers = new BufferedImage[3];
	private Room currentRoom = new Room();
	private Player player = new Player();
	
	Thread thread;
	//we're gonna need to have it so panel is drawing from a room object. some sort of loop that draws all objects in room
	public GamePanel(){
		
		this.setFocusable(true);
		
 		try {
 			layers[0] = ImageIO.read(getClass().getResource("/gameobjects/backdrop.png"));
 			layers[1] = playerSprites[0] = ImageIO.read(getClass().getResource("/gameobjects/game_sprite.png"));
 			playerSprites[1] = ImageIO.read(getClass().getResource("/gameobjects/252.png"));
 			playerSprites[2] = ImageIO.read(getClass().getResource("/gameobjects/501.png"));
 			playerSprites[3] = ImageIO.read(getClass().getResource("/gameobjects/502.png"));
 			layers[2] = ImageIO.read(getClass().getResource("/gameobjects/castle.png"));
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 		Sprite playerSprite = new Sprite();
 		playerSprite.setImage(playerSprites[0]);
 		player.setSprite(playerSprite);
 		player.setSpeed(5);
		
		currentRoom.addPlayer(player);
		
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
		
	}

	 protected void paintComponent(Graphics g) {
		 
         super.paintComponent(g);
         
         if(currentRoom.getPlayer().getMovingState() == true){
         
        	 if(currentRoom.getPlayer().getDirection() == 0){
        	 
        	 	Sprite playerSprite = new Sprite();
      			playerSprite.setImage(playerSprites[0]);
      			currentRoom.getPlayer().setSprite(playerSprite);
      			System.out.println("0");
        	 
         	}else if(currentRoom.getPlayer().getDirection() == 1){
        	 	
        	 	Sprite playerSprite = new Sprite();
      			playerSprite.setImage(playerSprites[1]);
      			currentRoom.getPlayer().setSprite(playerSprite);
        	 
         	}else if(currentRoom.getPlayer().getDirection() == 2){
        	
        	 	Sprite playerSprite = new Sprite();
      			playerSprite.setImage(playerSprites[2]);
      			currentRoom.getPlayer().setSprite(playerSprite);
      		
         	}else if(currentRoom.getPlayer().getDirection() == 3){
        	 	
        	 	Sprite playerSprite = new Sprite();
      			playerSprite.setImage(playerSprites[3]);
      			currentRoom.getPlayer().setSprite(playerSprite);
      		
         	}
        	 
         }else{
        	 
        	Sprite playerSprite = new Sprite();
   			playerSprite.setImage(playerSprites[0]);
   			currentRoom.getPlayer().setSprite(playerSprite);
        	 
         }
         
         g.drawImage(layers[0], 
        		 0, 0, null);
         g.drawImage(currentRoom.getPlayer().getSprite().getImage(), 
        		 (int) currentRoom.getPlayer().getPosition().getX(), (int) currentRoom.getPlayer().getPosition().getY(), null);
         g.drawImage(layers[2], 
        		 500, 500, null);
         
         repaint();
     }

	public void run() {
		long start = System.currentTimeMillis();
		
		Thread current = Thread.currentThread();
		
		while(current == thread){
			
			
			try{
				
				Thread.sleep(0);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
			repaint();
			
		}
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W){
			
			currentRoom.getPlayer().setDirection(2);
			currentRoom.getPlayer().setMovingState(true);
			currentRoom.getPlayer().move(0, ((-1)*player.getSpeed()));
			
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			
			currentRoom.getPlayer().setDirection(0);
			currentRoom.getPlayer().setMovingState(true);
			currentRoom.getPlayer().move(0, player.getSpeed());
			
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			
			//move, flip sprite if necessary, sprite animation, move character on screen
			currentRoom.getPlayer().setDirection(1);
			currentRoom.getPlayer().setMovingState(true);
			currentRoom.getPlayer().move(((-1)*player.getSpeed()), 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			
			currentRoom.getPlayer().setDirection(3);
			currentRoom.getPlayer().setMovingState(true);
			currentRoom.getPlayer().move(player.getSpeed(), 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
  		currentRoom.getPlayer().setMovingState(false);
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
}
