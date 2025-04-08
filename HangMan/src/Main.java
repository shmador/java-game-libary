import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		HangMan game = new HangMan();
		Scanner scan = new Scanner(System.in);
		while (!game.lose() && !game.win()) {
			String in = scan.next().toUpperCase();
			if (game.guess(in.charAt(0)))
				System.out.println(game);
		}
		boolean isWin = game.win();
		if (isWin)
			System.out.println("YOU WIN");
		else
			System.out.println("YOU LOSE");
		scan.close();
	}

}
