
public class Main {

	public static void main(String[] args) {
		Shape s1 = new Shape();
		Shape s2 = new Circle();
		Shape s3 = new Cylinder();
		Circle c = new Cylinder();
		

		
		//Circle c0 = new Shape();
		
		//Cricle c1 = s1;
		
		Circle c2 = (Circle)s2;
		
		Circle c3 = (Circle)s3;
		
		//Circle c4 = (Circle)s1; RUNTIME
		
		Triangle t = new Triangle();
		Shape s5 = t;
		//Circle c5 = (Circle)s5; RUNTIME
		
		Shape s = (Circle)new Cylinder();
		
		//Circle c6 = (Shape)new Cylinder();
		
		I i = (B)new B();
		i.foo();
	}
}
