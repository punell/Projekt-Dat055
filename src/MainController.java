import gameLayer.CharacterModel;
import gameLayer.GameController;
import gameLayer.PlayerModel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JPanel;

import SaveAndLoad.SaveAndLoadController;
import dialogue.DialogueController;
import mapRenderer.MapController;
import menuRenderer.MenuController;



public class MainController extends KeyAdapter
{
	private MainView mainWindow;
	private MapController mapControl;
	private GameController gameControl;
	private HashMap<Integer, String> keyMap;
	private MenuController menuControl;
	//private EncounterController encounterControl;
	private SaveAndLoadController saveLoadControl;
	private Dimension screenSize;
	private int screenWidth; //future versions might allow for changes based on the resolution
	private int screenHeight;
	private String command;
	private MainController(String title) throws IOException
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		//Jockes superspecial-solution, VERY temporary
		if(screenWidth==3968) screenWidth=2048;
		
		mainWindow = new MainView(title, screenWidth, screenHeight);
		mapControl = new MapController(screenWidth, screenHeight);
		gameControl = new GameController(screenWidth, screenHeight);
		menuControl = new MenuController(screenWidth, screenHeight);
		//encounterControl = new EncounterController(screenResolutionWidth, screenResolutionWidth);
		saveLoadControl = new SaveAndLoadController();
		mainWindow.setContentPane(mapControl.getView());
		//mainWindow.setContentPane(encounterControl.getView());
		mainWindow.setGlassPane(gameControl.getView());
		mainWindow.getGlassPane().setVisible(true);
		mainWindow.addKeyListener(this);
		keyMap = new HashMap<Integer, String>();
		populateKeyMap();
		

		
		
		
	}
		
	public void keyPressed(KeyEvent e)
	{
		if(keyMap.containsKey(e.getKeyCode()))
		{
			command = keyMap.get(e.getKeyCode());
			checkInput();
		}
	}
	
	private void checkInput()
	{
		switch(command)
		{
			case "Esc":  changeGlassPane(menuControl.getView()); break;
			case "Save": saveLoadControl.save(gameControl.packageForSave()); break;
			case "Load": gameControl.restoreFromLoad(saveLoadControl.load());
						 mapControl.restoreFromLoad(gameControl.getPlayerArea(), 
						 gameControl.getPlayerCoords('r')); break;
						 
			case "CheckInventory": 		gameControl.checkInventory(); break;
			case "DrinkHealthPotion": 	gameControl.playerUseItem("Health Potion"); break;
			case "CheckDialogue": 		DialogueController test = new DialogueController(50,50);
									    test.getView().pack();
									    test.getView().setVisible(true); break;
								  
			default: playerMovement(); break;
		}
		
	}
	
	private void playerMovement()
	{
		int[] oldRoomCoords = gameControl.getPlayerCoords('r');				
		gameControl.move(command);
		playerMovementLogic(oldRoomCoords);
		
		if(!mapControl.isWalkable(gameControl.getPlayerCoords('c')))
		{
			oldRoomCoords = gameControl.getPlayerCoords('r');	
			gameControl.moveRevert();
			playerMovementLogic(oldRoomCoords);
		}
	}
	
	private void playerMovementLogic(int[] oldRoomCoords)
	{
		if(!Arrays.equals(oldRoomCoords, gameControl.getPlayerCoords('r')))
		{
			mapControl.updateMapRenderView(gameControl.getPlayerCoords('r'));
			gameControl.updateCellGrid();
		}
		
		if(mapControl.isLink(gameControl.getPlayerCoords('c')))
		{
			String link = mapControl.linksTo(gameControl.getPlayerCoords('c'));
			if(link.equals(gameControl.getPlayerArea()))
			{
				gameControl.setPlayerArea("overworld");
				mapControl.setCurrentArea("overworld");
			}
			else
			{
				gameControl.setPlayerArea(link);
				mapControl.setCurrentArea(link);
			}
			
			mapControl.updateMapRenderView(gameControl.getPlayerCoords('r'));
		}
	}
	
	
	private void changeContentPane(JPanel changeTo)
	{
		//This method is a stub, made to illustrate how 
		//easily we want to be able to change
		//between the map-view and the encounter-view
		mainWindow.setContentPane(changeTo); 
	}
	
	private void changeGlassPane(JPanel changeTo)
	{
		//This method is a stub, made to illustrate how 
		//easily we want to be able to change
		//between the character-view and the menu-view (needed?)
		mainWindow.setGlassPane(changeTo); 
	}
	
	private void populateKeyMap()
	{
		try 
		{
			BufferedReader keys = new BufferedReader(new FileReader("keymap.txt"));
			String line = keys.readLine();
			int index;
			String value;
			String keyString;
			int key;
			while(line != null)
			{
				while(line.charAt(0) == '#')
					line = keys.readLine();
				index = line.indexOf(Character.valueOf('='));
				value = line.substring(0, index);
				keyString = line.substring(index+1);
				key = Integer.parseInt(keyString.trim());
				keyMap.put(key, value);
				line = keys.readLine();
			}
			keys.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void pack()
	{
		mainWindow.pack();
	}
	private void setVisible(boolean isVisible)
	{
		mainWindow.setVisible(isVisible);
	}
	public static void main(String[] args) throws IOException 
	{
		MainController app = new MainController("spelet v0.40 (Inventory and items!)");
		app.pack();
		app.setVisible(true);
	}
}
