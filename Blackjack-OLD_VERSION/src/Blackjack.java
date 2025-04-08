import java.util.Collections;
import java.util.Stack;
public class Blackjack {
	private Stack<Card> deck;
	public Blackjack() {
		deck = createDeck();
	}
	public static Stack<Card> createDeck() {
		Stack <Card> newDeck = new Stack<>();
		char[] suits = {'h', 'd', 's', 'c'};
		String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K"};
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				Card c = new Card(suits[j], cards[i]);
				newDeck.push(c);
			}
		}
		return newDeck;
	}
	public Card drawCard() {
		int len = deck.size();
		if (len > 0) {
			int rand = (int)(Math.random()*len);
			Collections.swap(deck, len-1, rand);
			return deck.pop();
		}
		else {
			deck = createDeck();
			return drawCard();
		}
	}
	public Stack<Card> getDeck() {
		return deck;
	}


}
