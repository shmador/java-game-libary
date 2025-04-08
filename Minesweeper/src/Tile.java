
public class Tile {
	private boolean mine;
	private int count;
	private boolean visible;
	private int row;
	private int col;
	private boolean flagged;
	public boolean isFlagged() {
		return flagged;
	}
	public void setFlagged() {
		this.flagged = !flagged;
	}
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public boolean isMine() {
		return mine;
	}
	protected void setMine(boolean mine) {
		this.mine = mine;
	}
	public int getCount() {
		return count;
	}
	protected void addCount() {
		count++;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setVisible() {
		this.visible = true;
	}
	public Tile() {
		this(false);
	}
	public Tile(boolean mine) {
		this.mine = mine;
	}
	public String toString() {
		if(flagged)
			return "!";
		else if (!visible)
			return ".";
		else {
			if(mine)
				return "*";
			else
				return ""+count;
		}
	}
}
