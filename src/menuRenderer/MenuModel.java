package menuRenderer;

import gameLayer.GameController;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class MenuModel
{
	private GameController gameControl;
	
	private JPanel mainMenuPanel;
	private JButton newGameButton;
	private JButton saveGameButton;
	private JButton loadGameButton;
	private JButton exitGameButton;
	
	private JPanel loadGamePanel;
	private JPanel loadButtonsPanel;
	private JButton loadBackButton;
	private JButton loadButton;
	private JList loadGameList;
	private DefaultListModel loadListModel;
	
	private JPanel saveGamePanel;
	private JPanel saveButtonsPanel;
	private JButton saveBackButton;
	private JButton saveButton;
	private JList saveGameList;
	private DefaultListModel saveListModel;
	private JTextField saveGameField;
	
	
	public MenuModel()
	{
		//MAIN MENU
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new GridLayout(4,1));
		newGameButton = new JButton("New Game");
		saveGameButton = new JButton("Save");
		loadGameButton = new JButton("Load");
		exitGameButton = new JButton("Exit");
		
		newGameButton.setActionCommand("newgame");
		saveGameButton.setActionCommand("savegame");
		loadGameButton.setActionCommand("loadgame");
		exitGameButton.setActionCommand("exitgame");
		
		mainMenuPanel.add(newGameButton);
		mainMenuPanel.add(saveGameButton);
		mainMenuPanel.add(loadGameButton);
		mainMenuPanel.add(exitGameButton);
		
		//LOAD MENU
		loadGamePanel = new JPanel(new BorderLayout());	
		loadButtonsPanel = new JPanel(new GridLayout(1,2));
		loadBackButton = new JButton("Back");
		loadButton = new JButton("Load");
		loadListModel = new DefaultListModel();
		loadGameList = new JList(loadListModel);
		
		
		loadGameList.setFocusable(false);
		
		loadBackButton.setActionCommand("back");
		loadButton.setActionCommand("loadsel");
		
		loadButtonsPanel.add(loadBackButton);
		loadButtonsPanel.add(loadButton);
		loadGamePanel.add(loadGameList, BorderLayout.CENTER);
		loadGamePanel.add(loadButtonsPanel, BorderLayout.SOUTH);
		
		//SAVE MENU
		saveGamePanel = new JPanel(new BorderLayout());
		saveButtonsPanel = new JPanel(new GridLayout(1,2));
		saveBackButton = new JButton("Back");
		saveButton = new JButton("Save");
		saveListModel = new DefaultListModel();
		saveGameList = new JList(saveListModel);
		saveGameField = new JTextField();
		
		saveGameList.setFocusable(false);
		saveGameField.setEditable(true);
		
		saveBackButton.setActionCommand("back");
		saveButton.setActionCommand("savesel");
		
		
		saveButtonsPanel.add(saveBackButton);
		saveButtonsPanel.add(saveButton);
		saveGamePanel.add(saveGameField, BorderLayout.NORTH);
		saveGamePanel.add(saveGameList, BorderLayout.CENTER);
		saveGamePanel.add(saveButtonsPanel, BorderLayout.SOUTH);
		
		
		
		
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
	
	public DefaultListModel getLoadListModel()
	{
		return loadListModel;
	}
	public JList getLoadList()
	{
		return loadGameList;
	}
	
	public DefaultListModel getSaveListModel()
	{
		return saveListModel;
	}
	public JList getSaveList()
	{
		return saveGameList;
	}
	public JTextField getSaveField()
	{
		return saveGameField;
	}
	
	public JPanel[] getMenus()
	{
		JPanel[] temp = {mainMenuPanel, saveGamePanel, loadGamePanel};
		return temp;
	}
	
	
}