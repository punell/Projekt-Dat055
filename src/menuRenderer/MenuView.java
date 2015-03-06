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

public class MenuView extends Observable implements ActionListener, KeyListener
{
	/** MenuView displays the menu 
	 * 
	 **/
	
	private JFrame menuFrame;
	private MenuModel menuModel;
	private CardLayout cl;
	private String currentCard;
	
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
		
		
		setAL();
		setMenu("main");
		
		
		
	}
	
	public void setMenu(String card)
	{
		cl.show(menuFrame.getContentPane(), card);
		currentCard = card;
	}
	
	public void setVisible(boolean isVisible)
	{
		menuFrame.setVisible(isVisible);
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
			case "newgame": 	setChanged(); notifyObservers(); break;
			case "loadgame": 	populateLoadList("load"); setMenu("load"); break;
			case "loadsel":		loadGame(); break;
			case "savegame": 	populateLoadList("save"); setMenu("save"); break;
			case "savesel":		saveGame(); break;
			case "back": 		setMenu("main"); break;
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
	
	private void loadGame()
	{
		String savefile = (String)menuModel.getLoadList().getSelectedValue();
		String[] commands = {"Load", savefile};
		setChanged();
		notifyObservers(commands);
		/*String savefile = (String)menuModel.getLoadList().getSelectedValue();
		gameControl.restoreFromLoad(saveLoadControl.load(savefile));
		mapControl.restoreFromLoad(gameControl.getPlayerArea(), gameControl.getPlayerCoords('r'));*/
		menuFrame.setVisible(false);
	}
	
	private void saveGame()
	{
		menuModel.getSaveField().requestFocusInWindow();
		String savefile = menuModel.getSaveField().getText();
		
		//saveLoadControl.save(gameControl.packageForSave(), savefile);
		String[] commands = {"Save", savefile};
		setChanged();
		notifyObservers(commands);
		menuModel.getSaveListModel().addElement(savefile+".sav");
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 27) // "esc"
		{
			if(currentCard.equals("main"))
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