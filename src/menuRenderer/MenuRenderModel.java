package menuRenderer;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;


public class MenuRenderModel
{
	private ArrayList<JLabel> labelList;
	private JLabel save;
	private JLabel load;
	private JLabel quit;
	
	public MenuRenderModel()
	{
		labelList = new ArrayList<>();
		save = new JLabel("Save");
		load = new JLabel("Load");
		quit = new JLabel("Quit");
		//quit.setPreferredSize(new Dimension (200, 100));
		labelList.add(save);
		labelList.add(load);
		labelList.add(quit);
	}
	
	public ArrayList getLabels()
	{
		return labelList;
	}
}