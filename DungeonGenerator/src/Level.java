import java.util.ArrayList;
import java.util.List;

public class Level {
	List<List<RoomObject>> levelGrid;
	
	RoomGenerator rg;
	
	public Level(int width, int height, RoomGenerator rg) {
		levelGrid = new ArrayList<>();
		this.rg = rg;
	}
	
	public void copy(RoomObject[][] grid, int x, int y) {

	}

}
