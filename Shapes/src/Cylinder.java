
public class Cylinder extends Circle{
	public static int i = 5;
	
	public void foo(int i) {
		Cylinder.i=i;
		System.out.println(i);
	}
	
	public static void main (String[] args) {
		Cylinder c = new Cylinder();
		c.foo();
	}
}
