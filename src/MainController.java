import gameLayer.CharacterModel;
import gameLayer.GameController;
import gameLayer.PlayerModel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JPanel;

import saveAndLoad.SaveAndLoadController;
import dialogue.DialogueController;
import encounterLayer.EncounterController;
import mapRenderer.MapController;
import menuRenderer.MenuController;
import menuRenderer.MenuView;



public class MainController implements KeyListener, Observer
{
	private MainView mainWindow;
	private MapController mapControl;
	private GameController gameControl;
	private HashMap<Integer, String> keyMap;
	private MenuController menuControl;
	private EncounterController encounterControl;
	private SaveAndLoadController saveLoadControl;
	private Dimension screenSize;
	private int screenWidth; //future versions might allow for changes based on the resolution
	private int screenHeight;
	private String command;
	private String commandArgs;
	private MainController(String title) throws IOException
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		//Jockes superspecial-solution for crazy screen-res-reporting
		if(screenWidth==3968) screenWidth=2048;
		
		mainWindow = new MainView(title, screenWidth, screenHeight);
		menuControl = new MenuController(screenWidth, screenHeight);
		menuControl.getView().addObserver(this);
		mainWindow.addKeyListener(this);
		keyMap = new HashMap<Integer, String>();
		populateKeyMap();
		menuControl.setFirstGame();
		menuControl.show();
		
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
			case "Esc":  menuControl.show(); break;
			case "Save": if(commandArgs == null) saveLoadControl.save(gameControl.packageForSave());
						 else saveLoadControl.save(gameControl.packageForSave(), commandArgs); break;
							
			case "Load": if(commandArgs == null) gameControl.restoreFromLoad(saveLoadControl.load());
						 else gameControl.restoreFromLoad(saveLoadControl.load(commandArgs));
						 mapControl.restoreFromLoad(gameControl.getPlayerArea(), 
						 gameControl.getPlayerCoords('r')); break;
						 
			case "CheckInventory": 		gameControl.checkInventory(); break;
			case "DrinkHealthPotion": 	gameControl.playerUseItem("Health Potion"); break;
			case "CheckDialogue": 		DialogueController test = new DialogueController(50,50);break;
			case "CheckEncounter":		changeContentPane(encounterControl.getView()); break;
								  
			default: playerMovement(); break;
		}
		commandArgs = null;
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
		int[] playerLocation = gameControl.getPlayerCoords('c');
		int encounterChance = mapControl.encounterChance(playerLocation);
		Random encounter = new Random(encounterChance);
		int chance = encounter.nextInt();
		if(chance == 1)
		{
			changeContentPane(encounterControl.getView());
			/*encounterControl.setPlayerStats(
					gameControl.getHealth(), //int
					gameControl.getPlayerStats(), //hashmap
					gameControl.getBackpack()); //linkedlist
			encounterControl.setMonsterLevel();*/
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
			gameControl.updateCellGrid();
		}
	}
	
	
	private void changeContentPane(JPanel changeTo)
	{
		//This method is a stub, made to illustrate how 
		//easily we want to be able to change
		//between the map-view and the encounter-view
		mainWindow.setContentPane(changeTo); 
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
	
	private void setVisible(boolean isVisible)
	{
		mainWindow.setVisible(isVisible);
	}
	public static void main(String[] args) throws IOException 
	{
		MainController app = new MainController("spelet v0.40 (Inventory and items!)");
		app.setVisible(true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable thing, Object argument) 
	{
		if(thing instanceof MenuView)
		{
			if(argument instanceof String[])
			{
				String[] args = (String[])argument;
				command = args[0];
				commandArgs = args[1];
				checkInput();
				
			}
			else if(argument instanceof String)
			{
				String arg = (String)argument;
				if(arg.equals("New Game"))
				{
					mapControl = new MapController(screenWidth, screenHeight);
					gameControl = new GameController(screenWidth, screenHeight);
					saveLoadControl = new SaveAndLoadController();
					mainWindow.setContentPane(mapControl.getView());
					mainWindow.setGlassPane(gameControl.getView());
					mainWindow.getGlassPane().setVisible(true);
					mainWindow.revalidate();
				}
			}
		}
		
		
	}
}
