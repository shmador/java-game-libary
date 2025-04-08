
public class Knight extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
		int rowStep = Math.abs(currRow-nextRow);
		int colStep = Math.abs(currCol-nextCol);
		if (!(rowStep == 2 && colStep == 1 || rowStep == 1 && colStep == 2))
			return false;
		return board.isValidDestination(currRow, currCol, nextRow, nextCol);
	}
	public Knight(int color) {
		super(color);
		setIcon('N');
	}
}
