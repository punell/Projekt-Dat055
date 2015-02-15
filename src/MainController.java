import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JPanel;


public class MainController extends KeyAdapter
{
	private MainView mainWindow;
	private MapController mapControl;
	private GameController gameControl;
	//private MenuController menuControl;
	//private EncounterController encounterControl;
	private int screenResolutionWidth = 1920; //future versions might allow for changes based on the resolution
	private int screenResolutionHeight = 1080;
	private char[] command;
	private MainController(String title) throws IOException
	{
		command = new char[2];
		mainWindow = new MainView(title);
		mapControl = new MapController(screenResolutionWidth, screenResolutionHeight);
		gameControl = new GameController(screenResolutionWidth, screenResolutionHeight);
		mainWindow.setContentPane(mapControl.getView());
		mainWindow.setGlassPane(gameControl.getView());
		mainWindow.getGlassPane().setVisible(true);
		mainWindow.addKeyListener(this);

		//menuControl = new MenuController();
		//encounterControl = new EncounterController;
		
		
	}
		
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 37)
		{
			command[0] = 'P';
			command[1] = 'W';
		}
		else if(e.getKeyCode() == 38)
		{
			command[0] = 'P';
			command[1] = 'N';
		}
		else if(e.getKeyCode() == 39)
		{
			command[0] = 'P';
			command[1] = 'E';
		}
		else if(e.getKeyCode() == 40)
		{
			command[0] = 'P';
			command[1] = 'S';
		}
		checkInput();
	}
	
	private void checkInput()
	{
		if(command[0] == 'P')
		{
			gameControl.move(command[1]);
			mapControl.updateView(gameControl.getPlayerCoords());
			if(!mapControl.isWalkable(gameControl.getPlayerCell()))
			{
				gameControl.moveRevert();
				mapControl.updateView(gameControl.getPlayerCoords());
			}
		}
		// and then something like this
		//if command == esc
			//changeGlassPane(menu)
		
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
