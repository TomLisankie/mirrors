
import gameobjects.*;

import java.util.*;

public class Room {
	
	ArrayList<GameObject> objectsInRoom = new ArrayList<GameObject>();
	
	//need to have a thing for different physical heights/levels in the room
	
	public void add(GameObject obj){
		
		objectsInRoom.add(obj);
		
	}
	

}
