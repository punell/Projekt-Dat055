import java.io.Serializable;




public class PlayerModel implements Serializable
{
	/** PlayerModel is the player character class
	 *  Everything about our nameless (?) hero is in here
	 *  including future level/stats/gear/inventory-data
	 */
	private String name;
	private int maxHealth;
	private int currentHealth;
	private Inventory backpack;
	private int roomX;
	private int roomY;
	private int cellX;
	private int cellY;
	private String currentArea;
	
	public PlayerModel()
	{
		name = "test";
		maxHealth = 50;
		currentHealth = 30;
		backpack = new Inventory(50, 50);
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
		for(Item x : backpack.checkContents())
		{
			System.out.println(x.getName());
		}
		//return backpack.checkContents();
	}
	public void addItem(Item item)
	{
		backpack.put(item);
	}
	public void healPlayer(int heal) //heals cannot go above maxHealth
	{
		currentHealth += heal;
		if(currentHealth >= maxHealth)
			currentHealth = maxHealth;
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
	


}
