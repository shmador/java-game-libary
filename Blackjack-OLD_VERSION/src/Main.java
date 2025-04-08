import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		boolean newGame = true;
		Scanner in = new Scanner(System.in);
		final int DEFAULT_CASH = 100;
		int cash = DEFAULT_CASH;
		int bet = 0;
		while(newGame) {
			System.out.println("["+cash+"$]");
			System.out.println("HOW MUCH TO BET?(1-"+cash+")");
			while(!in.hasNextInt()) {
				System.out.println("HOW MUCH TO BET?(1-"+cash+")");
				in.next();
			}
			bet = in.nextInt();
			if (bet > cash)
				bet = cash;
			if (bet < 0)
				bet = 1;
			cash -= bet;
			Blackjack bj = new Blackjack();
			Player p1 = new Player();
			Player p2 = new Player("Dealer");
			Player p = p1;
			for (int i = 0; i < 3; i++) { //deal cards
				if (i%2 == 0)
					p = p1;
				else
					p = p2;
				p.addCard(bj.drawCard());
			}
			System.out.println(p1);
			System.out.println(p2);
			p2.addCard(bj.drawCard());
			if (p2.getSum() == 21) { //early blackjack
				System.out.println(p2);
				if (p1.getSum() == 21) {
					System.out.println("PUSH");
					cash += bet;
				}
				else
					System.out.println("BLACKJACK");
			}
			else if(p1.getSum() == 21) {
				System.out.println("BLACKJACK");
				cash += bet*2.5;
			}
			else {//hit or stand
				boolean hit = true;
				while(p1.getSum() < 21 && hit) {
					System.out.println("HIT OR STAND?(H/S)");
					String input = in.next().toUpperCase();
					char c = input.charAt(0);
					hit = c != 'S';
					if (c != 'H' && hit);
					else if (hit) {
						p1.addCard(bj.drawCard());
						System.out.println(p1);
					}
				}
				int p1Sum = p1.getSum();
				if(p1Sum > 21)
					System.out.println("BUST");
				else {//dealer's turn
					System.out.println(p2);
					while(p2.getSum() < 17) {
						p2.addCard(bj.drawCard());
						System.out.println(p2);
					}//decide winner
					int p2Sum = p2.getSum();
					if (p1Sum > p2Sum || p2Sum > 21) {
						System.out.println(p1.getName() + " WINS");
						cash += bet*2;
					}
					else if (p1Sum == p2Sum) {
						System.out.println("PUSH");
						cash += bet;
					}
					else
						System.out.println(p2.getName() + " WINS");
				}
			}
			if (cash <= 0)
				cash = DEFAULT_CASH;
			System.out.println("NEW GAME? (Y/N)");//new game
			String input = in.next().toUpperCase();
			char c = input.charAt(0);
			while (c != 'Y' && c != 'N') {
				System.out.print("NEW GAME?(Y/N)");
				input = in.next().toUpperCase();
				c = input.charAt(0);
			}
			newGame = c == 'Y';
		}
	}
}
