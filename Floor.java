package Dash;

import java.awt.Color;
import java.awt.Graphics;

public class Floor {
	int x1;//x-coordinate for the beginning of the floor
	int y1;//y-coordinate for the beginning of the floor
	
	int x2;//x-coordinate for the ending of the floor
	int y2;//y-coordinate for the beginning of the floor
	
	double xVector;
	double yVector;
	
//---------- constructor --------------------------------------------------------------------------------------------------------------------
	public Floor(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		double magnitud = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		xVector = (x2-x1)/magnitud;
		yVector = (y2-y1)/magnitud;
	}
	
/********************************************************************************************************************************************
 *This method keeps track of the character's height. If the distance between the floor
 *and the character is decreasing, it means the character is falling and therefore,
 *we make sure it does not go beyond the floor.
 * @param Batman
 */
	@SuppressWarnings("static-access")
	public void keepPlayerOnTheGround(Player Batman){
		double distance = (((Batman.x - x1)*yVector)-((Batman.y - y1)*xVector));
		
		if(distance <= 100){
			Batman.y = this.y1-100;
			Batman.fallingSpeed = 0;
			Batman.onGround = true;
			Batman.canRotate = true;
		}
	}
	
/********************************************************************************************************************************************
 * Draw method to illustrate the floor for the game(it is currently used as a reference, 
 * it will be delated later on). 
 * @param g
 */
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawLine(x1, y1, x2, y2);
	}
}
