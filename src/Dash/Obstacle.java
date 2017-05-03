package Dash;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Edgar Morales
 *
 */
public class Obstacle{//Obstacle is going to be a rectangle
	
	int x;//x-coordinate of left upper corner where the origen for the obstacle will be
	int y;//y-coordinate of left upper corner where the origen for the obstacle will be
	
	int width;//width for the obstacle
	int height;//height for the obstacle
	
	double leftSideVectorX;//starting and endig x-coordinates of the left side
	double leftSideVectorY;//starting and endig y-coordinates of the left side
	
	double upperSideVectorX;//starting and endig x-coordinates of the upper side
	double upperSideVectorY;//starting and endig y-coordinates of the upper side
	
//---------- constructor to initialize the origin, width, height, and sides vectors ------------------------------------------------------------------------------------------------------------
	public Obstacle(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		
		//applying the Pythagorean Theorem for two order pair of points(http://orion.math.iastate.edu/butler/2012/spring/265/week2_review.pdf)
		double sideMagnitud = Math.sqrt((x-x)*(x-x)+(y-(y+height))*(y-(y+height)));
		leftSideVectorX = ((x-x))/sideMagnitud;
		leftSideVectorY = (y-(y+height))/sideMagnitud;
		
		//applying the Pythagorean Theorem for two order pair of points(http://orion.math.iastate.edu/butler/2012/spring/265/week2_review.pdf)
		double upperSideMagnitud = Math.sqrt((x-(x+width))*((x-(x+width))+(y-y)*(y-y)));
		upperSideVectorX = ((x+width)-x)/upperSideMagnitud;
		upperSideVectorY = (y-y)/upperSideMagnitud;
	}
	
/**********************************************************************************************************************************************************************************************
 * CollisionDetection checks the distance between the character and the obstacle.
 * If character collides with the obstacle, it will stop moving forward(for now).
 * It handles other possible events besides colliding with the wall 
 * @param Batman
 */
	@SuppressWarnings("static-access")
	public void collisionDetection(Player Batman){

		//measuring the distance between Batman and the side of the obstacle
		double distanceToTheSide = (((Batman.x - x)*leftSideVectorY)-((Batman.y - y)*leftSideVectorX));
		
		//measuring the distance between Batman and the top of the obstacle
		double distanceToTheUpperSide = (((Batman.x-x)*upperSideVectorY)-((Batman.y-y)*upperSideVectorX));
		
		//if the character is colliding with the left side wall, and is to the left of the wall, and at the same height of the obstacle
		if(Batman.x+100 <= 100 && distanceToTheSide >=0 && (Batman.y-100) >= this.y){
			Batman.velocity = 0;//then he can no longer move forward(it will instead explode later on)
			System.out.println(distanceToTheSide);
		}
		
		//if character's passed the obstacle origen, and hasn't gone beyond the end point of obstacle
		//and it remains at certain distance from the top of the obstacle
		if(Batman.x+100 > x && Batman.x-100 < x+width && 
				distanceToTheUpperSide <= 100 && distanceToTheUpperSide >= 0){
			Batman.y = this.y-100;//change the character's height to be on top of the obstacle
			Batman.fallingSpeed = 0;//he's no longer falling
			Batman.onGround = true;//he's on solid ground again, which means he can jump again
			Batman.canRotate = true;//he can rotate again too
		}
		
		//if the character has passed the obstacle, then make him fall
		if(Batman.x - 100 == x+width){
			Batman.onGround = false;//he's no longer on solid ground 
			Batman.canRotate = false;//he can't rotate anymore
			Batman.y += Batman.gravity;//make gravity do the falling effect
		}
		//System.out.println(distanceToTheUpperSide);
	}
	
/***********************************************************************************************************************************************************************************************
 * Draw method just to show the a simple obstacle. Better graphics will be implemented later on
 * @param g
 */
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect((int)(x-Camera.x), y, width, height);
		}
}

