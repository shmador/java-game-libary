
public class Main {
	public static void main(String[] args) {
		BinNode<Integer> root = new BinNode<>(1);
		root.left = new BinNode<>(2);
		root.right = new BinNode<>(3);
		root.left.right = new BinNode<>(4);
		System.out.println(root);
		System.out.println(depth(root, 1));
		System.out.println(hasEven(root));
		preOrder(root);
	}
	
	public static <T> int depth(BinNode<T> root, T val) {
		if (root == null)
			return -1;
		if (root.data.equals(val))
			return 0;
		int res = depth(root.left, val);
		if (res != -1)
			return res+1;
		res = depth(root.right, val);
		if (res != -1)
			res++;
		return res;
	}
	
	public static boolean hasEven(BinNode<Integer> root) {
		if (root == null)
			return false;
		return root.data%2 == 0 || hasEven(root.left) || hasEven(root.right);
	}
	
	public static <T> void preOrder(BinNode<T> t) {
		if (t == null)
			return;
		preOrder(t.left);
		preOrder(t.right);
		System.out.print(t.data + " ");
	}
}
