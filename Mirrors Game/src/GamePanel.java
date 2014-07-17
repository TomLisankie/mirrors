import gameobjects.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	BufferedImage[] image = new BufferedImage[4];
	private Room currentRoom = new Room();
	private Player player = new Player();
	
	Thread thread;
	
	public GamePanel(){
		
		System.out.println("yooo");
		this.setFocusable(true);
		
 		try {
 			image[0] = ImageIO.read(getClass().getResource("/gameobjects/game_sprite.png"));
 			image[1] = ImageIO.read(getClass().getResource("/gameobjects/252.png"));
 			image[2] = ImageIO.read(getClass().getResource("/gameobjects/501.png"));
 			image[3] = ImageIO.read(getClass().getResource("/gameobjects/502.png"));
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 		Sprite playerSprite = new Sprite();
 		playerSprite.setImage(image[0]);
 		player.setSprite(playerSprite);
 		player.setSpeed(5);
		
		currentRoom.addPlayer(player);
		
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
		
	}

	 protected void paintComponent(Graphics g) {
		 
         super.paintComponent(g);
         
         if(player.getDirection() == 0){
        	 
        	Sprite playerSprite = new Sprite();
      		playerSprite.setImage(image[0]);
      		currentRoom.getPlayer().setSprite(playerSprite);
        	 
         }else if(player.getDirection() == 1){
        	 
        	Sprite playerSprite = new Sprite();
      		playerSprite.setImage(image[1]);
      		currentRoom.getPlayer().setSprite(playerSprite);
        	 
         }else if(player.getDirection() == 2){
        	
        	Sprite playerSprite = new Sprite();
      		playerSprite.setImage(image[2]);
      		currentRoom.getPlayer().setSprite(playerSprite);
        	 
         }else if(player.getDirection() == 3){
        	
        	Sprite playerSprite = new Sprite();
      		playerSprite.setImage(image[3]);
      		currentRoom.getPlayer().setSprite(playerSprite);
        	 
         }
         
         g.drawImage(currentRoom.getPlayer().getSprite().getImage(), 
        		 (int) currentRoom.getPlayer().getPosition().getX(), (int) currentRoom.getPlayer().getPosition().getY(), null);
         
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
			currentRoom.getPlayer().move(0, ((-1)*player.getSpeed()));
			
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			
			currentRoom.getPlayer().setDirection(0);
			currentRoom.getPlayer().move(0, player.getSpeed());
			
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			
			//move, flip sprite if necessary, sprite animation, move character on screen
			currentRoom.getPlayer().setDirection(1);
			currentRoom.getPlayer().move(((-1)*player.getSpeed()), 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			
			currentRoom.getPlayer().setDirection(3);
			currentRoom.getPlayer().move(player.getSpeed(), 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
		Sprite playerSprite = new Sprite();
  		playerSprite.setImage(image[0]);
  		System.out.println(image[0]);
  		currentRoom.getPlayer().setSprite(playerSprite);
  		System.out.println("gaefuygaudyfgayue");
		
	}

	public void keyTyped(KeyEvent e) {
		System.out.println("hello");
		
	}
	
}
