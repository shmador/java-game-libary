
public class Main {

	public static void main(String[] args) {
		CardDeck cd = new CardDeck(2);
		
		System.out.println(cd);

		cd.shuffle();
		
		System.out.println(cd);
		
		System.out.println(cd.deal(true));

	}

}
