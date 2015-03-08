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


public class DialogueView extends JFrame implements KeyListener
{
	/** 
	 *  Displays dialogue windows
	 **/
	private DialogueModel dialogueModel; 
	private JTextArea textArea;
	private int sRW;
	private int sRH;
	
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
	
	public void Text(String key)
	{
		HashMap<String, String> map = dialogueModel.getMap(); 
		if(map.containsKey(key))
			textArea.append(map.get(key));
		else
			textArea.append(key);
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
