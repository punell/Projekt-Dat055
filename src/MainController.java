import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;


public class MainController extends KeyAdapter
{
	private MainView mainWindow;
	private MapController mapControl;
	private GameController gameControl;
	private HashMap<Integer, String> keyMap;
	private MenuController menuControl;
	private EncounterController encounterControl;
	private Dimension screenSize;
	private int screenResolutionWidth; //future versions might allow for changes based on the resolution
	private int screenResolutionHeight;
	private String command;
	private MainController(String title) throws IOException
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenResolutionWidth = (int)screenSize.getWidth();
		screenResolutionHeight = (int)screenSize.getHeight();
		
		mainWindow = new MainView(title, screenResolutionWidth, screenResolutionHeight);
		mapControl = new MapController(screenResolutionWidth, screenResolutionHeight);
		gameControl = new GameController(screenResolutionWidth, screenResolutionHeight);
		menuControl = new MenuController(screenResolutionWidth, screenResolutionHeight);
		encounterControl = new EncounterController(screenResolutionWidth, screenResolutionWidth);
		//mainWindow.setContentPane(mapControl.getView());
		mainWindow.setContentPane(encounterControl.getView());
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
		//checks if gameControl is the current glasspane (i.e. what is actually seen by the player right now)
		if(mainWindow.getGlassPane().equals(gameControl.getView())) 
		{
			if(command.equals("Esc"))
				changeGlassPane(menuControl.getView());
			else
			{
				gameControl.move(command);
				mapControl.updateView(gameControl.getPlayerCoords('a'));
				if(!mapControl.isWalkable(gameControl.getPlayerCoords('c')))
				{
					gameControl.moveRevert();
					mapControl.updateView(gameControl.getPlayerCoords('a'));
				}
			}
		}
		else if(mainWindow.getGlassPane().equals(menuControl.getView()))
		{
			if(command.equals("Esc"))
				changeGlassPane(gameControl.getView());
		}
	}
	
	
	private void changeContentPane(JPanel changeTo)
	{
		//This method is a stub, made to illustrate how easily we want to be able to change
		//between the map-view and the encounter-view
		mainWindow.setContentPane(changeTo); 
	}
	
	private void changeGlassPane(JPanel changeTo)
	{
		//This method is a stub, made to illustrate how easily we want to be able to change
		//between the character-view and the menu-view
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
		MainController app = new MainController("spelet v0.20 (changed entire design)");
		app.pack();
		app.setVisible(true);
	}
}
