
public class Program {
	public static void main (String[] args) {
		System.out.println ("************");
		Apple a1 = new Apple (10);
		Object a2 = new Apple (10);
		Banana b1 = new Banana (10);
		Object b2 = new Banana (10);
		//System.out.println (a1.weight); # COMPILE #
		//System.out.println (((Banana)a2).getWeight()); # RUNTIME #
		System.out.println (a1.equals(a2));
		System.out.println (a2.equals(a1));
		System.out.println (b1.equals(b2));
		System.out.println (b2.equals(b1));
		System.out.println (a1.equals((Banana)b2));
		System.out.println (a1.equals((Apple)a2));
		System.out.println (b1.equals((Apple)a2));
		//System.out.println (b1.equals((Banana)a2)); # COMPILE #
	}
} 