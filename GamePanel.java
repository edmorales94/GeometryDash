package Dash;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GamePanel extends JPanel implements KeyListener, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame;//needed for a fullScreen
	BufferStrategy flipPages;//needed for Page Flipping approach 
	
	boolean paused = false;
	boolean finished = false;
	boolean rotatePlayer = false;
	Thread gameThread;
	
	boolean[] input = new boolean[1024];
	public final static int up = KeyEvent.VK_UP;

//---------- set FullScreen method --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void setFullScreen(){
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = environment.getDefaultScreenDevice();
		JFrame frame = new JFrame();// creating a frame
		frame.add(this);
		frame.setUndecorated(true);//doesn't dispay borders and title bar
		screen.setFullScreenWindow(frame);//set the frame to fullScreen
		
		frame.createBufferStrategy(3);//creating 3 buffers for page flipping technique
		flipPages = frame.getBufferStrategy();//get a reference of the bufferStrategy created
	}
	
//---------- start Frame ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void startFrame(){
		addKeyListener(this);// Attach the KeyListener to the JPanel in order to monitor keypresses
		setFocusable(true);//KeyEvent will only be dispatched to the panel if it is "focusable"	
		setFullScreen();//Set the screen
		
		gameThread = new Thread(this);// Create the thread for the main loop
		gameThread.start();//starts the gameThread
	}
	
//----------- stop thread -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void stopGameThread(){
		try {
			gameThread.join();
		} 
		catch (InterruptedException e) {
			System.out.println("Could't terminate gameThread");
		}
	}

//---------- put thread to sleep ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("static-access")
	public void sleepGameThread(int duration){
		try {gameThread.sleep(duration);} 
		catch (InterruptedException e) {System.out.println("Couldn't put thread to sleep");}
	}
//---------- keyPressed -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(KeyEvent.VK_Q == code) finished = true;
		if(KeyEvent.VK_P == code) paused = true;
		if(KeyEvent.VK_C == code) paused = false;
		if(KeyEvent.VK_UP == code) rotatePlayer = true;
		
		input[code] = true;
	}

//---------- keyReleased ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		input[code] = false;
	}
	
//---------- preGame loop -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void preGameLoop(){}
	
//---------- respond to input -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public abstract void respondToInput();
	
//---------- handle Collisions ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public abstract void handleCollisions();
	
//---------- inGame loop ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public final void inGameLoop(){
		respondToInput();
		handleCollisions();
	}
	
//---------- postGame loop ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void postGameLoop(){}
	
//---------- paint ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public abstract void draw(Graphics g);
//---------- update display ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void update(){
		Graphics g = flipPages.getDrawGraphics();
		draw(g);
		g.dispose();
		flipPages.show();
	}
//---------- run method called by gameThread.start() --------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void run() {
		preGameLoop();
		while(!finished){
			if(!paused)
				inGameLoop();
				update();
				sleepGameThread(15);
		}
		postGameLoop();
		System.exit(0);
	}

//---------- keyTyped ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
}
