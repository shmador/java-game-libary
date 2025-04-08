import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
	private Board board;
	private int turn;
	private boolean gameEnd;
	public Game() {
		board = new Board();
		turn = 1;
	}
	public void playerTurn(int currRow, int currCol, int nextRow, int nextCol) {//implements player's move, and checks if game is over.
		Piece piece = board.getPiece(currRow, currCol);
		if (piece == null) {
			System.out.println("No piece at given position");
			return;
		}
		if (turn != piece.getColor()) {
			System.out.println("Piece belongs to the opponent");
			return;
		}
		if (!board.isBoardMoveValid(currRow, currCol, nextRow, nextCol)) {
			System.out.println("Move is not possible");
			return;
		}
		if (board.simulateCheck(currRow, currCol, nextRow, nextCol, turn)) {
			System.out.println("In check");
			return;
		}
		board.performMove(currRow, currCol, nextRow, nextCol);
		board.setLastMove(new Move(currRow, currCol, nextRow, nextCol, piece));
		piece.setMoved();
		switchTurn();
		if (board.isOutOfMoves(turn)) {
			if(board.isInCheck(turn)) {
				if (-turn == 1)
					System.out.println("1-0");
				else
					System.out.println("0-1");
			}
			else
				System.out.println("1/2-1/2");
			gameEnd =true;
		}
	}
	public boolean gameOver() {
		return gameEnd;
	}
	public void moveInput(String str) {//translates chess notation to board coordinates, also validates out of bounds 
		String chessMovePattern = "^(?:[KQRBN]?[a-h][1-8](?:x?[a-h][1-8])?|[a-h][1-8](?:[a-h])?|O-O(-O)?)(?:=[QRBN])?$";
		Pattern pat = Pattern.compile(chessMovePattern);
		Matcher match = pat.matcher(str);
		if (match.matches()) {
			int rowTo = -1;
			int colTo = -1;
			int colFrom = -1;
			int rowFrom = -1;
			char icon = 0;
			char promotionIcon = 0;
			boolean promotion = false;
			for (char c: str.toCharArray()) {
				if (c == '=') {
					promotion = true;
				}
				if (Character.isLowerCase(c)) {
					int col = c-'a';
					if (colTo == -1)
						colFrom = col;
					colTo = col;
				}
				if (Character.isDigit(c)) {
					int row = 8 - (c - '0');
					if (rowTo == -1)
						rowFrom = row;
					rowTo = row;
				}
				if (Character.isUpperCase(c)) {
					if(promotion)
						promotionIcon = c;
					else
						icon = c;
				}
			}
			if (rowFrom != rowTo || colFrom != colTo) {//moves like Nb1c3
				playerTurn(rowFrom, colFrom, rowTo, colTo);
				return;
			}
			if (icon == 'O') {//castling
				rowTo = 0;
				if (turn == 1)
					rowTo = 7;
				if (str.equals("O-O-O")) {
					playerTurn(rowTo, 4, rowTo, 2);
					return;
				}
				playerTurn(rowTo, 4, rowTo, 6);
				return;
			}
			if (icon == 0)
				icon = 'P';
			for (int row = 0; row < board.length(); row++) {//all other moves, like e4, Nf6
				for (int col = 0; col < board.length(); col++) {
					Piece piece = board.getPiece(row,col);
					if (piece == null || piece.getIcon() != icon || piece.getColor()!= turn) continue;
					if (board.isBoardMoveValid(row, col, rowTo, colTo)) {
						if (board.isPromotion(row, col, rowTo, colTo))
							board.setPromoted(board.getNewPiece(promotionIcon, turn));
						playerTurn(row, col, rowTo, colTo);
						return;
					}
				}
			}
		}
		System.out.println("Invalid move");
	}
	private void switchTurn() {
		turn *= -1;
	}
	public void print() {
		board.printBoard();
	}
}
