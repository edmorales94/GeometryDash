package Dash;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Edgar Morales
 *
 */
public class ImageLayer {
	
	Image image;//picture to be used
	double x;//where on the x-axis will be placed
	double y;//where on the y-axis will be placed
	double z;//z is used to make it look like is far away from the camera
	
	int width;//picture's width
	
//---------- constructor ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public ImageLayer(String filename, double x, double y, double z, int width){
		//TookKit loads the image in another thread, so it takes longer to paint in the Game.java 
		
		image = new ImageIcon(filename).getImage();//ImageIcon loads the image and waits for it to 
	      											//finish loading before painting anything(Developing Games in Java,pg. 36)
		this.x = x;//initializing x coordinate
		this.y = y;//initializing y coordinate
		this.z = z;//initializing the distance from this picture and camera
		this.width = width;//setting the width
	}
	
/******************************************************************************************************************************************************************************************************
 * 
 * @param g
 */
	public void draw(Graphics g){
		for(int i = 0; i < 4; i++){//places 4 continous pictures
			g.drawImage(image, (int)(x - Camera.x/z)+width*i, (int)(y), null);
			//placing the image, adjusting x according to depth, y location, and ImageObserver
		}
	}
}
