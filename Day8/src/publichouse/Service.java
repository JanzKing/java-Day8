package publichouse;
import java.util.*; 

public class Service {
	private static Function function = new Function();
	public static int addrooms(Vector<String>RoomInfo){
		if(function.insertRoom(RoomInfo)==-1)
			return -1;
		return 0;
	}
	/*public static int modifyRoom(int updateRoomID,Vector<String>RoomInfo)
	{
		if(function.updateRoom(updateRoomID,RoomInfo)==-1)
			return -1;
		return 0;
	}*/
}
