package menuRenderer;

import gameLayer.GameController;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import mapRenderer.MapController;
import saveAndLoad.SaveAndLoadController;

public class MenuView extends JFrame implements ActionListener, KeyListener
{
	/** MenuView displays the menu 
	 * 
	 **/
	
	private MenuModel menuModel;
	private JPanel superPanel;
	private CardLayout cl;
	private GameController gameControl;
	private SaveAndLoadController saveLoadControl;
	private MapController mapControl;
	
	public MenuView(MapController mC, SaveAndLoadController sALC, GameController gC, MenuModel mM, int screenWidth, int screenHeight)
	{
		super();
		menuModel = mM;
		gameControl = gC;
		saveLoadControl = sALC;
		mapControl = mC;
		
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
				if(comp instanceof JButton)
				{
					JButton pushButton = (JButton)comp;
					pushButton.addActionListener(this);
					pushButton.setFocusable(false);
				}
				else if(comp instanceof JPanel)
				{
					JPanel tempPanel = (JPanel)comp;
					for(Component innerComp : tempPanel.getComponents())
					{
						JButton pushButton = (JButton)innerComp;
						pushButton.addActionListener(this);
						pushButton.setFocusable(false);
					}
				}
				else if(comp instanceof JTextField)
				{
					JTextField textField = (JTextField)comp;
					textField.addKeyListener(this);
				}
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
			case "newgame": 	System.out.println("walla"); break;
			case "loadgame": 	populateLoadList("load"); 
								cl.show(getContentPane(), "load"); break;
			case "loadsel":		loadGame(); break;
			case "savegame": 	populateLoadList("save");
								cl.show(getContentPane(), "save"); break;
			case "savesel":		saveGame(); break;
			case "back": 		cl.show(getContentPane(), "main"); break;
			case "exitgame":	System.exit(0);
		}
		
		
	} 
	
	private void populateLoadList(String display)
	{
		if(display.equals("load"))
			menuModel.getLoadListModel().clear();
		else if(display.equals("save"))
			menuModel.getSaveListModel().clear();
		File folder = new File("save");
		File[] saveGames = folder.listFiles();
		
		
		for(int i=0;i<saveGames.length;i++)
		{
			if(display.equals("load"))
				menuModel.getLoadListModel().addElement(saveGames[i].getName());
			else if(display.equals("save"))
				menuModel.getSaveListModel().addElement(saveGames[i].getName());
			
		}
		
	}
	
	private void loadGame()
	{
		String savefile = (String)menuModel.getLoadList().getSelectedValue();
		gameControl.restoreFromLoad(saveLoadControl.load(savefile));
		mapControl.restoreFromLoad(gameControl.getPlayerArea(), gameControl.getPlayerCoords('r'));
		setVisible(false);
	}
	
	private void saveGame()
	{
		menuModel.getSaveField().requestFocusInWindow();
		String savefile = menuModel.getSaveField().getText();
		saveLoadControl.save(gameControl.packageForSave(), savefile);
		//menuModel.getSaveListModel().addElement(savefile+".sav");
		setVisible(false);
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
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