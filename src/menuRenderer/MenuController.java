package menuRenderer;

import mapRenderer.MapController;
import saveAndLoad.SaveAndLoadController;
import gameLayer.GameController;


public class MenuController
{
	private MenuModel menuModel; 
	private MenuView menuView; 
	public MenuController(int screenWidth, int screenHeight)
	{
		menuModel = new MenuModel();
		menuView = new MenuView(menuModel, screenWidth, screenHeight);
	}
	
	public void show()
	{
		menuView.setMenu("main");
		menuView.setVisible(true);
	}
	
	public MenuView getView()
	{
		return menuView;
	}
	
	public void setFirstGame()
	{
		menuView.setFirstGame();
	}
	public boolean firstGame()
	{
		return menuView.firstGame();
	}
	
}