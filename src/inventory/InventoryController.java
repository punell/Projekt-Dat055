package inventory;

import items.Item;
import items.ItemEquipment;

import java.io.Serializable;
import java.util.LinkedList;

import gameLayer.PlayerModel;

public class InventoryController implements Serializable
{
	private InventoryModel invModel;
	private InventoryView invView;
	public InventoryController(PlayerModel pM)
	{
		invModel = new InventoryModel();
		invView = new InventoryView(invModel, pM);
	}
	
	public void show()
	{
		invView.updateInventoryView();
		invView.setVisible(true);
	}
	
	public void put(Item item)
	{
		invModel.put(item);
	}
	
	public Item get(String itemName)
	{
		return invModel.get(itemName);
	}
	
	public Item getEquipped(String slot)
	{
		return invModel.getEquipped(slot);
	}

	public LinkedList<ItemEquipment> checkEquipment() 
	{
		return invModel.checkEquipment();
	}
	public LinkedList<Item> checkBackpack()
	{
		return invModel.checkBackpack();
	}
}
