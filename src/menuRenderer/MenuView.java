package menuRenderer;

import gameLayer.GameController;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import mapRenderer.MapController;
import saveAndLoad.SaveAndLoadController;

/**
 * Creates a JFrame for the menu and displays the menu. Uses a CardLayout to show the different menus in the same window.
 * Reads the name of the different save-files in the folder "save"
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class MenuView extends Observable implements ActionListener, KeyListener
{
	private JFrame menuFrame;
	private MenuModel menuModel;
	private CardLayout cl;
	private String currentCard;
	private boolean firstGame;
	
	public MenuView(MenuModel mM, int screenWidth, int screenHeight)
	{
		super();
		menuModel = mM;
		
		menuFrame = new JFrame();
		menuFrame.setUndecorated(true);
		menuFrame.addKeyListener(this);
		menuFrame.setAlwaysOnTop(true);
		menuFrame.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		cl = new CardLayout();
		menuFrame.setLayout(cl);
		currentCard = "main";
		
		menuFrame.add(menuModel.getMainMenu(), "main");
		menuFrame.add(menuModel.getLoadMenu(), "load");
		menuFrame.add(menuModel.getSaveMenu(), "save");
		menuFrame.add(menuModel.getNewGameMenu(), "newgame");
		
		setAL();
		setMenu("main");
	}
	
	/**
	 * Sets the firstGame-variable to true
	 */
	public void setFirstGame()
	{
		firstGame = true;
	}
	/**
	 * @return The firstGame-variable
	 */
	public boolean firstGame()
	{
		return firstGame;
	}
	
	/**
	 * Sets the menu-panel to parameter
	 * @param card Card name
	 */
	
	public void setMenu(String card)
	{
		cl.show(menuFrame.getContentPane(), card);
		currentCard = card;
	}
	
	/**
	 * Sets the menu frame to parameter
	 * @param isVisible 
	 */
	public void setVisible(boolean isVisible)
	{
		menuFrame.setVisible(isVisible);
	}
	
	/**
	 * Creates action-listeners for all buttons and objects 
	 */
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
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
			case "newgame": 	if(firstGame == false) setMenu("newgame");
								else newGame(); break;
			case "newyes":		newGame(); break;
			case "newno":		setMenu("main"); break;
			case "loadgame": 	populateLoadList("load"); setMenu("load"); break;
			case "loadsel":		loadGame(); break;
			case "savegame": 	populateLoadList("save"); setMenu("save"); break;
			case "savesel":		saveGame(); break;
			case "back": 		setMenu("main"); break;
			case "exitgame":	System.exit(0);
		}
		
		
	} 
	
	/**
	 * Fills the load list or the save list with all save-games
	 * @param display	Type of list
	 */
	private void populateLoadList(String display)
	{
		if(display.equals("load"))
			menuModel.getLoadListModel().clear();
		else if(display.equals("save"))
			menuModel.getSaveListModel().clear();
		File folder = new File("save");
		File[] saveGames = folder.listFiles();
		
		if(saveGames != null)
		{
			for(int i=0;i<saveGames.length;i++)
			{
				if(display.equals("load"))
					menuModel.getLoadListModel().addElement(saveGames[i].getName());
				else if(display.equals("save"))
					menuModel.getSaveListModel().addElement(saveGames[i].getName());
				
			}
		}
		
	}
	
	/**
	 * Prepares for the new game, notifies the MainController.
	 */
	private void newGame()
	{
		firstGame = false;
		setChanged(); 
		notifyObservers("New Game");
		menuFrame.setVisible(false);
		menuModel.enableSaveButton();
	}
	
	/**
	 * Notifies the MainController to load the selected game.
	 */
	private void loadGame()
	{
		String savefile = (String)menuModel.getLoadList().getSelectedValue();
		String[] commands = {"Load", savefile};
		setChanged();
		notifyObservers(commands);
		menuFrame.setVisible(false);
	}
	
	/**
	 * Notifies the MainController to save the game.
	 */
	private void saveGame()
	{
		menuModel.getSaveField().requestFocusInWindow();
		String savefile = menuModel.getSaveField().getText();
		
		String[] commands = {"Save", savefile};
		setChanged();
		notifyObservers(commands);
		menuModel.getSaveListModel().addElement(savefile+".sav");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 27) // "esc"
		{
			if(currentCard.equals("main") && firstGame == false)
				setVisible(false);
			else if(currentCard.equals("load"))
				setMenu("main");
			else if(currentCard.equals("save"))
				setMenu("main");
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}

}