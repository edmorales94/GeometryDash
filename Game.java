package Dash;

import java.awt.Graphics;
//import java.awt.Toolkit;

public class Game extends GamePanel{
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
//---------- character instance -----------------------------------------------------------------------------------------------------------------
	Player batman = new Player(100, 900,0);
	
/***************************************************************************************************************************************
 * startTheGame calls a method in GamePanel that sets up the screen
 */
	public void startTheGame(){
		startFrame();
	}

/****************************************************************************************************************************************
 * respondToInput is used to determine what to do when the user presses keys or mouse
 */
	@Override
	public void respondToInput() {
		batman.moveForward();
		batman.jump();
	}

/****************************************************************************************************************************************
 * handleCollisions is used to determine what to do when the character collides
 */
	@Override
	public void handleCollisions() {
		
	}
	
/****************************************************************************************************************************************
 * draw calls the update method inherited from GamePanel
 */
	@Override
	public void draw() {
		flipPages = getBufferStrategy();//get a reference of the bufferStrategy created in GamePanel
		if(flipPages == null){//if bufferStrategy is empty
			createBufferStrategy(3);//create a new one
			return;
		}
		
		Graphics g = flipPages.getDrawGraphics();//get the graphics object
		super.update(g);//The canvas is first cleared by filling it with the background color, and then completely redrawn by calling this canvas's paint method
		batman.draw(g);
		
		g.dispose();
		flipPages.show();
		//Toolkit.getDefaultToolkit().sync();
	}
	
/****************************************************************************************************************************************
	 * respondToInput is used to determine what to do when the user presses keys or mouse
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.startTheGame();
	}

}
