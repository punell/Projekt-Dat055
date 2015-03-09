package dialogue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Displays the dialogue window and it's textarea. Receives a HashMap from DialogueModel
 * @author Jesper Kjellqvist
 * @version 2015-03-09
 *
 */
public class DialogueView extends JFrame implements KeyListener
{
	private DialogueModel dialogueModel; 
	private JTextArea textArea;
	private int sRW;
	private int sRH;
	
	/**
	 * This constructor creates the dialogue window, with a TextArea, then adds text to the TextArea.
	 * @param dM Creates the DialogueModel
	 * @param screenResolutionWidth Sets the resolution 
	 * @param screenResolutionHeight Sets the resolution
	 */
	public DialogueView(DialogueModel dM, int screenResolutionWidth, int screenResolutionHeight)
	{
		super();
		sRW = screenResolutionWidth;
		sRH = screenResolutionHeight;
		addKeyListener(this);
		dialogueModel = dM;
		setBounds(screenResolutionWidth/4, screenResolutionHeight/4, screenResolutionWidth/2, screenResolutionHeight/2);
		setLayout(new BorderLayout());
		setUndecorated(true);
		getContentPane().setBackground(new Color(160,82,45));
		textArea = new JTextArea(15, 30);
		textArea.setOpaque(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		textArea.setFont(new Font("Verdana", 1, 17));
		add(textArea); 
	}
	
	/**
	 * Adds the text to the dialogue window.
	 * @param key Fetches the text related to the specific key, to be added to the dialogue window
	 */
	public void Text(String key)
	{
		HashMap<String, String> map = dialogueModel.getMap(); 
		if(map.containsKey(key))
			textArea.setText(map.get(key));
		else
			textArea.setText(key);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 32) // 'space'
		{
			setVisible(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
