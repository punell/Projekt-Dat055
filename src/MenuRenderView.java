import javax.swing.JPanel;


public class MenuRenderView extends JPanel 
{
	/** MenuRenderView displays the menu 
	 * 
	 **/
	
	private MenuRenderModel menuModel;
	public MenuRenderView(MenuRenderModel mMD, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		menuModel = mMD;
		setSize(screenResolutionWidth, screenResolutionHeight);
	} 
}