package menuRenderer;

import mapRenderer.MapController;
import saveAndLoad.SaveAndLoadController;
import gameLayer.GameController;


/**
 * Creates a MenuModel and a MenuView. Handles the communication with MenuView
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class MenuController
{
	private MenuModel menuModel; 
	private MenuView menuView; 
	
	/** Constructor
	 * @param screenWidth The screen resolution width
	 * @param screenHeight The screen resolution height
	 */
	public MenuController(int screenWidth, int screenHeight)
	{
		menuModel = new MenuModel();
		menuView = new MenuView(menuModel, screenWidth, screenHeight);
	}
	
	/**
	 * Sets it to main-menu then shows the menu.
	 */
	public void show()
	{
		menuView.setMenu("main");
		menuView.setVisible(true);
	}
	
	/**
	 * Gets the menu view
	 * @return MenuView
	 */
	public MenuView getView()
	{
		return menuView;
	}
	
	/**
	 * Sets the firstGame-variable to true
	 */
	public void setFirstGame()
	{
		menuView.setFirstGame();
	}
	
	/**
	 * Gets the firstGame-variable
	 * @return firstGame-variable
	 */
	public boolean firstGame()
	{
		return menuView.firstGame();
	}
	
}