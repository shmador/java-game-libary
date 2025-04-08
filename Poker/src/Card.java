
public class Card implements Comparable<Card> {
	private int rank;
	private char suit;
	private static int MAX_RANK = 14;

	public Card(int rank, char suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public boolean isAce() {
		return rank == MAX_RANK;
	}

	public int getRank() {
		return rank;
	}
	
	public char getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		if (rank <= 10)
			return "" + rank + suit;
		char[] royals = { 'J', 'Q', 'K', 'A' };
		return "" + royals[rank % 10 - 1] + suit;
	}

	@Override
	public int compareTo(Card c) {
		return this.rank - c.getRank();
	}
}
