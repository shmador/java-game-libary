
public class Move {
	private int colFrom;
	private int colTo;
	private int rowFrom;
	private int rowTo;
	Piece movedPiece;
	public Move(int rowFrom, int colFrom, int rowTo, int colTo, Piece piece) {
		this.colFrom = colFrom;
		this.colTo = colTo;
		this.rowFrom = rowFrom;
		this.rowTo = rowTo;
		movedPiece = piece;
	}
	public boolean isDoublePawn() {
		if (movedPiece == null || !(movedPiece instanceof Pawn)) return false;
		return Math.abs(rowTo - rowFrom) == 2;
	}
	public Piece getMovedPiece() {
		return movedPiece;
	}
	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}
	public int getColFrom() {
		return colFrom;
	}
	public void setColFrom(int colFrom) {
		this.colFrom = colFrom;
	}
	public int getColTo() {
		return colTo;
	}
	public void setColTo(int colTo) {
		this.colTo = colTo;
	}
	public int getRowFrom() {
		return rowFrom;
	}
	public void setRowFrom(int rowFrom) {
		this.rowFrom = rowFrom;
	}
	public int getRowTo() {
		return rowTo;
	}
	public void setRowTo(int rowTo) {
		this.rowTo = rowTo;
	}
}
