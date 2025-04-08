public class Apple {
	private int weight;
	public Apple (int w) {
		weight = w;
	}
	public int getWeight () {
		return weight;
	}
	public boolean equals(Apple other) {
		System.out.println("APPLE");
		return ((other!=null) &&
		(weight == other.weight));
	}
}
