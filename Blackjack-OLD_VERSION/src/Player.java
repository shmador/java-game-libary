import java.util.ArrayList;
import java.util.List;

public class Player {
	private int sum;
	private int aces;
	private List<Card> hand;
	String name;
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
	}
	public Player() {
		this("Player");
	}
	public void addCard(Card card) {
		hand.add(card);
		sum += card.getValue();
		char c = card.getCard().charAt(0);
		if (c == 'A') {
			aces ++;
		}
		while(sum > 21 && aces > 0) {
			sum -= 10;
			aces --;
		}
	}

	public int getSum() {
		return sum;
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		return "<"+name+">"+hand+"("+sum+")";
	}
}
