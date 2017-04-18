package Dash;

import java.awt.*;

public abstract class HandleCharacter {
	
	double x;// x-coordinate where the character will be placed
	double y;// y-coordinate where the character will be placed
	
	int angle;//angle where the character will be facing 
	
	int[][] allXCoordinates = getAllXCoordinates();// contains all Xs of each polygon
	int[][] allYCoordinates = getAllYCoordinates();// contains all Ys of each polygon
	
//---------- constructor to initialize global variable -------------------------------------------------------------------------
	public HandleCharacter(double x, double y, int angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

//---------- abstract methods --------------------------------------------------------------------------------------------------
	public abstract int[][] getAllXCoordinates();
	public abstract int[][] getAllYCoordinates();

//---------- rotate method -----------------------------------------------------------------------------------------------------
	public void rotate(){
		angle += 90;//rotates the character clockwise 90 degrees
		if(angle > 359){angle -= 360;}
	}

//---------- moveForward method ------------------------------------------------------------------------------------------------
	public void moveForward(){
		x += 5;
	}
//---------- draw method -------------------------------------------------------------------------------------------------------
	public void draw(Graphics g){

		int[] xPoints;
		int[] yPoints;
		
		double cosAngle = Lookup.cos[angle];//will help to set the correct x-value for the position
		double sinAngle = Lookup.sin[angle];//will help to set the correct y-value for the polygon
		
		Color color = null;//color for each polygon will be different
		g.drawString("This is x: " +x+ "and y: " + y,(int)(x),(int)(y));
		g.fillOval((int)(x),(int)( y), 5, 5);
		for(int polygon = 0; polygon < allXCoordinates.length; polygon++){//go through all elements inside the array
			xPoints = new int[allXCoordinates[polygon].length];//the number of x-coordinates depends on the number of elements of each polygon
			yPoints = new int[allYCoordinates[polygon].length];//the number of y-coordinates depends on the number of elements of each polygon
			for(int vertex = 0; vertex < allXCoordinates[polygon].length; vertex++){//go through each elements of this current element in the main array
				xPoints[vertex] = (int)(allXCoordinates[polygon][vertex]*cosAngle - allYCoordinates[polygon][vertex]*sinAngle + x);
				yPoints[vertex] = (int)(allXCoordinates[polygon][vertex] * sinAngle + allYCoordinates[polygon][vertex]*cosAngle + y);
			}
			
			//depending on which polygon we are, we choose its color
			if(polygon == 0){ color = new Color(36,36,36);}//for the head
			if(polygon == 1 || polygon == 2){ color = new Color(153,255,255);}//left eye || right eye
			if(polygon == 3){ color = new Color(255,229,204);}//visible face section
			if(polygon == 4){ color = Color.black;}//mouth
			
			g.setColor(color);
			g.drawPolygon(xPoints, yPoints,allXCoordinates[polygon].length);
			//g.fillPolygon(xPoints, yPoints, allXCoordinates[polygon].length);			
		}
	}

}
