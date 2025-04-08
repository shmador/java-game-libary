import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Minesweeper {
	private Tile[][] board;
	private int mines;
	private int revealed;
	public Minesweeper(int rows, int cols, int mines) {
		//VALIDATE CUSTOM PARAMETERS
		rows = Math.max(3, Math.min(rows, 30));
		cols = Math.max(3, Math.min(cols, 24));
		mines = Math.max(1, Math.min(mines, rows * cols - 1));
		//GENERATE BOARD
		this.mines = mines;
		createBoard(rows, cols);
		//GENERATE MINES
		while(mines > 0) {
			int row = (int)(Math.random()*rows);
			int col = (int)(Math.random()*cols);
			if (!board[row][col].isMine()) {
				board[row][col].setMine(true);
				mines--;
				addCounts(board[row][col]);
			}
		}
	}
	private void addCounts(Tile tile) {//ADDS +1 TO THE NEIGHBORING TILES
		for (int i = -1; i <=1; i ++) {
			for (int j = -1; j <=1; j ++) {
				int x = tile.getRow()+i, y = tile.getCol()+j;
				boolean inRange = x < board.length && x >= 0 && y <board[0].length && y >= 0;
				if (!inRange)
					continue;
				if (!board[x][y].isMine())
					board[x][y].addCount();
			}
		}
	}
	private void createBoard(int rows, int cols) {
		board = new Tile[rows][cols];
		for (int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = new Tile();
				board[i][j].setRow(i);
				board[i][j].setCol(j);
			}
		}
	}
	public Tile string2Tile(String input) {
	    Pattern pat =  Pattern.compile("(?i)[a-z](?:[1-9]|[12][0-9]|30)!?");//PATTERN A-Z, 1-30, "" OR "!"
		Matcher match = pat.matcher(input);
		if (match.matches()) {
			//CONVERT TO COL
			input = input.toUpperCase();
			int col = input.charAt(0)-'A';
			if (col >= board[0].length)
				return null;
			//CHECK FLAGGED
			boolean isFlagged = input.contains("!");
			if(isFlagged)
				input = input.substring(0, input.length()-1);
			//CONVERT TO ROW
			int row = Integer.parseInt(input.substring(1));
			row--;
			if (row >= board.length)
				return null;
			//GET TILE
			Tile selected = board[row][col];
			if (selected.isVisible()) 
				return null;
			else if(isFlagged) {
				selected.setFlagged();
				printBoard();
				return null;
			}
			else if (selected.isFlagged())//TILE WAS ALREADY FLAGGED, AND USER DIDNT UNFLAG.
				return null;
			return selected;
		}
		return null;
	}
	public int getRows() {
		return board.length;
	}
	public int getCols() {
		return board[0].length;
	}
	public boolean checkTile(Tile tile) {//RETURNS IF ON MINE
		if (tile.isMine()) {
			showMines();
			return true;
		}
		if (!tile.isFlagged() && !tile.isVisible()) 
			reveal(tile);
		return false;
	}
	public void reveal(Tile tile) {//REVEALS TILE, AND ALL NEIGHBORING SAFE TILES.
		tile.setVisible();
		revealed++;
		if (tile.getCount() > 0)
			return;
		//REVEAL NEIGHBORS
		for (int i = -1; i <=1; i ++) { 
			for (int j = -1; j <=1; j ++) {
				int x = tile.getRow()+i, y = tile.getCol()+j;
				boolean inRange = x < board.length && x >= 0 && y <board[0].length && y >= 0;
				if (!inRange)
					continue;
				Tile neighbor = board[x][y];
				if (!neighbor.isMine() && !neighbor.isVisible()) 
					reveal(neighbor);
			}
		}
	}
	public boolean win() {
		return revealed == board.length * board[0].length - mines;
	}
	public void showMines() {//REVEALS ALL MINES
		for (int i = 0; i < board.length; i ++) 
			for(int j = 0; j < board[0].length; j++)
				if (board[i][j].isMine())
					board[i][j].setVisible();
	}
	public void printBoard() {
		//PRINT COLUMN INDEXES
		System.out.print("   ");
		for (int i = 0; i < board[0].length; i ++)
			System.out.print((char)(i+'A')+" ");
		System.out.println();
		//PRINT BOARD
		for (int i = 0; i < board.length; i ++) {
			for(int j = 0; j < board[0].length; j++) {
				if (j == 0) {
					System.out.print(i+1+" "); //PRINT ROW INDEXES
					if (i+1 < 10)//PRINT EXTRA SPACE FOR ONE DIGIT INDEXES
						System.out.print(" ");
				}
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
