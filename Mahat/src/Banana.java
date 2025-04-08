
public class Banana {
	private int weight;
	public Banana (int w) {
		weight = w;
	}
	public int getWeight () {
		return weight;
	}
	public boolean equals (Object other) {
		System.out.println("BANANA");
		return ((other != null) &&
		(other instanceof Banana) &&
		(weight == ((Banana)other).weight));
	}
}

