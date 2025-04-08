import java.util.ArrayList;
import java.util.List;

public class RoomGenerator {
	List<Room> rooms;

	public RoomGenerator() {
		rooms = new ArrayList<>();
	}
	
	public Room randomRoom() {
		int index = (int)(Math.random()*rooms.size());
		return rooms.get(index);
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
}
