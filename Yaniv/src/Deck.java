
public class Deck {
	
	Card[] cards;
	int last;
	boolean hasAce = true;
	
	public Deck(){
		this.cards = new Card[52];
		last = cards.length;
		int count = 0;
		for (int i = 1; i <= 13; i ++) {
			for (int j = 1; j <= 4; j++) {				
				cards[count] = new Card(i, j);
				count ++;
			}
		}
		hasAce = true;
	}
	public Deck(int size){
		this.cards = new Card[size];
		last = 0;
		hasAce = false;
	}
	
	public void removeFromDeck(int ind) {
		cards[ind] = null;
		boolean last = true;
		for (int i = 0; last && i < cards.length; i ++) {
			last = cards[cards.length-1-i] == null;
			if (!last) {
				swap(ind, cards.length-1-i, cards);
				this.last = cards.length-1-i;
			}
		}
	}
	public void addToDeck(Card card) {
		cards[last] = card;
		last ++;
		if(card.value == 1)
			hasAce = true;
	}
	
	public int sum() {
		int sum = 0;
		for (int i = 0; i < last; i++)
			sum += cards[i].value;
		return sum;
	}
	
	public static void swap(int n, int m, Card[] cards) {
		Card temp = cards[n];
		cards[n] = cards[m];
		cards[m] = temp;
	}
	
	public Card pullRandom() {
		int rand = 0+(int)(Math.random()*(last-1));
		Card temp = cards[rand];
		this.removeFromDeck(rand);
		return temp;
	}
	
}
