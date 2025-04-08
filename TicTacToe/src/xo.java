
public class xo {
	private char[][] board;
	public xo() {
		board = new char[3][3];
	}
	public void play(int cell, char player) {
		cell--;
		int row = cell / 3;
		int col = cell % 3;
		board[row][col] = player;
	}
	public boolean win(char player) {
		boolean rowWin = false, colWin = false, diagWin1 = false, diagWin2 = false;
		for (int i = 0; i < 3; i++) {
			if (!rowWin)
				rowWin = board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == player;
			if (!colWin)
				colWin = board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == player;
		}
		diagWin1 = board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == player;
		diagWin2 = board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[0][2] == player;
		if (rowWin || colWin || diagWin1 || diagWin2) {
			System.out.println(player + " wins");
			return true;
		}
		return false;
	}
	public void printBoard() {
		int count =0;
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				count++;
				if (board[i][j] == 0)
					System.out.print(count);
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
