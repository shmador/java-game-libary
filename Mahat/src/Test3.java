
import java.util.Stack;

public class Test3 {
	
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		s.push(6);
		s.push(12);
		s.push(2);
		s.push(10);
		s.push(7);
		s.push(5);
		stackSort2(s);
		System.out.println(s);
	}
	public static void stackSort2(Stack<Integer> s) {
		Stack<Integer> temp = new Stack<>();
		int sum=0;
		int count =0;
		while(!s.isEmpty()) {
			int val = s.pop();
			sum+=val;
			count++;
			temp.push(val);
		}
		int avg = sum/count;
		Stack<Integer> top = new Stack<>();
		while(!temp.isEmpty()) {
			int val=temp.pop();
			if (val > avg)
				top.push(val);
			else
				s.push(val);
		}
		while(!top.isEmpty())
			s.push(top.pop());
	}
	public static void stackSort(Stack<Integer> s) {
		int avg = stackSum(s)/stackSize(s);
		System.out.println(avg);
		stackSort(s, avg);
	}
	
	public static void stackSort(Stack<Integer> s, int avg) {
		if (!s.isEmpty()) {
			int val = s.pop();
			stackSort(s, avg);
			stackSortHelper(s, val, avg);
		}
	}
	
	public static void stackSortHelper(Stack<Integer> s, int x, int avg) {
		if (s.isEmpty() || x > avg) {
			s.push(x);
			return;
		}
		int val = s.pop();
		stackSortHelper(s, x, avg);
		s.push(val);
	}
	
	public static int stackSum(Stack<Integer> s) {
		if (s.isEmpty())
			return 0;
		int val = s.pop();
		int res= val+stackSum(s);
		s.push(val);
		return res;
	}
	
	public static int stackSize(Stack<Integer> s) {
		if (s.isEmpty())
			return 0;
		int val=s.pop();
		int res= 1+stackSize(s);
		s.push(val);
		return res;
	}
}
