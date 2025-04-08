
public class Card {
	int num;
	int symbol;
	int value;
	
	public Card(int num, int symbol) {
		this.num = num;
		this.symbol = symbol;
		if (num > 10)
			value = 10;
		else value = num;
	}
	
	public String toString() {
		String numStr = "" + num;
		switch(num) {
			case 1 : numStr = "A"; break;
			case 11 : numStr = "J"; break;
			case 12 : numStr = "Q"; break;
			case 13 : numStr = "K"; break;
		}
		String symbolStr = "";
		switch(symbol) {
			case 1 : symbolStr = "h"; break;
			case 2 : symbolStr = "d"; break;
			case 3 : symbolStr = "s"; break;
			case 4 : symbolStr = "c"; break;
		}
		return numStr + symbolStr;	
	}
}
