package inventory;
import items.Item;
import items.ItemEquipment;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Holds all the picked-up items in two lists. Handles the transfer of items between the lists. 
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class InventoryModel implements Serializable
{
	private LinkedList<Item> itemList;
	private LinkedList<ItemEquipment> equippedList;
	public InventoryModel()
	{
		itemList = new LinkedList<>();
		equippedList = new LinkedList<>();
	}
	
	/**
	 * Adds an item to the inventory
	 * @param item Item to be added
	 */
	public void put(Item item)
	{
		itemList.add(item);	
	}
	
	/**
	 * Transfer item from/to backpack to/from equipped. 
	 * Takes into consideration if the item already is equipped and what kind of item it is.
	 * @param item Item to transfer
	 */
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
			String slot[] = itemCasted.getSlot();
			for(int i=0; i<slot.length;i++)
			{
				if((itemCasted = getEquipped(slot[i])) != null)
					itemList.add(itemCasted);
			}
			
			equippedList.add((ItemEquipment)itemList.remove(index));
		}
		
	}
	/**
	 * Get wanted item
	 * @param name Name of the wanted item
	 * @return Wanted item, null if empty
	 */
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
	/**
	 * Gets list of items in backpack
	 * @return List of items
	 */
	public LinkedList<Item> checkBackpack()
	{
		return itemList;
	}
	
	/**
	 * Gets list of equipped items
	 * @return List of equipped items
	 */
	public LinkedList<ItemEquipment> checkEquipment()
	{
		return equippedList;
	}
	
	/**
	 * Removes and returns an item in specified slot 
	 * @param slot Specified slot
	 * @return Item i specified slot, null if empty
	 */
	public ItemEquipment getEquipped(String slot)
	{
		Iterator<ItemEquipment> it = equippedList.iterator();
		while(it.hasNext())
		{
			ItemEquipment element = (ItemEquipment)it.next();
			for(int i=0;i<element.getSlot().length;i++)
			{
				if(slot.equals(element.getSlot()[i]))
				{
					it.remove();
					return element;
				}
			}
		}
		return null;
	}
}
