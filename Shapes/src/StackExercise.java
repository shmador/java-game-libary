import java.util.Stack;

public class StackExercise {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		s.push(7);
		s.push(22);
		s.push(10);
		s.push(12);
		s.push(21);
		s.push(25);
		s.push(4);
		s.push(13);
		s.push(15);
		s.push(3);
		s.push(18);
		System.out.println(s);
		System.out.println(equalSums(s));
		System.out.println(s);
	}
	public static <T> int stackLength(Stack<T> s) {
		if (s.isEmpty())
			return 0;
		T t = s.pop();
		int res = 1 + stackLength(s);
		s.push(t);
		return res;
	}

	public static boolean equalSums(Stack<Integer> s) {
		int length = stackLength(s);
		Stack<Integer> top = new Stack<>();
		Stack<Integer> temp = new Stack<>();
		for (int i = 0; i < length/2;i++) {
			int val = s.pop();
			top.push(val);
			temp.push(val);
		}
		int mid = s.pop();
		System.out.println(mid);
		temp.push(mid);
		Stack<Integer> bottom = new Stack<>();
		while(!s.isEmpty()) {
			int val = s.pop();
			bottom.push(val);
			temp.push(val);
		}
		Stack<Integer> reversetop = new Stack<>();
		while (!top.isEmpty())
			reversetop.push(top.pop());
		boolean flag = true;
		while(!reversetop.isEmpty() && flag)
			flag = reversetop.pop()+bottom.pop()==mid;
		while(!temp.isEmpty())
			s.push(temp.pop());
		return flag;
	}
}
