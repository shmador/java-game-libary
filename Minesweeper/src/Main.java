import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Minesweeper game = new Minesweeper(16,30,99);
		game.printBoard();
		boolean isPlaying = true;
		boolean firstTurn = true;
		while (isPlaying) {
			System.out.println("Enter tile (COLUMN A-"+(char)(game.getCols()+'A'-1)+", ROW 1-"+game.getRows()+"), flag with '!'.");
			if (firstTurn)
				System.out.println("Example: A1, B10, P30, A1!, B10!");
			Tile selected = null;
			while (selected == null) 
				selected = game.string2Tile(in.nextLine());
			isPlaying = !game.checkTile(selected) && !game.win();
			game.printBoard();
			firstTurn = false;
		}
		if (game.win())
			System.out.println("YOU WIN");
		else
			System.out.println("YOU LOSE");
	}

}
