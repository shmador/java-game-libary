
public class Main {

	public static void main(String[] args) {
		Inventory inv = new Inventory(6);
		inv.display();
		for (int i = 0; i < 2; i ++)
			inv.store(new Potion());
		inv.display();
		for (int i = 0; i < 2; i ++)
			inv.store(new Sword());
		inv.display();
		for (int i = 0; i < 22; i ++)
			inv.store(new Potion());
		inv.display();
		inv.throwAway(inv.getByIndex(0), 2);
		inv.display();
		inv.throwAway(inv.getByIndex(1), 2);
		inv.display();
		for (int i = 0; i < 4; i ++)
			inv.store(new Sword());
		inv.display();
	}

}
