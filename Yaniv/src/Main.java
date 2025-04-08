import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Deck deck = new Deck();
		Player p1 = new Player("Dor");
		Player dealer = new Player("Dealer");
		dealer.hide(1);
		for(int i = 0; i < 3; i ++) {
			Player p = i%2 == 0 ? dealer : p1;
			p.hand.addToDeck(deck.pullRandom());
		}
		boolean player = true;
		while(player) {
			System.out.println(p1);
			System.out.println(dealer);
			if (p1.hand.sum() > 21) {
				System.out.print("bust");
				return;
			}
			else
				System.out.println("h/s?");
			String str = in.next();
			if(str.equals("h"))
				p1.hand.addToDeck(deck.pullRandom());
			else if(str.equals("s"))
				player = false;

		}
		System.out.println(p1);


		
		
	}
	

}
