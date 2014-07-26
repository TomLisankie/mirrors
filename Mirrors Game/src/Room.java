
import gameobjects.*;

import java.util.*;

public class Room {
	
	ArrayList<GameObject> objectsInRoom = new ArrayList<GameObject>();
	ArrayList<Floor> roomFloors = new ArrayList<Floor>();
	Player thePlayer;
	
	//need to have a thing for different physical heights/levels in the room
	
	public void add(GameObject obj){
		
		objectsInRoom.add(obj);
		
	}
	
	public void addPlayer(Player player){
		
		add(player);
		thePlayer = player;
		
	}
	
	public Player getPlayer(){
		
		return thePlayer;
		
	}
	

}
