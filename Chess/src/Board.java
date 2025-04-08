

public class Board {
	private Piece[][] board;
	final String WHITE_SQUARE = " "; 
	final String BLACK_SQUARE = "#";
	private Piece promoted;
	private Move lastMove;
	public Board() {
		promoted =null;
		board = new Piece[][]{
			{new Rook(-1), new Knight(-1), new Bishop(-1), new Queen(-1), new King(-1), new Bishop(-1), new Knight(-1), new Rook(-1)},
			{new Pawn(-1), new Pawn(-1), new Pawn(-1), new Pawn(-1), new Pawn(-1), new Pawn(-1), new Pawn(-1), new Pawn(-1)},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1)},
			{new Rook(1), new Knight(1), new Bishop(1), new Queen(1), new King(1), new Bishop(1), new Knight(1), new Rook(1)},
			};
	}
	public boolean isOutOfMoves(int color) {//is specified player is out of legal moves
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				Piece piece = board[row][col];
				if (piece == null || piece.getColor() != color)
					continue;
				for (int nextRow = 0; nextRow < board.length; nextRow++) {
					for (int nextCol = 0; nextCol < board[nextRow].length; nextCol++) {
						if (isBoardMoveValid(row, col, nextRow, nextCol)) {
							if (!simulateCheck(row, col, nextRow, nextCol, color))
								return false;
						}
					}
				}
			}
		}
		return true;
	}
	public void setLastMove(Move move) {
		lastMove = move;
	}
	public boolean isEnPassantPossible(int currRow, int currCol, int nextRow, int nextCol) {
		Piece piece = getPiece(currRow, currCol);
		if (piece == null || !(piece instanceof Pawn)) return false;
		if (lastMove == null || !lastMove.isDoublePawn()) return false;
		if (nextCol != lastMove.getColTo()) return false;
		if (Math.abs(nextRow -currRow) != 1 || Math.abs(nextCol -currCol) != 1 ) return false;
		if (lastMove.getRowTo() != nextRow + piece.getColor()) return false;
		return true;
	}
	public void performEnPassant(int currRow, int currCol, int nextRow, int nextCol) {
		Piece piece = getPiece(currRow, currCol);
		movePiece(currRow, currCol, nextRow, nextCol);
		board[nextRow + piece.getColor()][nextCol] = null;
	}
	public boolean simulateEnPassant(int currRow, int currCol, int nextRow, int nextCol, int color) {
		Piece piece = getPiece(currRow, currCol);
		Piece captured = board[nextRow + piece.getColor()][nextCol];
		Piece next = board[nextRow][nextCol];
		performEnPassant(currRow, currCol, nextRow, nextCol);
		boolean inCheck = isInCheck(color);
		board[currRow][currCol] = board[nextRow][nextCol];
		board[nextRow + piece.getColor()][nextCol] = captured;
		board[nextRow][nextCol] = next;
		return inCheck;
	}
	public boolean isInCheck(int color) {
		int kingRow = -1, kingCol = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Piece piece = board[i][j];
				if (piece != null && piece instanceof King && piece.getColor() == color) {
					kingRow = i;
					kingCol = j;
					break;
				}
			}
			if (kingRow != -1) break;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Piece piece = board[i][j];
				if (piece != null && piece.getColor() != color && isBoardMoveValid(i, j, kingRow, kingCol))
					return true;
			}
		}
		return false;
	}
	public boolean isBoardMoveValid(int currRow, int currCol, int nextRow, int nextCol) {//is move a valid board move, in castling cases returns true.
		Piece piece = getPiece(currRow, currCol);
		if (piece == null) return false;
		if (!piece.validMove(currRow, currCol, nextRow, nextCol, this)) return false;
		return true;
	}
	public Piece getPiece(int row, int col) {
		return board[row][col];
	}
	public boolean isPathClear(int currRow, int currCol, int nextRow, int nextCol) { //check if there are any pieces between "curr" and "next", non inclusive.
	    int rowStep = Integer.signum(nextRow - currRow); 
	    int colStep = Integer.signum(nextCol - currCol); 
	    int tempRow = currRow + rowStep;
	    int tempCol = currCol + colStep;
	    while (tempRow != nextRow || tempCol != nextCol) {
	        if (board[tempRow][tempCol] != null) return false;
	        tempRow += rowStep;
	        tempCol += colStep;
	    }
	    return true;
	}
	public boolean isValidDestination(int currRow, int currCol, int nextRow, int nextCol) {//true if "next" is occupied by the same player occupying "curr"
        Piece currPiece = getPiece(currRow, currCol);
        Piece nextPiece = getPiece(nextRow, nextCol);
        return nextPiece == null || currPiece.getColor() != nextPiece.getColor();
	}
	public int length() {
		return board.length;
	}
	public boolean isCastleMove(int currRow, int currCol, int nextRow, int nextCol) {//is castle move is possible, with the current input
	    if (currRow != nextRow) return false;
	    Piece king = board[currRow][currCol];
	    if (king == null || !(king instanceof King) || king.hasMoved()) return false;
	    int rookCol = getCastlingRook(currRow, nextCol);
	    if (rookCol == -1) {
	        return false; 
	    }
	    Piece rook = board[currRow][rookCol];
	    if (rook == null || !(rook instanceof Rook) || rook.hasMoved()) return false;
	    if (rook.getColor() != king.getColor()) return false;
	    if (!isPathClear(currRow, currCol, currRow, rookCol)) return false;
	    for (int col = Math.min(currCol, nextCol); col <= Math.max(currCol, nextCol); col++) {
	    	if (col == currCol) continue;
    		if (simulateMoveCheck(currRow, currCol, currRow, col, king.getColor()))
    			return false;
	    }
	    return true;
	}
	protected void movePiece(int currRow, int currCol, int nextRow, int nextCol) {
	    board[nextRow][nextCol] = board[currRow][currCol];
	    board[currRow][currCol] = null;
	}
	protected void performCastle(int currRow, int currCol, int nextRow, int nextCol) {
	    int rookCol = getCastlingRook(currRow, nextCol);
	    movePiece(currRow, currCol, nextRow, nextCol);
	    int rookNewCol = nextCol + 1;
	    if(nextCol > currCol) 
	    	rookNewCol = nextCol - 1;
	    movePiece(currRow, rookCol, currRow, rookNewCol);
	}
	public void setPromoted(Piece promoted) {
		this.promoted = promoted;
	}
	public boolean isPromotion(int currRow, int currCol, int nextRow, int nextCol) {
		Piece pawn = getPiece(currRow, currCol);
		if (pawn == null || !(pawn instanceof Pawn)) return false;
		if (nextRow != 0 && nextRow != 7) return false;
		return true;
	}
	public void performPromotion(int currRow, int currCol, int nextRow, int nextCol) {
		board[currRow][currCol] = promoted;
	}
	public Piece getNewPiece(char icon, int color) {
		if (icon == 'R') return new Rook(color);
		if (icon == 'B') return new Bishop(color);
		if (icon == 'N') return new Knight(color);
		return new Queen(color);
	}
	protected boolean simulateCastleCheck(int currRow, int currCol, int nextRow, int nextCol, int color) {//true if castling puts specified player in check. use with isCastleMove(), won't validate if castling is legal
    	int step = (int)Math.signum(nextCol-currCol);
    	performCastle(currRow, currCol, nextRow, nextCol);
    	boolean inCheck = isInCheck(color);
    	board[currRow][currCol] = board[nextRow][nextCol];
    	board[nextRow][nextCol] = null;
    	board[currRow][getCastlingRook(currRow,nextCol)] = board[currRow][nextCol-step];
    	board[currRow][nextCol-step] = null;
    	return inCheck;
	}
	protected boolean simulateCheck(int currRow, int currCol, int nextRow, int nextCol, int color) {//moves a piece, checks if specified player is in check, then reverts the move.
	    if (isEnPassantPossible(currRow, currCol, nextRow, nextCol))
	    	return simulateEnPassant(currRow, currCol, nextRow, nextCol, color);
		if (isCastleMove(currRow, currCol, nextRow, nextCol)) 
	    	return simulateCastleCheck(currRow, currCol, nextRow, nextCol, color);
	    return simulateMoveCheck(currRow, currCol, nextRow, nextCol, color);
	}
	protected void performMove(int currRow, int currCol, int nextRow, int nextCol) {
	    if (isEnPassantPossible(currRow, currCol, nextRow, nextCol)) {
	    	performEnPassant(currRow, currCol, nextRow, nextCol);
	    	return;
	    }
		if (isCastleMove(currRow, currCol, nextRow, nextCol)) {
	    	performCastle(currRow, currCol, nextRow, nextCol);
	    	return;
		}
		if (isPromotion(currRow, currCol, nextRow, nextCol)) 
			performPromotion(currRow, currCol, nextRow, nextCol);
	    movePiece(currRow, currCol, nextRow, nextCol);
	    return;
	}
	protected boolean simulateMoveCheck(int currRow, int currCol, int nextRow, int nextCol, int color) {//simulates a move without validating it, true if specified player in check, use with isValidBoardMove()
	    Piece capturedPiece = board[nextRow][nextCol];
	    movePiece(currRow, currCol, nextRow, nextCol);
	    boolean inCheck = isInCheck(color);
	    board[currRow][currCol] = board[nextRow][nextCol];
	    board[nextRow][nextCol] = capturedPiece;
	    return inCheck;
	}
	private int getCastlingRook(int row, int col) {//returns rook column index, to the relevant side castling
		if (col == 6) return 7;
		if (col == 2) return 0;
		return -1;
	}
	public void printBoard() {
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0)
					System.out.print(board.length-i + " ");
				if (board[i][j] != null)
					System.out.print(board[i][j]+ " ");
				else if ((i+j)%2 == 0)
					System.out.print(WHITE_SQUARE + " ");
				else
					System.out.print(BLACK_SQUARE + " ");
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int j = 0; j < board[0].length; j++) 
			System.out.print((char)('a'+j) + " ");
		System.out.println();
		System.out.println();
	}
}
