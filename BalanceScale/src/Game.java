import java.util.Stack;

public class Game {
	private Stack<Player> players;
	public static final double MULTIPLIER=0.8;
	public static final int MIN_GUESS=0;
	public static final int MAX_GUESS=100;
	public static final int MIN_POINTS=-10;
	private boolean gameOver;
	
	public Game(Stack<Player> players) {
		this.players=players;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	private double calculateAvarage() {
		int sum = calculateSum();
		int players = calculatePlayers();
		double avg = ((double)sum/players);
		System.out.println("\n[Results]");
		System.out.printf("Avarage \t%.2f%n", avg);
		avg*=MULTIPLIER;
		System.out.printf("Times "+MULTIPLIER+"\t%.2f%n",avg);
		return avg;
	}
	
	private int calculateSum() {
		if (players.isEmpty())
			return 0;
		Player p = players.pop();
		int res = p.getGuess() + calculateSum();
		players.push(p);
		return res;
	}
	
	private int calculatePlayers() {
		if (players.isEmpty())
			return 0;
		Player p = players.pop();
		int res = 1 + calculatePlayers();
		players.push(p);
		return res;
	}
	
	private void eliminatePlayers() {
		if (players.isEmpty())
			return;
		Player p = players.pop();
		eliminatePlayers();
		int points = p.getPoints();
		if (points <= MIN_POINTS) 
			System.out.println("\n<"+p+" has been eliminated>");
		else
			players.push(p);
	}
	private double calculateClosest() {
		return calculateClosest(calculateAvarage());
	}
	private double calculateClosest(double target) {
		if(players.isEmpty())
			return MAX_GUESS;
		Player p = players.pop();
		double val = Math.abs(p.getGuess()-target);
		double res = Math.min(val, calculateClosest(target));
		players.push(p);
		return res;
	}
	private Stack<Player> calculateLosers() {
		double avg = calculateAvarage();
		System.out.println("\n[Winners]");
		return calculateLosersHelper(new Stack<Player>(), calculateClosest(avg), avg);
	}
	private Stack<Player> calculateLosersHelper(Stack<Player> s, double min, double target) {
		if (players.isEmpty())
			return s;
		Player p = players.pop();
		double val = Math.abs(p.getGuess()-target);
		if (val != min)
			s.push(p);
		else
			System.out.println(p);
		Stack<Player> res = calculateLosersHelper(s, min, target);
		players.push(p);
		return res;
	}
	public void calculatePoints() {
		calculatePointsHelper(calculateLosers());
	}
	private void calculatePointsHelper(Stack<Player> losers) {
		if (losers.isEmpty()) 
			return;
		Player p = losers.pop();
		p.reducePoint();
		System.out.println("\n<"+p+" has lost a point>");
		calculatePointsHelper(losers);
		losers.push(p);
	}
	private void takeGuesses() {
		if (players.isEmpty())
			return;
		Player p = players.pop();
		takeGuesses();
		p.takeGuess();
		players.push(p);
	}
	public void displayPoints() {
		if (players.isEmpty()) {
			System.out.println("\n[Points]");
			return;
		}
		Player p = players.pop();
		displayPoints();
		System.out.println(p+"\t"+p.getPoints());
		players.push(p);
	}
	private void displayGuesses() {
		if (players.isEmpty()) {
			System.out.println("\n[Guesses]");
			return;
		}
		Player p = players.pop();
		displayGuesses();
		System.out.println(p+"\t"+p.getGuess());
		players.push(p);
	}
	
	public void playRound() {
		takeGuesses();
		displayGuesses();
		calculatePoints();
		displayPoints();
		eliminatePlayers();
		System.out.println();
		gameOver=calculatePlayers()<=1;
		if (gameOver)
			System.out.println("<The winner is "+players.peek()+">");
	}
	
	public static void main (String[] args) {
		Stack<Player> s = new Stack<>();
		String[] names= {"Dor", "Shimon", "Fufu"}; 
		for (int i = 0; i < 5; i++) 
			if (i < names.length)
				s.push(new HumanPlayer(names[i]));
			else
				s.push(new HumanPlayer());
		Game g = new Game(s);
		while(!g.isGameOver())
			g.playRound();
	}
}
