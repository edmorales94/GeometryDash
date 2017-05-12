package Dash;

import java.awt.Graphics;

/**
 * 
 * @author Edgar Morales
 *
 */

public class Game extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//---------- instances- -----------------------------------------------------------------------------------------------------------------
	AudioPlayer backgroundMusic;
	ImageLayer background = new ImageLayer("background.jpg", 0,0,8,800,20);
	ImageLayer realFloor = new ImageLayer("floor.png",0,700,1,2000,30);
	Player Batman = new Player(-200,0,0);
	Floor floor = new Floor(0,700,7000,700);
	Obstacle obstacle1 = new Obstacle(1000,550,800,150);
	Obstacle obstacle2 = new Obstacle(3700,500,800,200);
	Obstacle obstacle3 = new Obstacle(4300,350,200,150);
	Obstacle obstacle4 = new Obstacle(6000,500,500,200);
	
/***************************************************************************************************************************************
 * startTheGame calls a method in GamePanel that sets up the screen
 */
	public void startTheGame(){
		backgroundMusic = new AudioPlayer("music/mr.mp3");//this loads the music background
		backgroundMusic.play();//play the background music
		startFrame();
	}

/****************************************************************************************************************************************
 * respondToInput is used to determine what to do when the user presses keys or mouse
 */
	@Override
	public void handleMovement() {
		//if Batman is at a certain point and hasn't collided yet. move everything slowly 
		if(Batman.x-560 > 0 && Batman.x-560 < 400 && !Batman.collided){
			Camera.movetoTheRight(25);
			HandleCharacter.velocity = 25;
		}
		
		//if Batman is at a certain point and hasn't collided yet. move everything faster
		if(Batman.x-560 >= 400 && !Batman.collided){
			Camera.movetoTheRight(28);
			HandleCharacter.velocity = 28;
		}
		Batman.moveForward();//will move if velocity is not zero(when collides, it changes to zero)
		Batman.jump();//will jump if Batman is on ground 
	}

/****************************************************************************************************************************************
 * handleCollisions is used to determine what to do when the character collides
 */
	@Override
	public void handleCollisions() {
		floor.keepPlayerOnTheGround(Batman);
		obstacle1.collisionDetection(Batman);
		obstacle2.collisionDetection(Batman);
		obstacle3.collisionDetection(Batman);
		obstacle4.collisionDetection(Batman);
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
		background.draw(g);
		realFloor.draw(g);
		Batman.draw(g);
		//floor.draw(g);
		obstacle1.draw(g);
		obstacle2.draw(g);
		obstacle3.draw(g);
		obstacle4.draw(g);
		
		g.dispose();
		flipPages.show();
	}
	
/****************************************************************************************************************************************
	 * respondToInput is used to determine what to do when the user presses keys or mouse
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.startTheGame();
	}

}
