import gameobjects.*;
import java.util.*;

public class Layer {
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public void add(GameObject obj){
		
		gameObjects.add(obj);
		
	}
	
	public ArrayList<GameObject> getObjectsOnLayer(){
		
		return gameObjects;
		
		
	}

}
