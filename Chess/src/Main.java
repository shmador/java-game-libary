import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Game game = new Game();
		game.print();
		while (!game.gameOver()){
			game.moveInput(scan.nextLine());
			game.print();
		}
	}
}

