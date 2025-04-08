
public class T {

	public static void main(String[] args) {
		
		A a = new A();
		B b = new B();
		A c = new B();
		//B d = new A(); # COMPILE #
		A d = new C();
		//C e = new A(); # COMPILE #
		A e = new C();
		//e=(D)e; # RUNTIME #
		//Object f = (D)new C(); # RUNTIME #
		D f = new D();
		C g = new D();
		
		((A)a).myFun();
		//((B)a).myFun(); # RUNTIME #
		
		((A)b).myFun();
		((B)b).myFun();
		
		((A)c).myFun();
		((B)c).myFun();
		//((C)c).myFun(); # RUNTIME #
		//((C)((A)c)).myFun(); # RUNTIME #
		((D)((A)f)).myFun();
		
		a = (A)a;
		//a = (B)a; # RUNTIME #
		
		//b = (A)b; # COMPILE #
		b = (B)b;

		c = (A)c;
		c = (B)c;
		//c = (C)c; # RUNTIME #
		//c = (C)((A)c); # RUNTIME #
		
	}
}
