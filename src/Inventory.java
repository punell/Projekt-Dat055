import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;


public class Inventory implements Serializable
{
	private LinkedList<Item> itemList;
	private int sizeCapacity;
	private int weightCapacity;
	public Inventory(int size, int weight)
	{
		itemList = new LinkedList<>();
		sizeCapacity = size;
		weightCapacity = weight;
	}
	
	public void put(Item item)
	{
		itemList.add(item);
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
	public LinkedList<Item> checkContents()
	{
		return itemList;
	}
}
