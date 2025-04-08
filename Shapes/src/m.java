import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class m {
	public static void main (String[] args) {
		Queue<Integer> q = new LinkedList<>();
		q.add(8);q.add(10);q.add(11);q.add(2);
		q.add(3);q.add(16);q.add(2);q.add(4);
		System.out.println(q);
		System.out.println(removeLast(q));
		System.out.println(q);
		Stack<Integer> st = new Stack<>();
		st.push(1);
		st.push(3);
		st.push(2);
		st.push(7);
		System.out.println(st);
		System.out.println(isUniform(st));
		System.out.println(st);
	}
	public static int removeLast(Queue<Integer> q) {
		int res=0;
		q.add(null);
		while(q.peek()!=null) {
			int val = q.remove();
			if (q.peek()!=null) 
				q.add(val);
			else
				res=val;
		}
		q.remove();
		return res;
	}
	public static boolean isUniform(Stack<Integer> st) {
        int val = st.peek();        
        return isUniform(st, val%2==0);
    }
    public static boolean isUniform(Stack<Integer> st, boolean even) {
        if (st.isEmpty()) return true;
        int val = st.peek();
        boolean flag = val%2==0;
        if (flag != even) return false;    
        val=st.pop();
        boolean res = isUniform(st, even);
        st.push(val);
        return res;
    }
}
