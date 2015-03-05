package menuRenderer;

import mapRenderer.MapController;
import saveAndLoad.SaveAndLoadController;
import gameLayer.GameController;


public class MenuController
{
	private MenuModel menuModel; 
	private MenuView menuView; 
	public MenuController(MapController mC, SaveAndLoadController sALC, GameController gC, int screenWidth, int screenHeight)
	{
		menuModel = new MenuModel();
		menuView = new MenuView(mC, sALC, gC, menuModel, screenWidth, screenHeight);
	}
	
	public void show()
	{
		menuView.setMenu("main");
		menuView.setVisible(true);
	}
	
}