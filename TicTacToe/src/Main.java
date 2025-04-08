import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		xo game = new xo();
		char player;
		int turn = 1;
		do {
			turn *= -1;
			player = 'X';
			if (turn == 1) player = 'O';
			game.play(in.nextInt(), player);
			game.printBoard();
		}
		while (!game.win(player));
	}

}
