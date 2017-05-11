package Dash;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Edgar Morales
 *
 */

public class collisionBox {
	double x;//x-coordinte starting corner
	double y;//y-coordinte starting corner
	int w;//width for the rectangle
	int h;//height for the rectangle
	static int boxAngle;//angle used for the rotation of the rectangle
	
//---------- constructor -------------------------------------------------------------------------------------------------------------------------------------------------
	public collisionBox(int x,int y, int w, int h, int angle){//constructor must protect the code, what if it was negative??
		this.x = x;
		this.y = y;
	
		this.w = w;
		this.h = h;
		boxAngle = angle;
	}
	
/*************************************************************************************************************************************************************************
 * This method checks if another rectangle has collides with this rectangle
 * @param r
 * @return
 */
	public boolean hasCollidedWith(collisionBox otherBox){
		if(otherBox.x + otherBox.w >= x && otherBox.y >= y){
			return true;
		}
		else if(otherBox.x + otherBox.w >= this.x + this.w && otherBox.y < this.y){
			return false;
		}
		else{
			return false;
		}
	}
	
/*************************************************************************************************************************************************************************
 *This function is used to move the character forward according to the angle and distance desired
 * @param d
 */
	public void moveForwardCollisionBox(int d){
		x += d;
		y +=  (d * Lookup.sin[boxAngle]);
	}
	
/*************************************************************************************************************************************************************************
 * Function used for other classes to adjust the box y-coordinate
 * @param y2
 */
	public void setBoxY(double y2){
		this.y = y2;
	}
	
/*************************************************************************************************************************************************************************
 * Function used to draw the collision box around the objects
 * @param g
 */
	public void draw(Graphics g){
		g.setColor(Color.yellow);
		g.drawRect((int)(x-Camera.x),(int)y,w,h);
	}
		
}
