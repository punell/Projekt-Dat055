package gameLayer;
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
	private Inventory backpack;
	private Inventory equipped;
	private HashMap<String, Integer> stats;
	private int roomX;
	private int roomY;
	private int cellX;
	private int cellY;
	private String currentArea;
	
	public PlayerModel()
	{
		name = "test";
		baseHealth = 50;
		currentHealth = 50;
		backpack = new Inventory("backpack");
		equipped = new Inventory("equipped");
		stats = new HashMap<>();
		stats.put("maxhealth", baseHealth);
		stats.put("damage", 0);
		stats.put("armor", 0);
		
		roomX = 0;
		roomY = 0;
		cellX = 1;
		cellY = 1;
		currentArea = "overworld"; //not entirely sure what this was supposed to be for... I'll leave it here just in case
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
	
	public int getHealth()
	{
		return currentHealth;
	}
	public Item getItem(String itemName)
	{
		return backpack.get(itemName);
	}
	public void checkInventory()
	{
		LinkedList<Item> inBackpack = backpack.checkContents();
		for(Item item : inBackpack)
			System.out.println(item.getName());
		//return backpack.checkContents();
	}
	public void addItem(Item item)
	{
		backpack.put(item);
	}
	public void healPlayer(int heal) //heals cannot go above maxHealth
	{
		currentHealth += heal;
		if(currentHealth >= stats.get("maxhealth"));
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
		ItemConsumable item = (ItemConsumable)backpack.get(itemName); //holding in hand...
		switch(item.getEffect())
		{
			case "heal": healPlayer(item.getEffectValue()); break;
		}
	}
	
	public void equipItem(String itemName)
	{
		ItemEquipment item = (ItemEquipment)backpack.get(itemName); // pick the to-be equipped item from the bag
		Item lastEquipped = (ItemEquipment)equipped.getEquipped(item.getSlot()); //pick the old item from the slot
		equipped.put(item); //put in the new item in equipped

		if(lastEquipped != null) //if there was anything in the slot before...
			backpack.put(lastEquipped); //we put it back in the backpack
		calculateEquipmentBonus();
	}
	
	private void calculateEquipmentBonus()
	{
		LinkedList<ItemEquipment> equipment = equipped.checkContents();
		int health = baseHealth;
		int damage = 0;
		int armor = 0;
		for(ItemEquipment item : equipment)
		{
			switch(item.getEffect())
			{
				case "bonushealth": health += item.getEffectValue(); break;
				case "bonusdamage": damage += item.getEffectValue(); break;
				case "bonusarmor": armor += item.getEffectValue(); break;
			}
		}
		stats.put("maxhealth", health);
		stats.put("damage", damage);
		stats.put("armor", armor);
	}
	


}
