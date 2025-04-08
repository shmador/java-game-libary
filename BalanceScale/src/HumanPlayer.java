import java.util.Scanner;
public class HumanPlayer extends Player {
	Scanner scan;
	public HumanPlayer() {
		super();
		scan = new Scanner(System.in);
	}
	public HumanPlayer(String name) {
		super(name);
		scan = new Scanner(System.in);
	}
	@Override
	public void takeGuess() {
		System.out.println("["+this+"]\t<enter your guess>");
		String in;
		do 
			in = scan.nextLine();
		while(!isNumber(in) || !isValidGuess(Integer.parseInt(in)));
		setGuess(Integer.parseInt(in));
	}
	
	public boolean isNumber(String s) {
		String error = "<"+s+" is not a number>";
		if (s.length() == 0) {
			System.out.println(error);
			return false;
		}
		for (char c: s.toCharArray())
			if ('9' < c || c < '0') {
				System.out.println(error);
				return false;
			}
		return true;
	}
}
