import java.awt.*;
import java.awt.image.*;
import java.io.*;

import gameobjects.*;

import javax.imageio.*;
import javax.swing.*;

public class Main extends JFrame {
	
	private Room currentRoom = new Room();
	private JPanel mainPanel;

	public static void main(String[] args) {
		
		Main main = new Main();

	}
	
	public Main(){
		
		
		mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                BufferedImage image = null;
        		try {
        			image = ImageIO.read(getClass().getResource("/gameobjects/diaz.jpg"));
        		} catch (IOException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
                
                g.drawImage(image, 50, 50, null);
            }
        };
		
		add(mainPanel);
		
		Player player = new Player();
		currentRoom.add(player);
		
		setSize(800, 600);
		setVisible(true);
	}

}
