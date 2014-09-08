package gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	
	//represents image of a Character
	
	private BufferedImage imgL;
	private Image image;
	private int layerNumber;
	
	public Sprite(Image image){
		
		this.image = image;
	}
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image,x,y,null);
	}
	
	public void setImage(BufferedImage img){
		
		imgL = img;
		
	}
	
	public BufferedImage getImage(){
		
		return imgL;
		
	}
	public int getLayerNumber() {
		return layerNumber;
	}
	public void setLayerNumber(int layerNumber) {
		this.layerNumber = layerNumber;
	}

}
