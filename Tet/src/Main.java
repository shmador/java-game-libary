import java.util.Random;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		int[][] block = createGrid(3, 3);
		block[0][0] = 1;
		block[1][0] = 1;
		block[2][0] = 1;
		block[1][1] = 1;
		int[][] grid = createGrid(9, 9);
		int x = 0; int y = 0, ind = 0;
		while(true) {
			x = move(grid, block, x, y);
			showGrid(grid);
			//block = rotate(grid, block, x, y);
			showGrid(grid);
			if (fall(grid, block, x, y, 1))
				y++;
			else {
				ind++;
				block = createPiece(ind);
				x = 4;
				y = 0;
				addToGrid(grid, block, x, y);
			}
			showGrid(grid);
		}
	}
	public static int[][] createGrid(int rows, int cols){
		return new int[rows][cols];
	}
	public static int[][] createPiece(int ind) {
		int[][] p = {{0, 0, 0}, {ind, ind, ind}, {0, ind, 0}};
		return p;
	}
	public static void showGrid(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(num2Char(grid[i][j]));
			System.out.println();
		}
		System.out.println();
	}
	public static char num2Char(int n) {
		if (n == 0)
			return '-';
		else
			return '#';
	}
	public static int[][] transpose(int[][] arr) {
		int[][] newArr = new int[arr[0].length][arr.length];
		for (int i = 0; i <  newArr.length; i++)
			for (int j = 0; j <  newArr[0].length; j++)
				newArr[i][j] = arr[j][i];
		return newArr;
	}
	public static int[][] mirrorHor(int[][] arr) {
		int[][] newArr = new int[arr.length][arr[0].length];
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j <  arr[0].length; j++)
				newArr[i][j] = arr[i][arr[0].length-1 - j];
		return newArr;
	}
	public static int[][] mirrorVer(int[][] arr) {
		int[][] newArr = new int[arr.length][arr[0].length];
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j <  arr[0].length; j++)
				newArr[i][j] = arr[arr.length-1 - i][j];
		return newArr;
	}
	public static int[][] rotateArr(int angle, int[][] arr){
		int[][] newArr = arr;
		angle = angle % 360;
		if (angle < 0)
			angle = 360 + angle;
		for (int i = 0; i < angle/ 90; i ++){
			if (newArr == mirrorHor(newArr))
				newArr = mirrorVer(newArr);
			else
				newArr = mirrorHor(newArr);
			newArr = transpose(newArr);
		}
		return newArr;
	}
	public static boolean addToGrid(int [][] grid, int[][] arr, int x, int y) {
		int [][] newGrid = grid;
		if (!inBounds(grid, arr, x ,y))
			return false;
		for (int i = 0; i <= furthestY(arr); i++)
			for (int j = 0; j <= furthestX(arr); j++)
				if (arr[i][j] != 0)
					if (grid[y + i][x + j] != 0)
						return false;
					else
						newGrid[y + i][x + j] = arr[i][j];
		grid = newGrid;
		return true;
	}
	public static void removeFromGrid(int [][] grid, int[][] arr, int x, int y) {
		if (!inBounds(grid, arr, x ,y))
			return;
		for (int i = 0; i <= furthestY(arr); i++)
			for (int j = 0; j <= furthestX(arr); j++)
				if (arr[i][j] == grid[y + i][x + j])
					grid[y + i][x + j] = 0;
	}
	public static int move(int[][] grid, int arr[][], int currentX, int currentY) {
		Scanner scan = new Scanner(System.in);
		int newX = currentX + scan.nextInt();
		removeFromGrid(grid, arr, currentX, currentY);
		if(addToGrid(grid, arr, newX, currentY))
			return newX;
		addToGrid(grid, arr, currentX, currentY);
		return currentX;
	}
	public static int furthestX(int [][] arr) {
		int furthest = 0;
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j <  arr[0].length; j++)
				if (arr[i][j] != 0 && j > furthest)
					furthest = j;
		return furthest;
	}
	public static int nearestX(int [][] arr) {
		int nearest = arr[0].length;
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (arr[i][j] != 0 && j < nearest)
					nearest = j;
		return nearest;
	}
	public static int furthestY(int [][] arr) {
		int furthest = 0;
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j <  arr[0].length; j++)
				if (arr[i][j] != 0 && i > furthest)
					furthest = i;
		return furthest;
	}
	public static int nearestY(int [][] arr) {
		int nearest = arr[0].length;
		for (int i = 0; i <  arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (arr[i][j] != 0 && i < nearest)
					nearest = i;
		return nearest;
	}
	public static boolean inBounds(int[][] grid, int[][] arr, int x, int y) {
		return(x + furthestX(arr) < grid[0].length && x - nearestX(arr)+1 > 0 && y + furthestY(arr) < grid.length && y - nearestY(arr)+1 > 0);
	}
	public static int[][] rotate(int[][] grid,int[][] arr, int currentX, int currentY) {
		Scanner scan = new Scanner(System.in);
		int rot = scan.nextInt()*90;
		removeFromGrid(grid, arr, currentX, currentY);
		int[][] newArr = new int[arr.length][arr[0].length];
		newArr = rotateArr(rot, arr);
		if(addToGrid(grid, newArr, currentX, currentY))
			return newArr;
		addToGrid(grid, arr, currentX, currentY);
		return arr;
	}
	public static boolean fall(int[][] grid,int[][] arr, int currentX, int currentY, int fall) {
		int newY = currentY+fall;
		removeFromGrid(grid, arr, currentX, currentY);
		if(addToGrid(grid, arr, currentX, newY))
			return true;
		addToGrid(grid, arr, currentX, currentY);
		return false;
	}
}