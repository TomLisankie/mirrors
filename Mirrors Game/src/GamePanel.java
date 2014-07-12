import gameobjects.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	BufferedImage image;
	private Room currentRoom = new Room();
	Player player = new Player();
	
	Thread thread;
	
	public GamePanel(){
		
		System.out.println("yooo");
		this.setFocusable(true);
		image = null;
 		try {
 			image = ImageIO.read(getClass().getResource("/gameobjects/game_sprite.png"));
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 		Sprite playerSprite = new Sprite();
 		playerSprite.setImage(image);
 		player.setSprite(playerSprite);
		
		currentRoom.add(player);
		
		addKeyListener(this);
		
		thread = new Thread(this);
		thread.start();
		
	}

	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         System.out.println("repaint");
         
         g.drawImage(player.getSprite().getImage(), (int) player.getPosition().getX(), (int) player.getPosition().getY(), null);
         
         repaint();
     }

	public void run() {
		long start = System.currentTimeMillis();
		
		Thread current = Thread.currentThread();
		
		while(current == thread){
			//System.out.println("in the thread");
			
			try{
				
				Thread.sleep(0);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
			repaint();
			
		}
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			
			player.move(0, -5);
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			
			player.move(0, 5);
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
			//move, flip sprite if necessary, sprite animation, move character on screen
			
			player.move(-5, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			
			player.move(5, 0);
			
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
