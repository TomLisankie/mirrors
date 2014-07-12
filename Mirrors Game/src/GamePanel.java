import gameobjects.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {
	
	BufferedImage image;
	private Room currentRoom = new Room();
	Player player = new Player();
	
	Thread thread;
	
	public GamePanel(){
		System.out.println("yooo");
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
		
		addKeyListener(player);
		
		thread = new Thread(this);
		
	}

	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.drawImage(player.getSprite().getImage(), (int) player.getPosition().getX(), (int) player.getPosition().getY(), null);
         
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
	
}
