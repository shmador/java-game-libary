
public class Card {
	
	private int rank;
	private int suit;
	private boolean faceUp;

	public int getRank() {
		return rank;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
	
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	private String suitToString(int suit) {
		switch(suit) {
			case 0:
				return "h";
			case 1:
				return "d";
			case 2:
				return "c";
			case 3:
				return "s";
			default:
				return "";
		}
	}
	
	protected String rankToString(int rank) {
		switch(rank) {
			case 11:
				return "J";
			case 12:
				return "Q";
			case 13:
				return "K";
			default:
				return ""+rank;
		}
	}
	
	public String toString() {
		if (!faceUp)
			return "?";
		return rankToString(rank)+suitToString(suit);
	}
}
