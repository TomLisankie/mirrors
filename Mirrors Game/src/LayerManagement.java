import java.awt.*;
import gameobjects.*;

public class LayerManagement {
	
	final int MAXLAYERNUMBER = 5;
	Layer[] layers;
	
	public LayerManagement(){
		
		layers = new Layer[MAXLAYERNUMBER];
		
	}
	
	public void addGameObjectToLayer(GameObject obj, int layerNumber){
		
		layers[layerNumber].add(obj);
		
		
	}

}
