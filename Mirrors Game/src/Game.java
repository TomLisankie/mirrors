
import gameobjects.Entity;
import gameobjects.Player;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main hook of our game. This class with both act as a manager for the
 * display and central mediator for the game logic.
 * 
 * Display management will consist of a loop that cycles round all entities in
 * the game asking them to move and then drawing them in the appropriate place.
 * With the help of an inner class it will also allow the player to control the
 * main ship.
 * 
 * As a mediator it will be informed when entities within our game detect events
 
 * 
 * @author Andrew Plaza
 */
public class Game extends Canvas {

	// SystemTimer Timer = new SystemTimer();
	private Room currentRoom = new Room();

	/** The stragey that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	private boolean gameRunning = true;
	/** The list of all the entities that exist in our game */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList removeList = new ArrayList();
	/** The entity representing the player */

	private Entity player;
	/** The speed at which the player's ship should move (pixels/sec) */

	/** The message to display which waiting for a key press */
	private String message = "";
	/** True if we're holding up game play until a key has been pressed */
	private boolean waitingForKeyPress = true;
	/** True if the left cursor key is currently pressed */
	private boolean leftPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean spacePressed = false;
	private double moveSpeed = 300;
	//for attacking
	private long lastAttack = 0;
	//to change attack delay, change this
	private long attackInterval = 500;
	
	/**
	 * True if game logic needs to be applied this loop, normally as a result of
	 * a game event
	 */
	private boolean logicRequiredThisLoop = false;
	/** The last time at which we recorded the frame rate */
	private long lastFpsTime;
	/** The current number of frames recorded */
	private int fps;
	/** The normal title of the game window */
	private String windowTitle = "Mirrors";
	/** The game window that we'll update with the frame count */
	private JFrame container;
	
	//path to pics

	String path = "gameobjects/";
	// player spritees
	Image[] playerSprites = new Image[4];
	// List for layers	
	Image[] layers = new Image[3];
	/**
	 * Construct our game and set it running.
	 */
	public Game() {
		// create a frame to contain our * 
		container = new JFrame("Mirrors");

		// get hold the content of the frame and set up the resolution of the
		// game

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame

		setBounds(0, 0, 800, 600);
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're

		// going to do that our self in accelerated mode

		setIgnoreRepaint(true);

		// finally make the window visible

		container.pack();
		container.setResizable(true);
		container.setVisible(true);

		// add a listener to respond to the user closing the window. If they

		// do we'd like to exit the game

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// add a key input system (defined below) to our canvas

		// so we can respond to key pressed

		addKeyListener(new KeyInputHandler());
		addMouseListener(new MouseInputHandler());

		// request the focus so key events come to us

		requestFocus();

		// create the buffering strategy which will allow AWT

		// to manage our accelerated graphics

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		
		// Player Sprites
		
		try {
			layers[0] = ImageIO.read(getClass().getResource( path+ "backdrop.png"));
		
		layers[1] = playerSprites[0] = ImageIO.read(getClass().getResource( path + "game_sprite.png"));
		layers[2] = ImageIO.read(getClass().getResource( path + "castle.png"));
		playerSprites[1] = ImageIO.read(getClass().getResource( path + "252.png"));
		playerSprites[2] = ImageIO.read(getClass().getResource( path + "501.png"));
		playerSprites[3] = ImageIO.read(getClass().getResource( path +"502.png"));
		
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// initialise the entities in our game so there's something
		// to see at startup

		initEntities();
		
	}

	/**
	 * Start a fresh game, this should clear out any old data and create a new
	 * set.
	 */
	private void startGame() {
		// clear out any existing entities and intialise a new set

		entities.clear();
		initEntities();

		// blank out any keyboard settings we might currently have

		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		downPressed = false;
		gameLoop();
	}

	/**
	 * Initialise the starting state of the entities  Each
	 * entitiy will be added to the overall list of entities in the game.
	 */
	private void initEntities() {

		// Sets player sprite
		
		player = new Player( path + "game_sprite.png", 400, 400);
		entities.add(player);
		
		//we can add other entities here into the windows (Such as the Kathrepti)
	}

	 //Game Logic- for when we need to update other characters/movements/things
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}

	/**
	 * Notification that the player has died.
	 */
	public void notifyDeath() {
	message = "You Died";
		waitingForKeyPress = true;
	}

	/**
	 * Notification that the player has won.
	 */
	public void notifyWin() {
		message = "You Won.";
		waitingForKeyPress = true;
	}

	
	
	//Tryign to make a thing to delay attacks, so that you don't attack 5 times when clicking space once
	public void tryToAttack() {
		// check that we have waiting long enough to attack

		if (System.currentTimeMillis() - lastAttack < attackInterval) {
			return;
		}
		
		// if we waited long enough, attack
		lastAttack = System.currentTimeMillis();
		player.attack();
		
	}
	
