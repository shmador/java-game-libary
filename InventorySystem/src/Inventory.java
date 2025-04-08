
public class Inventory {
	private final int IN_ROW = 1;
	InventorySlot[] slots;
	
	public Inventory(int size) {
		slots = new InventorySlot[size];
		for (int i = 0; i < slots.length; i++)
			slots[i] = new InventorySlot();
	}
	
	public boolean store(Item item) {
		for (InventorySlot slot: slots) {
			Item held = slot.getLast();
			if (held != null && held.getClass() == item.getClass())
				if (slot.getAmount() < item.maxHeld()) {
					slot.add(item);
					return true;
				}
		}
		for (InventorySlot slot: slots) 
			if (slot.getAmount() == 0) {
				slot.add(item);
				return true;
			}
		System.out.println("Inventory full\n");
		
		return false;
	}
	
	public void throwAway(InventorySlot slot, int amount) {
		for(;amount > 0 && !slot.isEmpty(); amount--)
			slot.removeLast();
	}
	
	public InventorySlot getByIndex(int i) {
		return slots[i];
	}
	
	public void display() {
		for (int i = 0; i < slots.length; i++) {
			System.out.print(slots[i]);
			if ((i+1) % IN_ROW == 0)
				System.out.println();
		}
		System.out.println();
	}
}
