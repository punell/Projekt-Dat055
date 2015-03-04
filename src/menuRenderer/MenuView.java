package menuRenderer;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuView extends JFrame implements ActionListener, KeyListener
{
	/** MenuView displays the menu 
	 * 
	 **/
	
	private MenuModel menuModel;
	private JPanel superPanel;
	private CardLayout cl;
	
	
	public MenuView(MenuModel mM, int screenWidth, int screenHeight)
	{
		super();
		menuModel = mM;
		setUndecorated(true);
		addKeyListener(this);
		setAlwaysOnTop(true);
		setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		cl = new CardLayout();
		setLayout(cl);
		add(menuModel.getMainMenu(), "main");
		add(menuModel.getLoadMenu(), "load");
		add(menuModel.getSaveMenu(), "save");
		
		
		setAL();
		setMainMenu();
		
		
	}
	
	public void setMainMenu()
	{
		cl.show(getContentPane(), "main");
	}
	
	
	private void setAL()
	{
		JPanel[] panelList = menuModel.getMenus();
		for(JPanel panel : panelList)
		{
			for(Component comp : panel.getComponents())
			{
				JButton pushButton = (JButton)comp;
				pushButton.addActionListener(this);
				pushButton.setFocusable(false);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
			case "newgame": System.out.println("walla"); break;
			case "loadgame": cl.show(getContentPane(), "load"); break;
			case "savegame": cl.show(getContentPane(), "save"); break;
			case "back": cl.show(getContentPane(), "main"); break;
		}
		
		
	} 

	@Override
	public void keyPressed(KeyEvent e) 
	{
		System.out.println(e);
		if(e.getKeyCode() == 27) // "esc"
		{
			setVisible(false);
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}


	
	
	
	
	
}