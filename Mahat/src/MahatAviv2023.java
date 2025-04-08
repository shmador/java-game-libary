import java.util.Scanner;
public class MahatAviv2023 {
	public static void main(String[] args) {
		// 1
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 100; i++) {
			int num = in.nextInt();
			if (isThreeDigit(num))
				System.out.println(digitSum(num));
		}
		// 2
		String str = "";
		int hasX = 0;
		while (str.length() > 13) {
			str = in.nextLine();
			if (str.indexOf('X') != -1)
				hasX++;
			if (str.indexOf('Y') != -1)
				hasX--;
		}
		System.out.println(hasX);
		
	}
	public static boolean isThreeDigit(int num) {
		if (num < 0)
			num = -num;
		return 100 <= num && num <= 999;
	}
	public static int digitSum(int num) {
		int res = 0;
		for (;num > 0; num /= 10) 
			res += num%10;
		return res;
	}
	public static boolean isInArray(int[] arr, int ind, int value) {
		for (int i = ind+1; i < arr.length; i++)
			if (arr[i] == value)
				return true;
		return false;
	}
	public static boolean isUniqueArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) 
			if (isInArray(arr, i, arr[i]))
				return false;
		return true;
	}

}
