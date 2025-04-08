import java.util.LinkedList;
import java.util.Queue;

public class Test2 {

	public static void main(String[] args) {
		Queue<Integer> q = foo(4);
		System.out.println(isFoo(q));
	}
	public static Queue<Integer> foo(int n) {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			for (int j = 0; j < i; j++)
				q.add(i);
		return q;
	}
	public static boolean isFoo(Queue<Integer> q) {
		q.add(null);
		int count = 0;
		int expected = 1;
		boolean res = true;
		while (q.peek() != null) {
			int val = q.remove();
			if (val != expected) 
				res = false;
			count++;
			if (count >= expected) {
				expected++;
				count = 0;
			}
			q.add(val);
		}
		q.remove();
		return res;
	}
}
