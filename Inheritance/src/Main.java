public class Main {
    
    public static void main(String[] args) {
    	String[] arr = { "1", "2", "3" };
    	String[] brr = { "4", "5", "6" };
    	Node<String> chain1 = arr2Chain(arr);
    	System.out.println(chain1);
    	System.out.println(dataAt(chain1, 0));
    	System.out.println(middle(chain1));
    	Node<String> chain2 = arr2Chain(brr);
    	/*
    	add(chain1, chain2);
    	System.out.println(chain1);
    	*/
    	alternate(chain1, chain2);
    	System.out.println(chain1);
    	Integer[] crr = {1, 3, 6, 13, 27, 300};
    	Node<Integer> intChain = arr2Chain(crr);
    	/*
    	balance(intChain);
    	*/
    	System.out.println(intChain);
    	System.out.println(isSuper(intChain));
    	addToSuper(intChain, 51);
    	System.out.println(intChain);
    	
    }
    public static <T> Node<T> arr2Chain(T[] arr) {
    	return arr2Chain(arr, 0);
    }
    public static <T> Node<T> arr2Chain(T[] arr, int i) {
    	if (i == arr.length)
    		return null;
    	return new Node<>(arr[i], arr2Chain(arr, i + 1));
    }
    public static <T> T dataAt(Node<T> chain, int i) {
    	return dataAt(chain, i, 0);
    }
    public static <T> T dataAt(Node<T> chain, int i, int j) {
    	if (i == j)
    		return chain.data;
    	return dataAt(chain.next, i, j+1);
    }
    public static <T> T middle(Node<T> chain) {
    	return middle(chain, chain);
    }
    public static <T> T middle(Node<T> chain, Node<T> res) {
    	if (chain.next == null)
    		return res.data;
    	return middle(chain.next.next, res.next);
    }
    public static <T> void add(Node<T> chain1, Node<T> chain2) {
    	if (chain1.next == null) {
    		chain1.next = chain2;
    		return;
    	}
    	add(chain1.next, chain2);
    }
    public static <T> void alternate(Node<T> chain1, Node<T> chain2) {
    	if (chain2 == null)
    		return;
    	if (chain1.next == null) {
    		chain1.next = chain2;
    		return;
    	}
    	Node<T> temp = chain1.next;
    	chain1.next = chain2;
    	chain2 = chain2.next;
    	chain1.next.next = temp;
    	alternate(temp, chain2);
    }
    public static void balance(Node<Integer> chain) {
    	balance(chain, chain.data, 1);
    }
    public static void balance(Node<Integer> chain, int sum, int length) {
    	if(chain.next == null) {
    		if (sum != 0) {
    			chain.next = new Node<>(-sum);
    			chain = chain.next;
    			length++;
    		}
    		if (length%2!=0) 
    			chain.next = new Node<>(0);
    		return;
    	}
    	balance(chain.next, sum+chain.next.data, length+1);
    }
    public static boolean isSuper(Node<Integer> n) {
    	return isSuper(n, 0);
    }
    public static boolean isSuper(Node<Integer> n, int sum) {
    	if (n == null)
    		return true;
    	if (n.data <= sum)
    		return false;
    	return isSuper(n.next, sum+n.data);
    }
    public static boolean addToSuper(Node<Integer> n, int num) {
    	return addToSuper(n, num, 0);
    }
    public static boolean addToSuper(Node<Integer> n, int num, int sum) {
    	sum += n.data;
    	if (num <= sum)
    		return false;
    	if (n.next == null || n.next.data > sum + num) {
    		boolean canAdd = isSuper(n.next, sum+n.data+num);
    		if (canAdd) 
    			n.next = new Node<>(num, n.next);
    		return canAdd;
    	}
    	return addToSuper(n.next, num, sum);
    }
}
