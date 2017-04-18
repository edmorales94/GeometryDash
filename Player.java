package Dash;

import java.awt.Graphics;

public class Player extends HandleCharacter{

//---------- constrcutor ------------------------------------------------------------------------------------------------------------------------
	public Player(double x, double y, int angle) {
		super(x, y, angle);//constructor from HandleCharacter.java
	}
	
//---------- getAllXCoordinates -----------------------------------------------------------------------------------------------------------------
	@Override
	public int[][] getAllXCoordinates() {
		int [][] allXCoordinates = 
			{ 
			  {-100,-30,30,100,100,-100},//x-coordinates for the head
			  {-48,-18,-48,-63},//x-coordinates for left eye
			  {48,18,48,63},//x-coordinates for right eye
			  {-75,0,75,75,65,65,-65,-65,-75},//x-coordinates for the skin section
			  {-55,55,55,-55}
			};
		return allXCoordinates;
	}

//---------- getAllYCoordinates -----------------------------------------------------------------------------------------------------------------
	@Override
	public int[][] getAllYCoordinates() {
		int[][] allYCoordinates = 
			{ 
			 {-100,-50,-50,-100,100,100},//y-coordinates for the head
			 {-12,0,12,0},//y-coordinates for left eye
			 {-12,0,12,0},//y-coordinates for right eye
			 {30,55,30,60,55,85,85,55,60},//x-coordinates for the skin section
			 {67,67,68,68}
		};
		return allYCoordinates;
	}
	
	public void respondToInput(boolean[] input){
		if(input[GamePanel.up]== true) rotate();
	}
//---------- draw method ------------------------------------------------------------------------------------------------------------------------
	public void draw(Graphics g){
		super.draw(g);
	}
}



//Arianna.FloresPerez14@bcacad.local
//112314114
