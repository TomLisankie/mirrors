package gameobjects;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class GameObject implements Serializable {

	private BufferedImage image;
	
	
	//I am God. All objects have a little bit of me in them. 
	
	public GameObject (){
		
	}
	public GameObject(BufferedImage img ){
		image = img;
		
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
}
