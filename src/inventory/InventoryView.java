package inventory;

import gameLayer.PlayerModel;
import items.Item;
import items.ItemButton;
import items.ItemEquipment;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Holds the GUI for the inventory window. Handles mouse-clicks on the items to perform actions.
 * @author Robin Punell
 * @version 2015-03-09
 */
public class InventoryView extends JFrame implements ActionListener, KeyListener
{
	private InventoryModel invModel;
	private JPanel backpackPanel;
	private JPanel equippedPanel;
	private JPanel statsPanel;
	private PlayerModel player;
	private JLabel healthLabel;
	private JLabel damageLabel;
	private JLabel armorLabel;
	
	
	/**
	 * Constructor - Sets up the GUI for the inventory
	 * @param iM InventoryModel
	 * @param pM PlayerModel
	 */
	public InventoryView(InventoryModel iM, PlayerModel pM)
	{
		super();
		addKeyListener(this);
		invModel = iM;
		
		setBounds(300,300,500,500);
		setLayout(new GridLayout(2,2,5,0));
		player = pM;
		
		setUndecorated(true);
		backpackPanel = new JPanel();
		equippedPanel = new JPanel();
		statsPanel = new JPanel();
		healthLabel = new JLabel("Health: "+player.getHealth()+"/"+player.getStats("maxhealth"));
		damageLabel = new JLabel("Damage: "+player.getStats("damage"));
		armorLabel = new JLabel("Armor: "+player.getStats("armor"));
		statsPanel.add(healthLabel);
		statsPanel.add(damageLabel);
		statsPanel.add(armorLabel);
		
		backpackPanel.setBackground(Color.darkGray);
		equippedPanel.setBackground(Color.darkGray);
		backpackPanel.setLayout(new GridLayout(5,5,3,3));
		equippedPanel.setLayout(new GridLayout(5,5,3,3));
		statsPanel.setLayout(new FlowLayout());
		add(backpackPanel);
		add(equippedPanel);
		add(statsPanel);
		
		populatePanels();	
	}

	/**
	 * Updates and repaints the inventory view
	 */
	public void updateInventoryView() 
	{
		/* This could be made nicer by inventing the ItemPanel which is a
		 * JPanel designed for ItemButton-handling. Then the for-loops
		 * could probably be removed, or at least reduced to 2 instead of 4.
		 */
		healthLabel.setText("Health: "+player.getHealth()+"/"+player.getStats("maxhealth"));
		damageLabel.setText("Damage: "+player.getStats("damage"));
		armorLabel.setText("Armor: "+player.getStats("armor"));
		ItemButton pushButton = null;
		Item item = null;
		int backpackSize = invModel.checkBackpack().size();
		for(int i=0;i<backpackSize;i++)
		{
			item = invModel.checkBackpack().get(i);
			pushButton = (ItemButton) backpackPanel.getComponent(i);
			pushButton.setItem(item);
		}
		for(int i=backpackSize;i<25-backpackSize;i++)
		{
			pushButton = (ItemButton) backpackPanel.getComponent(i);
			pushButton.clear();
		}
		
		int equippedSize = invModel.checkEquipment().size();
		for(int i=0;i<equippedSize;i++)
		{
			item = invModel.checkEquipment().get(i);
			pushButton = (ItemButton) equippedPanel.getComponent(i);
			pushButton.setItem(item);
		}
		for(int i=equippedSize;i<25-equippedSize;i++)
		{
			pushButton = (ItemButton) equippedPanel.getComponent(i);
			pushButton.clear();
		}
		
		repaint();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		ItemButton buttonPressed = null;
		String itemName = null;
		if(e.getActionCommand().equals("ToggleEquip"))
		{
			buttonPressed = (ItemButton)e.getSource();
			invModel.toggleEquip(buttonPressed.getItem());
		}
		
		else if(e.getActionCommand().equals("Use"))
		{
			buttonPressed = (ItemButton)e.getSource();
			itemName = buttonPressed.getItem().getName();
			player.useItem(itemName);
		}
		player.calculateEquipmentBonus();
		updateInventoryView();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 73) // 'i'
		{
			setVisible(false);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	/**
	 * Fills the backpack-panel and equipped-panel with buttons
	 */
	private void populatePanels()
	{
		ItemButton pushButton = null;
		for(int i=0;i<25;i++)
		{
			pushButton = new ItemButton();
			pushButton.addActionListener(this);
			backpackPanel.add(pushButton);
		}
		
		for(int i=0;i<25;i++)
		{
			pushButton = new ItemButton();
			pushButton.addActionListener(this);
			equippedPanel.add(pushButton);
		}
	}
	

}
