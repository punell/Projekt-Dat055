import java.io.IOException;


public class MenuController
{
	private MenuRenderModel menuModel; 
	private MenuRenderView menuView; 
	public MenuController(int screenResolutionWidth,int screenResolutionHeight)
	{
		menuModel = new MenuRenderModel();
		menuView = new MenuRenderView(menuModel, screenResolutionWidth, screenResolutionHeight);
	}
	
	public MenuRenderView getView()
	{
		return menuView;
	}
}