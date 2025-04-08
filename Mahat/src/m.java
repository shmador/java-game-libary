import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class m {
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		q.add(2);
		q.add(5);
		q.add(5);
		q.add(7);
		q.add(2);
		q.add(4);
		q.add(1);
		q.add(3);
		q.add(2);
		q.add(5);
		q.add(5);
		q.add(1);
		System.out.println(moreThanTwice(q));
	}
	

	public static Queue<Integer> moreThanTwice(Queue<Integer> q) {
		int max = q.peek();
		q.add(null);
		while (q.peek() != null) {
			int n = q.remove();
			if (n > max)
				max = n;
			q.add(n);
		}
		q.remove();
		int[] counts = new int[max+1];
		boolean[] used = new boolean[max+1];
		Queue<Integer> res = new LinkedList<>();
		q.add(null);
		while (q.peek() != null) {
			int n = q.remove();
			counts[n]++;
			if (counts[n] > 2 && !used[n]) {
				res.add(n);
				used[n] = true;
			}
			q.add(n);
		}
		q.remove();
		return res;
	}

}
