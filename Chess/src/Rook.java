
public class Rook extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
		if (!(currRow == nextRow || currCol == nextCol))
			return false;
		return board.isPathClear(currRow, currCol, nextRow, nextCol) 
			&& board.isValidDestination(currRow, currCol, nextRow, nextCol);
	}
	public Rook(int color) {
		super(color);
		setIcon('R');
	}
}
