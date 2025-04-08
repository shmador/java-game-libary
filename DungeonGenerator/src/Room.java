import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private RoomObject[][] roomGrid;
	
	public RoomObject[][] getRoomGrid() {
		return roomGrid;
	}
	
	public int getWidth() {
		return roomGrid[0].length;
	}
	
	public int getHeight() {
		return roomGrid.length;
	}
	
	public Room(int width, int height) {
		roomGrid = new RoomObject[height][width];
		
		int rows = roomGrid.length;
		int cols = roomGrid[0].length;
		
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (i % (rows-1) == 0 || j % (cols-1) == 0)
					roomGrid[i][j] = new Wall();
				else
					roomGrid[i][j] = new Floor();
			}
		
		place(new Door(), rows/2, cols-1);
		
		place(new Door(), 0, cols/2);
	}
	
	public void place(RoomObject obj, int x, int y) {
		roomGrid[x][y] = obj;
	}
	
	public void remove(int x, int y) {
		roomGrid[x][y] = new Floor();
	}
	
	public void swap(int x1, int y1, int x2, int y2) {
		RoomObject temp = roomGrid[x1][y1];
		roomGrid[x1][y1] = roomGrid[x2][y2];
		roomGrid[x2][y2] = temp;
	}
	
	public void rotate90() {
		RoomObject rotated[][] = new RoomObject[roomGrid[0].length][roomGrid.length];
		for (int i = 0; i < roomGrid.length; i++)
			for (int j = 0; j < roomGrid[i].length; j++)
				rotated[j][roomGrid.length-1-i] = roomGrid[i][j];
		roomGrid = rotated;
	}
	
	public List<Point> doorPositions() {
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < roomGrid.length; i++)
			for (int j = 0; j < roomGrid[i].length; j++)
				if (roomGrid[i][j] instanceof Door)
					points.add(new Point(i, j));
		return points;
	}
	
	public void display() {
		for (int i = 0; i < roomGrid.length; i++) {
			for (int j = 0; j < roomGrid[i].length; j++)
				System.out.print(roomGrid[i][j]);
			System.out.println();
		}
		System.out.println();
	}
}
