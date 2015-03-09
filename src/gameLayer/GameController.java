package gameLayer;

import java.util.HashMap;
import java.util.LinkedList;

import dialogue.DialogueController;
import items.Item;
import items.ItemUnpickable;
import saveAndLoad.SaveGame;



/**GameController handles communication between the gameLayer-package
 * and the main-package. 
 * @author Joakim Schmidt
 * @version 2015-03-09
 */
public class GameController 
{
	private CharacterModel charModel;
	private PlayerModel playerModel;
	private CharacterView charView;
	private int screenWidth;
	private int screenHeight;
	private String lastMove;
	private DialogueController dialogueControl;
	
	/**Constructor
	 * @param screenWidth The width of the screen
	 * @param screenHeight The height of the screen
	 */
	public GameController(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		charModel = new CharacterModel(screenWidth, screenHeight);
		playerModel = new PlayerModel();
		charView = new CharacterView(charModel, playerModel, screenWidth, screenHeight);
		dialogueControl = new DialogueController(screenWidth, screenHeight);
		
	}
	
	/**@return currently displayed CharacterView
	 */
	public CharacterView getView() //MainView uses this
	{
		return charView;
	}
	
	/**@param flag allows for a choice between what coordinates are wanted
	 * @return players current location
	 */
	public int[] getPlayerCoords(char flag) // use flag to change what to get (r for room, c for cell, anything else for both)
	{
		return playerModel.getPlayerCoords(flag);
	}
	/**
	 * @return players current area (underground or overworld (or other))
	 */
	public String getPlayerArea()
	{
		return playerModel.getPlayerArea();
	}
	/**Sets players current area to parameter
	 * @param area name of area
	 */
	public void setPlayerArea(String area)
	{
		playerModel.setPlayerArea(area);
	}
	/**
	 * @return players current health
	 */
	public int getHealth()
	{
		return playerModel.getHealth();
	}
	/**Gets an Item from the players inventory
	 * @param itemName name of item
	 * @return the Item, if found
	 */
	public Item getItem(String itemName)
	{
		return playerModel.getItem(itemName);
	}
	/**Heals player for parameter amount
	 * @param heal amount to heal
	 */
	public void healPlayer(int heal) //heals cannot go above maxHealth
	{
		playerModel.healPlayer(heal);
	}
	/**Damages player for parameter amount
	 * @param damage amount of damage
	 * @return true if player died from damage, false if not
	 */
	public boolean damagePlayer(int damage)
	{
		return playerModel.damagePlayer(damage);
	}
	/**
	 * Shows the players inventory
	 */
	public void checkInventory()
	{
		playerModel.checkInventory();
	}
	/**Makes player "use" item with parameter name (potions)
	 * @param itemName name of item
	 */
	public void playerUseItem(String itemName)
	{
		playerModel.useItem(itemName);
	}
	/**Gets players current stats (health, armor, damage)
	 * @return HashMap containing stats
	 */
	public HashMap<String, Integer> getPlayerStats()
	{
		return playerModel.getPlayerStats();
	}
	/**Gets players current inventory
	 * @return LinkedList containing Items
	 */
	public LinkedList<Item> getBackpack()
	{
		return playerModel.getBackpack();
	}
	/**Sets players health to parameter
	 * @param health amount
	 */
	public void setPlayerHealth(int health)
	{
		playerModel.setPlayerHealth(health);
	}
	/**Calculates all stat-bonuses for currently equipped Items
	 */
	public void calculateEquipmentBonus()
	{
		playerModel.calculateEquipmentBonus();
	}
	/** Makes the player move in parameter direction
	 * @param direction N/E/S/W
	 */
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
		if(item instanceof ItemUnpickable)
		{
			
			dialogueControl.show(item.getName());
			moveRevert();
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
	
	/**Forces CharacterView-object to update the cellgrid
	 * 
	 */
	public void updateCellGrid()
	{
		charView.updateCellGrid();
	}
	
	/**Reverts last made move
	 */
	public void moveRevert()
	{
		move(lastMove);
	}

	/** Gets the PlayerModel-object
	 * @return player
	 */
	public PlayerModel getPlayer() 
	{
		return playerModel;
	}
	/**Gets the CharacterModel-object
	 * @return charactermodel
	 */
	public CharacterModel getCharModel()
	{
		return charModel;
	}
	
	/**Packages current player and charactermodel in a new SaveGame-object
	 * @return the new SaveGame-object
	 */
	public SaveGame packageForSave()
	{
		return new SaveGame(playerModel, charModel);
	}

	/**Restores gamestate from parameter SaveGame-file
	 * @param fromLoad from the load method
	 */
	public void restoreFromLoad(SaveGame fromLoad) 
	{
		playerModel = fromLoad.getPlayer();
		playerModel.restoreFromLoad();
		charModel = fromLoad.getCharModel();
		charView.updateCharacterViewModels(charModel, playerModel);
		charView.updatePlayerPosition();
		charView.updateCellGrid();
	}
	
	
}
