import java.io.Serializable;




public class PlayerModel implements Serializable
{
	/** PlayerModel is the player character class
	 *  Everything about our nameless (?) hero is in here
	 *  including future level/stats/gear/inventory-data
	 */
	private String name;
	private int roomX;
	private int roomY;
	private int cellX;
	private int cellY;
	private String currentArea;
	
	public PlayerModel()
	{
		name = "test";
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
	


}
