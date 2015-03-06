package gameLayer;
import inventory.InventoryController;
import items.Item;
import items.ItemConsumable;
import items.ItemEquipment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;




public class PlayerModel implements Serializable
{
	/** PlayerModel is the player character class
	 *  Everything about our nameless (?) hero is in here
	 *  including future level/stats/gear/inventory-data
	 */
	private String name;
	private int baseHealth;
	private int currentHealth;
	private InventoryController inventory;
	private HashMap<String, Integer> stats;
	private int roomX;
	private int roomY;
	private int cellX;
	private int cellY;
	private String currentArea;
	
	public PlayerModel()
	{
		name = "Blade";
		baseHealth = 50;
		currentHealth = 50;
		
		stats = new HashMap<>();
		stats.put("maxhealth", baseHealth);
		stats.put("damage", 0);
		stats.put("armor", 0);
		inventory = new InventoryController(this);
		
		roomX = 0;
		roomY = 0;
		cellX = 1;
		cellY = 1;
		currentArea = "overworld"; 
	}
	public int[] getPlayerCoords(char flag)
	{
		if(flag == 'r')
		{
			int[] coords = {roomX, roomY};
			return coords;
		}
		else if(flag == 'c')
		{
			int[] coords = {cellX, cellY};
			return coords;
		}
		else if(flag == 't')
		{
			System.out.println(cellX+":"+cellY);
			System.out.println(roomX+":"+roomY);
			System.out.println(currentArea);
			return null;
			
		}
		else
		{
			int[] coords = {cellX, cellY, roomX, roomY};
			return coords;
		}
		
	}
	public String getPlayerArea()
	{
		return currentArea;
	}
	public void setPlayerArea(String area)
	{
		currentArea = area;
	}
	public int getStats(String key)
	{		
		return stats.get(key);
	}
	
	public int getHealth()
	{
		return currentHealth;
	}
	public Item getItem(String itemName)
	{
		return inventory.get(itemName);
	}
	public HashMap<String, Integer> getPlayerStats()
	{
		return stats;
	}
	public LinkedList<Item> getBackpack()
	{
		return inventory.checkBackpack();
	}
	public void setPlayerHealth(int health)
	{
		currentHealth = health;
	}
	public void checkInventory()
	{
		inventory.show();
		calculateEquipmentBonus();
	}
	public void addItem(Item item)
	{
		inventory.put(item);
	}
	public void healPlayer(int heal) //heals cannot go above maxHealth
	{
		currentHealth += heal;
		if(currentHealth >= stats.get("maxhealth"))
			currentHealth = stats.get("maxhealth");
	}
	public boolean damagePlayer(int damage) //returns true on death
	{
		currentHealth -= damage;
		if(currentHealth <= 0)
			return true;
		else
			return false;
	}

	protected void moveNorth() //protected because we can... no real reason
	{
		//X=0, Y=0 is Top Left (because everything always is in graphical programming (for some reason))
		cellY--; 
		if(cellY < 0) //this means we went outside the current rooms borders
		{
			cellY = 17; //we therefore set our new position at the opposite side in the new room
			roomY--; //and change room...
		}
	}
	protected void moveEast()
	{
		cellX++;
		if(cellX > 31)
		{
			cellX = 0;
			roomX++;
		}
	}
	protected void moveSouth()
	{
		cellY++;
		if(cellY > 17)
		{
			cellY = 0;
			roomY++;
		}
	}
	protected void moveWest()
	{
		cellX--;
		if(cellX < 0)
		{
			cellX = 31;
			roomX--;
		}
	}
	public void useItem(String itemName) 
	{
		ItemConsumable item = (ItemConsumable)inventory.get(itemName); //holding in hand...
		for(int i=0;i<item.getEffect().length;i++)
		{
			switch(item.getEffect()[i])
			{
				case "Heal": healPlayer(item.getEffectValue()[i]); break;
			}
		}
	}
	
	public void calculateEquipmentBonus()
	{
		LinkedList<ItemEquipment> equipment = inventory.checkEquipment();
		int health = baseHealth;
		int damage = 0;
		int armor = 0;
		for(ItemEquipment item : equipment)
		{
			for(int i=0;i<item.getEffect().length;i++)
			{
				switch(item.getEffect()[i])
				{
					case "Health": health += item.getEffectValue()[i]; break;
					case "Damage": damage += item.getEffectValue()[i]; break;
					case "Armor": armor += item.getEffectValue()[i]; break;
				}
			}
		}
		stats.put("maxhealth", health);
		stats.put("damage", damage);
		stats.put("armor", armor);
	}
	


}
