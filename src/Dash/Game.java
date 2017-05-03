package Dash;

import java.awt.Graphics;
//import java.awt.Toolkit;

public class Game extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//---------- instances- -----------------------------------------------------------------------------------------------------------------
	Player Batman = new Player(-300, 200,0);
	Floor floor = new Floor(0,1100,3000,1100);
	Obstacle obstacle1 = new Obstacle(500,900,300,200);
	Obstacle obstacle2 = new Obstacle(1400,900,200,200);
	
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
		Batman.moveForward();
		Batman.jump();
	}

/****************************************************************************************************************************************
 * handleCollisions is used to determine what to do when the character collides
 */
	@Override
	public void handleCollisions() {
		floor.keepPlayerOnTheGround(Batman);
		obstacle1.collisionDetection(Batman);
		obstacle2.collisionDetection(Batman);
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
		Batman.draw(g);
		floor.draw(g);
		obstacle1.draw(g);
		obstacle2.draw(g);
		
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
