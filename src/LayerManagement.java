import java.awt.*;
import gameobjects.*;

public class LayerManagement {
	
	final int MAXLAYERNUMBER = 4; //one less than actual number of layers because arrays start at 0
	private Layer[] layers = new Layer[MAXLAYERNUMBER];
	
	public LayerManagement(){
		
		for(int i = 0; i< MAXLAYERNUMBER; i++){
			
			layers[i] = new Layer();
			
		}
		
	}
	
	public void addGameObjectToLayer(GameObject obj, int layerNumber){
		
		layers[layerNumber].add(obj);
		
		
	}
	
	public Layer getLayer(int layerNum){
		
		Layer layer = layers[layerNum];
		return layer;
		
	}
	
	public int getNumberOfLayers(){
		
		return layers.length;
		
	}

}
