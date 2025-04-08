import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		System.out.println(strangers(8, 9));
		System.out.println(strangers(21, 14));
		System.out.println(strangers(6, 12));
		Queue<Integer> q = new LinkedList<>();
		q.add(2);
		q.add(10);
		q.add(12);
		q.add(3);
		q.add(7);
		q.add(4);
		q.add(1);
		System.out.println(q);
		change(q, 9);
		System.out.println(q);
		Integer[] arr = {10, 20, 5, 7, 8, 4, 6, 11, 7};
		Node<Integer> head = arr2Chain(arr);

		System.out.println(head);
		System.out.println(split(head));
		System.out.println(head);
		
		Stack<Integer> stk = new Stack<>();
		stk.push(1);
		stk.push(2);
		stk.push(4);
		stk.push(5);
		stk.push(4);
		stk.push(4);
		System.out.println(stk);
		System.out.println(lastPlace(stk, 4));
		System.out.println(stk);
		
		stk = new Stack<>();
		stk.push(1);
		stk.push(1);
		stk.push(2);
		stk.push(3);
		stk.push(4);
		stk.push(2);
		stk.push(3);
		stk.push(4);
		System.out.println(stk);
		System.out.println(minDist(stk));
		System.out.println(stk);
	}
	
	public static boolean strangers(int num1, int num2) {
		int min = Math.min(num1, num2);
		for (int i = 2; i <= min; i++) 
			if (num1%i == 0 && num2%i == 0)
				return false;
		return true;
	}
	
	public static void change(Queue<Integer> q, int num) {
		q.add(null);
		Queue<Integer> strangers = new LinkedList<>();
		while (q.peek() != null) {
			int val = q.remove();
			if (strangers(val, num))
				q.add(val);
			else
				strangers.add(val);
		}
		q.remove();
		while (!strangers.isEmpty())
			q.add(strangers.remove());
	}
	
	public static Node<Integer> split (Node<Integer> chain) {
		int even = chain.data%2;
		Node<Integer> prev = chain;
		Node<Integer> res = new Node<Integer>(null, null);
		chain = chain.next;
		while(chain != null) {
			if (chain.data%2 == even) {
				res.next = new Node<Integer>(chain.data, null);
				res = res.next;
				prev.next = chain.next;
			}
			else
				prev = chain;
			chain = chain.next;
		}
		return res;
	}
	
	
	public static void chain2Num(Node<Integer> head, int num) {
		while (head.next != null)
			head = head.next;
		head.next = new Node<Integer>(num, null);
	}
	
	public static <T> Node<T> arr2Chain (T[] arr) {
		Node<T> res = new Node<T>(arr[0], null);
		Node<T> ret = res;
		for (int i = 1; i < arr.length; i++) {
			ret.next = new Node<T>(arr[i], null);
			ret = ret.next;
		}
		return res;
	}
	
	public static int firstPlace(Stack<Integer> stk, int num) {
		return firstPlace(stk, num, 1);
	}
	public static int firstPlace(Stack<Integer> stk, int num, int res) {
		if (stk.isEmpty())
			return -1;
		if (stk.peek() == num)
			return res;
		int val = stk.pop();
		int ret = firstPlace(stk, num, res+1);
		stk.push(val);
		return ret;
	}
	
	public static int lastPlace(Stack<Integer> stk, int num) {
		return lastPlace(stk, num, 1, -1);
	}
	public static int lastPlace(Stack<Integer> stk, int num, int res, int max) {
		if (stk.isEmpty()) {
			if (max == -1)
				return res;
			return max;
			
		}
		if (stk.peek() == num) {
			if (res > max)
				max = res;
		}
		int val = stk.pop();
		int ret = lastPlace(stk, num, res+1, max);

		stk.push(val);
		return ret;
	}
	public static int minDist(Stack<Integer> stk) {
		int min = -1;
		Stack<Integer> temp = new Stack<>();
		while (!stk.isEmpty()) {
			int val = stk.pop();
			int dist = minDist(stk, val, 0);
			if (min == -1 && dist != -1 ||
				min != -1 && dist != -1 && dist < min)
				min = dist;
			temp.push(val);
		}
		while (!temp.isEmpty())
			stk.push(temp.pop());
		return min;
		
	}
	public static int minDist(Stack<Integer> stk, int target, int distance) {
		if(stk.isEmpty())
			return -1;
		if(stk.peek() == target)
			return distance;
		int val = stk.pop();
		int res = minDist(stk, target, distance+1);
		stk.push(val);
		return res;
	}
}
