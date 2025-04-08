import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		q.add(-15);q.add(-5);q.add(5);
		System.out.println(q);
		System.out.println(isNJumpQueue(q, 10));
		System.out.println(q);
		halfJump(q, 10);
		System.out.println(q);
		
	}
	public static boolean isNJumpQueue(Queue<Integer> q, int n) {
		boolean flag = true;
		q.add(null);
		int last = q.remove();
		q.add(last);
		while (q.peek()!=null) {
			int val = q.remove();
			int m = last+n;
			flag = flag && val == m;
			q.add(val);
			last=val;
		}
		q.remove();
		return flag;
	}
	
	public static void halfJump(Queue<Integer> q, int n) {
		if (n%2!= 0) return;
		if (!isNJumpQueue(q, n)) return;
		n/=2;
		int first=q.remove(), last = 0;
		q.add(null);
		while (q.peek()!=null) {
			int val = q.remove();
			if (q.peek()==null)
				last=val;
		}
		q.remove();
		q.add(first);
		while(first!=last) {
			first+=n;
			q.add(first);
		}
	}

}
