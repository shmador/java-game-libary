
public class Main {
	public static void main (String[] args) {
		Room rm = new Room(10, 5);
		
		System.out.println(rm.doorPositions());
		
		rm.display();
		
		
		rm.rotate90();
		
		System.out.println(rm.doorPositions());
		
		rm.display();
		
		rm.rotate90();
		
		System.out.println(rm.doorPositions());
		
		rm.display();
	}
}