	/**
	 * The main game loop. This loop is running during all game play as is
	 * responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves - Moving the
	 * game entities - Drawing the screen contents (entities, text) - Updating
	 * game events - Checking Input
	 * <p>
	 */
	public void gameLoop() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		// keep looping round til the game ends

		while (gameRunning == true) {
			// work out how long its been since the last update, this
			System.out.println("Game running");
			// will be used to calculate how far the entities should

			// move this loop

			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			// update the frame counter

			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
				System.out.println("what the fuck");
				System.out.println("(FPS: " + fps + ")");
				
				lastFpsTime = 0;
				fps = 0;
			}

			// This is where we should update Game Logic

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			
			ImageObserver observer = null;
			
			g.drawImage(layers[0], 0, 0, observer);
			
			//loop for drawing all entities in the entity array list. Commented out for now b/c only one entity so far (player)
			 for (int i=0;i<entities.size();i++) {
				 Entity entity = (Entity) entities.get(i);
			
				 entity.draw(g);
			
			 } 
			
			/*
			 * // finally, we've completed drawing so clear up the graphics
			 */
			// and flip the buffer over

			g.dispose();
			strategy.show();

			
			//
			player.setHorizontalMovement(0);
			player.setVerticalMovement(0);
			
			if ((leftPressed) && (!rightPressed)) {
				player.setHorizontalMovement(-moveSpeed);
			} 
			else if ((rightPressed) && (!leftPressed)) {
				player.setHorizontalMovement(moveSpeed);
			}
		    if ((upPressed)&& (!downPressed)){
				player.setVerticalMovement(-moveSpeed);
			}
			else if ((downPressed)&&(!upPressed)){
				player.setVerticalMovement(moveSpeed);
			}
		    
		    if (spacePressed){
		    	
		    	tryToAttack();
		    	
		    }

		    System.out.println("lastLoopTime: " + lastLoopTime);
		    System.out.println("System.nanoTime(): " + System.nanoTime());
		    System.out.println("OPTIMAL_TIME: " + OPTIMAL_TIME);
		    
			player.move(10);
			
			try {
				System.out.println("Hello bitch");
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Thread sleep");
			}
		}
	}

	/**
	 * A class to handle keyboard input from the user. The class handles both
	 * dynamic input during game play, i.e. left/right and shoot, and more
	 * static type input (i.e. press any key to continue)
	 * 
	 * This has been implemented as an inner class more through habbit then
	 * anything else. Its perfectly normal to implement this as seperate class
	 * if slight less convienient.
	 * 
	 * @author Andrew Plaza
	 */
	private class KeyInputHandler extends KeyAdapter {
		/**
		 * The number of key presses we've had while waiting for an "any key"
		 * press
		 */
		private int pressCount = 1;

		/**
		 * Notification from AWT that a key has been pressed. Note that a key
		 * being pressed is equal to being pushed down but *NOT* released. Thats
		 * where keyTyped() comes in.
		 *
		 * @param e
		 *            The details of the key that was pressed
		 */
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't
			// want to do anything with just a "press"
			if (waitingForKeyPress) {
				return;
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_W){
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_S){
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				spacePressed = true;
			}
			
			
		}

		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e
		 *            The details of the key that was released
		 */
		public void keyReleased(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't
			// want to do anything with just a "released"
			if (waitingForKeyPress) {
				return;
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_W){
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S){
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				spacePressed = false;
			}

		}

		/**
		 * Notification from AWT that a key has been typed. Note that typing a
		 * key means to both press and then release it.
		 *
		 * @param e
		 *            The details of the key that was typed.
		 */
		public void keyTyped(KeyEvent e) {
			// if we're waiting for a "any key" type then
			// check if we've recieved any recently. We may

			// have had a keyType() event from the user releasing

			// the attack or move keys, so use of the "pressCount"
			// counter.

			if (waitingForKeyPress) {
				if (pressCount == 1) {
					// since we've now recieved our key typed

					// event we can mark it as such and start

					// our new game

					waitingForKeyPress = false;
					startGame();
					pressCount = 0;
				} else {
					pressCount++;
				}
			}

			// if we hit escape, then quit the game

			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
	private class MouseInputHandler implements MouseListener{

		//put mouse methods here
	
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/**
	 * The entry point into the game. We'll simply create an instance of class
	 * which will start the display and game loop.
	 * 
	 * @param argv
	 *            The arguments that are passed into our game
	 */
	public static void main(String args[]) {
		Game g = new Game();

		// Start the main game loop, note: this method will not

		// return until the game has finished running. Hence we are

		// using the actual main thread to run the game.

		g.gameLoop();
	}
}
