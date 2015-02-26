package inventory;

import gameLayer.Item;
import gameLayer.ItemEquipment;
import gameLayer.PlayerModel;

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

public class InventoryView extends JFrame implements ActionListener, KeyListener
{
	private InventoryModel invModel;
	private LinkedList<Item> backpackContents;
	private LinkedList<ItemEquipment> equippedContents;
	//private Image panelBackground;
	private JPanel backpackPanel;
	private JPanel equippedPanel;
	private JPanel statsPanel;
	private PlayerModel player;
	private JLabel healthLabel;
	private JLabel damageLabel;
	private JLabel armorLabel;
	public InventoryView(InventoryModel iM, PlayerModel pM)
	{
		super();
		addKeyListener(this);
		invModel = iM;
		backpackContents = new LinkedList<>();
		equippedContents = new LinkedList<>();
		
		setBounds(300,300,500,500);
		setLayout(new GridLayout(2,2,5,0));
		player = pM;
		// Might want a background?
		/*try {
			panelBackground = ImageIO.read(new File("textures/inventorybackground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
		
		
	}

	public void updateInventoryView() 
	{
		/* This can be maybe be made simpler by having Item NOT be a button
		 * but instead create buttons for all the Items, and then set their
		 * icons as the Items icon... 
		 */
		healthLabel.setText("Health: "+player.getHealth()+"/"+player.getStats("maxhealth"));
		damageLabel.setText("Damage: "+player.getStats("damage"));
		armorLabel.setText("Armor: "+player.getStats("armor"));
		for(Item item : backpackContents)
		{
			backpackPanel.remove(item);
			item.removeAllActionListeners();
		}
		for(Item item : equippedContents)
		{
			equippedPanel.remove(item);
			item.removeAllActionListeners();
		}
		
		backpackContents = invModel.checkBackpack();
		for(Item item : backpackContents)
		{
			backpackPanel.add(item);
			item.addActionListener(this);
		}
		equippedContents = invModel.checkEquipment();
		for(ItemEquipment item : equippedContents)
		{
			equippedPanel.add(item);
			item.addActionListener(this);
		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().equals("ToggleEquip"))
		{
			Item clicked = (Item)e.getSource();
			invModel.toggleEquip(clicked);
			player.calculateEquipmentBonus();
		}
		else if(e.getActionCommand().equals("Use"))
		{
			Item clicked = (Item)e.getSource();
			player.useItem(clicked.getName());
		}
		updateInventoryView();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == 73) // 'i'
		{
			setVisible(false);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	

}
