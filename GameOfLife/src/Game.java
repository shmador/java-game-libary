
public class Game {
	int[][] mat;

	public Game(int rows, int cols) {
		mat = new int[rows][cols];
		
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[i].length; j++)
				mat[i][j] = (int)(Math.random()*2);
	}

	public int neighbours(int row, int col) {
		if (row > mat.length || col > mat[row].length)
			return -1;
		int res = 0;
		for (int i = row - 1; i <= row + 1; i++)
			for (int j = col - 1; j <= col + 1; j++)
				if (i != row || j != col)
					if (mat.length > i && i >= 0 &&
							mat[i].length > j && j >= 0 &&
							mat[i][j] == 1)
						res++;
		return res;
	}
	
	public void generation() {
		int[][] next = new int[mat.length][mat[0].length];
		
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[i].length; j++)
				next[i][j] = mat[i][j];
		
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[i].length; j++) {
				int n = neighbours(i, j);
				int cell = mat[i][j];
				if (cell == 1) {
					if (3 < n || n < 2)
						next[i][j] = 0;
				}
				else if (n == 3)
					next[i][j] = 1;
			}
		mat = next;
	}
	
	public void print() {
		for (int i = 0; i < mat.length; i++) {
			System.out.print("[");
			for (int j = 0; j < mat[i].length; j++) {
				if (j != 0)
					System.out.print(", ");
				System.out.print(mat[i][j]);
			}
			System.out.println("]");
		}
	}

	public static void main (String[] args) {
		Game g = new Game(10, 10);
		for (int i = 0; i < 1000; i ++) {
			g.print();
			System.out.println();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println(e);
			}
			g.generation();
		}
		g.print();
	}
}
