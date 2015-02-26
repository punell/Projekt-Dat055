package menuRenderer;


public class MenuController
{
	private MenuModel menuModel; 
	private MenuView menuView; 
	public MenuController(int screenWidth,int screenHeight)
	{
		menuModel = new MenuModel();
		menuView = new MenuView(menuModel, screenWidth, screenHeight);
	}
	
	public MenuView getView()
	{
		menuView.pack();
		return menuView;
	}
	
}