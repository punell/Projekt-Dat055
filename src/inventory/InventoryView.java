package inventory;

import gameLayer.Item;

import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InventoryView extends JFrame
{
	private InventoryModel invModel;
	private String inventoryType;
	private LinkedList<Item> contents;
	public InventoryView(InventoryModel iM, String inventoryType)
	{
		super();
		invModel = iM;
		this.inventoryType = inventoryType;
		contents = new LinkedList<>();
		
		setUndecorated(true);
		if(inventoryType.equals("backpack"))
		{
			setLayout(new GridLayout(5,5));
			setBounds(400, 200, 400, 400);
		}
		else if(inventoryType.equals("equipped"))
		{
			setLayout(new GridLayout(3,3));
			setBounds(400, 50, 400, 140);
		}
		
	}
	
	public void updateInventoryView()
	{
		for(Item item : contents)
		{
			remove(item);
		}
		contents = invModel.checkContents();
		for(Item item : contents)
		{
			add(item);
		}
	}
}
