
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 2, 1, 3, 4, 17, 9, 5, 22, 104};
		int[] brr = sort (arr);
		for (int n: brr)
			System.out.print(n + " ");
	}
	public static int[] sort(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			res[i] = arr[i];
		for (int i = 0; i < arr.length; i++) {
			int k = i;
			for (int j = i; j >= 0; j--) {
				if (res[j] > res[k]) {
					int temp = res[k];
					res[k] = res[j];
					res[j] = temp;
					k = j;
				}
			}
		}
		return res;
	}

}
