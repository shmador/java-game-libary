
public class Ace extends Card {
	public Ace(int suit) {
		super(1, suit);
	}
	@Override
	protected String rankToString(int rank) {
		return "A";
	}
}
