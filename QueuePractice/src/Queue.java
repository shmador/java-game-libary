
public interface Queue<T> {
	public boolean isEmpty();
	public void add(T x);
	public T remove();
	public T peek();
}
