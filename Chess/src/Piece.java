
public abstract class Piece {
	private int color; //White: 1, Black: -1
	private char icon;
	boolean hasMoved;
	public abstract boolean validMove(int currRow, int currCol, int nextRow, int nextCol, Board board);
	public Piece(int color) {
		this.color = color;
	}
	public String toString() {
		if (color == 1)
			return (""+ icon).toUpperCase();
		return (""+ icon).toLowerCase();
	}
	public int getColor() {
		return color;
	}
	protected void setColor(int color) {
		this.color = color;
	}
	public char getIcon() {
		return icon;
	}
	protected void setIcon(char icon) {
		this.icon = icon;
	}
	public boolean hasMoved() {
		return hasMoved;
	}
	protected void setMoved() {
		hasMoved = true;
	}
}
