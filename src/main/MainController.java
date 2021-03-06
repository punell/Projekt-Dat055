package main;
import gameLayer.CharacterModel;
import gameLayer.GameController;
import gameLayer.PlayerModel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
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



/** MainController manages most of the input from the keyboard. 
 *  It creates most of the other Controllers and manages the communication
 *  between them. 
 * 
 * @author Joakim Schmidt
 * @version 2015-03-09
 */

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
	private int screenWidth;
	private int screenHeight;
	private String command;
	private String commandArgs;
	private Random encounterRandomizer;
	private DialogueController dialogueControl;
	private String currentContentPane;
	
	/** MainController constructor. Only creates what is necessary for basic
	 * menu functionality.
	 * @param Window-title
	 * @throws IOException
	 */
	private MainController(String title) throws IOException
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		//Jockes superspecial-solution for crazy screen-res-reporting by window manager
		if(screenWidth==3968) screenWidth=2048;
		
		mainWindow = new MainView(title, screenWidth, screenHeight);
		menuControl = new MenuController(screenWidth, screenHeight);
		currentContentPane = null;
		menuControl.getView().addObserver(this);
		mainWindow.addKeyListener(this);
		keyMap = new HashMap<Integer, String>();
		dialogueControl = new DialogueController(screenWidth, screenHeight);
		encounterRandomizer = new Random();
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
	
	/** Causes events based on the input which can be from both the
	 *  keyboard or the Observer
	 *  
	 */
	private void checkInput()
	{
		//if(mainWindow.getContentPane() == null || mainWindow.getContentPane().equals(mapControl.getView()))
		if(currentContentPane == null || currentContentPane.equals("mapControl"))
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
									  
				default: playerMovement(); break;
			}
			commandArgs = null;
		}
		//else if(mainWindow.getContentPane().equals(encounterControl.getView()))
		else if(currentContentPane.equals("encounterControl"))
		{
			encounterControl.input(command);
		}
	}
	
	
	/** Causes the manages communication between the player and the map
	 *  to check for properties such as walkable (ie, can the player walk here?)
	 *  
	 */
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
		if(encounterChance != 0)
		{
			int chance = encounterRandomizer.nextInt(encounterChance);
			if(chance == 0)
			{
				startEncounter(encounterChance);
			}
		}
	}
	
	/** Prepares the encounterLayer for an encounter, and then 
	 * executes it.
	 * @param chance of encounter at the players current location
	 */
	private void startEncounter(int chance)
	{
		currentContentPane = "encounterControl";
		mainWindow.getGlassPane().setVisible(false);
		if(chance == 1)
			encounterControl.setMonsterLevel(99);
		else if(chance == 10)
			encounterControl.setMonsterLevel(3); 
		else
			encounterControl.setMonsterLevel(1);
		encounterControl.setPlayerStats(
				gameControl.getHealth(), //int
				gameControl.getPlayerStats()); //hashmap
		encounterControl.setView();
		mainWindow.setContentPane(encounterControl.getView());
	}
	
	/** Additional logic needed for moving the player to a valid location
	 * @param coordinates for players last known "room"
	 */
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
	
	/** Reads the keymap.txt file and creates entries in the keyMap (Hashmap)
	 */
	private void populateKeyMap()
	{
		try 
		{
			InputStream filepath = getClass().getClassLoader().getResourceAsStream("resource/textfiles/keymap.txt");
			InputStreamReader reader = new InputStreamReader(filepath);
			BufferedReader keys = new BufferedReader(reader);
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
	
	/** Sets the MainView-object visible or invisible
	 * @param isVisible (true or false)
	 */
	private void setVisible(boolean isVisible)
	{
		mainWindow.setVisible(isVisible);
	}
	/** Main-method, execution starts here
	 * @param args arguments from commandline
	 * @throws IOException may throw IOException
	 */
	public static void main(String[] args) throws IOException 
	{
		MainController app = new MainController("spelet v0.90 (Encounters and Dialogues!)");
		app.setVisible(true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable fromClass, Object argument) 
	{
		if(fromClass instanceof MenuView)
		{
			if(argument instanceof String[])
			{
				String[] args = (String[])argument;
				command = args[0];
				commandArgs = args[1];
				if(menuControl.firstGame())
				{
					mapControl = new MapController(screenWidth, screenHeight);
					gameControl = new GameController(screenWidth, screenHeight);
					saveLoadControl = new SaveAndLoadController();
					encounterControl = new EncounterController(screenWidth, screenHeight);
					encounterControl.addObserver(this);
					currentContentPane = "mapControl";
					mainWindow.setContentPane(mapControl.getView());
					mainWindow.setGlassPane(gameControl.getView());
					mainWindow.getGlassPane().setVisible(true);
					mainWindow.revalidate();
				}
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
					encounterControl = new EncounterController(screenWidth, screenHeight);
					encounterControl.addObserver(this);
					currentContentPane = "mapControl";
					mainWindow.setContentPane(mapControl.getView());
					mainWindow.setGlassPane(gameControl.getView());
					mainWindow.getGlassPane().setVisible(true);
					mainWindow.revalidate();
				}
			}
		}
		else if(fromClass instanceof EncounterController)
		{
			if(argument instanceof Integer)
			{			

				int playerHealth = (Integer)argument;
				if(playerHealth <= 0)
				{
					menuControl.setFirstGame();
					menuControl.show();
				}
				else
				{
					gameControl.setPlayerHealth(playerHealth);
					gameControl.calculateEquipmentBonus();
					mainWindow.setGlassPane(gameControl.getView());
					mainWindow.setContentPane(mapControl.getView());
					mainWindow.getGlassPane().setVisible(true);
					currentContentPane = "mapControl";
					mainWindow.revalidate();
				}
			}
		}
		
		
	}
}
