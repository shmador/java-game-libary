
public class Board {
	private char[][] board;
	private final int COLUMNS;
	private final int ROWS;
	private boolean isWin;
	private int turns;
	public Board() {
		ROWS = 6;
		COLUMNS = 7;
		board = new char[ROWS][COLUMNS];
	}
	public boolean getWin() {
		return isWin;
	}
	public boolean insertToken(String input, char player) {//returns true if move is legal
		int column = -1;
		if (input.length()>0) {
			char c = input.charAt(0);
			if (Character.isDigit(c))
				column = c-'0'-1;
		}
		if (0 > column || column >= COLUMNS) {
			System.out.println("input not in range 1-"+COLUMNS);
			return false;
		}
		for (int i = ROWS-1; i >= 0; i--) {
			if (board[i][column] == 0) {
				board[i][column] = player;
				turns++;
				isWin = win(i, column, player);
				return true;
			}
		}
		System.out.println("column is full");
		return false;
	}
	private boolean win(int row, int col, char player) {
		if (turns >= ROWS * COLUMNS) {
			System.out.println("tie");
			return true;
		}
		int rowCount = 0;
		int colCount = 0;
		int majorDiagonalCount = 0;
		int minorDiagonalCount = 0;
		for (int i = -3; i <= 3; i++) {
			boolean rowInBounds = 0 <= row+i && row+i < ROWS;
			boolean colInBounds = 0 <= col+i && col+i < COLUMNS;
			boolean rowInBounds2 = 0 <= row-i && row-i < ROWS;
			if (rowInBounds&&board[row+i][col] == player)
				rowCount++;
			if (colInBounds&&board[row][col+i] == player)
				colCount++;
			if (rowInBounds&&colInBounds&&board[row+i][col+i] == player)
				majorDiagonalCount++;
			if (rowInBounds2&&colInBounds&&board[row-i][col+i] == player)
				minorDiagonalCount++;
		}
		if (rowCount >= 4 || colCount >= 4 || majorDiagonalCount >= 4 || minorDiagonalCount >= 4) {
			System.out.println(player + " wins");
			return true;
		}
		return false;
	}
	public void printBoard() {
		for (int i = 0; i < COLUMNS; i ++) 
			System.out.print(i+1);
		System.out.println();
		for (int i = 0; i < ROWS; i ++) {
			for (int j = 0; j < COLUMNS; j ++) {
				if (board[i][j] != 0)
					System.out.print(board[i][j]);
				else
					System.out.print("-");
			}
			System.out.println();
		}
	}
}
