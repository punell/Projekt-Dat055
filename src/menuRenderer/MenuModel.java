package menuRenderer;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuModel
{

	private JButton newGameButton;
	private JButton saveGameButton;
	private JButton loadGameButton;
	private JButton exitGameButton;
	
	private JButton saveBackButton;
	private JButton loadBackButton;
	
	private JPanel mainMenuPanel;
	private JPanel loadGamePanel;
	private JPanel saveGamePanel;
	
	public MenuModel()
	{
		mainMenuPanel = new JPanel();
		loadGamePanel = new JPanel();
		saveGamePanel = new JPanel();
		mainMenuPanel.setLayout(new GridLayout(4,1));
		loadGamePanel.setLayout(new GridLayout(5,4));
		saveGamePanel.setLayout(new GridLayout(2,1));
		
		newGameButton = new JButton("New Game");
		saveGameButton = new JButton("Save");
		loadGameButton = new JButton("Load");
		exitGameButton = new JButton("Exit");
		
		saveBackButton = new JButton("Back");
		loadBackButton = new JButton("Back");
		
		newGameButton.setActionCommand("newgame");
		saveGameButton.setActionCommand("savegame");
		loadGameButton.setActionCommand("loadgame");
		exitGameButton.setActionCommand("exitgame");
		
		saveBackButton.setActionCommand("back");
		loadBackButton.setActionCommand("back");
		
		mainMenuPanel.add(newGameButton);
		mainMenuPanel.add(saveGameButton);
		mainMenuPanel.add(loadGameButton);
		mainMenuPanel.add(exitGameButton);
		
		saveGamePanel.add(saveBackButton);
		loadGamePanel.add(loadBackButton);
		
		
	}
	
	public JPanel getMainMenu()
	{
		return mainMenuPanel;
	}
	
	public JPanel getSaveMenu()
	{
		return saveGamePanel;
	}
	
	public JPanel getLoadMenu()
	{
		return loadGamePanel;
	}
	
	public JPanel[] getMenus()
	{
		JPanel[] temp = {mainMenuPanel, saveGamePanel, loadGamePanel};
		return temp;
	}
	
	
}