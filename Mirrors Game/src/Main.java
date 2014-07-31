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
		mainPanel.requestFocus();
		
		add(mainPanel);
		
		setSize(800, 600);
		setVisible(true);
	}
	
	/*
	 * Summary of what we have for the story so far for clarity's sake:
- You begin as just a normal sort of person (also we should think about what time period this takes place in or at least the sort of air of it)
- Suddenly mirror people appear in some way. You almost get killed, but are saved by a mirror person.
- While under the guidance of the first mirror person, you almost get killed again but are saved by another mirror person and are guided by them for a bit.
- During the times you are under the guide of both, you see them both do sort of morally ambiguous things. This makes it so you can't really tell which mirror person is good or bad.
- You encounter the first mirror per		son again while under the guidance of the second and have to choose which one to go with. Probably shouldn't be direct choice like "which one do you choose?" because that's lame.
- You're given the key item that allows you to transfer between worlds by your mirror person (from here-on-out referred to as a companion).
- Your companion tells you that in order to seal off the mirror world from the real world, five parts must be collected. The game involves collecting these 5 parts.
- At the end of the game, depending on whether your companion was good or evil, you find out whether the machine seals off the two worlds or makes it so the mirror world can completely overflow into reality.
- If your companion was good you also have to break communication with them by destroying their presence in your world. They want you to do this.
- Also, I was thinking we could call the mirror people "Imagos" after the Latin word for reflection which is "imago."
	 * */

}
