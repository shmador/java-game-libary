
public class Main {

	public static void main(String[] args) {
		StackQueue<Integer> queue = new StackQueue();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		queue.add(7);
		queue.add(8);
		queue.add(9);
		System.out.println(queue);
		System.out.println(queue.peek());
		queue.remove();
		System.out.println(queue);
		System.out.println(length(queue));
		System.out.println(queue);
		System.out.println(isIn(queue, 2));
		System.out.println(queue);
		removeOdds(queue);
		System.out.println(queue);
		Queue<Integer> marks = new StackQueue<>();
		marks.add(80);
		marks.add(90);
		marks.add(100);
		marks.add(-1);
		marks.add(75);
		marks.add(96);
		marks.add(-1);
		marks.add(100);
		marks.add(100);
		marks.add(97);
		marks.add(96);
		marks.add(-1);
		marks.add(-1);
		marks.add(88);
		marks.add(94);
		marks.add(-1);
		System.out.println(marks);
		System.out.println(arrangeData(marks));
		System.out.println(marks);
	}
	
	public static <T> int length(Queue<T> q) {
		int res = 0;
		Queue<T> temp = new StackQueue<>();
		for (;!q.isEmpty(); res++) 
			temp.add(q.remove());
		while (!temp.isEmpty())
			q.add(temp.remove());
		return res;
	}
	public static boolean isIn(Queue<Integer> q, Integer num) {
		boolean isIn = false;
		q.add(null);
		while (q.peek() != null) {
			Integer val = q.remove();
			q.add(val);
			isIn = isIn || val.equals(num);
		}
		q.remove();
		return isIn;
	}
	public static void removeOdds(Queue<Integer> q) {
		q.add(null);
		while (q.peek() != null) {
			Integer val = q.remove();
			if (val % 2 == 0)
				q.add(val);
		}
		q.remove();
	}
	public static Queue<Integer> arrangeData(Queue<Integer> marks) {
		marks.add(null);
		int count = 0;
		Queue<Integer> res = new StackQueue<>();
		while (marks.peek() != null) {
			Integer val = marks.remove();
			if (val != -1) {
				marks.add(val);
				count++;
			}
			else {
				res.add(count);
				count = 0;
			}
		}
		marks.remove();
		return res;
	}
}
