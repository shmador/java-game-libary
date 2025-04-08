import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck{
	
	List<Card> cards;
	
	private final int JOKERS;
	private final int MAX_SUITS = 4;
	private final int MAX_RANKS = 13;
	
	public CardDeck(int jokers) {
		cards = new ArrayList<>();
		JOKERS = jokers;
		createDeck();
	}
	
	public CardDeck() {
		this(0);
	}
	
	private void createDeck() {
		for (int i = 0; i < JOKERS; i++)
			cards.add(new Joker());
		
		for (int i = 0; i < MAX_SUITS; i++)
			for(int j = 0; j < MAX_RANKS; j++) {
				if (j == 0)
					cards.add(new Ace(i));
				else
					cards.add(new Card(j+1, i));
			}
	}
	
	public Card deal(boolean faceUp) {
		Card card = cards.remove(cards.size()-1);
		card.setFaceUp(faceUp);
		return card;
	}
	
	public void shuffle() {
		for (int i = cards.size()-1; i >= 0; i--) {
			int index = (int)(Math.random()*(i+1));
			Collections.swap(cards, i, index);
		}
	}
	
	public String toString() {
		return ""+cards;
	}
}
