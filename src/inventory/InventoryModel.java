package inventory;
import gameLayer.Item;
import gameLayer.ItemEquipment;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


public class InventoryModel implements Serializable
{
	private LinkedList<Item> itemList;
	private LinkedList<ItemEquipment> equippedList;
	public InventoryModel()
	{
		itemList = new LinkedList<>();
		equippedList = new LinkedList<>();
	}
	
	public void put(Item item)
	{
		itemList.add(item);
			
	}
	
	public void toggleEquip(Item item)
	{
		if(equippedList.contains(item))
		{
			int index = equippedList.indexOf(item);
			itemList.add(equippedList.remove(index));
		}
		else
		{
			int index = itemList.indexOf(item);
			ItemEquipment itemCasted = (ItemEquipment)item;
			String slot = itemCasted.getSlot();
			if((itemCasted = getEquipped(slot)) != null)
				itemList.add(itemCasted);
			
			equippedList.add((ItemEquipment)itemList.remove(index));
		}
		
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
	public LinkedList<Item> checkBackpack()
	{
		return itemList;
	}
	
	public LinkedList<ItemEquipment> checkEquipment()
	{
		return equippedList;
	}
	
	public ItemEquipment getEquipped(String slot)
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
