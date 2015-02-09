import java.io.IOException;


public class PlayerModel
{
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
		characterView = cV;
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
	}
	private boolean moveNorth()
	{
		cellY--;
		if(cellY < 0)
		{
			cellY = 15;
			roomY--;
			return true;
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
		characterView.updateCellGrid(cellX, cellY, false);
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
			mapRenderModel.updateMapRenderModel(roomX, roomY);
		
		if(!mapRenderModel.isWalkable(cellX, cellY))
		{
			
			roomChange = moveRevert(lastMove);
			if(roomChange)
				mapRenderModel.updateMapRenderModel(roomX, roomY);
			
		}
		characterView.updateCellGrid(cellX, cellY, true);
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
