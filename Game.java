package Dash;

import java.awt.Graphics;

public class Game extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Player batman = new Player(400, 900,0);
	public void startTheGame(){
		startFrame();
	}

	@Override
	public void respondToInput() {
		batman.respondToInput(input);
		batman.moveForward();
	}

	@Override
	public void handleCollisions() {
		
	}
	
	@Override
	public void draw(Graphics g) {
		super.paintComponent(g);
		batman.draw(g);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.startTheGame();
	}

}
