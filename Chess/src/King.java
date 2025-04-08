
public class King extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
		if (board.isCastleMove(currRow, currCol, nextRow, nextCol)) 
			return true;
        int rowStep = Math.abs(currRow - nextRow);
        int colStep = Math.abs(currCol - nextCol);
        if (rowStep > 1 || colStep > 1) 
            return false;
        return board.isValidDestination(currRow, currCol, nextRow, nextCol);
	}
	public King(int color) {
		super(color);
		setIcon('K');
	}
}
