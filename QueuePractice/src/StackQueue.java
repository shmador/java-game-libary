import java.util.Stack;
public class StackQueue<T> implements Queue<T> {
	private Stack<T> stack = new Stack<>();
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	@Override
	public void add(T x) {
		if (stack.isEmpty()) {
			stack.push(x);
			return;
		}
		T temp = stack.pop();
		add(x);
		stack.push(temp);
	}
	@Override
	public T remove() {
		return stack.pop();
	}
	public T peek() {
		return stack.peek();
	}
	public String toString() {
		return ""+stack;
	}
}
