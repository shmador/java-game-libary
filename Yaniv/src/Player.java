
public class Player {
	String name;
	Deck hand;
	int hide;
	public Player (String name) {
		this.name = name;
		hand = new Deck(21);
		hide = -1;
	}
	
	public void hide(int hide) {
		this.hide = hide;
	}

	
	public String toString() {
		String str = name + " [";
		for(int i = 0; i< hand.last; i++) {
			if (i == hide)
				str += "?";
			else
				str = str + hand.cards[i];
			if (i < hand.last-1)
				str += " ";
		}	
		str += "] ";
		int sum = hand.sum();
		if (hide != -1 && hide < hand.last)
			sum -= hand.cards[hide].value;
		String sumStr = sum == 0 ? "" : ""+sum;
		str += "[" + sumStr + "]";
		return str;
	}
}
