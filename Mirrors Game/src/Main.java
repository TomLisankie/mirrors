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
		
		mainPanel = new JPanel();
		mainPanel.setSize(800,600);
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("diaz.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Graphics g = mainPanel.getGraphics();
		g.drawImage(image, 50, 50, null);
		
		add(mainPanel);
		
		Player player = new Player();
		currentRoom.add(player);
		
		setSize(800, 600);
		setVisible(true);
	}

}
