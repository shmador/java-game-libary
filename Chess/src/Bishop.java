
public class Bishop extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
		if(!(Math.abs(currRow - nextRow) == Math.abs(currCol - nextCol)))
			return false;
		return board.isPathClear(currRow, currCol, nextRow, nextCol) 
			&& board.isValidDestination(currRow, currCol, nextRow, nextCol);
	}
	public Bishop(int color) {
		super(color);
		setIcon('B');
	}
}
