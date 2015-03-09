package gameLayer;
import inventory.InventoryController;
import items.Item;
import items.ItemConsumable;
import items.ItemEquipment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;




/**Manages everything about the player; stats, location, inventory, etc
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
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
	
	/**Constructor 
	 */
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
	
	/**Gets players current location
	 * @param flag a flag
	 * @return coordinates in different forms depending on the flag
	 */
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
	/**@return players current area-location
	 */
	public String getPlayerArea()
	{
		return currentArea;
	}
	/**Sets players current area-location to parameter
	 * @param area name of area
	 */
	public void setPlayerArea(String area)
	{
		currentArea = area;
	}
	/**Return one stat from stats HashMap
	 * @param key hash-key
	 * @return value hash-value
	 */
	public int getStats(String key)
	{		
		return stats.get(key);
	}
	
	/**@return players current health
	 */
	public int getHealth()
	{
		return currentHealth;
	}
	/**Gets an item depending on parameter item name
	 * @param itemName name of item
	 * @return Item found, if any
	 */
	public Item getItem(String itemName)
	{
		return inventory.get(itemName);
	}
	/**@return whole stats HashMap
	 */
	public HashMap<String, Integer> getPlayerStats()
	{
		return stats;
	}
	/**@return players inventory
	 */
	public LinkedList<Item> getBackpack()
	{
		return inventory.checkBackpack();
	}
	/**Sets players current health to parameter
	 * @param health amount to set
	 */
	public void setPlayerHealth(int health)
	{
		currentHealth = health;
	}
	/**Shows and updates players inventory
	 */
	public void checkInventory()
	{
		inventory.show();
		calculateEquipmentBonus();
	}
	/**Puts parameter Item in players inventory
	 * @param item an Item
	 */
	public void addItem(Item item)
	{
		inventory.put(item);
	}
	/**Heals player for parameter amount
	 * @param heal amount to heal
	 */
	public void healPlayer(int heal)
	{
		currentHealth += heal;
		if(currentHealth >= stats.get("maxhealth"))
			currentHealth = stats.get("maxhealth");
	}
	/**Damages player for parameter amount
	 * @param damage amount of damage
	 * @return true if player died from damage, otherwise false
	 */
	public boolean damagePlayer(int damage)
	{
		currentHealth -= damage;
		if(currentHealth <= 0)
			return true;
		else
			return false;
	}

	/**Moves the player one step north
	 */
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
	/**Moves the player one step east
	 */
	protected void moveEast()
	{
		cellX++;
		if(cellX > 31)
		{
			cellX = 0;
			roomX++;
		}
	}
	/**Moves the player one step south
	 */
	protected void moveSouth()
	{
		cellY++;
		if(cellY > 17)
		{
			cellY = 0;
			roomY++;
		}
	}
	/**Moves the player one step west
	 */
	protected void moveWest()
	{
		cellX--;
		if(cellX < 0)
		{
			cellX = 31;
			roomX--;
		}
	}
	/**Consumes item with parameter name, if any found. 
	 * Is supposed to work for more than just heal, such as mana, but is not
	 * yet implemented
	 * @param itemName name of item
	 */
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
	
	/**Calculates all bonuses from currently equipped equipment
	 */
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
	
	/**Used when loading a previously saved game, to restore the inventory
	 */
	public void restoreFromLoad()
	{
		inventory.restoreFromLoad(this);
	}
	


}
