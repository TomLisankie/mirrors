import gameobjects.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	BufferedImage image;
	private Room currentRoom = new Room();
	Player player = new Player();
	
	public GamePanel(){
		
		image = null;
 		try {
 			image = ImageIO.read(getClass().getResource("/gameobjects/diaz.jpg"));
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		
 		Sprite playerSprite = new Sprite();
 		playerSprite.setImage(image);
 		player.setSprite(playerSprite);
		
		currentRoom.add(player);
		
		addKeyListener(player);
		
	}

	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         g.drawImage(player.getSprite().getImage(), (int) player.getPosition().getX(), (int) player.getPosition().getY(), null);
         
         repaint();
     }
	
}
