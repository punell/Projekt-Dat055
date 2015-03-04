package dialogue;
import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class DialogueView extends JFrame
{
	/** 
	 *  Displays dialogue windows
	 **/
	private DialogueModel dialogueModel; 
	private JTextArea textArea;
	
	public DialogueView(DialogueModel dM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		dialogueModel = dM;
		setBounds(screenResolutionWidth/4, screenResolutionHeight/4, screenResolutionWidth/2, screenResolutionHeight/2);
		setLayout(new BorderLayout());	
		add(textArea);
		pack();
		setVisible(true);
		
	}
	
	public void Text(String key)
	{
		HashMap<String, String> map = dialogueModel.getMap(); 
		textArea = new JTextArea(15, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.append(map.get(key));
	}
}
