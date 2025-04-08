public class Queen extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
        boolean isDiagonal = Math.abs(currRow - nextRow) == Math.abs(currCol - nextCol);
        boolean isStraight = currRow == nextRow || currCol == nextCol;
        if (!isDiagonal && !isStraight) return false;
		return board.isPathClear(currRow, currCol, nextRow, nextCol) 
			&& board.isValidDestination(currRow, currCol, nextRow, nextCol);
	}
	public Queen(int color) {
		super(color);
		setIcon('Q');
	}
}
