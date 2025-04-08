
public class Potion implements Item {
	public int maxHeld() {
		return 20;
	}
	
	@Override
	public String toString() {
		return "Potion";
	}
}
