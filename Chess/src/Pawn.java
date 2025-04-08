
public class Pawn extends Piece{
	public boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board) {
	    int rowStep = nextRow - currRow;
	    int colStep = nextCol - currCol;
	    int direction = getColor(); // White: -1, Black: 1
	    boolean regularMove = colStep == 0 && rowStep == -direction 
	                          && board.getPiece(nextRow, nextCol) == null;
	    boolean doubleMove = !hasMoved() && colStep == 0 && rowStep == -2 * direction 
	                         && board.isPathClear(currRow, currCol, nextRow, nextCol)
	                         && board.getPiece(nextRow, nextCol) == null;
	    boolean diagonalCapture = Math.abs(colStep) == 1 && rowStep == -direction 
	                              && board.getPiece(nextRow, nextCol) != null;
	    boolean enPassant = Math.abs(colStep) == 1 && rowStep == -direction 
	                       && board.isEnPassantPossible(currRow, currCol, nextRow, nextCol);
	    return (regularMove || doubleMove || diagonalCapture || enPassant) 
	           && board.isValidDestination(currRow, currCol, nextRow, nextCol);
		
	}
	public Pawn(int color) {
		super(color);
		setIcon('P');
	}
}
