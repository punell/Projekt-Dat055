


public class GameController 
{
	private PlayerModel playerModel;
	private CharacterView charView;
	private char lastMove;
	public GameController(int screenResolutionWidth, int screenResolutionHeight)
	{
		playerModel = new PlayerModel();
		//other models here, monsters and chests and stuff
		charView = new CharacterView(playerModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	public CharacterView getView() //MainView uses this
	{
		return charView;
	}
	
	public int[] getPlayerCoords() //all coords (cellx, celly, roomx, roomy)
	{
		return playerModel.getPlayerCoords();
	}
	public int[] getPlayerRoom() //room coords
	{
		return playerModel.getPlayerRoom();
	}
	public int[] getPlayerCell() //cell coords
	{
		return playerModel.getPlayerCell();
	}
	
	public void move(char direction) 
	{
		//this can easily be moved into PlayerModel if ever necessary (might be if we want to move monsters as well)
		charView.updateCharacterView("player", false); // causes the picture of the player character to disappear
		if(direction == 'N')
		{
			playerModel.moveNorth();
			lastMove = 'S';
		}
		else if(direction == 'E')
		{
			playerModel.moveEast();
			lastMove = 'W';
		}
		else if(direction == 'S')
		{
			playerModel.moveSouth();
			lastMove = 'N';
		}
		else if(direction == 'W')
		{
			playerModel.moveWest();
			lastMove = 'E';
		}
		charView.updateCharacterView("player", true); // makes it appear again
	}
	
	public void moveRevert()
	{
		move(lastMove);
	}
	
	
}
