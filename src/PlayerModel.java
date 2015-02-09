import java.io.IOException;


public class PlayerModel
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
	private boolean underground;
	private CharacterView characterView;
	private MapRenderModel mapRenderModel;
	
	public PlayerModel(CharacterView cV, MapRenderModel mRM) throws IOException
	{
		// the characterView object is necessary for PlayerModel to update the rendering
		characterView = cV; 
		// the mapRenderModel object is necessary to check information about the world (the type of ground mostly)
		mapRenderModel = mRM; 
		name = "test";
		roomX = 0;
		roomY = 0;
		cellX = 1;
		cellY = 1;
		underground = false;
		characterView.updateCellGrid(cellX, cellY, true); //initial placing of character
		mapRenderModel.updateMapRenderModel(roomX, roomY);
	}
	/* The functions commented below are currently not in use, but may be useful in future versions
	public int getCellX()
	{
		return cellX;
	}
	public int getCellY()
	{
		return cellY;
	}
	public void setCellX(int x)
	{
		cellX = x;
	}
	public void setCellY(int y)
	{
		cellY = y;
	}*/
	private boolean moveNorth()
	{
		//X=0, Y=0 is Top Left (because everything always is in graphical programming (for some reason))
		cellY--; 
		if(cellY < 0) //this means we went outside the current rooms borders
		{
			cellY = 15; //we therefore set our new position at the opposite side in the new room
			roomY--; //and change room...
			return true; //...and tells the move-method about it
		}
		return false;
	}
	private boolean moveEast()
	{
		cellX++;
		if(cellX > 15)
		{
			cellX = 0;
			roomX++;
			return true;
		}
		return false;
	}
	private boolean moveSouth()
	{
		cellY++;
		if(cellY > 15)
		{
			cellY = 0;
			roomY++;
			return true;
		}
		return false;
	}
	private boolean moveWest()
	{
		cellX--;
		if(cellX < 0)
		{
			cellX = 15;
			roomX--;
			return true;
		}
		return false;
	}
	
	public void move(char direction) throws IOException
	{
		char lastMove = 'N'; //must be initialized for the moveRevert-method
		boolean roomChange = false;
		characterView.updateCellGrid(cellX, cellY, false); // causes the picture of the character to disappear
		if(direction == 'N')
		{
			roomChange = moveNorth();
			lastMove = 'S';
		}	
		else if(direction == 'E')
		{
			roomChange = moveEast();
			lastMove = 'W';
		}
		else if(direction == 'S')
		{
			roomChange = moveSouth();
			lastMove = 'N';
		}
		else if(direction == 'W')
		{
			roomChange = moveWest();
			lastMove = 'E';
		}
		
		if(roomChange)
			mapRenderModel.updateMapRenderModel(roomX, roomY); //changes room and displays it, if necessary
		
		if(!mapRenderModel.isWalkable(cellX, cellY)) //some cells (mountains, water...) are not walkable
		{
			//we revert the last move we made, and check again if we changed room (if so, we display the new (old) room again
			roomChange = moveRevert(lastMove);
			if(roomChange)
				mapRenderModel.updateMapRenderModel(roomX, roomY);
			
		}
		characterView.updateCellGrid(cellX, cellY, true); //turn on display of the character again (in his new position)
	}
	
	private boolean moveRevert(char direction)
	{
		boolean roomChange = false;
		if(direction == 'N')
			roomChange = moveNorth();
		else if(direction == 'E')
			roomChange = moveEast();
		else if(direction == 'S')
			roomChange = moveSouth();
		else if(direction == 'W')
			roomChange = moveWest();
		return roomChange;
	}
	

}
