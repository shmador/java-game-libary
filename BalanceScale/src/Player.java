
public abstract class Player {
	private int guess;
	private int points;
	private String name;
	private static int id=1;
	
	public Player() {
		this("Player"+id++);
	}
	public Player(String name) {
		points=0;
		this.name=name;
	}
	
	public int getGuess() {
		return guess;
	}
	
	protected void setGuess(int guess) {
		this.guess=guess;
	}
	
	public abstract void takeGuess();
	
	public boolean isValidGuess(int guess) {
		if (guess < Game.MIN_GUESS || guess > Game.MAX_GUESS) {
			System.out.println("<guess a number between "+Game.MIN_GUESS+" and "+Game.MAX_GUESS+">");
			return false;
		}
		return true;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void reducePoint() {
		points--;
	}
	public String toString() {
		return name;
	}
}
