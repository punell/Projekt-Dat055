package gameLayer;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


public class Inventory implements Serializable
{
	private LinkedList<Item> itemList;
	private LinkedList<ItemEquipment> equippedList;
	private String inventoryType;
	public Inventory(String inventoryType)
	{
		itemList = new LinkedList<>();
		equippedList = new LinkedList<>();
		this.inventoryType = inventoryType;
	}
	
	public void put(Item item)
	{
		if(inventoryType.equals("backpack"))
			itemList.add(item);
		else if(inventoryType.equals("equipped") && item instanceof ItemEquipment)
			equippedList.add((ItemEquipment) item);
			
	}
	public Item get(String name)
	{
		Iterator<Item> it = itemList.iterator();
		while(it.hasNext())
		{
			Item element = (Item)it.next();
			if(name.equals(element.getName()))
			{
				it.remove();
				return element;
			}
		}
		return null;
	}
	public LinkedList checkContents()
	{
		if(inventoryType.equals("backpack"))
			return itemList;
		else if(inventoryType.equals("equipped"))
			return equippedList;
		else
			return null;
	}
	
	public Item getEquipped(String slot)
	{
		Iterator<ItemEquipment> it = equippedList.iterator();
		while(it.hasNext())
		{
			ItemEquipment element = (ItemEquipment)it.next();
			if(slot.equals(element.getSlot()))
			{
				it.remove();
				return element;
			}
		}
		return null;
	}
}
