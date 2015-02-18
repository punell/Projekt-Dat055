


public class GameController 
{
	private PlayerModel playerModel;
	private CharacterView charView;
	//private char lastMove;
	private String lastMove;
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
	
	public int[] getPlayerCoords(char flag) // use flag to change what to get (r for room, c for cell, anything else for both)
	{
		
		return playerModel.getPlayerCoords(flag);
	}
	
	public void move(String direction) 
	{
		//this can easily be moved into PlayerModel if ever necessary (might be if we want to move monsters as well)
		charView.updateCharacterView("player", false); // causes the picture of the player character to disappear

		switch(direction)
		{
			case "Up": playerModel.moveNorth(); lastMove = "Down"; break;
			case "Right": playerModel.moveEast(); lastMove = "Left"; break;
			case "Down": playerModel.moveSouth(); lastMove = "Up"; break;
			case "Left": playerModel.moveWest(); lastMove = "Right"; break;
		}
		charView.updateCharacterView("player", true); // makes it appear again
	}
	
	public void moveRevert()
	{
		move(lastMove);
	}
	
	
}
