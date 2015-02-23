import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class DialogueView extends JFrame
{
	/** 
	 *  Displays dialogue windows
	 **/
	private ArrayList<JLabel> labelList;
	private DialogueModel dialogueModel; 
	
	public DialogueView(DialogueModel dM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		dialogueModel = dM;
		setSize(screenResolutionWidth, screenResolutionHeight);
		setLayout(new GridLayout());		
		labelList = dialogueModel.getFrame(); 
		addFrame();
	}
	
	private void addFrame()
	{
		for(JLabel x: labelList)
		{
			add(x);
		}
	}
}