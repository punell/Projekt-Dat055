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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


/**
 * Creates all the buttons, panels and lists for the menu.
 * @author Robin Punell
 * @version 2015-03-09
 *
 */
public class MenuModel
{	
	private JPanel mainMenuPanel;
	private JButton newGameButton;
	private JButton saveGameButton;
	private JButton loadGameButton;
	private JButton exitGameButton;
	
	private JPanel newGamePanel;
	private JPanel newButtonsPanel;
	private JLabel newGameLabel;
	private JButton newYesButton;
	private JButton newNoButton;
	
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
	
	
	/**
	 * Constructor
	 */
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
		saveGameButton.setEnabled(false);
		
		mainMenuPanel.add(newGameButton);
		mainMenuPanel.add(saveGameButton);
		mainMenuPanel.add(loadGameButton);
		mainMenuPanel.add(exitGameButton);
		
		//NEW GAME MENU
		newGamePanel = new JPanel(new BorderLayout());
		newButtonsPanel = new JPanel(new GridLayout(1,2));
		newGameLabel = new JLabel("Are you sure you want to start a new game?"
								+ "All your unsaved progress will be lost!");
		newYesButton = new JButton("Yes");
		newNoButton = new JButton("No");
		
		newGameLabel.setFocusable(false);
		
		newYesButton.setActionCommand("newyes");
		newNoButton.setActionCommand("newno");
		
		newButtonsPanel.add(newNoButton);
		newButtonsPanel.add(newYesButton);
		newGamePanel.add(newGameLabel, BorderLayout.CENTER);
		newGamePanel.add(newButtonsPanel, BorderLayout.SOUTH);
		
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
	
	/**
	 * @return The main menu panel
	 */
	public JPanel getMainMenu()
	{
		return mainMenuPanel;
	}
	
	/**
	 * @return The new game panel
	 */
	public JPanel getNewGameMenu()
	{
		return newGamePanel;
	}
	
	/**
	 * @return The save game panel
	 */
	public JPanel getSaveMenu()
	{
		return saveGamePanel;
	}
	
	/**
	 * @return The load game panel
	 */
	public JPanel getLoadMenu()
	{
		return loadGamePanel;
	}
	
	/**
	 * @return The load list model
	 */
	public DefaultListModel getLoadListModel()
	{
		return loadListModel;
	}
	/**
	 * @return The load game list
	 */
	public JList getLoadList()
	{
		return loadGameList;
	}
	
	/**
	 * @return The save list model
	 */
	public DefaultListModel getSaveListModel()
	{
		return saveListModel;
	}
	/**
	 * @return The save game list
	 */
	public JList getSaveList()
	{
		return saveGameList;
	}
	/**
	 * @return The save game field
	 */
	public JTextField getSaveField()
	{
		return saveGameField;
	}
	
	/**
	 * @return All the menus in a list
	 */
	public JPanel[] getMenus()
	{
		JPanel[] temp = {mainMenuPanel, newGamePanel, saveGamePanel, loadGamePanel};
		return temp;
	}
	
	/**
	 * Turns on the save button
	 */
	public void enableSaveButton()
	{
		saveGameButton.setEnabled(true);
	}
	
	
}