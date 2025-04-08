
public class Card {
	private char suit;
	private String card;
	private int value;
	public int getValue() {
		return value;
	}
	public char getSuit() {
		return suit;
	}
	public String getCard() {
		return card;
	}
	protected Card(char suit, String card) {
		this.suit = suit;
		this.card = card;
		char c = card.charAt(0);
		boolean isNum = '0' <= c && c <= '9';
		if (c == 'A')
			value = 11;
		else if (!isNum || card.equals("10"))
			value = 10;
		else
			value = c-'0';
	}
	public String toString() {
		return card+suit;
	}

	
}
