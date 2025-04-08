
public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int distanceX(Point p) {
		return Math.abs(p.getX()-x);
	}
	
	public int distanceY(Point p) {
		return Math.abs(p.getY()-y);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public String toString() {
		return "["+x+", "+y+"]";
	}
	public boolean equals(Point p) {
		return (x == p.getX() && y == p.getY());
	}
}
