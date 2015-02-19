import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuRenderView extends JPanel 
{
	/** MenuRenderView displays the menu 
	 * 
	 **/
	private ArrayList<JLabel> labelList;
	private MenuRenderModel menuModel;
	
	public MenuRenderView(MenuRenderModel mMD, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		menuModel = mMD;
		setSize(1920, 1080); // temporary bug fix
		setLayout(new BorderLayout());
		labelList = menuModel.getLabels();
		addLabels();
	} 
	
	private void addLabels()
	{
		for(JLabel x:labelList)
		{
			add(x, BorderLayout.CENTER);
		}
	}
	
}