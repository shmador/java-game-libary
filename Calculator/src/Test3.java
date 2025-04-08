import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		int[] arr = {12, 15, 40, 21, 17, 15, 80, 21};
		int[] brr = {12, 25, 21, 86, 12, 17};
		int[] res = complement(arr,brr);
		print(res);
		
		Scanner in = new Scanner(System.in);
		String s ="";
		int ans = 0;
		do {
			s = in.nextLine();
			if (endsOrStartsWith('Z', s))
				ans++;
		}
		while (!endsOrStartsWith('A', s));
		System.out.println(ans);

	}
	public static int[] diff(int[] arr, int[] brr) {
		boolean[] used = new boolean[100];
		int size = 0;
		for (int n: arr) {
			if (!used[n])
				size++;
			used[n]=true;
		}
		for (int n: brr) {
			if (used[n])
				size--;
			used[n]=false;
		}
		int[] res = new int[size];
		size = 0;
		for (int n: arr) 
			if (used[n]) {
				res[size]=n;
				used[n]=false;
				size++;
			}
		return res;
	}
	public static int[] complement(int[] arr, int[] brr) {
		int[] nums = new int[100];
		for (int i = 0; i < 100; i++)
			if(i < 10)
				nums[i]=10;
			else
				nums[i]=i;
		nums = diff(nums, arr);
		nums = diff(nums, brr);
		return nums;
	}
	private static void print(int[] arr) {
		for (int n: arr)
			System.out.print(n+" ");
	}
	private static boolean isCute(int n) {
		if (n < 10)
			return false;
		int right = n%10;
		for(;n >= 10; n/= 10);
		int left = n%10;
		return left%right==0;
	}
	private static boolean isMotek(int[][] arr) {
		for (int i = 0; i < arr[0].length; i++)
			if (isNice(i, arr))
				return true;
		return false;
	}
	private static boolean isNice(int col, int[][] arr) {
		for (int i = 0; col < arr[i].length && i < arr.length; i++) 
			if (!isCute(arr[i][col]))
				return false;
		return true;
	}
	private static boolean endsOrStartsWith(char c, String s) {
		if (s.length() == 0)
			return false;
		return (s.charAt(0) == c || s.charAt(s.length()-1) == c);
	}
}
