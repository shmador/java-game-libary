import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LiarsDice {
	private int turn;
	private Bid lastBid;
	private List<Player> players;
	public LiarsDice(String[] names) {
		players = new ArrayList<>();
		for (String n: names)
			players.add(new Player(n));
	}
	public void bid(int amount, int faceValue, Player player) {
		if (lastBid != null) {
			if (amount < lastBid.amount) {
				System.out.println("Cannot bid a smaller amount then the last bidder!");
				return;
			}
			if (amount == lastBid.amount && faceValue <= lastBid.faceValue) {
				System.out.println("Bids in the same amount must have a higher face value!");
				return;
			}
		}
		lastBid = new Bid(amount, faceValue, player);
		System.out.println(playing()+" bids: ["+amount+" "+faceValue+"s]");
		switchTurn();
	}
	public boolean callLiar() {
		int actual = sum(lastBid.faceValue);
		int bid = lastBid.amount;
		System.out.println(playing()+" calls out "+lastBidder()+" on a lie!");
		System.out.println("Total: ["+actual+"]\tBid: ["+bid+"]");
		if (bid <= actual) {
			System.out.println(lastBidder()+" was telling the truth!");
			return false;
		}
		System.out.println(lastBidder()+" was telling a lie!");
		return true;
	}
	public boolean callSpotOn() {
		int actual = sum(lastBid.faceValue);
		int bid = lastBid.amount;
		System.out.println(playing()+" is calling spot on!");
		System.out.println("Total: ["+actual+"]\tBid: ["+bid+"]");
		if(bid == actual) {
			System.out.println(playing()+" is spot on!");
			return true;
		}
		System.out.println(playing()+" missed by ["+Math.abs(actual-bid)+"]");
		return false;
	}
	public void play() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\t'e' to challange.\t'f' to call spot on.\n\t\t[amount value] to bid.");
		String in = scan.nextLine();
		if (validInput(in)) {
			if (in.length() == 1) { //player made a call, and didn't bid.
				if (lastBid == null) {
					System.out.println("Can't call on the first bid!");
					return;
				}
				char c = in.charAt(0);
				if (c == 'f' && callSpotOn() || c == 'e' && callLiar()) { playerLoss(lastBidder()); }
				else { playerLoss(playing()); }
				newRound();
			}
			else {
				String[] ins = in.split(" ");
				bid(Integer.parseInt(ins[0]), Integer.parseInt(ins[1]), playing());
			}
		}
		else {
			System.out.println("Input does not follow the format!");
			play();
		}
	}
	public void playerLoss(Player player) {
		player.removeDie();
		switchTurn(player);
		if (player.outOfDice()) {
			System.out.println(player+" is out of the game!");
			players.remove(player);
		}
	}
	public boolean validInput(String in) {
		if (in.length() <= 0) return false;
		int playerCount = players.size();
		String regex = "";
		if (playerCount == 4) regex = "^((1[0-9] |20 |[1-9] )(6|[1-5])|f|e)$";
		if (playerCount == 3) regex = "^((1[0-5] |[1-9] )(6|[1-5])|f|e)$";
		if (playerCount == 2) regex = "^((10 |[1-9] )(6|[1-5])|f|e)$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(in);
		return mat.matches();
	}
	public void switchTurn(Player player) {
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i) == player) {
				turn = i;
				return;
			}
		}
	}
	public boolean gameOver() {
		if(players.size() == 1) {
			System.out.println("The winner is "+players.get(0)+"!");
			return true;
		}
		return false;
	}
	public void switchTurn() {
		if (turn++ >= players.size()-1) turn =0;
	}
	public Player lastBidder() {
		return lastBid.bidder;
	}
	public Player playing() {
		return players.get(turn);
	}
	public void newRound() {
		lastBid = null;
		for (Player p: players)
			p.shuffle();
	}
	public int sum(int faceValue) {
		int sumCount = 0;
		for (Player p: players)
			sumCount=sumCount+p.getCount(faceValue)+p.getCount(1);
		return sumCount;
	}
	public void printGame() {
		players.get(0).printDice();
	}
	private class Die {
		private int number;
		public static final int LOWEST = 1;
		public static final int HIGHEST = 6;
		public static String[] diceFaces;
		public Die() {
			roll();
			diceFaces = new String[]{
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "|       | |\n"
				+ "|   o   | /\n"
				+ "|       |/ \n"
				+ "'-------'  ",
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "| o     | |\n"
				+ "|       | /\n"
				+ "|     o |/ \n"
				+ "'-------'  ",
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "| o     | |\n"
				+ "|   o   | /\n"
				+ "|     o |/ \n"
				+ "'-------'  ",
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "| o   o | |\n"
				+ "|       | /\n"
				+ "| o   o |/ \n"
				+ "'-------'  ",
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "| o   o | |\n"
				+ "|   o   | /\n"
				+ "| o   o |/ \n"
				+ "'-------'  ",
				  "  .-------.\n"
				+ " /       /|\n"
				+ "/_______/ |\n"
				+ "| o   o | |\n"
				+ "| o   o | /\n"
				+ "| o   o |/ \n"
				+ "'-------'  ",
			};
		}
		public void roll() {
			this.number = LOWEST+(int)(Math.random()*(HIGHEST-LOWEST+1));
		}
		public int getNumber() {
			return number;
		}
		public String toString() {
			return diceFaces[number-1];
		}
	}
	private class Bid {
		private int faceValue;
		private int amount;
		private Player bidder;
		public Bid(int amount, int faceValue, Player bidder) {
			this.faceValue = faceValue;
			this.amount = amount;
			this.bidder = bidder;
		}
	}
	private class Player {
		private Stack<Die> dice;
		private int[] counts;
		public static final int STARTING_DICES = 5;
		private String name;
		public Player(String name) {
			this.name = name;
			dice = new Stack<>();
			counts = new int[Die.HIGHEST+1];
			for (int i = 0; i < STARTING_DICES; i++) {
				Die die = new Die();
				counts[die.getNumber()]++;
				dice.push(die);
			}
		}
		public void shuffle() {
			counts = new int[Die.HIGHEST+1];
			for (int i = 0; i < dice.size(); i++) {
				dice.get(i).roll();
				counts[dice.get(i).getNumber()]++;
			}
		}
		public int getCount(int i) {
			return counts[i];
		}
		public void removeDie() {
			dice.pop();
		}
		public boolean outOfDice() {
			return dice.size() == 0;
		}
		public String toString() {
			return "" + name;
		}
	    public void printDice() {
	        StringBuilder[] rows = new StringBuilder[7];
	        for (int i = 0; i < 7; i++) 
	            rows[i] = new StringBuilder();
	        for (int i = 0; i < dice.size(); i++) {
	            String[] dieRows = Die.diceFaces[dice.get(i).number - 1].split("\n"); 
	            for (int j = 0; j < 7; j++) 
	                rows[j].append(dieRows[j]).append("  ");
	        }
	        for (StringBuilder row : rows) 
	            System.out.println(row.toString());
	    }
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = "";
		char c = 0;
		while (!('2' <= c && c <= '4') || in.length() != 1){
			System.out.println("How many players?[2-4]");
			in = scan.nextLine();
			if (in.length() > 0) c = in.charAt(0);
		}
		String[] names = new String[c-'0'];
		for (int i = 0; i < names.length; i++) {
			System.out.println("What is your name?[Player "+(i+1)+"]");
			names[i] = scan.nextLine();
		}
		LiarsDice game = new LiarsDice(names);
		while(!game.gameOver()) {
			game.printGame();
			game.play();
		}
	}
}
