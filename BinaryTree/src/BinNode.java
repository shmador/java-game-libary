
public class BinNode<T> {
	public T data;
	public BinNode<T> left;
	public BinNode<T> right;
	public BinNode(T data) {
		this.data = data;
	}
	public BinNode(T data, BinNode<T> left, BinNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public String toString() {
		return toString("");
	}
	private String toString(String indent) {
		String res = "";
		if (right!=null)
			res += right.toString(indent+ "/ ");
		res += indent + data + "\n";
		if (left!=null) 
			res += left.toString(indent+"\\ ");
		return res;
	}
}
