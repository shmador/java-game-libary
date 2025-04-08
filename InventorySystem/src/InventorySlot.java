import java.util.Stack;

public class InventorySlot{
	
	Stack<Item> items;
	
	public InventorySlot() {
		items = new Stack<>();
	}
	
	public void add(Item item) {
		items.push(item);
	}
	
	public int getAmount() {
		return items.size();
	}
	
	public Item getLast() {
		if (isEmpty())
			return null;
		return items.peek();
	}
	
	public Item removeLast() {
		return items.pop();
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	@Override
	public String toString() {
		Item last = getLast();
		if (last == null)
			return "[\t]";
		return "["+last+" x"+items.size()+"]";
	}
}
