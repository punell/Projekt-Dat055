package gameLayer;

import java.util.HashMap;
import java.util.LinkedList;

import dialogue.DialogueController;
import items.Item;
import saveAndLoad.SaveGame;



public class GameController 
{
	private CharacterModel charModel;
	private PlayerModel playerModel;
	private CharacterView charView;
	private int screenWidth;
	private int screenHeight;
	//private char lastMove;
	private String lastMove;
	public GameController(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		charModel = new CharacterModel(screenWidth, screenHeight);
		playerModel = new PlayerModel();
		charView = new CharacterView(charModel, playerModel, screenWidth, screenHeight);
	}
	
	public CharacterView getView() //MainView uses this
	{
		return charView;
	}
	
	public int[] getPlayerCoords(char flag) // use flag to change what to get (r for room, c for cell, anything else for both)
	{
		return playerModel.getPlayerCoords(flag);
	}
	public String getPlayerArea()
	{
		return playerModel.getPlayerArea();
	}
	public void setPlayerArea(String area)
	{
		playerModel.setPlayerArea(area);
	}
	public int getHealth()
	{
		return playerModel.getHealth();
	}
	public Item getItem(String itemName)
	{
		return playerModel.getItem(itemName);
	}
	public void healPlayer(int heal) //heals cannot go above maxHealth
	{
		playerModel.healPlayer(heal);
	}
	public boolean damagePlayer(int damage) //returns true on death
	{
		return playerModel.damagePlayer(damage);
	}
	public void checkInventory()
	{
		playerModel.checkInventory();
	}
	public void playerUseItem(String itemName)
	{
		playerModel.useItem(itemName);
	}
	public HashMap<String, Integer> getPlayerStats()
	{
		return playerModel.getPlayerStats();
	}
	public LinkedList<Item> getBackpack()
	{
		return playerModel.getBackpack();
	}
	public void move(String direction) 
	{
		//this can easily be moved into PlayerModel if ever necessary (might be if we want to move monsters as well)
		switch(direction)
		{
			case "Up": playerModel.moveNorth(); lastMove = "Down"; break;
			case "Right": playerModel.moveEast(); lastMove = "Left"; break;
			case "Down": playerModel.moveSouth(); lastMove = "Up"; break;
			case "Left": playerModel.moveWest(); lastMove = "Right"; break;
		}
		Item item = charView.checkCellContents(getPlayerCoords('c'));
		if(item != null && item.getName().contains("Sign"))
		{
			DialogueController dC = new DialogueController(screenWidth, screenHeight);
		}
		else
		{
			item = charView.getCellContents(getPlayerCoords('c'));
			if(item != null)
			{
				playerModel.addItem(item);
				
			}
			charView.updatePlayerPosition();
		}
	}
	
	public void updateCellGrid()
	{
		charView.updateCellGrid();
	}
	
	public void moveRevert()
	{
		move(lastMove);
	}

	public PlayerModel getPlayer() 
	{
		return playerModel;
	}
	public CharacterModel getCharModel()
	{
		return charModel;
	}
	
	public SaveGame packageForSave()
	{
		return new SaveGame(playerModel, charModel);
	}

	public void restoreFromLoad(SaveGame fromLoad) 
	{
		playerModel = fromLoad.getPlayer();
		charModel = fromLoad.getCharModel();
		charView.updateCharacterViewModels(charModel, playerModel);
		charView.updatePlayerPosition();
		charView.updateCellGrid();
	}
	
	
}
