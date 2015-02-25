package menuRenderer;

import javax.swing.JFrame;

public class MenuView extends JFrame
{
	/** MenuRenderView displays the menu 
	 * 
	 **/
	
	private MenuModel menuModel;
	
	public MenuView(MenuModel mM, int screenWidth, int screenHeight)
	{
		super("Menu");
		menuModel = mM;
		add(menuModel.getButton());
	} 
	
	
	
	
}