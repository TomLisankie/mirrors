import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import gameobjects.*;

import javax.imageio.*;
import javax.swing.*;

public class Main extends JFrame {
	
	
	private GamePanel mainPanel;

	public static void main(String[] args) {
		
		Main main = new Main();

	}
	
	public Main(){
		
		
		mainPanel = new GamePanel();
		
		add(mainPanel);
		
		setSize(800, 600);
		setVisible(true);
	}

}
