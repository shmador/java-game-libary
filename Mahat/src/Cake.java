
public class Cake {
	private boolean fresh;
	private static int id = 1;
	public Cake(boolean fresh) {
		this.fresh=fresh;

	}
	public boolean isFresh() {
		return fresh;
	}
	
	public String toString() {
		return ""+id+++" "+fresh;
	}
}
