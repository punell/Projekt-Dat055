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
		//checks if gameControl is the current glasspane 
		//(i.e. what is actually seen by the player right now)
		if(mainWindow.getGlassPane().equals(gameControl.getView())) 
		{
			if(command.equals("Esc"))
				changeGlassPane(menuControl.getView());
			
			else if(command.equals("Save"))
				saveLoadControl.saveGame(gameControl.getPlayer(), gameControl.getCharModel());
			
			else if(command.equals("Load"))
			{
				saveLoadControl.loadGame();
				PlayerModel pmFromLoad = saveLoadControl.getPlayer();
				CharacterModel cmFromLoad = saveLoadControl.getCharModel();
				gameControl.updateGameControllerModels(pmFromLoad, cmFromLoad);
				gameControl.getView().updateCharacterViewModels(cmFromLoad, pmFromLoad);
				gameControl.getView().updatePlayerPosition();
				gameControl.getView().updateCellGrid();
				mapControl.setCurrentArea(gameControl.getPlayerArea());
				mapControl.updateMapRenderView(gameControl.getPlayerCoords('r'));
			}
			
			else if(command.equals("CheckInventory"))
				gameControl.checkInventory();
			
			else if(command.equals("DrinkHealthPotion"))
			{
				System.out.println(gameControl.getHealth());
				gameControl.playerUseItem("Health Potion");
				System.out.println(gameControl.getHealth());
			}
			else if(command.equals("CheckDialogue")) //Temporary for Jeppes dialogue-testing
			{
				DialogueController test = new DialogueController(50,50);
				test.getView().pack();
				test.getView().setVisible(true);
				
			}
			//movement, all other commands come first, because movement has
			//four invokers (four directions), while others only have one each
			else 
			{
				int[] oldRoomCoords = gameControl.getPlayerCoords('r');				
				gameControl.move(command);
				moveLogic(oldRoomCoords);
				
				if(!mapControl.isWalkable(gameControl.getPlayerCoords('c')))
				{
					oldRoomCoords = gameControl.getPlayerCoords('r');	
					gameControl.moveRevert();
					moveLogic(oldRoomCoords);
				}
			}
			
			
		}
		else if(mainWindow.getGlassPane().equals(menuControl.getView()))
		{
			if(command.equals("Esc"))
				changeGlassPane(gameControl.getView());
		}
	}
	
	private void moveLogic(int[] oldRoomCoords)
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
