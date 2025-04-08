
public class Joker extends Card {
	public Joker() {
		super(0, -1);
	}
	@Override
	protected String rankToString(int rank) {
		return "JOKER";
	}
}
